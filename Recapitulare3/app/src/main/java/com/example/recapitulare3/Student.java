package com.example.recapitulare3;

import java.io.Serializable;

public class Student implements Serializable {
    private String nume;
    private float medie;
    private String specializare;

    public Student(String nume, float medie, String specializare) {
        this.nume = nume;
        this.medie = medie;
        this.specializare = specializare;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getMedie() {
        return medie;
    }

    public void setMedie(float medie) {
        this.medie = medie;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + '\'' +
                ", medie=" + medie +
                ", specializare='" + specializare + '\'' +
                '}';
    }
}
