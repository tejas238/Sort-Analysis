import java.math.BigInteger;
import java.util.Vector;

public class Testdata {
    private Vector<Vector<Vector<BigInteger[]>>>  alldata;

    public Testdata() {
        alldata = new Vector<Vector<Vector<BigInteger[]>>>(12);
    }

    public BigInteger[] request(int base2size, int indexforsize, String type) {

        if (base2size < 0 || indexforsize < 0) throw new RuntimeException;

        int lookup;
        if (type.equals("totalrand")) lookup = 0;
        else if (type.equals("totaldup")) lookup = 1;
        else if (type.equals("almostsortdup")) lookup = 2;
        else if (type.equals("sortdup")) lookup = 3;
        else if (type.equals("revsortdup")) lookup = 4;
        else if (type.equals("almostrevsortdup")) lookup = 5;
        else if (type.equals("randup")) lookup = 6;
        else if (type.equals("almostsortuniq")) lookup = 7;
        else if (type.equals("sortuniq")) lookup = 8;
        else if (type.equals("revsortuniq")) lookup = 9;
        else if (type.equals("almostrevsortuniq")) lookup = 10;
        else if (type.equals("randuniq")) lookup = 11;
        else throw new RuntimeException;

        Vector<Vector<BigInteger[]>> typeref = alldata.elementAt(lookup);

        boolean correctsize = false;

        while (correctsize == false) {
            try {
                Vector<BigInteger[]> sizeref = typeref.elementAt(base2size);
                correctsize = true;

                boolean correctindex = false;
                while (correctindex == false) {
                    try {
                        BigInteger[] requested = sizeref.elementAt(indexforsize);
                        correctindex = true;

                        return requested;
                    } finally {
                        sizeref.add(create(lookup,base2size));
                    }
                }
            } finally {
                typeref.add(new Vector<BigInteger[]>());
            }
        }
    }

    private BigInteger [] create(int type,int base2size) {
        if (type == 0) return totalrand(base2size);
        else if (type == 1) return totaldup(base2size);
        else if (type == 2) return almostsortdup(0,base2size);
        else if (type == 3) return sortdup(0, base2size);
        else if (type == 4) return sortdup(1, base2size);
        else if (type == 5) return almostsortdup(1,base2size);
        else if (type == 6) return randup(base2size);
        else if (type == 7) return almostsortuniq(0,base2size);
        else if (type == 8) return sortuniq(0,base2size);
        else if (type == 9) return sortuniq(1,base2size);
        else if (type == 10) return almostsortuniq(1,base2size);
        else if (type == 11) return randuniq(base2size);
    }

    private BigInteger [] totalrand(int base2size) {

        
    }





}
