package model;

public class Entity<ID> {
    private ID id;

    public ID getId(){
        return id;
    }

    public void setId(ID i){
        this.id = i;
    }
}
