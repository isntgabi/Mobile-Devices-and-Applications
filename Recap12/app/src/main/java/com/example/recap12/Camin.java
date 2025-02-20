package com.example.recap12;

import java.util.Date;

public class Camin {
    private String nume;
    private int nrPersoane;
    private Date dataInf;

    public Camin(String nume, int nrPersoane, Date dataInf) {
        this.nume = nume;
        this.nrPersoane = nrPersoane;
        this.dataInf = dataInf;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNrPersoane() {
        return nrPersoane;
    }

    public void setNrPersoane(int nrPersoane) {
        this.nrPersoane = nrPersoane;
    }

    public Date getDataInf() {
        return dataInf;
    }

    public void setDataInf(Date dataInf) {
        this.dataInf = dataInf;
    }

    @Override
    public String toString() {
        return "Camin{" +
                "nume='" + nume + '\'' +
                ", nrPersoane=" + nrPersoane +
                ", dataInf=" + dataInf +
                '}';
    }
}
