package ch.fhnw.webec.exercise.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Location {

    @Id
    private int standortId;

    @NotEmpty
    private String gebaeudeBezeichnung;

    @NotEmpty
    private String raumBezeichnung;

    @NotEmpty
    private String strasseNr;

    @NotEmpty
    private String plzOrt;

    @OneToMany(mappedBy = "location")
    private List<Device> devices;

    public Location(){}

    public String getGebaeudeBezeichnung() {
        return gebaeudeBezeichnung;
    }

    public void setGebaeudeBezeichnung(String gebaeudeBezeichnung) {
        this.gebaeudeBezeichnung = gebaeudeBezeichnung;
    }

    public String getRaumBezeichnung() {
        return raumBezeichnung;
    }

    public void setRaumBezeichnung(String raumBezeichnung) {
        this.raumBezeichnung = raumBezeichnung;
    }

    public String getStrasseNr() {
        return strasseNr;
    }

    public void setStrasseNr(String strasseNr) {
        this.strasseNr = strasseNr;
    }

    public String getPlzOrt() {
        return plzOrt;
    }

    public void setPlzOrt(String plzOrt) {
        this.plzOrt = plzOrt;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
