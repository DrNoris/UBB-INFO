package com.example.seminar8.Domain;

public class Tema {
    private String desc;
    private String id;

    public Tema(String Id, String desc){
        this.desc = desc;
        this.id = Id;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public String getDesc(){
        return this.desc;
    }

    @Override
    public String toString(){
        return desc;
    }
}
