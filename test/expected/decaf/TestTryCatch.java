import java.util.Scanner;

public class TestTryCatch {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            println("Hello world!");
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }
}