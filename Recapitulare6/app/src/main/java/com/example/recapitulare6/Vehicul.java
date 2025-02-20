package com.example.recapitulare6;

import java.io.Serializable;
import java.util.Date;

public class Vehicul implements Serializable {
    private String denumire;
    private int nrLocuri;
    private Date data;
    private String tip;
    private String culoare;

    public Vehicul(String denumire, int nrLocuri, Date data, String tip, String culoare) {
        this.denumire = denumire;
        this.nrLocuri = nrLocuri;
        this.data = data;
        this.tip = tip;
        this.culoare = culoare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    @Override
    public String toString() {
        return "Vehicul{" +
                "denumire='" + denumire + '\'' +
                ", nrLocuri=" + nrLocuri +
                ", data=" + data +
                ", tip='" + tip + '\'' +
                ", culoare='" + culoare + '\'' +
                '}';
    }
}
