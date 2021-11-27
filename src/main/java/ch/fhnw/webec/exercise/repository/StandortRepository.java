package ch.fhnw.webec.exercise.repository;

import ch.fhnw.webec.exercise.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandortRepository extends JpaRepository<Location, Integer> {
}
