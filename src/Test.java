public class Test {
    public static void main(String[]args) {
        Testdata test = new Testdata();
        Testrunner run = new Testrunner();
        int [] arr = test.request(39,0,"totalrand");
        try {
            System.out.println(run.exec(arr, 1));
        } catch(UnsortedException e) {
            e.getMessage();
        }
    }
}
