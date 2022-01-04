package ch.fhnw.webec.exercise.controller;


import ch.fhnw.webec.exercise.model.Device;
import ch.fhnw.webec.exercise.model.Location;
import ch.fhnw.webec.exercise.repository.DeviceRepository;
import ch.fhnw.webec.exercise.repository.LocationRepository;
import ch.fhnw.webec.exercise.repository.StatusRepository;
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
@WebMvcTest(DeviceController.class)
public class DeviceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceRepository deviceRepository;

    @MockBean
    private LocationRepository locationRepository;

    @MockBean
    private StatusRepository statusRepository;

    @MockBean
    private UserService userService;

    @Test
    public void testDeviceIndex() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("List of Devices")));
    }

    @Test
    public void testDeviceIndexWithDevice() throws Exception {
        when(this.deviceRepository.findBySearch("")).thenReturn(Arrays.asList(
                new Device("ABC", "MacBook Air", "13 Zoll", "M1", "01.01.2021", "16GB", "Apple"),
                new Device("123", "iPad Pro", "12 Zoll", "Intel", "01.02.2021", "8GB", "Apple")
                ));

        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ABC")))
                .andExpect(content().string(containsString("MacBook Air")))
                .andExpect(content().string(containsString("13 Zoll")))
                .andExpect(content().string(containsString("M1")))
                .andExpect(content().string(containsString("01.01.2021")))
                .andExpect(content().string(containsString("16GB")))
                .andExpect(content().string(containsString("Apple")))

                .andExpect(content().string(containsString("123")))
                .andExpect(content().string(containsString("iPad Pro")))
                .andExpect(content().string(containsString("12 Zoll")))
                .andExpect(content().string(containsString("Intel")))
                .andExpect(content().string(containsString("01.02.2021")))
                .andExpect(content().string(containsString("8GB")))
                .andExpect(content().string(containsString("Apple")));
    }

    @Test
    public void testSearch() throws Exception {
        var search = "my test search";

        this.mockMvc.perform(get("/?search={search}", search))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(search)));

        verify(this.deviceRepository, times(1)).findBySearch(search);
        verify(this.deviceRepository, never()).findAll();
    }

    @Test
    public void testShowDevice() throws Exception {
        var deviceId = 1;
        var device = new Device("ABC", "MacBook Air", "13 Zoll", "M1", "01.01.2021", "16GB",
                "Apple");

        when(this.deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));

        this.mockMvc.perform(get("/devices/{id}", deviceId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ABC")))
                .andExpect(content().string(containsString("MacBook Air")));

        verify(this.deviceRepository, times(1)).findById(deviceId);
    }

    @Test
    public void testDeleteDevice() throws Exception {
        var deviceId = 1;
        var device = new Device("ABC", "MacBook Air", "13 Zoll", "M1", "01.01.2021", "16GB",
                "Apple");

        when(this.deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));

        this.mockMvc.perform(post("/devices/{id}/delete", deviceId)
                        .with(csrf())
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().is3xxRedirection());

        verify(this.deviceRepository, times(1)).findById(deviceId);
        verify(this.deviceRepository, times(1)).delete(device);

    }

    // TODO: 29.12.21 TestAddDevice
    @Test
    public void testEditLocation() throws Exception {

    }

}
