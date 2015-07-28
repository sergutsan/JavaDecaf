public class TestJavaMultipleMethods2 {
    public static void main(String[] args) {
        meth1();
        meth2();
        meth3();
    }

    public void meth1() {
        for (int i = 0; i<3; i++){
            System.out.println(i);
        }
    }

    public void meth2() {
        System.out.println("Hello world!");
    }

    public void meth3() {
        int i = 10;
        if (i < 20) {
            i++;
        }
    }


}