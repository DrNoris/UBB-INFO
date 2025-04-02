package com.example.seminar14.Domain;

public class Sarcina {
    private String sarcinaId;
    private Dificultate dificultate;
    private int nrOreEstimate;


    public Sarcina(String sarcinaId, Dificultate dificultate, int nrOreEstimate) {
        this.sarcinaId = sarcinaId;
        this.dificultate = dificultate;
        this.nrOreEstimate = nrOreEstimate;
    }

    public String getSarcinaId() {
        return sarcinaId;
    }

    public void setSarcinaId(String sarcinaId) {
        this.sarcinaId = sarcinaId;
    }

    public Dificultate getDificultate() {
        return dificultate;
    }

    public void setDificultate(Dificultate dificultate) {
        this.dificultate = dificultate;
    }

    public int getNrOreEstimate() {
        return nrOreEstimate;
    }

    public void setNrOreEstimate(int nrOreEstimate) {
        this.nrOreEstimate = nrOreEstimate;
    }

    // Optional: Override toString() for better representation
    @Override
    public String toString() {
        return "Sarcina{" +
                "sarcinaId='" + sarcinaId + '\'' +
                ", dificultate=" + dificultate +
                ", nrOreEstimate=" + nrOreEstimate +
                '}';
    }
}
