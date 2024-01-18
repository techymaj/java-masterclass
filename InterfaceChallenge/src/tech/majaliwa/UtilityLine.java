package tech.majaliwa;

public class UtilityLine implements Mappable {
    String line;
    String name;
    String utility;

    public UtilityLine(String line, String name, String utility) {
        this.line = line;
        this.name = name;
        this.utility = utility;
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
}
