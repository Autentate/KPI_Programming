// Перетворення String у число у шістнадцятковій системі числення, у значенні int

package lab5;

public class task4 {
    public static int hexStringToInt(String s) {
        if (s == null) {
            throw new NullPointerException("Рядок не може бути null");
        }
        if (s.isEmpty()) {
            throw new IllegalArgumentException("Рядок не може бути порожнім");
        }

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int digit;

            if (c >= '0' && c <= '9') {
                digit = c - '0';
            } else if (c >= 'A' && c <= 'F') {
                digit = c - 'A' + 10;
            } else if (c >= 'a' && c <= 'f') {
                digit = c - 'a' + 10;
            } else {
                throw new IllegalArgumentException("Неприпустимий символ у шістнадцятковому рядку: " + c);
            }

            result = result * 16 + digit;
        }
        return result;
    }

    public static void main(String[] args) {
        // Дозволені комбінації
        String test1 = "CAFE";
        System.out.println("Аргумент: \"" + test1 + "\" -> Результат: " + hexStringToInt(test1)); // Очікується 51966

        String test2 = "1a3F";
        System.out.println("Аргумент: \"" + test2 + "\" -> Результат: " + hexStringToInt(test2)); // Очікується 6719

        // Заборонені комбінації
        try {
            hexStringToInt("G123");
        } catch (IllegalArgumentException e) {
            System.out.println("Перехоплено IllegalArgumentException: " + e.getMessage());
        }

        try {
            hexStringToInt(null);
        } catch (NullPointerException e) {
            System.out.println("Перехоплено NullPointerException: " + e.getMessage());
        }
    }
}