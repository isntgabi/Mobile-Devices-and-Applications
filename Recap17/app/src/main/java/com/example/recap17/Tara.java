package com.example.recap17;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tari")
public class Tara implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String nume;
    private double populatie;
    private String continent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tara(String nume, double populatie, String continent) {
        this.nume = nume;
        this.populatie = populatie;
        this.continent = continent;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getPopulatie() {
        return populatie;
    }

    public void setPopulatie(double populatie) {
        this.populatie = populatie;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Override
    public String toString() {
        return "Tara{" +
                "nume='" + nume + '\'' +
                ", populatie=" + populatie +
                ", continent='" + continent + '\'' +
                '}';
    }
}
