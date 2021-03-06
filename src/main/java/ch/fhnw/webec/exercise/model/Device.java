package ch.fhnw.webec.exercise.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Class that represents an electronic device in the inventory
 */
@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @NotEmpty
    private String serialNumber;

    @NotEmpty
    private String model;

    @NotEmpty
    private String displaySize;

    @NotEmpty
    private String processor;

    @NotEmpty
    private String purchaseDate;

    @NotEmpty
    private String memory;

    @NotEmpty
    private String manufacturer;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Status status;

    public Device() {}

    // Constructor for Integration Tests
    public Device(String serialNumber, String model, String displaySize, String processor,
                  String purchaseDate, String memory, String manufacturer) {
        this();
        this.serialNumber = serialNumber;
        this.model = model;
        this.displaySize = displaySize;
        this.processor = processor;
        this.purchaseDate = purchaseDate;
        this.memory = memory;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

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

    public @NotEmpty String getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(@NotEmpty String displaySize) {
        this.displaySize = displaySize;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public @NotEmpty String getMemory() {
        return memory;
    }

    public void setMemory(@NotEmpty String memory) {
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
