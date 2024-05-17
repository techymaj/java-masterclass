public class Supplier {
    static int i = 0;

    Supplier() {
        i++;
    }

    public static void main(String[] args) {

        InfiltrateTheDealersFindTheSupplier<Supplier> supplier = Supplier::new;
        System.out.println(supplier.get());

        InfiltrateTheDealersFindTheSupplier<Supplier> supplier2 = Supplier::new;
        System.out.println(supplier2.get());
    }

    @Override
    public String toString() {
        return i + " times";
    }
}

@FunctionalInterface
interface InfiltrateTheDealersFindTheSupplier<T> {

    T get();
}

