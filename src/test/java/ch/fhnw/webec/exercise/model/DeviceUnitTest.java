package ch.fhnw.webec.exercise.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeviceUnitTest {

    private Status status;
    private Location location;
    private Device device;

    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }

    @BeforeEach
    void setUp() {
        device = new Device();
        createMacBook();
        status = new Status();
        location = new Location();
    }

    private void createMacBook() {
        device.setSerialNumber(TestHelper.MACBOOK_SERIALNUMBER);
        device.setModel(TestHelper.MACBOOK_MODEL);
        device.setDisplaySize(TestHelper.MACBOOK_DISPLAYSIZE);
        device.setProcessor(TestHelper.MACBOOK_PROCESSOR);
        device.setPurchaseDate(TestHelper.MACBOOK_PURCHASEDATE);
        device.setMemory(TestHelper.MACBOOK_MEMORY);
        device.setManufacturer(TestHelper.MACBOOK_MANUFACTURER);
    }

    @Test
    public void deviceStatusChangeTest() {
        status.setName(TestHelper.STATUS_INUSE);
        device.setStatus(status);

        assertEquals(TestHelper.STATUS_INUSE, device.getStatus().getName());

        // change status of device
        status.setName(TestHelper.STATUS_DEFECT);
        device.setStatus(status);

        assertEquals(TestHelper.STATUS_DEFECT, device.getStatus().getName());
    }

    @Test
    public void deviceLocationChangeTest() {
        // location: HRS E12 Schanzengraben 12 8001
        location.setBuildingName(TestHelper.LOCATION_BUILDINGNAME);
        location.setRoomName(TestHelper.LOCATION_ROOMNAME);
        location.setStreetAndNumber(TestHelper.LOCATION_STREET);
        location.setZipCity(TestHelper.LOCATION_ZIP);

        device.setLocation(location);
        assertEquals(TestHelper.LOCATION_BUILDINGNAME, device.getLocation().getBuildingName());
        assertEquals(TestHelper.LOCATION_ROOMNAME, device.getLocation().getRoomName());
        assertEquals(TestHelper.LOCATION_STREET, device.getLocation().getStreetAndNumber());
        assertEquals(TestHelper.LOCATION_ZIP, device.getLocation().getZipCity());

        // change room in same building
        location.setRoomName("E15");
        String newRoom = location.getRoomName();
        assertEquals(newRoom, device.getLocation().getRoomName());

        // change street
        location.setStreetAndNumber("Schanzengraben 11");
        String newStreet = location.getStreetAndNumber();
        assertEquals(newStreet, device.getLocation().getStreetAndNumber());
    }

    @Test
    void purchaseDateTest() {
        LocalDate date = LocalDate.now();
        assertEquals(date, device.getPurchaseDate());
    }

    @Test
    public void testValidation() {
        var validator = createValidator();
        var constraintViolations = validator.validate(device);
        assertEquals(0, constraintViolations.size());

        device.setMemory(null);

        // set one field to null
        constraintViolations = validator.validate(device);
        assertEquals(1, constraintViolations.size());

        for (var violation : constraintViolations) {
            assertEquals("must not be empty", violation.getMessage());
        }
    }

}