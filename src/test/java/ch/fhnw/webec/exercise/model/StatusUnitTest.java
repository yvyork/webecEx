package ch.fhnw.webec.exercise.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

public class StatusUnitTest {

    private Status status;
    private Device device;


    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }

    @BeforeEach
    public  void setup() {
        status = new Status();
    }

    private void createIPad(int counter) {
        device = new Device();
        device.setSerialNumber(TestHelper.IPAD_SERIALNUMBER.concat(String.valueOf(counter)));
        device.setManufacturer(TestHelper.IPAD_MANUFACTURER);
        device.setModel(TestHelper.IPAD_MODEL);
        device.setDisplaySize(TestHelper.IPAD_DISPLAYSIZE);
        device.setProcessor(TestHelper.IPAD_PROCESSOR);
        device.setMemory(TestHelper.IPAD_MEMORY);
    }

    @Test
    public void testAddDevice() {
        // given
        device = new Device();
        int expectedDeviceListCount = 1;

        // when
        assertEquals(0, status.getDevices().size());
        assertNull(device.getStatus());
        status.addDevice(device);

        // then
        assertEquals(expectedDeviceListCount, status.getDevices().size());
        assertTrue(status.getDevices().contains(device));
        assertEquals(status, device.getStatus());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    public void testAddIpad(int deviceCounter) {
        status = new Status();

        assertEquals(0, status.getDevices().size());

        for (int i = 0; i < deviceCounter; i++){
            // given
            createIPad(i + 1);
            // when
            assertNull(device.getStatus());
            status.addDevice(device);
        }

        // then
        assertEquals(deviceCounter, status.getDevices().size());
        assertTrue(status.getDevices().contains(device));
        assertEquals(status, device.getStatus());

        for (int i = 0; i < deviceCounter; i++){
            assertEquals(TestHelper.IPAD_SERIALNUMBER.concat(String.valueOf(i + 1)),
                    status.getDevices().get(i).getSerialNumber());
        }

        assertEquals(TestHelper.IPAD_MANUFACTURER, status.getDevices().get(0).getManufacturer());
        assertEquals(TestHelper.IPAD_MODEL, status.getDevices().get(0).getModel());
        assertEquals(TestHelper.IPAD_DISPLAYSIZE, status.getDevices().get(0).getDisplaySize());
        assertEquals(TestHelper.IPAD_PROCESSOR, status.getDevices().get(0).getProcessor());
        assertEquals(TestHelper.IPAD_MEMORY, status.getDevices().get(0).getMemory());
    }

    @Test
    public void testAddNullStatus() {
        // given

        // when

        // then
        NullPointerException exception = assertThrows(NullPointerException.class, () -> status.addDevice(device));
        assertEquals(Location.NULLDEVICE, exception.getMessage());
        assertEquals(0, status.getDevices().size());
        assertThrows(NullPointerException.class, () -> device.getStatus());
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
