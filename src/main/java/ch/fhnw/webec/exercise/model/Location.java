package ch.fhnw.webec.exercise.model;

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

    @Id
    private int locationId;

    @NotEmpty
    private String buildingName;

    @NotEmpty
    private String roomName;

    @NotEmpty
    private String streetAndNumber;

    @NotEmpty
    private String zipCity;

    @OneToMany(mappedBy = "location")
    private List<Device> devices = new ArrayList<>();

    public Location(){}

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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
        if (device != null){
            if (!this.getDevices().contains(device)) {
                this.getDevices().add(device);
            }
            if (device.getLocation() != this ){
                device.setLocation(this);
            }
        }
    }
}
