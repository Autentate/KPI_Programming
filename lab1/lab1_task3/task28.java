package lab1_task3;

public class task28 {
    public static void main(String[] args) {
        double a = 1.478;
        double b = 9.26;
        double c = 0.68;
        double d = 2.24;

        double result = 2 * (Math.log(Math.abs(b / a)) + Math.sqrt(Math.sinh(c) + Math.exp(d)));
        System.out.println("Task №28 result: " + result);
    }
}