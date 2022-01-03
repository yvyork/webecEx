package ch.fhnw.webec.exercise.controller;

import ch.fhnw.webec.exercise.model.Location;
import ch.fhnw.webec.exercise.repository.LocationRepository;
import ch.fhnw.webec.exercise.service.UserService;
import com.mitchellbosecke.pebble.boot.autoconfigure.PebbleAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(PebbleAutoConfiguration.class)
@WebMvcTest(LocationController.class)
public class LocationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationRepository locationRepository;

    @MockBean
    private UserService userService;

    @Test
    public void testIndex() throws Exception {
        // then
        this.mockMvc.perform(get("/locations/")
                        .with(csrf())
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("List of Locations")));
    }

    @Test
    public void textIndexWithLocations() throws Exception {
        // given
        when(this.locationRepository.findBySearch("")).thenReturn(Arrays.asList(
                new Location("Nordfluegel","EG01","Musterstrasse 1", "3000 Bern"),
                new Location("Ostfluegel","EG01","Musterstrasse 2", "3000 Bern"),
                new Location("Suedfluegel","EG01","Musterstrasse 3", "3000 Bern"),
                new Location("Westfluegel","EG01","Musterstrasse 4", "3000 Bern")
        ));

        // then
        this.mockMvc.perform(get("/locations/")
                        .with(csrf())
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("List of Locations")))
                .andExpect(content().string(containsString("Nordfluegel")))
                .andExpect(content().string(containsString("EG01")))
                .andExpect(content().string(containsString("Musterstrasse 1")))
                .andExpect(content().string(containsString("3000 Bern")))
                .andExpect(content().string(containsString("Ostfluegel")));
        verify(this.locationRepository, times(1)).findBySearch("");
    }

    @Test
    public void testIndexAbbreviateDescription() throws Exception {
        // given
        when(this.locationRepository.findBySearch("")).thenReturn(Arrays.asList(
                new Location("Nordfluegel","EG01","Musterstrasse 1", "3000 Bern")
        ));

        // then
        this.mockMvc.perform(get("/locations")
                        .with(csrf())
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nord")))
                .andExpect(content().string(containsString("EG01")))
                .andExpect(content().string(containsString("Musterstrasse")))
                .andExpect(content().string(containsString("Bern")))
                .andExpect(content().string(not(containsString("THE END"))));
        verify(this.locationRepository, times(1)).findBySearch("");
    }

    @Test
    public void testSearch() throws Exception {
        // given
        var search = "n";

        // then
        this.mockMvc.perform(get("/locations/?search={search}", search)
                        .with(csrf())
                        .with(user("admin").roles("ADMIN")))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(search)));

        verify(this.locationRepository, times(1)).findBySearch(search);
        verify(this.locationRepository, never()).findAll();
    }

    @Test
    public void testSearchNotFound() throws Exception {
        // given
        var search = "no location";

        // then
        assertThrows(AssertionError.class, () ->
                this.mockMvc.perform(get("/locations/?search={search}", search)
                                .with(csrf())
                                .with(user("admin").roles("ADMIN")))
                        .andExpect(status().isOk())
                        .andExpect(content().string(containsString(search))));

        verify(this.locationRepository, times(1)).findBySearch(search);
    }

    @Test
    public void testShowLocation() throws Exception {
        // given
        var id = 1;
        var location = new Location("Nordfluegel","EG01","Musterstrasse 1", "3000 Bern");

        // when
        when(this.locationRepository.findById(id)).thenReturn(Optional.of(location));

        // then
        this.mockMvc.perform(get("/locations/{id}/", id)
                        .with(csrf())
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nordfluegel")))
                .andExpect(content().string(containsString("Musterstrasse 1")));

        verify(this.locationRepository, times(1)).findById(id);
    }

    @Test
    public void testAddOrEditLocation() throws Exception {
        // given
        var id = 1;
        var location = new Location("Nordfluegel","EG01","Musterstrasse 1", "3000 Bern");

        // when
        when(this.locationRepository.findById(id)).thenReturn(Optional.of(location));

        // then
        this.mockMvc.perform(get("/locations/{id}/edit?", id)
                        .with(csrf())
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nordfluegel")))
                .andExpect(content().string(containsString("Musterstrasse 1")));

        verify(this.locationRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteLocation() throws Exception {
        // given
        var id = 1;
        var location = new Location("Nordfluegel","EG01","Musterstrasse 1", "3000 Bern");

        // when
        when(this.locationRepository.findById(id)).thenReturn(Optional.of(location));

        // then
        this.mockMvc.perform(post("/locations/{id}/delete", id)
                        .with(csrf())
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().is3xxRedirection());

        verify(this.locationRepository, times(1)).findById(id);
        verify(this.locationRepository, times(1)).delete(location);
    }
}
