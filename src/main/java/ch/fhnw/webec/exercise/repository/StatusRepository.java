package ch.fhnw.webec.exercise.repository;

import ch.fhnw.webec.exercise.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
