
import java.util.Scanner;

public class TestParensInIf {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int x = 10;
        if (x % 2 == 0 && x * 2 < 100) {
            System.out.println("Hello world!");
        }
    }
}
