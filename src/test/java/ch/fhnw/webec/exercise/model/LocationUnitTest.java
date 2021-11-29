package ch.fhnw.webec.exercise.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    public void testAddIpad(int deviceCounter){
        location = new Location();

        assertEquals(0, location.getDevices().size());

        for (int i = 0; i < deviceCounter; i++){
            // given
            createIPad(i + 1);
            // when
            assertNull(device.getLocation());
            location.addDevice(device);
        }

        // then
        assertEquals(deviceCounter, location.getDevices().size());
        assertTrue(location.getDevices().contains(device));
        assertEquals(location, device.getLocation());

        for (int i = 0; i < deviceCounter; i++){
            assertEquals(TestHelper.IPAD_SERIALNUMBER.concat(String.valueOf(i + 1)),
                           location.getDevices().get(i).getSerialNumber());
        }

        assertEquals(TestHelper.IPAD_MANUFACTURER, location.getDevices().get(0).getManufacturer());
        assertEquals(TestHelper.IPAD_MODEL, location.getDevices().get(0).getModel());
        assertEquals(TestHelper.IPAD_DISPLAYSIZE, location.getDevices().get(0).getDisplaySize());
        assertEquals(TestHelper.IPAD_PROCESSOR, location.getDevices().get(0).getProcessor());
        assertEquals(TestHelper.IPAD_MEMORY, location.getDevices().get(0).getMemory());
    }

    @Test
    public void testAddNullDevice() {
        // given
        // when
        // then
        NullPointerException exception = assertThrows(NullPointerException.class, () -> location.addDevice(device));
        assertEquals(Location.NULLDEVICE, exception.getMessage());
        assertEquals(0, location.getDevices().size());
        assertThrows(NullPointerException.class, () -> device.getLocation());
    }

    @Test
    public void testValidation() {
        var validator = createValidator();
        var constraintViolations = validator.validate(location);
        assertEquals(4, constraintViolations.size());

        for (var violation: constraintViolations) {
            assertEquals("must not be empty", violation.getMessage());
        }
    }
}
