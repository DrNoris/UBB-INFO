package model;

public class Cerc {
    private double raza;

    public Cerc(double raza){
        this.raza = raza;
    }

    public double getRaza(){
        return this.raza;
    }

    public void setRaza(double raza){
        this.raza = raza;
    }

    @Override
    public String toString(){
        return String.valueOf(raza);
    }
}
