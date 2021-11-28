package ch.fhnw.webec.exercise.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a status of a device
 * it has a one to many relation to devices
 */
@Entity
public class Status {
    @Id
    private int statusId;

    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "status")
    private List<Device> deviceList = new ArrayList<>();

    public Status() {}

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String status) {
        this.name = status;
    }

    public List<Device> getDevices() {
        return deviceList;
    }

    public void setDevices(List<Device> devices) { this.deviceList = devices; }

    public void addDevice(Device device) {
        if (device != null) {
            if (!this.getDevices().contains(device)) {
                this.getDevices().add(device);
            }
            if (device.getStatus() != this ) {
                device.setStatus(this);
            }
        }
    }
}
