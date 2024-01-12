package tech.majaliwa;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class LinkedListChallenge {


    public static void main(String[] args) {
        var townsToVisit = createTownsToVisit(); // create list with default town

        addTown(new Town("Adelaide", 1374), townsToVisit);
        addTown(new Town("Alice Springs", 2771), townsToVisit);
        addTown(new Town("Brisbane", 917), townsToVisit);
        addTown(new Town("Darwin", 3972), townsToVisit);
        addTown(new Town("Melbourne", 877), townsToVisit);
//        addTown(new Town("Brisbane", 917), townsToVisit);
        addTown(new Town("Perth", 3923), townsToVisit);
//        addTown(new Town("Darwin", 3972), townsToVisit);

        // after adding the towns, then iterate
        ListIterator<Town> seek = townsToVisit.listIterator();

        actions(townsToVisit, seek);
    }

    public static void actions(LinkedList<Town> townsList, ListIterator<Town> seek) {
        String actions = """
                Available actions (select word or letter):
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit""";
        System.out.println(actions);
        readUserInput(townsList, seek);
    }

    public static LinkedList<Town> createTownsToVisit() {
        var town = new Town();
        var townsToVisit = new LinkedList<Town>();
        townsToVisit.add(town);

        return townsToVisit;
    }

    public static void addTown(Town town, LinkedList<Town> towns) {
        for (Town value : towns) {
            if (value.equals(town)) {
                System.out.println("*".repeat(4) + town.getTown() + " already exists" + "*".repeat(4));
                return;
            }
        }

        addToSortedList(town, towns);
    }

    public static void addToSortedList(Town town, LinkedList<Town> towns) {
        double incomingTownDistance = town.getDistanceFromStart();

        var listIterator = towns.listIterator();

        while (listIterator.hasNext()) {
            Town currentTown = listIterator.next();
            double currentTownDistance = currentTown.getDistanceFromStart();

            if (currentTownDistance > incomingTownDistance) {
                // if currentTownDistance is greater,
                // go to previous node and insert new town there
                listIterator.previous();
                listIterator.add(town);
                return;
            }
        }
        towns.addLast(town);
    }

    public static void moveForward(LinkedList<Town> townsList, ListIterator<Town> seek) {

        if (townsList.isEmpty()) {
            System.out.println("There are no towns to move to");
        }

        if (seek.hasNext()) {
            var nextTown = seek.next();
            System.out.println("Current town is " + nextTown);
        } else {
            System.out.println("You are at the end");
        }

        displayList(townsList,seek);
        actions(townsList, seek);
    }

    public static void moveBackwards(LinkedList<Town> townsList, ListIterator<Town> seek) {

        if (townsList.isEmpty()) {
            System.out.println("There are no towns to move to");
        }

        if (seek.hasPrevious()) {
            var previousTown = seek.previous();
            System.out.println("Current town is " + previousTown);
        } else {
            System.out.println("You are at the beginning");
        }

        displayList(townsList,seek);
        actions(townsList, seek);
    }

    public static void displayList(LinkedList<Town> towns, ListIterator<Town> seek) {
        System.out.println(towns);
        actions(towns, seek);
    }

    public static void quitProgram(LinkedList<Town> list) {
        list.clear();
        System.exit(0);
    }

    public static void readUserInput(LinkedList<Town> townsList, ListIterator<Town> seek) {
        Scanner scanner = new Scanner(System.in);
        char s = scanner.nextLine().trim().toLowerCase().charAt(0);
        Town origins;
        Town finalDestination;

        switch (s) {
            case 'f' -> {
                if (!seek.hasPrevious()) {
                    origins = seek.next(); // origin
                    System.out.println("Origins: " + origins);
                }

                moveForward(townsList, seek);
            }
            case 'b' -> {
                if (!seek.hasNext()) {
                    finalDestination = seek.previous(); // final
                    System.out.println("Final destination: " + finalDestination);
                }

                moveBackwards(townsList, seek);
            }
            case 'l' -> displayList(townsList, seek);
            case 'q' -> quitProgram(townsList);
            default -> actions(townsList, seek);
        }
    }

}
