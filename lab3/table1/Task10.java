// Знайти середнє арифметичне елементів масиву

public class Task10 {
    public static double calculateAverage(double[] array) {
        if (array == null) {
            throw new NullPointerException("Масив не може бути null");
        }
        if (array.length == 0) {
            throw new IllegalArgumentException("Масив не може бути порожнім");
        }

        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum / array.length;
    }

    public static void main(String[] args) {
        System.out.println("=== Тестування Завдання 10 ===");

        // Дозволені комбінації
        double[] test1 = {1.5, 2.5, 3.0, 5.0};
        System.out.println("Тест 1 (Очікується 3.0): " + calculateAverage(test1));

        double[] test2 = {-10, 10, -5, 5};
        System.out.println("Тест 2 (Очікується 0.0): " + calculateAverage(test2));

        // Заборонені комбінації (перевірка виключень)
        try {
            calculateAverage(null);
        } catch (NullPointerException e) {
            System.out.println("Перехоплено NullPointerException: " + e.getMessage());
        }

        try {
            calculateAverage(new double[]{});
        } catch (IllegalArgumentException e) {
            System.out.println("Перехоплено IllegalArgumentException: " + e.getMessage());
        }
    }
}