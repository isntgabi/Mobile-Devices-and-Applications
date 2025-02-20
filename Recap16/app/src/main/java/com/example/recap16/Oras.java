package com.example.recap16;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "orase")
public class Oras implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String denumire;
    private double populatie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Oras(String denumire, double populatie) {
        this.denumire = denumire;
        this.populatie = populatie;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getPopulatie() {
        return populatie;
    }

    public void setPopulatie(double populatie) {
        this.populatie = populatie;
    }

    @Override
    public String toString() {
        return "Oras{" +
                "denumire='" + denumire + '\'' +
                ", populatie=" + populatie +
                '}';
    }
}
