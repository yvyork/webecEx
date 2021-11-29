package ch.fhnw.webec.exercise.model;
// TODO persistence or hibernate?
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * Class that represents an electronic device in the inventory
 */
@Entity
public class Device {

    @Id
    private String serialNumber;

    @NotEmpty
    private String model;
    // TODO: 29.11.21 change displaySize to type string?
    @NotEmpty
    private double displaySize;

    @NotEmpty
    private String processor;

    @NotEmpty
    private LocalDate purchaseDate;
    // TODO: 29.11.21 change memory to type string?
    @NotEmpty
    private int memory;

    @NotEmpty
    private String manufacturer;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Status status;

    public Device() {}

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public Location getLocation(){ return location; }

    public void setLocation(Location location) {
        this.location = location;
        this.location.addDevice(this);
    }

    public Status getStatus(){ return status; }

    public void setStatus(Status status) {
        this.status = status;
        this.status.addDevice(this);
    }

}
