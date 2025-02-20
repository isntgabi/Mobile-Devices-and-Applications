package com.example.recapitulare7;

import java.io.Serializable;
import java.util.Date;

public class Facultate implements Serializable {
    private String denumire;
    private float medieIntrare;
    private Date dataExaminarii;
    private String tip;
    private String examen;

    public Facultate(String denumire, float medieIntrare, Date dataExaminarii, String tip, String examen) {
        this.denumire = denumire;
        this.medieIntrare = medieIntrare;
        this.dataExaminarii = dataExaminarii;
        this.tip = tip;
        this.examen = examen;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public float getMedieIntrare() {
        return medieIntrare;
    }

    public void setMedieIntrare(float medieIntrare) {
        this.medieIntrare = medieIntrare;
    }

    public Date getDataExaminarii() {
        return dataExaminarii;
    }

    public void setDataExaminarii(Date dataExaminarii) {
        this.dataExaminarii = dataExaminarii;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    @Override
    public String toString() {
        return "Facultate{" +
                "denumire='" + denumire + '\'' +
                ", medieIntrare=" + medieIntrare +
                ", dataExaminarii=" + dataExaminarii +
                ", tip='" + tip + '\'' +
                ", examen='" + examen + '\'' +
                '}';
    }
}
