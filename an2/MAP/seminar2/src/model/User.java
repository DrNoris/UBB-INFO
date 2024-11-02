package model;

public class User extends Entity<Long> {
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return "Nume: " + firstName + " " + lastName;
    }
}
