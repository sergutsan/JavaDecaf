
import java.util.Scanner;

public class TestNestedClass {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Person p = new Person("Fred");
    }

    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }
    }
}