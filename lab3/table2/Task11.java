import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Task11 {
    public static int[] getFlawless(int[][] results) {
        if (results == null) {
            throw new NullPointerException("Матриця не може бути null");
        }
        int n = results.length;
        if (n == 0) {
            throw new IllegalArgumentException("Матриця результатів не може бути порожньою");
        }
        for (int i = 0; i < n; i++) {
            if (results[i] == null || results[i].length != n) {
                throw new IllegalArgumentException("Матриця результатів має бути квадратною");
            }
        }

        List<Integer> flawlessList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            boolean hasLoss = false;
            for (int j = 0; j < n; j++) {
                if (i != j && results[i][j] == 0) { // 0 — поразка
                    hasLoss = true;
                    break;
                }
            }
            if (!hasLoss) {
                flawlessList.add(i);
            }
        }

        int[] result = new int[flawlessList.size()];
        for (int i = 0; i < flawlessList.size(); i++) {
            result[i] = flawlessList.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== Тестування Завдання 11 (Чемпіонат) ===");

        // Дозволена комбінація
        // Команда 0: 0, 2, 1 (без поразок)
        // Команда 1: 0, 0, 2 (поразка)
        // Команда 2: 1, 1, 0 (без поразок)
        int[][] championship = {
            {0, 2, 1},
            {0, 0, 2},
            {1, 1, 0}
        };
        System.out.println("Команди без поразок: " + Arrays.toString(getFlawless(championship)));

        // Заборонена комбінація
        try {
            getFlawless(null);
        } catch (NullPointerException e) {
            System.out.println("Перехоплено NullPointerException: " + e.getMessage());
        }
    }
}