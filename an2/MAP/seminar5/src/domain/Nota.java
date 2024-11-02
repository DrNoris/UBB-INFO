package domain;

import java.time.LocalDate;

public class Nota {
    private Student student;
    private Tema tema;
    private double value;
    private String profesor;
    private LocalDate data;

    public Nota(Student student, Tema tema, double value,  LocalDate date, String profesor){
        student = student;
        tema = tema;
        value = value;
        profesor = profesor;
        data = date;
    }

    public Student getStudent(){
        return student;
    }

    public double getValue() {
        return value;
    }

    public Double getNota() {
        return value;
    }

    public String getTeacher() {
        return profesor;
    }

    public Tema getTema() {
        return tema;
    }
}
