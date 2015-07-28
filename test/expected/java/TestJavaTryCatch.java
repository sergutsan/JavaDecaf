public class TestJavaTryCatch {

    public static void main(String[] args) {
        try {
            println("Hello world!");
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }
}