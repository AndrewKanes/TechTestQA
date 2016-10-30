import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Generates X random character pairs
 * and analyzes them using the following rules:
 *
 * 1. Each pair should be one number (1-99) and one letter (a-z)
 * 2. Prime numbers should be Y times more likely than other numbers
 * 3. Perfects squares should be one third (1/3)x as likely as prime numbers
 * 4. Vowels (a, e, i, o, u) should be Z times more likely than consonants
 * 5. The letter "y" should be twice (2x) as likely as vowels
 * 6. Each letter must be translated to a number based on the its numerical
 * position in the alphabet.
 * ex: 'f' is the 6th letter of the alphabet, so it would be evaluated as 6.
 * 7. This program should return valid JSON that has the following structure:
 * {
 * "letters": {"wins": 4000, "streak": 17},
 * "numbers": {"wins": 6000, "streak": 6}
 * }
 * 8. Each number/letter value pair should be evaluated. If the letter value is lower,
 * incrememnt the "wins" field on the "letters" object. If the number value is lower,
 * increment the "wins" field on the "numbers" object.
 * 9. Keep track of the longest running "streak" for letters and numbers
 * ex: if "letters" is lower 5 times in a row, the letters streak would be 5
 * 10. This program should be able to take 3 inputs:
 * a. the number of "challenges" (X)
 * b. the likelihood of prime numbers (Y)
 * c. the likelihood of vowels (Z)
 */

public class TechTestQA {
    private final static int MAX_NUMBER = 99;       // Each pair should be one number (1-99)
    private final static ArrayList<Integer> PRIME_NUMBERS = findPrimes(); // Prime numbers should be Y times more likely than other numbers
    private final static ArrayList<Integer> PERFECT_SQUARES = findPerfectSquares(); // Perfects squares should be one third (1/3)x as likely as prime numbers

    /**
     * main method which mostly calls helper methods for logic
     * <p>
     * <p>
     * Q: "Whats the object-oriented way to become wealthy?"
     * A: Inheritance
     */
    public static void main(String[] args) {
        // Prompts for user to enter parameters
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is the number of challeges? "); // he number of "challenges" (X)
        Integer challenges = Integer.valueOf(scanner.next());

        System.out.print("What is the likelihood of primes? "); // he likelihood of prime numbers (Y)
        Integer primeOdds = Integer.valueOf(scanner.next());

        System.out.print("What is the likelihood of vowels? "); // The likelihood of vowels (Z)
        Integer vowelsOdds = Integer.valueOf(scanner.next());

        // Create ArrayLists from which to select character pairs
        final ArrayList<Integer> WEIGHTED_NUMBER_ARRAY = createArrayOfNumbers(primeOdds);
        final ArrayList<Character> WEIGHTED_CHARACTER_ARRAY = createArrayOfLetters(vowelsOdds);

        // Array to hold results of picks
        Integer[][] randomPairs = new Integer[2][challenges];

        // Loop through "challenges" (X) - choosing from weighted ArrayLists
        for (int i = 0; i < challenges; i++) {
            Random rand = new Random();
            // When your hammer is C++, everything begins to look like a thumb.
            int random = rand.nextInt(WEIGHTED_NUMBER_ARRAY.size() - 1);
            Integer randomNumber = WEIGHTED_NUMBER_ARRAY.get(random);

            int randomChar = rand.nextInt(26) + 'a';
            char randomletter = (char) rand.nextInt(WEIGHTED_CHARACTER_ARRAY.size() - 1);

            randomPairs[0][i] = randomNumber;
            randomPairs[1][i] = Integer.valueOf(randomletter);
        }

        // initialize variables which will contain random character pairs
        int lettersWins = 0;
        int numberWins = 0;
        int letterStreak = 0;
        int numberStreak = 0;
        int letterStreakMax = 0;
        int numberStreakMax = 0;

        for (int i = 0; i < randomPairs[0].length; i++) {
            int charValue = randomPairs[1][i];

            if (charValue >= randomPairs[0][i]) {
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
    }

    private static ArrayList createArrayOfNumbers(int primeOdds) {
        ArrayList numbersWithOdds = new ArrayList();

        for (int i = 0; i <= MAX_NUMBER; i++) {
            numbersWithOdds.add(i);
            if (PRIME_NUMBERS.contains(i)) {
                for (int x = 0; x < primeOdds - 1; x++) {
                    numbersWithOdds.add(i);
                }
            }
            if (PERFECT_SQUARES.contains(i)) {
                for (int x = 0; x < (primeOdds - 1) / 3; x++) {
                    numbersWithOdds.add(i);
                }
            }
        }
        return numbersWithOdds;
        // If you put a million monkeys at a million keyboards,
        // one of them will eventually write a Java program.
        // The rest of them will write Perl programs.
    }

    private static ArrayList createArrayOfLetters(int vowelOdds) {
        ArrayList<Character> letterWithOdds = new ArrayList();

        for (Character letter = 'a'; letter <= 'z'; letter++) {
            letterWithOdds.add(letter);
            if (Vowels.isVowel(letter)) {
                for (int x = 0; x < vowelOdds; x++) {
                    letterWithOdds.add(letter);
                }
            }
            if (letter == 'y') {
                for (int x = 0; x < vowelOdds * 2; x++) {
                    letterWithOdds.add(letter);
                }
            }
        }
        return letterWithOdds;
    }

    /**
     * Finds perfect squares less than maxNumber
     *
     * @return ArrayList of perfect squares
     */
    private static ArrayList<Integer> findPerfectSquares() {
        ArrayList<Integer> perfectSquares = new ArrayList();
        System.out.print("Perfect Squares: ");
        for (int i = 1; i * i <= MAX_NUMBER; i++) {
            perfectSquares.add(i * i);
            System.out.print(i * i + " ");
        }
        System.out.println();
        return perfectSquares;
    }

    /**
     * Finds prime numbers less the maxNumber
     * <p>
     * reference:
     * http://beginnersbook.com/2014/01/java-program-to-display-prime-numbers/
     *
     * @return ArrayList of prime numbers
     */
    private static ArrayList<Integer> findPrimes() {
        ArrayList<Integer> primeNumbers = new ArrayList();
        System.out.print("Prime Numbers: ");
        int num = 0;
        for (int i = 1; i <= MAX_NUMBER; i++) {
            int counter = 0;
            for (num = i; num >= 1; num--) {
                if (i % num == 0) {
                    counter = counter + 1;
                }
            }
            if (counter == 2) {
                //Appended the Prime number to the String
                primeNumbers.add(i);
                System.out.print(i + " ");
            }
        }
        System.out.println();
        return primeNumbers;
    }
}
