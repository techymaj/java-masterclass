package tech.majaliwa;

public class LargestPrime {
    // write code here
    public static void main(String[] args) {
        int x = getLargestPrime(16); // 2
        System.out.println(x);

        x = getLargestPrime(12); // 3
        System.out.println(x);
    }
    public static int getLargestPrime(int number) {

        int factor;
        int largestPrime = 0;
        int countPrime = 0;

        if (number < 0 || number == 1) return -1;

        for (int i = 2; i < number; i++ ) {
            if (number % i == 0) {
                factor = i;
                for (int j = 2; j <= factor; j++) {
                    while (factor % j == 0) {
                        // Check if j is prime
                        boolean isPrime = true;
                        for (int k = 2; k < j; k++) {
                            if (j % k == 0) {
                                isPrime = false;
                                break;
                            }
                        }
                        if (isPrime) {
                            largestPrime = j;
                            countPrime++;
                        }
                        factor /= j;  // Reduce factor by dividing it by the prime factor
                    }
                    if (countPrime == 2) break;
                }

            }
        }

        if (largestPrime == 0) {
            return number;
        }

        return largestPrime;
    }
}