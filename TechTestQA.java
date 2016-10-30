import javax.xml.stream.events.Characters;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TechTestQA {
    private final static int MAX_NUMBER = 99;       // Each pair should be one number (1-99)
    private final static ArrayList<Integer> PRIME_NUMBERS = findPrimes(); // Prime numbers should be Y times more likely than other numbers
    private final static ArrayList<Integer> PERFECT_SQUARES = findPerfectSquares(); // Perfects squares should be one third (1/3)x as likely as prime numbers
    private final static ArrayList<Character> VOWELS = vowels(); // Vowels (a, e, i, o, u) should be Z times more likely than consonants

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
        final ArrayList<Character>  WEIGHTED_CHARACTER_ARRAY =  createArrayOfLetters(vowelsOdds);

        // Array to hold results of picks
        Integer[][] randomPairs = new Integer[2][challenges];

        // Loop through "challenges" (X) - choosing from weighted ArrayLists
        for (int i = 0; i < challenges; i++) {
            Random rand = new Random();
            
            int random = rand.nextInt(WEIGHTED_NUMBER_ARRAY.size() - 1);
            Integer randomNumber = WEIGHTED_NUMBER_ARRAY.get(random);

            int randomChar = rand.nextInt(26) + 'a';
            char randomletter = (char) rand.nextInt(WEIGHTED_CHARACTER_ARRAY.size() - 1);

            randomPairs[0][i] = randomNumber;
            randomPairs[1][i] =  Integer.valueOf(randomletter);
        }

        int lettersWins = 0;
        int numberWins = 0;
        int letterStreak = 0;
        int numberStreak = 0;
        int letterStreakMax = 0;
        int numberStreakMax = 0;

        for (int i = 0; i < randomPairs[0].length; i++) {
            int charValue = randomPairs[1][i]; // (int) (randomPairs[1][i]).charAt(0) - 96;

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
    }

    private static ArrayList createArrayOfLetters(int vowelOdds) {
        ArrayList<Character> letterWithOdds = new ArrayList();

        for (Character letter = 'a'; letter <= 'z'; letter++) {
            letterWithOdds.add(letter);
            if (VOWELS.contains(letter)) {
                for (int x = 0; x < vowelOdds ; x++) {
                    letterWithOdds.add(letter);
                }
            }
            if (letter=='y') {
                letterWithOdds.add(letter);
                letterWithOdds.add(letter);
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

    private static ArrayList vowels( ) {
        ArrayList<Character> vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        return vowels;
    }
}
