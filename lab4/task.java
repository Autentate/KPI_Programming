import java.util.Arrays;

// Реалізація двох алгоритмів сортування (BubbleSort та SelectionSort) для масиву дійсних чисел
// у порядку спадання з використанням патерна «Стратегія».

interface SortingStrategy {
    void sort(double[] array);
}

class BubbleSort implements SortingStrategy {
    @Override
    public void sort(double[] array) {
        if (array == null) {
            return;
        }
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    double temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

class SelectionSort implements SortingStrategy {
    @Override
    public void sort(double[] array) {
        if (array == null) {
            return;
        }
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] > array[maxIndex]) {
                    maxIndex = j;
                }
            }
            double temp = array[maxIndex];
            array[maxIndex] = array[i];
            array[i] = temp;
        }
    }
}

public class task {
    private SortingStrategy strategy;

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeSort(double[] array) {
        if (strategy != null) {
            strategy.sort(array);
        }
    }

    public static void main(String[] args) {
        double[] input = { 3.5, 1.2, 5.8, 2.1, 4.4 };
        double[] bubbleResult = input.clone();
        double[] selectionResult = input.clone();

        task sorter = new task();

        System.out.print("Початковий масив: ");
        printArray(input);

        sorter.setStrategy(new BubbleSort());
        System.out.println("=== Тестування Bubble sort (по спаданню) ===");
        sorter.executeSort(bubbleResult);
        System.out.print("Результат: ");
        printArray(bubbleResult);

        sorter.setStrategy(new SelectionSort());
        System.out.println("\n=== Тестування Selection sort (по спаданню) ===");
        sorter.executeSort(selectionResult);
        System.out.print("Результат: ");
        printArray(selectionResult);
        System.out.println("Результати алгоритмів однакові: "
                + Arrays.equals(bubbleResult, selectionResult));
    }

    public static void printArray(double[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}