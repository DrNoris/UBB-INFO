import model.Arie;
import model.Cerc;
import model.Patrat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionalInterfaceTest {
    public static <E> void printArie(List<E> list, Arie<E> f){
        list.forEach(x -> System.out.println(f.calculeaza(x)));
    }

    public static void main(String args[]){
        Arie<Cerc> a1 = x -> Math.PI * Math.pow(x.getRaza(), 2);

        Cerc c1 = new Cerc(3);
        Cerc c2 = new Cerc(4);
        Cerc c3 = new Cerc(5.43);

        List<Cerc> listaCercuri = Arrays.asList(c1, c2, c3);

        printArie(listaCercuri, a1);

        Arie<Patrat> a2 = x -> Math.pow(x.getLatura(), 2);

        Patrat p1 = new Patrat(5);
        Patrat p2 = new Patrat(6);
        Patrat p3 = new Patrat(7.89);

        List<Patrat> listPatrat = Arrays.asList(p1, p2, p3);

        printArie(listPatrat, a2);
    }
}
