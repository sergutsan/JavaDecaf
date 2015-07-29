
import java.util.Scanner;

public class TestElseIf {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int x = input.nextInt();
        if (x% 2 == 0) {
            System.out.println("2");
        } else if (x % 3 == 0) {
            System.out.println("3");
        } else {
            System.out.println("no");
        }
    }
}