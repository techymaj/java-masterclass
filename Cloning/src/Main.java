class Person {
    private String name;
    private final Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    // New getter to recursively copy the referenced object
    public Address getAddress() {
        return address;
    }

    public static void main(String[] args) {
        Person person1 = new Person("Alice", new Address("Kanjokya St"));
        Person person2 = new Person(person1.getName(), new Address(person1.getAddress().getStreet()));
        person1.name = "Biba"; // Modification does not affect the cloned object
        System.out.println(person1.name);
        System.out.println(person2.address.getStreet());
        person1.address.setStreet("Bukoto St");
        System.out.println(person1.address.getStreet());// modification does not affect the cloned object
        System.out.println(person2.name);
        System.out.println(person2.address.getStreet());
    }
}

class Address {
    private String street;

    public Address(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
