package ch.fhnw.webec.exercise.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Zubehoer {
    @Id
    @Column(name = "id", nullable = false)
    //TODO add auto increment
    private Integer id;

    private String name;
    String hersteller;
    String tastaturBelegung;
    double displayGroesse;
    double wattage;

    public Zubehoer() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Zubehoer(String name, String hersteller, String tastaturBelegung, double displayGroesse, double wattage) {
        this.name = name;
        this.hersteller = hersteller;
        this.tastaturBelegung = tastaturBelegung;
        this.displayGroesse = displayGroesse;
        this.wattage = wattage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getTastaturBelegung() {
        return tastaturBelegung;
    }

    public void setTastaturBelegung(String tastaturBelegung) {
        this.tastaturBelegung = tastaturBelegung;
    }

    public double getDisplayGroesse() {
        return displayGroesse;
    }

    public void setDisplayGroesse(double displayGroesse) {
        this.displayGroesse = displayGroesse;
    }

    public double getWattage() {
        return wattage;
    }

    public void setWattage(double wattage) {
        this.wattage = wattage;
    }
}
