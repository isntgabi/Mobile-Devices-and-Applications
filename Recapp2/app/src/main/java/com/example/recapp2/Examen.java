package com.example.recapp2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "examene")
public class Examen implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String denumire;
    private int punctaj;
    private String tip;

    public Examen(String denumire, int punctaj, String tip) {
        this.denumire = denumire;
        this.punctaj = punctaj;
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

    public int getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(int punctaj) {
        this.punctaj = punctaj;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "Examenul " + denumire + " reprezinta " + punctaj +"% si se sustine in " + tip + "!";
    }
}
