package ch.fhnw.webec.exercise.repository;

import ch.fhnw.webec.exercise.model.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StatusRepositoryIntegrationTest {

    //@Autowired
    //private DeviceRepository deviceRepository;

    //@Autowired
    //private LocationRepository locationRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Test
    public void testFindAll() {
        // given
        // when
        var statuses = this.statusRepository.findAll();
        var firstStatus = statuses.get(0);
        // then
        assertEquals(4, statuses.size());
        assertEquals("new", firstStatus.getName());
    }

    @Test
    public void testSaveStatus() {
        // given
        var status = new Status("retired");
        assertEquals(4, this.statusRepository.findAll().size());
        // when
        var savedStatus = this.statusRepository.save(status);
        // then
        assertEquals(5, this.statusRepository.findAll().size());
        assertEquals("retired", savedStatus.getName());
    }

    @Test
    public void testSavedInvalidStatus() {
        // then
        assertThrows(ConstraintViolationException.class, () -> this.statusRepository.save(new Status("")));
    }

    @Test
    public void testUpdateStatus() {
        // given
        var status = this.statusRepository.findById(1).get();
        assertEquals("new", status.getName());
        // when
        status.setName("new device");
        var savedStatus = this.statusRepository.save(status);
        // then
        assertEquals(4, this.statusRepository.findAll().size());
        assertEquals("new device", savedStatus.getName());
    }

    @Test
    public void testDeleteStatus() {
        // given
        var status = this.statusRepository.findById(4).get();
        assertEquals(4, this.statusRepository.findAll().size());
        // when
        this.statusRepository.delete(status);
        // then
        assertEquals(3, this.statusRepository.findAll().size());
    }
}
