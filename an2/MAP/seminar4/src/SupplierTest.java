import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SupplierTest {
    public static void main(String[] args){
        Supplier<List> listSupplier = () -> new ArrayList();

        Supplier<List> listSupplier1 = ArrayList::new;

        Supplier<LocalDate> dateSupplier = LocalDate::now;
        Supplier<LocalDate> dateSupplier1 = () -> LocalDate.now();
    }
}
