import java.util.Scanner;

public class TestTryCatch {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.println("Hello world!");
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }
}