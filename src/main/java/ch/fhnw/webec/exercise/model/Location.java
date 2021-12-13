package ch.fhnw.webec.exercise.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a location of a device
 * it has a one to many relation to devices
 */
@Entity
public class Location {

    public static final String NULLDEVICE = "There is no device to add";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @NotEmpty
    @Length(min = 1, max = 255)
    private String buildingName;

    @NotEmpty
    @Length(min = 1, max = 255)
    private String roomName;

    @NotEmpty
    @Length(min = 1, max = 255)
    private String streetAndNumber;

    @NotEmpty
    @Length(min = 1, max = 255)
    private String zipCity;

    @OneToMany(mappedBy = "location")
    private List<Device> devices = new ArrayList<>();

    public Location(){}

    public Location(String buildingName, String roomName, String streetAndNumber, String zipCity){
        this();
        this.buildingName = buildingName;
        this.roomName = roomName;
        this.streetAndNumber = streetAndNumber;
        this.zipCity = zipCity;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public String getZipCity() {
        return zipCity;
    }

    public void setZipCity(String zipCity) {
        this.zipCity = zipCity;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public void addDevice(Device device){
        if (device != null) {
            if (!this.getDevices().contains(device)) {
                this.getDevices().add(device);
            }
            if (device.getLocation() != this) {
                device.setLocation(this);
            }
        } else {
            throw new NullPointerException(NULLDEVICE);
        }
    }
}
