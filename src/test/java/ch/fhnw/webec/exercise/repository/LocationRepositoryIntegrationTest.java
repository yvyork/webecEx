package ch.fhnw.webec.exercise.repository;

import ch.fhnw.webec.exercise.model.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class LocationRepositoryIntegrationTest {
    //@Autowired
    //private DeviceRepository deviceRepository;

    @Autowired
    private LocationRepository locationRepository;

    //@Autowired
    //private StatusRepository statusRepository;

    @Test
    public void testFindAll() {
        // given
        // when
        var locations = this.locationRepository.findAll();
        var firstLocation = locations.get(0);
        // then
        assertEquals(4, locations.size());
        assertEquals("NordflÃ¼gel", firstLocation.getBuildingName());
    }

    @Test
    public void testSaveLocation(){
        // given
        var location = new Location("Nordwestflügel","EG01","Musterstrasse 1", "3000 Bern");
        assertEquals(4, this.locationRepository.findAll().size());
        // when
        var savedLocation = this.locationRepository.save(location);
        // then
        assertEquals(5, this.locationRepository.findAll().size());
        assertEquals("Nordwestflügel", savedLocation.getBuildingName());
    }

    @Test
    public void testSavedInvalidLocation() {
        // then
        assertThrows(ConstraintViolationException.class, () -> this.locationRepository.save(new Location()));
    }

    @Test
    public void testUpdateLocation() {
        // given
        var location = this.locationRepository.findById(1).get();
        //assertEquals("Nordflügel", location.getBuildingName());
        assertEquals("NordflÃ¼gel", location.getBuildingName());
        // when
        location.setBuildingName("Nordostflügel");
        location.setRoomName("EG02");
        location.setStreetAndNumber("Musterstrasse 10");
        location.setZipCity("3000 Bern 10");
        var savedLocation = this.locationRepository.save(location);
        // then
        assertEquals(4, locationRepository.findAll().size());
        assertEquals("Nordostflügel", savedLocation.getBuildingName());
        assertEquals("EG02", savedLocation.getRoomName());
        assertEquals("Musterstrasse 10", savedLocation.getStreetAndNumber());
        assertEquals("3000 Bern 10", savedLocation.getZipCity());
    }

    @Test
    public void testDeleteLocation() {
        // given
        var location = this.locationRepository.findById(4).get();
        assertEquals(4, this.locationRepository.findAll().size());
        // when
        this.locationRepository.delete(location);
        // then
        assertEquals(3, this.locationRepository.findAll().size());
    }
}
