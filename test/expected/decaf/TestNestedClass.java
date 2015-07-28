import java.util.Scanner;

public class TestNestedClass {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Person p = new Person("Fred");
    }

    class Person {
        private String name;
§
        Person(String name) {
            this.name = name;
        }
    }
}