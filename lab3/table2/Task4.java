// void transpose(int[][] matrix) {}. Транспонувати квадратну матрицю.

import java.util.Arrays;

public class Task4 {
    public static void transpose(int[][] matrix) {
        if (matrix == null) {
            throw new NullPointerException("Матриця не може бути null");
        }
        int n = matrix.length;
        if (n == 0) {
            throw new IllegalArgumentException("Матриця не може бути порожньою");
        }
        for (int i = 0; i < n; i++) {
            if (matrix[i] == null || matrix[i].length != n) {
                throw new IllegalArgumentException("Матриця повинна бути квадратною");
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Тестування Завдання 4 (Транспонування) ===");

        // Дозволена комбінація
        int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        System.out.println("Початкова матриця: " + Arrays.deepToString(matrix));
        transpose(matrix);
        System.out.println("Транспонована матриця: " + Arrays.deepToString(matrix));

        // Заборонена комбінація (неквадратна матриця)
        try {
            int[][] invalidMatrix = {
                    { 1, 2, 3 },
                    { 4, 5, 6 }
            };
            transpose(invalidMatrix);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехоплено IllegalArgumentException: " + e.getMessage());
        }
    }
}