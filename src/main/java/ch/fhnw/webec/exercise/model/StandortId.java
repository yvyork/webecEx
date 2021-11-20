package ch.fhnw.webec.exercise.model;

import java.io.Serializable;
import java.util.Objects;

//*
/* solution from https://www.baeldung.com/jpa-composite-primary-keys
 */
public class StandortId implements Serializable {

    private String gebaeudeBezeichnung;
    private String raumBezeichnung;

    public StandortId(){}

    public StandortId(String gebaeudeBezeichnung, String raumBezeichnung){
        this.gebaeudeBezeichnung = gebaeudeBezeichnung;
        this.raumBezeichnung = raumBezeichnung;
    }

    //*
    /* solution from
    /* Core Java SE9 for the impatient,
    /* Chapter 4.2.2 The equals Methode, p. 148
    /* Second Edition 2018
    /* Cay s. Horstmann
    /* Addison-Wesley, Boston
    */
    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null) return false;
        if (this.getClass() != other.getClass()) return false;
        StandortId otherStandortId = (StandortId)other;
        return Objects.equals(this.gebaeudeBezeichnung, otherStandortId.gebaeudeBezeichnung)
            && Objects.equals(this.raumBezeichnung, ((StandortId) other).raumBezeichnung);
    }

//*
    /* solution from
    /* Core Java SE9 for the impatient,
    /* Chapter 4.2.3 The hashCode Methode, p. 150
    /* Second Edition 2018
    /* Cay s. Horstmann
    /* Addison-Wesley, Boston
    */
    @Override
    public int hashCode(){
        return Objects.hash(gebaeudeBezeichnung, raumBezeichnung);
    }
}
