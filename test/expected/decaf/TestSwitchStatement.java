
import java.util.Scanner;

public class TestSwitchStatement {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int num = input.nextInt();
        switch (num) {
            case 1:
                System.out.println("one");
                break;
            case 2:
                System.out.println("two");
                break;
            default:
                break;
        }
    }
}