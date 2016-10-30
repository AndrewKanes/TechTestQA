/**
 * enum of vowels. Reference:
 * http://stackoverflow.com/questions/20203378/constant-expression-required-when-using-an-array-with-a-switch-statement
 */
public enum Vowels {
    A,E,I,O,U;
    static boolean isVowel(char c) {
        switch (c) {
            case 'A': case 'a':
            case 'E': case 'e':
            case 'I': case 'i':
            case 'O': case 'o':
            case 'U': case 'u': return true;
        }
        return false;
    }
    static Vowels getVowel(char c) {
        switch (c) {
            case 'A': case 'a': return A;
            case 'E': case 'e': return E;
            case 'I': case 'i': return I;
            case 'O': case 'o': return O;
            case 'U': case 'u': return U;
        }
        return null;
    }
}