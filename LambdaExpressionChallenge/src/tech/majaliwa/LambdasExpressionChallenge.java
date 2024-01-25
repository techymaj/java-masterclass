package tech.majaliwa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LambdasExpressionChallenge {

    public static void main(String[] args) {

        String[] names = {"wilfried", "Maria", "BrIan", "AnNa", "bOB"};
        // namesListed is backed by an Array (operates as an array underneath)
        List<String> namesListed = Arrays.asList(names);
        namesListed.replaceAll(s -> s.toUpperCase());
        System.out.println("-------------toUpperCase---------------");
        // the underlying names array is modified
        System.out.println(Arrays.toString(names));
        System.out.println();
        namesListed.replaceAll(s -> {
            Random randomInitial = new Random();
            return s + " " + (char) randomInitial.nextInt(65, 91) + ".";
        });

        System.out.println("-------------addMiddleName + period---------------");
        namesListed.forEach(name -> System.out.println(name));
        System.out.println();
        namesListed.replaceAll(s -> {
            var namesSplit = s.split(" ");
            var firstName = namesSplit[0];
            // TODO: use StringBuilder to reverse the first name
            var lastNameReversed = firstName.split("");
            for (int i = 0; i < lastNameReversed.length / 2; i++) {
                var temp = "";
                temp = lastNameReversed[i];
                lastNameReversed[i] = lastNameReversed[lastNameReversed.length - 1 - i];
                lastNameReversed[lastNameReversed.length - 1 - i] = temp;
            }
            var lName = String.join("", lastNameReversed);
            return s + " " + lName;
        });

        System.out.println("-------------addLastName as FirstName Reversed---------------");
        namesListed.forEach(name -> System.out.println(name));
        System.out.println();

        System.out.println("-------------removeIf lname = fname---------------");
        var newModifiableList = new ArrayList<>(namesListed);
        newModifiableList.removeIf(s -> {
            var fullName = s.split(" ");
            var lname = fullName[2];
            // remove s if lname = fname
            return lname.equals(fullName[0]);
        });
        newModifiableList.forEach(name -> System.out.println(name));
    }
}
