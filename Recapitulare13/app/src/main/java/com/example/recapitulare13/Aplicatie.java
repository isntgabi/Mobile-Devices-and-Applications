package com.example.recapitulare13;

public class Aplicatie {
    private String denumire;
    private double stocare;
    private String autor;

    public Aplicatie(String denumire, double stocare, String autor) {
        this.denumire = denumire;
        this.stocare = stocare;
        this.autor = autor;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getStocare() {
        return stocare;
    }

    public void setStocare(double stocare) {
        this.stocare = stocare;
    }

    @Override
    public String toString() {
        return "Aplicatie{" +
                "denumire='" + denumire + '\'' +
                ", stocare=" + stocare +
                ", autor='" + autor + '\'' +
                '}';
    }
}
