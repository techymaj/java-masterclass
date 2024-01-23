package tech.majaliwa;

public class Building implements Mappable {
    String point;
    String name;
    String usage;

    public Building(String point, String name, String usage) {
        this.point = point;
        this.name = name;
        this.usage = usage;
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public String getGeometryType() {
        return null;
    }

    @Override
    public String getIconType() {
        return null;
    }

    @Override
    public String toString() {
        return "Building{" +
                "point='" + point + '\'' +
                ", name='" + name + '\'' +
                ", usage='" + usage + '\'' +
                '}';
    }
}
