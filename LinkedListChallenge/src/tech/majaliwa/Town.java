package tech.majaliwa;

public class Town {
    private String town;
    private double distanceFromStart;

    public Town(String town, double distanceFromStart) {
        this.town = town;
        this.distanceFromStart = distanceFromStart;
    }

    public Town() {
        this("Sydney", 0);
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public double getDistanceFromStart() {
        return distanceFromStart;
    }

    public void setDistanceFromStart(double distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Town otherTown = (Town) obj;
        boolean towns = (town.equals(otherTown.town));
        boolean distances = (distanceFromStart == otherTown.distanceFromStart);

        return towns && distances;
    }

    @Override
    public int hashCode() {
        return town.hashCode();
    }

    @Override
    public String toString() {
        return town;
    }
}
