import java.math.BigInteger;

public class main {
    public static void main(String [] args) {
        Testdata test = new Testdata();
        BigInteger[] requested = test.request(2,3,"totalrand");
        for (int i = 0;i<requested.length;++i) {
            System.out.println(requested[i]);
            //dfgkfdghk
        }
    }
}
