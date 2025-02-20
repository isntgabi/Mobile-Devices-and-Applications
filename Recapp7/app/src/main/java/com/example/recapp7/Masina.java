package com.example.recapp7;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "masini")
public class Masina implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String marca;
    private float pret;
    private String tip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Masina(String marca, float pret, String tip) {
        this.marca = marca;
        this.pret = pret;
        this.tip = tip;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "Masina{" +
                "marca='" + marca + '\'' +
                ", pret=" + pret +
                ", tip='" + tip + '\'' +
                '}';
    }
}
