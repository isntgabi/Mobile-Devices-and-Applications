package com.example.recapitulare10;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "carti")
public class Carte implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String titlu;
    private Date dataPublicare;
    private double pret;
    private String tip;

    public Carte(String titlu, Date dataPublicare, double pret, String tip) {
        this.titlu = titlu;
        this.dataPublicare = dataPublicare;
        this.pret = pret;
        this.tip = tip;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public Date getDataPublicare() {
        return dataPublicare;
    }

    public void setDataPublicare(Date dataPublicare) {
        this.dataPublicare = dataPublicare;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "titlu='" + titlu + '\'' +
                ", dataPublicare=" + dataPublicare +
                ", pret=" + pret +
                ", tip='" + tip + '\'' +
                '}';
    }
}
