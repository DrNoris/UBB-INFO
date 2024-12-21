package com.example.seminar8.Domain;

public class Student extends Entity<Integer>{
    private String name;
    private int group;

    public Student(String name, int group){
        this.name = name;
        this.group = group;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGroup(int group){
        this.group = group;
    }

    public String getName(){
        return this.name;
    }

    public int getGroup(){
        return this.group;
    }

    @Override
    public String toString(){
        return "Stundet: " + this.name + " | " + this.group;
    }
}
