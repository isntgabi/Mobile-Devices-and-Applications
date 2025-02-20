package com.example.recapp1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ochelari")
public class Ochelari implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String firma;
    private float dioptrie;
    private String tip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ochelari(String firma, float dioptrie, String tip) {
        this.firma = firma;
        this.dioptrie = dioptrie;
        this.tip = tip;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public float getDioptrie() {
        return dioptrie;
    }

    public void setDioptrie(float dioptrie) {
        this.dioptrie = dioptrie;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "Ochelari{" +
                "firma='" + firma + '\'' +
                ", dioptrie=" + dioptrie +
                ", tip='" + tip + '\'' +
                '}';
    }
}
