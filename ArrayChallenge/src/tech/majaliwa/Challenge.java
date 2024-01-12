package tech.majaliwa;

import java.util.Arrays;
import java.util.Random;

public class Challenge {
    public static void main(String[] args) {
        int[] ages = generateRandomArray(11);
        // before sort
        System.out.println(Arrays.toString(ages));
        // sort
        Arrays.sort(ages);
        System.out.println(Arrays.toString(ages));
        for (int i = 0; i < ages.length / 2; i++) {
            int temp = ages[i];
            ages[i] = ages[ages.length - 1 - i];
            ages[ages.length - 1 - i] = temp;
        }
        // after sort
        System.out.println(Arrays.toString(ages));
    }
    public static int[] generateRandomArray(int length) {
        Random random = new Random();
        int[] newArray = new int[length];
        for (int i = 0; i < length; i++) {
            newArray[i] = random.nextInt(11);
        }
        return newArray;
    }
}
