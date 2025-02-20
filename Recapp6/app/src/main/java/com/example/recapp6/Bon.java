package com.example.recapp6;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "bonuri")
public class Bon implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String cod;
    private float suma;
    private String plata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bon(String cod, float suma, String plata) {
        this.cod = cod;
        this.suma = suma;
        this.plata = plata;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public float getSuma() {
        return suma;
    }

    public void setSuma(float suma) {
        this.suma = suma;
    }

    public String getPlata() {
        return plata;
    }

    public void setPlata(String plata) {
        this.plata = plata;
    }

    @Override
    public String toString() {
        return "Bonul ["+cod+"] platit "+plata+" in valoare de: " + suma + " lei!";
    }
}
