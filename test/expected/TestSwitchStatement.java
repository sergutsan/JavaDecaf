import java.util.Scanner;

public class TestSwitchStatement {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int num = input.nextInt();

        switch (num) {
            case 1:
                println("one");
                break;
            case 2:
                println("two");
                break;
            default:
                break;
        }
    }
}