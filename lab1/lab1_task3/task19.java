package lab1_task3;

public class task19 {
    public static void main(String[] args) {
        double a = 1.234;
        double b = -3.12;
        double c = 5.45;
        double d = 2.0;

        double result = Math.pow(Math.tan(a), 1.0 / c) / (1 + (Math.sinh(b) / Math.log(Math.abs(d + c))));
        System.out.println("Task №19 result: " + result);
    }
}