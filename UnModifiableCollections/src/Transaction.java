//public class Transaction {
//    int routingNumber;
//    int customerID;
//    long transactionID;
//    double transactionAmount;
//}

public record Transaction(int age){

}

class Animal {

}

class Dog extends Animal {
    public static void main(String[] args) {
        Animal dog = new Dog();
        if (dog instanceof Dog) {
            System.out.println(dog);
        }
    }
}

