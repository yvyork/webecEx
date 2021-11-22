package ch.fhnw.webec.exercise.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Iphone extends Device {

    // temporaty constructor for testing
    public Iphone(String model, double displaySize, String processor, LocalDate purchaseDate, int memory, String manufacturer) {
        super(model, displaySize, processor, purchaseDate, memory, manufacturer);
    }

    public Iphone() {}
}

