package ch.fhnw.webec.exercise.repository;

import ch.fhnw.webec.exercise.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
