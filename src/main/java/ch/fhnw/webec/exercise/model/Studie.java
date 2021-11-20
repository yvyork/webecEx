package ch.fhnw.webec.exercise.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Studie {

    @Id
    private int studieID;

    @NotEmpty
    private String name;

    @OneToOne
    private Person studienVerantwortliche;

    @OneToMany(mappedBy = "studie")
    private List<Device> devices;

    public Studie() {}

    public int getStudieID() {
        return studieID;
    }

    public void setStudieID(int studieID) {
        this.studieID = studieID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getStudienVerantwortliche() {
        return studienVerantwortliche;
    }

    public void setStudienVerantwortliche(Person studienVerantwortliche) {
        this.studienVerantwortliche = studienVerantwortliche;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
