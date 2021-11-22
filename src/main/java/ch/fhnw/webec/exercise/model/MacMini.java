package ch.fhnw.webec.exercise.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class MacMini extends Device{

    // temporaty constructor for testing
    public MacMini(String model, double displaySize, String processor, LocalDate purchaseDate, int memory, String manufacturer) {
        super(model, displaySize, processor, purchaseDate, memory, manufacturer);
    }

    public MacMini() {}
}
