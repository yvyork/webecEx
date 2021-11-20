package ch.fhnw.webec.exercise.repository;

import ch.fhnw.webec.exercise.model.Studie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudieRepository extends JpaRepository<Studie, Integer> {
}
