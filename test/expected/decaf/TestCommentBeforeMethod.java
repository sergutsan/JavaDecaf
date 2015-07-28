import java.util.Scanner;

public class TestCommentBeforeMethod {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        meth();
    }

    /**
     * Comment
     */
    private static void meth() {
        return;
    }

    /**
     * Comment
     */
    private static void meth2() {
        return;
    }

}
