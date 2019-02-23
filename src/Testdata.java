import java.util.Random;
import java.util.Vector;

public class Testdata {
    private Vector<Vector<Vector<int[]>>>  alldata;
    static Random rand;

    public Testdata() {
        alldata = new Vector<Vector<Vector<int[]>>>(12);
        rand = new Random(System.nanoTime());
        for (int i = 0;i<12;++i) {
            alldata.add(new Vector<Vector<int[]>>());
        }
    }

    public int[] request(int base2size, int indexforsize, String type) {

        if (base2size < 0 || indexforsize < 0) throw new RuntimeException();

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
        else throw new RuntimeException();

        Vector<Vector<int[]>> typeref = alldata.elementAt(lookup);

        boolean correctsize = false;

        while (correctsize == false) {
            try {
                Vector<int[]> sizeref = typeref.elementAt(base2size);
                correctsize = true;

                boolean correctindex = false;
                while (correctindex == false) {
                    try {
                        int[] requested = sizeref.elementAt(indexforsize);
                        correctindex = true;

                        return requested;
                    } catch(Exception e) {
                        System.out.println("create");
                        sizeref.add(create(lookup,base2size));
                    }
                }
            } catch (Exception e){
                typeref.add(new Vector<int[]>());
            }
        }
        return null;
    }

    private int [] create(int type,int base2size) {
        if (type == 0) return totalrand(base2size);
        else if (type == 1) return totaldup(base2size);
        else if (type == 2) return almostsortdup(false,base2size);
        else if (type == 3) return sortdup(false, base2size);
        else if (type == 4) return sortdup(true, base2size);
        else if (type == 5) return almostsortdup(true,base2size);
        else if (type == 6) return randup(base2size);
        else if (type == 7) return almostsortuniq(false,base2size);
        else if (type == 8) return sortuniq(false,base2size);
        else if (type == 9) return sortuniq(true,base2size);
        else if (type == 10) return almostsortuniq(true,base2size);
        else if (type == 11) return randuniq(base2size);
        else throw new RuntimeException();
    }

    private int [] totalrand(int base2size) {
        int size = (int)Math.pow(2,base2size);
        int [] arr = new int[size];

        for(int i = 0;i<size;++i) {
            arr[i] = rand.nextInt();
        }

        return arr;
    }

    private int [] totaldup(int base2size) {
        int size = (int)Math.pow(2,base2size);
        int [] arr = new int[size];

        int randnum = rand.nextInt();
        for(int i = 0;i<size;++i) {
            arr[i] = randnum;
        }

        return arr;
    }

    private int [] almostsortdup(boolean reverse,int base2size) {
        int size = (int)Math.pow(2,base2size);
        int [] arr = sortdup(false,base2size);

        for (int i=0;i<size;++i) {
            int random = rand.nextInt(100) + 1;
            int random2 = rand.nextInt(size);
            if (random < 15) {
                int to_swap = arr[random2];
                arr[random2] = arr[i];
                arr[i] = to_swap;
            }
        }
        return arr;
    }

    private int [] sortdup()
}
