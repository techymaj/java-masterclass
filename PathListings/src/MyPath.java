import java.nio.file.Path;

public class MyPath {
    public static void main(String[] args) {
        Path path = Path.of("this/is/several/folders/testing.txt");
        Path parent = path.getParent();
        System.out.println(parent.getFileName());
    }
}
