import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.Arrays.binarySearch;

public class TechTestQA {
    final static int MAX_NUMBER = 99;
    final static int PRIME_NUMBERS = (MAX_NUMBER / 2) + (MAX_NUMBER % 2);
    //final static int PERFECT_SQUARES_COUNT = 9; //hate to hardcode this but the formula is a pain http://primes.utm.edu/howmany.html#pnt
    final static int[] PERFECT_SQUARES = new int[]{1, 4, 9, 16, 25, 36, 49, 84, 81};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is the number of challeges? ");
        Integer challenges = Integer.valueOf(scanner.next());

        System.out.print("What is the likelihood of primes? ");
        Integer primeOdds = Integer.valueOf(scanner.next());

        System.out.println("What is the likelihood of vowels? ");
        //Integer vowelsOdds = Integer.valueOf(scanner.next());

        String[][] randomPairs = new String[2][challenges];

        for (int i = 0; i < challenges; i++) {
            Random rand = new Random();
            int randomNumber = rand.nextInt(100);
            char randomChar = (char) (rand.nextInt(26) + 'a');

            randomPairs[0][i] = String.valueOf(randomNumber);
            randomPairs[1][i] = String.valueOf(randomChar);

            System.out.println(String.format("Random number: %d Random letter: %s", randomNumber, randomChar));
        }

        int lettersWins = 0;
        int numberWins = 0;
        int letterStreak = 0;
        int numberStreak = 0;
        int letterStreakMax = 0;
        int numberStreakMax = 0;


        for (int i = 0; i < randomPairs[0].length; i++) {
            //System.out.println(String.format("Random number: %s Random letter: %s", randomPairs[0][i], randomPairs[1][i]));
            int charValue = (int) (randomPairs[1][i]).charAt(0) - 96;

            //System.out.println(String.format("Number: %d Letter: %d  ", Integer.parseInt(randomPairs[0][i]), charValue));
            System.out.println(String.format("Letter: %s LetterValue: %d  ", randomPairs[1][i], charValue));

            if (charValue > Integer.parseInt(randomPairs[0][i])) {
                lettersWins++;
                letterStreak++;
                numberStreak = 0;
                if (letterStreak > letterStreakMax) {
                    letterStreakMax = letterStreak;
                }
            } else {
                numberWins++;
                numberStreak++;
                letterStreak = 0;
                if (numberWins > numberStreakMax) {
                    numberStreakMax = numberStreak;
                }
            }
        }

        System.out.println(String.format("Letter Wins: %d", lettersWins));
        System.out.println(String.format("Letter Streak: %d", letterStreakMax));
        System.out.println(String.format("Number Wins: %d", numberWins));
        System.out.println(String.format("Number Streak: %d", numberStreakMax));

        // Build array of numbers weighted by
        // Prime numbers (50 of them) should be primeOdds times more likely than other numbers
        // Perfects squares (9 of them) should be one third (1/3)x as likely as prime numbers
        // Total number of "numbers" is 99 + (50 * (primeOdds-1)) + (9 * ((primeOdds-1) * 1/3))

        System.out.println("Total numbers: " + MAX_NUMBER);
        System.out.println("Total prime numbers: " + PRIME_NUMBERS);

        System.out.println("primeOdds: " + primeOdds);

        int primeNumberWithOdds = PRIME_NUMBERS * (primeOdds - 1);
        int squaresWithOdds = PRIME_NUMBERS * ((primeOdds - 1) * (1 / 3));

        System.out.println("Adjusted prime numbers: " + primeNumberWithOdds);
        System.out.println("Adjusted squares: " + squaresWithOdds);

        int numberSetTotal = MAX_NUMBER + primeNumberWithOdds + squaresWithOdds - 1;

        System.out.println("Total  : " + numberSetTotal);

        int numbersWithOdds[] = new int[numberSetTotal + 1];
        int numberNode = 0;

        for (int i = 0; i <= MAX_NUMBER; i++) {
            //System.out.println("i: " + i + " numberNode: " + numberNode);
            numbersWithOdds[numberNode] = i;
            numberNode++;
            if (i % 2 == 0 && i != 0) {
                for (int x = 0; x < primeOdds - 1; x++) {
                    numbersWithOdds[numberNode] = i;
                    numberNode++;
                }
            }
            //System.out.println("bsearch: " + binarySearch(PERFECT_SQUARES, i));
            if (binarySearch(PERFECT_SQUARES, i) >= 0) {
                System.out.println("primeOdds * 4 / 3 - 1: " + (primeOdds * 4 / 3 - 1));
                for (int x = 0; x < (primeOdds - 1) / 3; x++) {
                    numbersWithOdds[numberNode] = i;
                    numberNode++;
                }
            }
            System.out.println("numberNode: " + numberNode + " i: " + i);
        }
        int x = 0;
        for (Object numbersWithOdd : numbersWithOdds) {
            System.out.println(x + ": " + numbersWithOdd);
            x++;
        }
    }

    private ArrayList createArrayOfNumbers(int primeOdds) {
        ArrayList numbersWithOdds = new ArrayList();

        for (int i = 0; i <= MAX_NUMBER; i++) {
            //System.out.println("i: " + i + " numberNode: " + numberNode);
            numbersWithOdds.add(i);
            if (binarySearch(PERFECT_SQUARES, i) >= 0) {
                for (int x = 0; x < primeOdds - 1; x++) {
                    numbersWithOdds.add(i);
                }
            }
            //System.out.println("bsearch: " + binarySearch(PERFECT_SQUARES, i));
            if (binarySearch(PERFECT_SQUARES, i) >= 0) {
                System.out.println("primeOdds * 4 / 3 - 1: " + (primeOdds * 4 / 3 - 1));
                for (int x = 0; x < (primeOdds - 1) / 3; x++) {
                    numbersWithOdds.add(i);
                }
            }
        }
        int x = 0;
        for (Object numbersWithOdd : numbersWithOdds) {
            System.out.println(x + ": " + numbersWithOdd);
            x++;
        }
        return numbersWithOdds;
    }


    /**
     * Finds prime numbers less the maxNumber
     * <p>
     * reference:
     * http://beginnersbook.com/2014/01/java-program-to-display-prime-numbers/
     *
     * @param maxNumber the upper limit for prime number search
     * @return ArrayList of primenumbers
     */
    private ArrayList findPrimes(int maxNumber) {
        ArrayList<> primeNumbers = new ArrayList; int num = 0;
        for (int i = 1; i <= 100; i++) {
            int counter = 0;
            for (num = i; num >= 1; num--) {
                if (i % num == 0) {
                    counter = counter + 1;
                }
            }
            if (counter == 2) {
                //Appended the Prime number to the String
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
}
