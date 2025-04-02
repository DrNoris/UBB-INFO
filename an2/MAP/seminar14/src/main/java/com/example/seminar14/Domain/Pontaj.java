package com.example.seminar14.Domain;

import java.time.LocalDateTime;

public class Pontaj {
    private Angajat angajat;
    private Sarcina sarcina;
    private LocalDateTime data;


    public Pontaj(Angajat angajat, Sarcina sarcina, LocalDateTime data) {
        this.angajat = angajat;
        this.sarcina = sarcina;
        this.data = data;
    }

    public Angajat getAngajat() {
        return angajat;
    }

    public void setAngajat(Angajat angajat) {
        this.angajat = angajat;
    }

    public Sarcina getSarcina() {
        return sarcina;
    }

    public void setSarcina(Sarcina sarcina) {
        this.sarcina = sarcina;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Pontaj{" +
                "angajat=" + angajat.getNume() +
                ", sarcina=" + sarcina.getSarcinaId() +
                ", data=" + data +
                '}';
    }
}
