
import java.util.Scanner;

public class TestComplexIf1 {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "World";
        if (isTrue() && s1.equals(s2)) {
            System.out.println("Hello world!");
        }
    }

    private static boolean isTrue(){
        return true;
    }
}
