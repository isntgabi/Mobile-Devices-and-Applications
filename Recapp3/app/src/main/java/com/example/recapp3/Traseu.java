package com.example.recapp3;

import android.app.appsearch.SearchResults;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "trasee")
public class Traseu implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String pornire;
    private String destinatie;
    private String vehicul;

    public Traseu(String pornire, String destinatie, String vehicul) {
        this.pornire = pornire;
        this.destinatie = destinatie;
        this.vehicul = vehicul;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPornire() {
        return pornire;
    }

    public void setPornire(String pornire) {
        this.pornire = pornire;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public String getVehicul() {
        return vehicul;
    }

    public void setVehicul(String vehicul) {
        this.vehicul = vehicul;
    }

    @Override
    public String toString() {
        return vehicul + "ul pleaca din " + pornire + " pana in " + destinatie + "!";
    }
}
