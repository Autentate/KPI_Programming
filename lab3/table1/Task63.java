import java.util.Arrays;

public class Task63 {
    public static int[] transformArray(int[] array) {
        if (array == null) {
            throw new NullPointerException("Масив не може бути null");
        }
        if (array.length == 0) {
            throw new IllegalArgumentException("Масив не може бути порожнім");
        }

        int max = array[0];
        for (int val : array) {
            if (val > max) {
                max = val;
            }
        }

        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                result[i] = array[i] + max;
            } else if (array[i] == 0) {
                result[i] = 1;
            } else {
                result[i] = array[i] * 2;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== Тестування Завдання 63 ===");

        int[] test1 = {-3, 0, 4, -1, 2}; // max = 4 -> [-3+4, 1, 4*2, -1+4, 2*2] = [1, 1, 8, 3, 4]
        System.out.println("Результат: " + Arrays.toString(transformArray(test1)));

        try {
            transformArray(null);
        } catch (NullPointerException e) {
            System.out.println("Перехоплено NullPointerException: " + e.getMessage());
        }
    }
}