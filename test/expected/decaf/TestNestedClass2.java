
import java.util.Scanner;

public class TestNestedClass2 {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Person p1 = new Person();
        p1.talk();
    }
    static class Person {
        void talk() {
            System.out.println("Hello!");
        }
    }
}