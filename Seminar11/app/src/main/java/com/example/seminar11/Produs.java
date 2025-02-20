package com.example.seminar11;

import java.io.Serializable;

public class Produs implements Serializable {
    private String id;
    private String denumire;
    private float pret;

    public Produs(String id, String denumire, float pret) {
        this.id = id;
        this.denumire = denumire;
        this.pret = pret;
    }

    public Produs() {}

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Produsul " + denumire + "costa " + pret + " lei!";
    }
}
