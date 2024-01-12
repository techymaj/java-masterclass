class AgeException extends Exception {
    public AgeException(String message) {
        super(message);
    }
}
class User {
    private String name;
    private int age;
    public User(String name, int age) throws AgeException {
        if (age < 0) {
            throw new AgeException("Age cannot be negative");
        }
        this.name = name;
        this.age = age;
    }
    public static void main(String[] args) {
        try {
            User user1 = new User("John", 25);
            User user2 = new User("Alice", -5); // This will throw an AgeException
            } catch (AgeException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}