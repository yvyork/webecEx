package ch.fhnw.webec.exercise.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.validation.Validator;

import org.mockito.Mockito;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class DeviceUnitTest2 {

    private Device device;
    private Location mockLocation;
    private Status mockStatus;

    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();

        return localValidatorFactoryBean;
    }

    @BeforeEach
    public void setup() {
        device = new Device();
    }

    @Test
    public void testAddLocation() {
        mockLocation = mock(Location.class);

        assertNull(device.getLocation());

        device.setLocation(mockLocation);

        when(this.mockLocation.getBuildingName()).thenReturn("Nordflügel");
        assertEquals("Nordflügel", device.getLocation().getBuildingName());

        when(this.mockLocation.getRoomName()).thenReturn("E05");
        assertEquals("E05", device.getLocation().getRoomName());

        verify(this.mockLocation, times(1)).getBuildingName();
        verify(this.mockLocation, times(1)).getRoomName();
    }

    @Test
    public void testAddStatus() {
        mockStatus = mock(Status.class);

        assertNull(device.getStatus());

        device.setStatus(mockStatus);

        when(this.mockStatus.getName()).thenReturn("Neu");
        assertEquals("Neu", device.getStatus().getName());
        verify(this.mockStatus, times(1)).getName();

    }

    @Test
    public void testNullPointerExceptions() {
        mockLocation = mock(Location.class);
        mockStatus = mock(Status.class);

        assertNull(device.getLocation());
        assertNull(device.getStatus());

        when(this.mockLocation.getRoomName()).thenThrow(new NullPointerException());
        assertThrows(NullPointerException.class, () -> device.getLocation().getRoomName());

        when(this.mockStatus.getName()).thenThrow(new NullPointerException());
        assertThrows(NullPointerException.class, () -> device.getStatus().getName());

    }

    @Test
    public void testValidation() {
        var validator = createValidator();
        var constraintViolation = validator.validate(device);
        // 7 @NotEmpty fields of Device
        assertEquals(7, constraintViolation.size());

        for (var violation : constraintViolation) {
            assertEquals("must not be empty", violation.getMessage());
        }

        // set one field
        device.setManufacturer("Apple");
        // update validator
        constraintViolation = validator.validate(device);
        assertEquals(6, constraintViolation.size());

    }

}
