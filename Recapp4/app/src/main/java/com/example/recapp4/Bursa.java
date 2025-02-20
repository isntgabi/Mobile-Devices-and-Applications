package com.example.recapp4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "burse")
public class Bursa implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String denumire;
    private int suma;
    private String tip;

    public Bursa(String denumire, int suma, String tip) {
        this.denumire = denumire;
        this.suma = suma;
        this.tip = tip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    @Override
    public String toString() {
        return "Bursa{" +
                "denumire='" + denumire + '\'' +
                ", suma=" + suma +
                ", tip='" + tip + '\'' +
                '}';
    }
}
