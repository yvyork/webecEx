package ch.fhnw.webec.exercise.repository;

import ch.fhnw.webec.exercise.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query("""
        SELECT location
          FROM Location location
         WHERE LOWER(location.buildingName) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(location.roomName) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(location.streetAndNumber) LIKE LOWER(CONCAT('%',:search,'%'))
            OR LOWER(location.zipCity) LIKE LOWER(CONCAT('%',:search,'%'))
    """)
    List<Location> findBySearch(@Param("search") String search);
}
