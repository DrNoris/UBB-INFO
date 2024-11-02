package domain;

public class Student extends Entity<Long> {
    private String nume;
    private int group;

    public Student(String nume, int group) {
        this.nume = nume;
        this.group = group;
    }

    public String getNume() {
        return nume;
    }

    public int getGroup(){
        return group;
    }

    public String getName() {
        return nume;
    }
}