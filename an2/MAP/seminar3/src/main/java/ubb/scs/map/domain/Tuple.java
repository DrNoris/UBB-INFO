package ubb.scs.map.domain;

public class Tuple <E1, E2>{
    private E1 e1;
    private E2 e2;

    public Tuple(E1 e1, E2 e2){
        this.e1 = e1;
        this.e2 = e2;
    }

    public E1 getLeft(){
        return e1;
    }

    public E2 getRight(){
        return e2;
    }

    public void setLeft(E1 newE1){
        e1 = newE1;
    }

    public void setRight(E2 newE2){
        e2 = newE2;
    }

    @Override
    public String toString(){
        return " " + e1 + "," + e2;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;

        Tuple newObj = (Tuple) obj;

        return e1.equals(newObj.getLeft()) && e2.equals(newObj.getRight());
    }
}
