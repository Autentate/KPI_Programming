// Переведення усіх символів String у символи нижнього регістру    

package lab5;

public class task11 {
    public static String lowercaseWords(String sentence) {
        if (sentence == null) {
            throw new NullPointerException("Рядок не може бути null");
        }

        char[] chars = sentence.toCharArray();
        boolean inToken = false;
        int tokenStart = 0;

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            boolean isLetterOrDigit = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');

            if (isLetterOrDigit) {
                if (!inToken) {
                    inToken = true;
                    tokenStart = i;
                }
            } else {
                if (inToken) {
                    processToken(chars, tokenStart, i - 1);
                    inToken = false;
                }
            }
        }

        if (inToken) {
            processToken(chars, tokenStart, chars.length - 1);
        }

        return new String(chars);
    }

    private static void processToken(char[] chars, int start, int end) {
        boolean onlyLetters = true;
        for (int i = start; i <= end; i++) {
            char c = chars[i];
            boolean isLetter = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
            if (!isLetter) {
                onlyLetters = false;
                break;
            }
        }

        if (onlyLetters) {
            for (int i = start; i <= end; i++) {
                chars[i] = Character.toLowerCase(chars[i]);
            }
        }
    }

    public static void main(String[] args) {
        String sentence = "The user with the nickname koala757677 this month wrote 3 times more comments than the user with the nickname croco181dile920 4 months ago";
        System.out.println("Початковий рядок:\n" + sentence);

        String result = lowercaseWords(sentence);
        System.out.println("\nРезультат:\n" + result);

        try {
            lowercaseWords(null);
        } catch (NullPointerException e) {
            System.out.println("\nПерехоплено NullPointerException: " + e.getMessage());
        }
    }
}