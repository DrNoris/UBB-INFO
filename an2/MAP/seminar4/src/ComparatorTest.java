import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {
    public static void main(String[] args){
        Comparator<Integer> comparator = (a, b) -> Integer.compare(b, a);
        Comparator<Integer> comparator1 = Integer::compare;

        List<Integer> integerList = new ArrayList<>();

        integerList.add(1);
        integerList.add(7);
        integerList.add(2);
        integerList.add(5);
        integerList.add(3);

        integerList.sort(comparator);
        System.out.println(integerList);
    }
}
