package ch.fhnw.webec.exercise.controller;

import ch.fhnw.webec.exercise.model.Location;
import ch.fhnw.webec.exercise.model.Status;
import ch.fhnw.webec.exercise.repository.StatusRepository;
import com.mitchellbosecke.pebble.boot.autoconfigure.PebbleAutoConfiguration;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(PebbleAutoConfiguration.class)
@WebMvcTest(StatusController.class)
public class StatusControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusRepository statusRepository;

    @Test
    public void testIndex() throws Exception {
        // then
        this.mockMvc.perform(get("/statuses/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("List of Status")));
    }

    @Test
    public void textIndexWithStatus() throws Exception {
        // given
        when(this.statusRepository.findBySearch("")).thenReturn(Arrays.asList(
                new Status("new"),
                new Status("assigned"),
                new Status("broken")
        ));

        // then
        this.mockMvc.perform(get("/statuses/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("List of Status")))
                .andExpect(content().string(containsString("1")))
                .andExpect(content().string(containsString("new")))
                .andExpect(content().string(containsString("2")))
                .andExpect(content().string(containsString("assigned")))
                .andExpect(content().string(containsString("3")))
                .andExpect(content().string(containsString("broken")));
        verify(this.statusRepository, times(1)).findBySearch("");
    }

    @Test
    public void testIndexAbbreviateDescription() throws Exception {
        // given
        when(this.statusRepository.findBySearch("")).thenReturn(Arrays.asList(
                new Status("new"),
                new Status("assigned"),
                new Status("broken")
        ));

        // then
        this.mockMvc.perform(get("/statuses"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("assig")))
                .andExpect(content().string(not(containsString("THE END"))));
        verify(this.statusRepository, times(1)).findBySearch("");
    }

    @Test
    public void testSearch() throws Exception {
        // given
        var search = "new";

        // then
        this.mockMvc.perform(get("/statuses/?search={search}", search))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(search)));

        verify(this.statusRepository, times(1)).findBySearch(search);
        verify(this.statusRepository, never()).findAll();
    }

    @Test
    public void testSearchNotFound() throws Exception {
        // given
        var search = "no status";

        // then
        assertThrows(AssertionError.class, () ->
        this.mockMvc.perform(get("/statuses/?search={search}", search))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(search))));

        verify(this.statusRepository, times(1)).findBySearch(search);
    }

    @Test
    public void testShowStatus() throws Exception {
        // given
        var id = 1;
        var status = new Status("new");

        // when
        when(this.statusRepository.findById(id)).thenReturn(Optional.of(status));

        // then
        this.mockMvc.perform(get("/statuses/{id}/", id))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1")))
                .andExpect(content().string(containsString("new")));

        verify(this.statusRepository, times(1)).findById(id);
    }

    @Test
    public void testAddOrEditStatus() throws Exception {
        // given
        var id = 1;
        var status = new Status("new");

        // when
        when(this.statusRepository.findById(id)).thenReturn(Optional.of(status));

        // then
        this.mockMvc.perform(get("/statuses/{id}/edit?", id))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1")))
                .andExpect(content().string(containsString("new")));

        verify(this.statusRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteStatus() throws Exception {
        // given
        var id = 1;
        var status = new Status("new");

        // when
        when(this.statusRepository.findById(id)).thenReturn(Optional.of(status));

        // then
        this.mockMvc.perform(post("/statuses/{id}/delete", id))
                .andExpect(status().is3xxRedirection());

        verify(this.statusRepository, times(1)).findById(id);
        verify(this.statusRepository, times(1)).delete(status);
    }
}
