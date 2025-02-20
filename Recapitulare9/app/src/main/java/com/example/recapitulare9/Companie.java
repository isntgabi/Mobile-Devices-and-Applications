package com.example.recapitulare9;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "companii")
public class Companie implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String nume;
    private String tip;
    private Date data;
    private double cifra;

    public Companie(String nume, String tip, Date data, double cifra) {
        this.nume = nume;
        this.tip = tip;
        this.data = data;
        this.cifra = cifra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getCifra() {
        return cifra;
    }

    public void setCifra(double cifra) {
        this.cifra = cifra;
    }

    @Override
    public String toString() {
        return "Companie{" +
                "nume='" + nume + '\'' +
                ", tip='" + tip + '\'' +
                ", data=" + data +
                ", cifra=" + cifra +
                '}';
    }
}
