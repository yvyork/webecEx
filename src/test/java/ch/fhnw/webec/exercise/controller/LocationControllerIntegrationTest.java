package ch.fhnw.webec.exercise.controller;

import ch.fhnw.webec.exercise.model.Location;
import ch.fhnw.webec.exercise.repository.LocationRepository;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(PebbleAutoConfiguration.class)
@WebMvcTest(LocationController.class)
public class LocationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationRepository locationRepository;

    @Test
    public void testIndex() throws Exception {
        // then
        this.mockMvc.perform(get("/locations/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("List of Locations")));
    }

    @Test
    public void textIndexWithLocations() throws Exception {
        // given
        when(this.locationRepository.findBySearch("")).thenReturn(Arrays.asList(
                new Location("Nordflügel","EG01","Musterstrasse 1", "3000 Bern"),
                new Location("Ostflügel","EG01","Musterstrasse 2", "3000 Bern"),
                new Location("Südflügel","EG01","Musterstrasse 3", "3000 Bern"),
                new Location("Westflügel","EG01","Musterstrasse 4", "3000 Bern")
        ));

        // then
        this.mockMvc.perform(get("/locations/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("List of Locations")))
                .andExpect(content().string(containsString("Nordflügel")))
                .andExpect(content().string(containsString("EG01")))
                .andExpect(content().string(containsString("Musterstrasse 1")))
                .andExpect(content().string(containsString("3000 Bern")))
                .andExpect(content().string(containsString("Ostflügel")));
        verify(this.locationRepository, times(1)).findBySearch("");
    }

    @Test
    //TODO: search functionality is not yet implemented
    public void testSearch() throws Exception {
        // given
        var search = "flügel";

        // then
        this.mockMvc.perform(get("?search={search}", search))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(search)));

        verify(this.locationRepository, times(1)).findBySearch(search);
        verify(this.locationRepository, never()).findAll();
    }

    @Test
    public void testShowLocation() throws Exception {
        // given
        var locationId = 1;
        var location = new Location("Nordflügel","EG01","Musterstrasse 1", "3000 Bern");

        // when
        when(this.locationRepository.findById(locationId)).thenReturn(Optional.of(location));

        // then
        this.mockMvc.perform(get("/locations/{id}/", locationId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nordflügel")))
                .andExpect(content().string(containsString("Musterstrasse 1")));

        verify(this.locationRepository, times(1)).findById(locationId);
    }

    @Test
    public void testAddOrEditLocation() throws Exception {
        // given
        var locationId = 1;
        var location = new Location("Nordflügel","EG01","Musterstrasse 1", "3000 Bern");

        // when
        when(this.locationRepository.findById(locationId)).thenReturn(Optional.of(location));

        // then
        this.mockMvc.perform(get("/locations/{id}/edit?", locationId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nordflügel")))
                .andExpect(content().string(containsString("Musterstrasse 1")));

        verify(this.locationRepository, times(1)).findById(locationId);
    }

    @Test
    public void testDeleteLocation() throws Exception {
        // given
        var locationId = 1;
        var location = new Location("Nordflügel","EG01","Musterstrasse 1", "3000 Bern");

        // when
        when(this.locationRepository.findById(locationId)).thenReturn(Optional.of(location));

        // then
        this.mockMvc.perform(post("/locations/{id}/delete", locationId))
                .andExpect(status().is3xxRedirection());

        verify(this.locationRepository, times(1)).findById(locationId);
        verify(this.locationRepository, times(1)).delete(location);
    }
}
