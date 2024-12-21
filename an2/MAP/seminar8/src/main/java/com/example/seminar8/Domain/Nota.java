package com.example.seminar8.Domain;

import java.rmi.StubNotFoundException;
import java.time.LocalDate;

public class Nota {
    private Student student;
    private Tema tema;
    private double value;
    private LocalDate date;
    private String profesor;

    public Nota(Student student, Tema tema, double value, LocalDate date, String profesor){
        this.student = student;
        this.tema = tema;
        this.date = date;
        this.value = value;
        this.profesor = profesor;
    }

    public String getProfesor(){
        return profesor;
    }

    public Student getStudent(){
        return student;
    }

    public double getValue(){
        return value;
    }

    public void setValue(double value){
        this.value = value;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public Tema getTema(){
        return tema;
    }

    public void setTema(Tema tema){
        this.tema = tema;
    }

    public void setProfesor(String profesor){
        this.profesor = profesor;
    }

    @Override
    public String toString(){
        return "Nota: " + value + " | " + tema + " | " + student + " | " + profesor;
    }
}
