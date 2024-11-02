package model;

import java.util.Objects;

public class Student implements Comparable<Student> {
    private String nume;
    private float medie;

    public Student(String n, float m){
        this.medie = m;
        this.nume = n;
    }

    @Override
    public String toString(){
        return "Student [nume: " + this.nume + " medie: " + this.medie + "]";
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this) {
            return true;
        }
        else if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }

        Student temp = (Student) obj;
        return Float.compare(medie, temp.medie) == 0 && nume.equals(temp.nume);
    }

    @Override
    public int hashCode(){
        return Objects.hash(nume, medie);
    }

    @Override
    public int compareTo(Student o){
        return nume.compareTo(o.nume);
    }

    public String getNume(){
        return this.nume;
    }

    public String getName(){
        return this.nume;
    }

    public float getMedie() {
        return this.medie;
    }
}
