
import java.util.Scanner;

public class TestElseIf {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int x = readInt();
        if (x% 2 == 0) {
            println("2");
        } else if (x % 3 == 0) {
            println("3");
        } else {
            println("no");
        }
    }
}