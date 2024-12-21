package com.example.seminar8.Service;

import com.example.seminar8.Domain.Student;
import com.example.seminar8.Domain.Nota;
import com.example.seminar8.Domain.Tema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceManager {

    public List<Student> findAllStudents() {
        List<Student> students = new ArrayList<>();

        // Creating 5 Romanian students
        students.add(new Student("Andrei", 101));
        students.add(new Student("Ioana", 102));
        students.add(new Student("Mihai", 103));
        students.add(new Student("Elena", 104));
        students.add(new Student("Gabriel", 105));

        return students;
    }

    public List<Nota> findAllGrades(List<Student> students, List<Tema> temas) {
        List<Nota> grades = new ArrayList<>();

        grades.add(new Nota(students.get(0), temas.get(0), 9.5, LocalDate.of(2024, 5, 1), "Prof. Popescu"));
        grades.add(new Nota(students.get(1), temas.get(1), 8.0, LocalDate.of(2024, 5, 3), "Prof. Ionescu"));
        grades.add(new Nota(students.get(2), temas.get(2), 7.5, LocalDate.of(2024, 5, 5), "Prof. Vasilescu"));
        grades.add(new Nota(students.get(3), temas.get(0), 10.0, LocalDate.of(2024, 5, 7), "Prof. Popescu"));
        grades.add(new Nota(students.get(4), temas.get(1), 6.5, LocalDate.of(2024, 5, 10), "Prof. Ionescu"));

        return grades;
    }

    public List<Tema> findAllHomeworks() {
        List<Tema> homeworks = new ArrayList<>();

        homeworks.add(new Tema("T1", "Mathematics Assignment"));
        homeworks.add(new Tema("T2", "Computer Science Assignment"));
        homeworks.add(new Tema("T3", "Physics Assignment"));
        homeworks.add(new Tema("T4", "Chemistry Assignment"));
        homeworks.add(new Tema("T5", "History Assignment"));

        return homeworks;
    }
}
