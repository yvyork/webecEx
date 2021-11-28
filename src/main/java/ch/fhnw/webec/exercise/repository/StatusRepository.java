package ch.fhnw.webec.exercise.repository;

import ch.fhnw.webec.exercise.model.Location;
import ch.fhnw.webec.exercise.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Integer> {

    @Query("""
        SELECT status
        FROM Status status
        WHERE LOWER(status.name) LIKE LOWER(CONCAT('%', :search, '%'))
    """)
    List<Status> findBySearch(@Param("search") String search);
}
