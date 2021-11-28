package ch.fhnw.webec.exercise.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    private void createIPad() {
        device = new Device();
        device.setSerialNumber(TestHelper.IPAD_SERIALNUMBER);
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

    @Test
    public void testAddIpad() {
        // given
        createIPad();
        int expectedDeviceListCount = 1;

        // when
        assertEquals(0, status.getDevices().size());
        assertNull(device.getStatus());
        status.addDevice(device);

        // then
        assertEquals(expectedDeviceListCount, status.getDevices().size());
        assertTrue(status.getDevices().contains(device));
        assertEquals(status, device.getStatus());
        assertEquals(TestHelper.IPAD_SERIALNUMBER, device.getSerialNumber());
        assertEquals(TestHelper.IPAD_MANUFACTURER, device.getManufacturer());
        assertEquals(TestHelper.IPAD_MODEL, device.getModel());
        assertEquals(TestHelper.IPAD_DISPLAYSIZE, device.getDisplaySize());
        assertEquals(TestHelper.IPAD_PROCESSOR, device.getProcessor());
        assertEquals(TestHelper.IPAD_MEMORY, device.getMemory());
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
