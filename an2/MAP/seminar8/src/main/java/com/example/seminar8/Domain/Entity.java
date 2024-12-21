package com.example.seminar8.Domain;

public class Entity<ID> {
    private ID id;

    public Entity(){};

    public void setId(ID id){
        this.id = id;
    }

    public ID getId(){
        return id;
    }

}
