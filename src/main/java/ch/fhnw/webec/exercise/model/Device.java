package ch.fhnw.webec.exercise.model;
// TODO persistence or hibernate?
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDate;
import java.util.Date;

//TODO commit to one language en or ge.
/**
 * Class that represents an electronic device in the inventory
 */
@Entity
public class Device {

    //TODO easier to use an incrementing PK as ID?
    @Id
    protected String serialNumber;


    protected String model;
    protected double displaySize;
    protected String processor;
    protected LocalDate purchaseDate;
    protected int memory;
    protected String manufacturer;
    //TODO implement class Standort;

    // temporaty constructor for testing
    public Device(String model, double displaySize, String processor, LocalDate purchaseDate, int memory, String manufacturer) {};

    // no-arg constructor
    public Device() {}


    public String getSerialNumber() {
        return serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(double displaySize) {
        this.displaySize = displaySize;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
