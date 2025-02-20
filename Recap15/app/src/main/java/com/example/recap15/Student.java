package com.example.recap15;

public class Student {
    private String nume;
    private String facultate;
    private double medie;

    public Student(String nume, String facultate, double medie) {
        this.nume = nume;
        this.facultate = facultate;
        this.medie = medie;
    }

    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public double getMedie() {
        return medie;
    }

    public void setMedie(double medie) {
        this.medie = medie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + '\'' +
                ", facultate='" + facultate + '\'' +
                ", medie=" + medie +
                '}';
    }
}
