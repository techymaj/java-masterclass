package tech.majaliwa;

public class Name {
    private String type;

    public Name(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Name{" +
                "type='" + type + '\'' +
                '}';
    }
}
