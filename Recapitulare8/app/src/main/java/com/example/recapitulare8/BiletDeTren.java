package com.example.recapitulare8;

import java.io.Serializable;
import java.util.Date;

public class BiletDeTren implements Serializable {
    private String nume;
    private String destinatie;
    private String metodaPlata;
    private Date dataPlecarii;
    private double pretBilet;

    public BiletDeTren(String nume, String destinatie, String metodaPlata, Date dataPlecarii, double pretBilet) {
        this.nume = nume;
        this.destinatie = destinatie;
        this.metodaPlata = metodaPlata;
        this.dataPlecarii = dataPlecarii;
        this.pretBilet = pretBilet;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public String getMetodaPlata() {
        return metodaPlata;
    }

    public void setMetodaPlata(String metodaPlata) {
        this.metodaPlata = metodaPlata;
    }

    public Date getDataPlecarii() {
        return dataPlecarii;
    }

    public void setDataPlecarii(Date dataPlecarii) {
        this.dataPlecarii = dataPlecarii;
    }

    public double getPretBilet() {
        return pretBilet;
    }

    public void setPretBilet(double pretBilet) {
        this.pretBilet = pretBilet;
    }

    @Override
    public String toString() {
        return "BiletDeTren{" +
                "nume='" + nume + '\'' +
                ", destinatie='" + destinatie + '\'' +
                ", metodaPlata='" + metodaPlata + '\'' +
                ", dataPlecarii=" + dataPlecarii +
                ", pretBilet=" + pretBilet +
                '}';
    }
}
