package com.example.recap14;

public class Parfum {
    private String denumire;
    private String aroma;
    private int ml;

    public Parfum(String denumire, String aroma, int ml) {
        this.denumire = denumire;
        this.aroma = aroma;
        this.ml = ml;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getAroma() {
        return aroma;
    }

    public void setAroma(String aroma) {
        this.aroma = aroma;
    }

    public int getMl() {
        return ml;
    }

    public void setMl(int ml) {
        this.ml = ml;
    }

    @Override
    public String toString() {
        return "Parfum{" +
                "denumire='" + denumire + '\'' +
                ", aroma='" + aroma + '\'' +
                ", ml=" + ml +
                '}';
    }
}
