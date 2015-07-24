import java.util.Scanner;

public class TestMultiLineMethod {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String name = input.next();
        sayHi();
    }

    private static void sayHi() {
        System.out.println("Hi");
        System.out.println("How are you?");
    }
}