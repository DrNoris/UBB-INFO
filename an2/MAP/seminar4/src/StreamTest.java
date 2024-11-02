import java.sql.ClientInfoStatus;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamTest {
    public static <E> List<E> filterGeneric(List<E> list, Predicate<E> p){
        return list.stream().filter(p).collect(Collectors.toList());
    }

    public static <E> List<E> filterGeneric1(List<E> lista, Predicate<E> p, Comparator<E> comp){
        return lista.stream().filter(p).sorted(comp).collect(Collectors.toList());
    }

    public static void main(String[] args){
        List<Integer> lista = Arrays.asList(1, 7, 9, 11, 20, 8, 5, 4);

        List<Integer> rezult1 = filterGeneric(lista, x -> x % 2 == 0);
        System.out.println(rezult1);

        List<Integer> rezult2 = filterGeneric1(lista, x -> x % 2 == 0, Integer::compare);
        System.out.println(rezult2);

        List<Integer> rezult3 = filterGeneric1(lista, x -> x % 2 == 0, (x, y) -> x > y);
        System.out.println(rezult3);
    }
}
