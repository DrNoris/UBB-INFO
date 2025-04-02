package com.example.seminar14.Domain;

public class Angajat {
    private String angajatId;
    private String nume;
    private double venitPeOra;
    private Senioritate senioritate;


    public Angajat(String angajatId, String nume, double venitPeOra, Senioritate senioritate) {
        this.angajatId = angajatId;
        this.nume = nume;
        this.venitPeOra = venitPeOra;
        this.senioritate = senioritate;
    }

    public String getAngajatId() {
        return angajatId;
    }

    public void setAngajatId(String angajatId) {
        this.angajatId = angajatId;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getVenitPeOra() {
        return venitPeOra;
    }

    public void setVenitPeOra(double venitPeOra) {
        this.venitPeOra = venitPeOra;
    }

    public Senioritate getSenioritate() {
        return senioritate;
    }

    public void setSenioritate(Senioritate senioritate) {
        this.senioritate = senioritate;
    }
}

