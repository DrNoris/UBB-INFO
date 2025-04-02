package org.example.practic.Domain;

public class Driver {
    private int id;
    private String name;

    // Constructor
    public Driver(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Default constructor
    public Driver() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
