package ch.fhnw.webec.exercise.repository;

import ch.fhnw.webec.exercise.model.Standort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandortRepository extends JpaRepository<Standort, Integer> {
}
