package ch.fhnw.webec.exercise.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

public class LocationUnitTest {

    private Location location;
    private Device device;


    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }

    @BeforeEach
    public void setup() {
        location = new Location();
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
    public void testAddDevice(){
        // given
        device = new Device();
        int expectedDeviceListCount = 1;

        // when
        assertEquals(0, location.getDevices().size());
        assertNull(device.getLocation());
        location.addDevice(device);

        // then
        assertEquals(expectedDeviceListCount, location.getDevices().size());
        assertTrue(location.getDevices().contains(device));
        assertEquals(location, device.getLocation());
    }

    @Test
    public void testAddIpad(){
        // given
        createIPad();
        int expectedDeviceListCount = 1;

        // when
        assertEquals(0, location.getDevices().size());
        assertNull(device.getLocation());
        location.addDevice(device);

        // then
        assertEquals(expectedDeviceListCount, location.getDevices().size());
        assertTrue(location.getDevices().contains(device));
        assertEquals(location, device.getLocation());
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
        var location = new Location();
        var constraintViolations = validator.validate(location);
        assertEquals(4, constraintViolations.size());

        for (var violation: constraintViolations) {
            assertEquals("must not be empty", violation.getMessage());
        }
    }
}
