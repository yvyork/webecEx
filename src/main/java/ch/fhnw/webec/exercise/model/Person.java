package ch.fhnw.webec.exercise.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Person {

    @Id
    private int mitarbeiterNummer;

    @NotEmpty
    private String vorname;

    @NotEmpty
    private String nachname;

    @NotEmpty
    private String bueroStandort;
    private String telefon;

    private String Abteilung;
    private String Studie;

    @OneToMany(mappedBy = "person")
    private List<Device> devices;

    public Person() {}

    public int getMitarbeiterNummer() {
        return mitarbeiterNummer;
    }

    public void setMitarbeiterNummer(int mitarbeiterNummer) {
        this.mitarbeiterNummer = mitarbeiterNummer;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getBueroStandort() {
        return bueroStandort;
    }

    public void setBueroStandort(String bueroStandort) {
        this.bueroStandort = bueroStandort;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAbteilung() {
        return Abteilung;
    }

    public void setAbteilung(String abteilung) {
        Abteilung = abteilung;
    }

    public String getStudie() {
        return Studie;
    }

    public void setStudie(String studie) {
        Studie = studie;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
