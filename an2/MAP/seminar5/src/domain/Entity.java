package domain;

public class Entity<Id> {
    private Id id;

    public Id getId(){
        return id;
    }

    public void setId(Id id){
        id = id;
    }
}
