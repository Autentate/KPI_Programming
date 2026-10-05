public class Task40 {
    public static int getDiffMaxAndSecondElement(int[] array) {
        if (array == null) {
            throw new NullPointerException("Масив не може бути null");
        }
        if (array.length < 2) {
            throw new IllegalArgumentException("Масив повинен містити щонайменше 2 елементи");
        }

        int max = Integer.MIN_VALUE;
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }

        int secondElement = array[1]; // другий елемент (індекс 1)
        return max - secondElement;
    }

    public static void main(String[] args) {
        System.out.println("=== Тестування Завдання 40 ===");

        // Дозволені комбінації
        int[] test1 = {10, 5, 20, 3}; // max = 20, другий = 5 -> 20 - 5 = 15
        System.out.println("Тест 1 (Очікується 15): " + getDiffMaxAndSecondElement(test1));

        int[] test2 = {-5, -2, -10}; // max = -2, другий = -2 -> -2 - (-2) = 0
        System.out.println("Тест 2 (Очікується 0): " + getDiffMaxAndSecondElement(test2));

        // Заборонені комбінації
        try {
            getDiffMaxAndSecondElement(new int[]{5});
        } catch (IllegalArgumentException e) {
            System.out.println("Перехоплено IllegalArgumentException: " + e.getMessage());
        }
    }
}