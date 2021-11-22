package ch.fhnw.webec.exercise.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class MacBook extends Device {
    private String keyboardLayout;

    // temporaty constructor for testing
    public MacBook(String model, double displaySize, String processor, LocalDate purchaseDate, int memory, String manufacturer, String keyboardLayout) {
        super(model, displaySize, processor, purchaseDate, memory, manufacturer);
        this.keyboardLayout = keyboardLayout;
    }

    public MacBook() {}

    public String getKeyboardLayout() {
        return keyboardLayout;
    }

    public void setKeyboardLayout(String keyboardLayout) {
        this.keyboardLayout = keyboardLayout;
    }
}
