package ch.fhnw.webec.exercise.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StatusUnitTest {

    public final String NODEVICE = "There is no device to add";
    public final String SERIALNUMMER = "ABCD";
    private Device mockDevice;
    private Status status;

    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }

    @BeforeEach
    public  void setup() {
        status = new Status();
    }

    @Test
    public void testAddDevice() {
        // given
        mockDevice = mock(Device.class);
        int expectedDeviceListCount = 1;

        // then
        assertEquals(0, status.getDevices().size());
        assertNull(mockDevice.getStatus());

        when(this.mockDevice.getStatus()).thenReturn(new Status("new"));
        status.addDevice(mockDevice);

        assertEquals(expectedDeviceListCount, status.getDevices().size());
        assertTrue(status.getDevices().contains(mockDevice));
        verify(this.mockDevice,times(2)).getStatus();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    public void testAddParameterizedDevices(int mockDeviceCounter) {
        // given
        status = new Status();

        // then
        assertEquals(0, status.getDevices().size());

        for (int i = 0; i < mockDeviceCounter; i++){
            mockDevice = mock(Device.class);
            assertNull(mockDevice.getStatus());
            status.addDevice(mockDevice);
        }

        assertEquals(mockDeviceCounter, status.getDevices().size());
        assertTrue(status.getDevices().contains(mockDevice));

        verify(this.mockDevice,times(2)).getStatus();
    }

    @Test
    public void testAddNullStatus() {
        // given
        mockDevice = mock(Device.class);
        // then
        assertEquals(0, status.getDevices().size());
        when(this.mockDevice.getStatus()).thenThrow(new NullPointerException(this.NODEVICE));
        NullPointerException exception = assertThrows(NullPointerException.class, () -> status.addDevice(mockDevice));
        assertEquals(this.NODEVICE, exception.getMessage());
        verify(this.mockDevice,times(1)).getStatus();
    }

    @Test
    public void testValidation() {
        var validator = createValidator();
        var status = new Status();
        var constraintViolations = validator.validate(status);
        assertEquals(1, constraintViolations.size());

        for (var violation: constraintViolations) {
            assertEquals("must not be empty", violation.getMessage());
        }
    }
}
