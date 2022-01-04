package ch.fhnw.webec.exercise.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LocationUnitTest {

    public final String NODEVICE = "There is no device to add";
    private Device mockDevice;
    private Location location;

    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }

    @BeforeEach
    public void setup() {
        location = new Location();
    }

    @Test
    public void testAddDevice(){
        // given
        mockDevice = mock(Device.class);
        when(this.mockDevice.getLocation()).thenReturn(null);
        int expectedDeviceListCount = 1;

        // then
        assertEquals(0, location.getDevices().size());
        assertNull(mockDevice.getLocation());

        when(this.mockDevice.getLocation()).thenReturn(
                new Location("Nordflügel","EG01",
                "Musterstrasse 1", "3000 Bern"));
        location.addDevice(mockDevice);

        assertEquals(expectedDeviceListCount, location.getDevices().size());
        assertTrue(location.getDevices().contains(mockDevice));
        verify(this.mockDevice, times(2)).getLocation();
    }

    @Test
    public void testAddNullDevice() {
        // given
        mockDevice = mock(Device.class);
        // then
        assertEquals(0, location.getDevices().size());
        when(this.mockDevice.getLocation()).thenThrow(new NullPointerException(this.NODEVICE));
        NullPointerException exception = assertThrows(NullPointerException.class, () -> location.addDevice(mockDevice));
        assertEquals(this.NODEVICE, exception.getMessage());
        verify(this.mockDevice, times(1)).getLocation();
    }

    @Test
    public void testGetValue() {
        // given
        // when
        location.setId(0);
        // then
        assertEquals("0", location.getValue());
    }

    @Test
    public void testGetLabel() {
        // given
        // when
        location.setBuildingName("Nordflügel");
        location.setRoomName("EG01");
        // then
        assertEquals("Nordflügel EG01", location.getLabel());
    }

    @Test
    public void testValidation() {
        location = getLocation();
        var validator = createValidator();
        var constraintViolations = validator.validate(location);
        assertEquals(0, constraintViolations.size());

        location.setBuildingName(null);
        constraintViolations = validator.validate(location);
        assertEquals(1, constraintViolations.size());

        for (var violation: constraintViolations) {
            assertEquals("must not be empty", violation.getMessage());
        }
    }

    private Location getLocation() {
        return new Location(TestHelper.LOCATION_BUILDINGNAME, TestHelper.LOCATION_ROOMNAME,
                TestHelper.LOCATION_STREET, TestHelper.LOCATION_ZIP);
    }
}

