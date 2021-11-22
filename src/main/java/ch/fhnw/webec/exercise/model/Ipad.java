package ch.fhnw.webec.exercise.model;

import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Ipad extends Device{

    // temporaty constructor for testing
    public Ipad(String model, double displaySize, String processor, LocalDate purchaseDate, int memory, String manufacturer) {
        super(model, displaySize, processor, purchaseDate, memory, manufacturer);
    }


    public Ipad() {}
}
