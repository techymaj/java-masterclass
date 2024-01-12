package tech.majaliwa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Challenge {
    public static void main(String[] args) {
        var groceries = createArrayList();
        actions(groceries);
    }

    private static void actions(ArrayList<String> arrayList) {
        System.out.println();
        System.out.println("Available actions:");
        System.out.println("0 - to shutdown");
        System.out.println("1 - to add item(s) to list (comma delimited list)");
        System.out.println("2 - to remove any items (comma delimited list)");
        System.out.println("3 - Show list");
        System.out.println("Enter a number for which action you want to do:");

        readUserInput(arrayList);
    }

    public static ArrayList<String> createArrayList() {
        return new ArrayList<>();
    }

    public static void addToList(ArrayList<String> arrayList) {
        System.out.println("Enter item to add below.");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (!arrayList.contains(s)) {
            arrayList.add(s.toLowerCase());
            actions(arrayList);
        } else {
            System.out.println("The item " + s + " is already on the list");
            arrayList.sort(Comparator.naturalOrder());
            System.out.println("Your list " + arrayList);
            actions(arrayList);
        }
    }

    public static void removeFromList(ArrayList<String> arrayList) {
        System.out.println("Enter item to remove below");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (arrayList.remove(s.toLowerCase())) {
            System.out.println(s + " removed successfully");
        } else {
            System.out.println(s + " does not exist on your list");
        }
        actions(arrayList);
    }

    public static void showList(ArrayList<String> arrayList) {
        arrayList.sort(Comparator.naturalOrder());
        if (!arrayList.isEmpty() && arrayList.size() > 1) {
            System.out.println("You have " + arrayList.size() + " items in your list ");
            System.out.println(arrayList);
        } else if (arrayList.size() == 1) {
            System.out.println("You have " + arrayList.size() + " item in your list ");
            System.out.println(arrayList);
        } else {
            System.out.println("Your list is empty. Mind adding to it?");
        }
        actions(arrayList);
    }

    public static void readUserInput(ArrayList<String> items) {
        int number;
        Scanner scanner = new Scanner(System.in);

        number = scanner.nextInt();

        switch (number) {
            case 0 -> {
                scanner.close();
                items.clear();
                System.exit(0);
            }
            case 1 -> addToList(items);
            case 2 -> removeFromList(items);
            case 3 -> showList(items);
        }
    }
}
