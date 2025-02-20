package com.example.recapitulare5;

import java.io.Serializable;

public class Energizant implements Serializable {
    private String denumire;
    private int cantitate;
    private String aroma;
    private String indulcit;

    public Energizant(String denumire, int cantitate, String aroma, String indulcit) {
        this.denumire = denumire;
        this.cantitate = cantitate;
        this.aroma = aroma;
        this.indulcit = indulcit;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public String getAroma() {
        return aroma;
    }

    public void setAroma(String aroma) {
        this.aroma = aroma;
    }

    public String getIndulcit() {
        return indulcit;
    }

    public void setIndulcit(String indulcit) {
        this.indulcit = indulcit;
    }

    @Override
    public String toString() {
        return "Energizant{" +
                "denumire='" + denumire + '\'' +
                ", cantitate=" + cantitate +
                ", aroma='" + aroma + '\'' +
                ", indulcit='" + indulcit + '\'' +
                '}';
    }
}
