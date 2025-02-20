package com.example.recapitulare4;

import java.io.Serializable;
import java.util.Date;

public class Melodie implements Serializable {
    private String nume;
    private float durata;
    private Date publicare;
    private String gen;
    private String feedback;

    public Melodie(String nume, float durata, Date publicare, String gen, String feedback) {
        this.nume = nume;
        this.durata = durata;
        this.publicare = publicare;
        this.gen = gen;
        this.feedback = feedback;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getDurata() {
        return durata;
    }

    public void setDurata(float durata) {
        this.durata = durata;
    }

    public Date getPublicare() {
        return publicare;
    }

    public void setPublicare(Date publicare) {
        this.publicare = publicare;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Melodie{" +
                "nume='" + nume + '\'' +
                ", durata=" + durata +
                ", publicare=" + publicare +
                ", gen='" + gen + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
