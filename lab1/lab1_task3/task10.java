package lab1_task3;

import java.lang.Math;

public class task10 {
    public static void main(String[] args) {
        double a = 1.27;
        double b = 10.99;
        double c = 2.73;
        double d = 25.32;

        double result = (Math.pow(a, b) / Math.sinh(Math.abs(b))) + 4 * (Math.log(c) / Math.pow(d, 0.25));
        System.out.println("Task №10 result: " + result);
    }
}