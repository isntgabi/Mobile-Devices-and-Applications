package com.example.recap11;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "medicamente")
public class Medicament implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String denumire;
    private Date dataExpirare;
    private String substantaPrincipala; //spinner

    public Medicament(String denumire, Date dataExpirare, String substantaPrincipala) {
        this.denumire = denumire;
        this.dataExpirare = dataExpirare;
        this.substantaPrincipala = substantaPrincipala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getSubstantaPrincipala() {
        return substantaPrincipala;
    }

    public void setSubstantaPrincipala(String substantaPrincipala) {
        this.substantaPrincipala = substantaPrincipala;
    }

    public Date getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(Date dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "denumire='" + denumire + '\'' +
                ", dataExpirare='" + dataExpirare + '\'' +
                ", substantaPrincipala='" + substantaPrincipala + '\'' +
                '}';
    }
}
