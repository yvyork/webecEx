package ch.fhnw.webec.exercise.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Location {

    @Id
    private int locationId;

    @NotEmpty
    private String buildindName;

    @NotEmpty
    private String roomName;

    @NotEmpty
    private String streetAndNumber;

    @NotEmpty
    private String zipCity;

    @OneToMany(mappedBy = "location")
    private List<Device> devices;

    public Location(){}

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getBuildindName() {
        return buildindName;
    }

    public void setBuildindName(String buildindName) {
        this.buildindName = buildindName;
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
}
