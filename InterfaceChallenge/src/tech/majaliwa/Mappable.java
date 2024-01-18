package tech.majaliwa;

public interface Mappable {
    String JSON_PROPERTY = """
            "properties":{%s}
            """;
    String getLabel(); // how the item will be described on the map
    String getGeometryType(); // what it will look like on the map
    String getIconType(); // map marker. push pin or solid line
    default void toJSON() {
        // print out the type, label, and marker
    }
    static void mappable(Mappable instance) {
        // print out the properties for each mappable type
        // including those mentioned above but also any other fields
        // on the business classes
    }
}
