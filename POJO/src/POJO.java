import java.util.Arrays;

public class POJO {
    private String name;
    private String hairColor;
    private int eyes;
    private String eyeColor;
    private int limbs;
    private int teeth;
    private double height;
    private int age;
    private String[] languages;

    public POJO(String name, String hairColor, int eyes, String eyeColor, int limbs, int teeth, double height, int age, String[] languages) {
        this.name = name;
        this.hairColor = hairColor;
        this.eyes = eyes;
        this.eyeColor = eyeColor;
        this.limbs = limbs;
        this.teeth = teeth;
        this.height = height;
        this.age = age;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public int getEyes() {
        return eyes;
    }

    public void setEyes(int eyes) {
        this.eyes = eyes;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public int getLimbs() {
        return limbs;
    }

    public void setLimbs(int limbs) {
        this.limbs = limbs;
    }

    public int getTeeth() {
        return teeth;
    }

    public void setTeeth(int teeth) {
        this.teeth = teeth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "POJO{" +
                "name='" + name + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", eyes=" + eyes +
                ", eyeColor='" + eyeColor + '\'' +
                ", limbs=" + limbs +
                ", teeth=" + teeth +
                ", height=" + height +
                ", age=" + age +
                ", languages=" + Arrays.toString(languages) +
                '}';
    }
}
