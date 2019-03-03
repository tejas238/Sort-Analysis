import java.util.Random;
import java.util.Vector;

public class Testdata {
    private Vector<Vector<Vector<int[]>>>  alldata;
    static Random rand;

    public Testdata() throws java.lang.RuntimeException, java.lang.OutOfMemoryError{
        alldata = new Vector<Vector<Vector<int[]>>>(12);
        rand = new Random(System.nanoTime());
        for (int i = 0;i<12;++i) {
            alldata.add(new Vector<Vector<int[]>>());
        }
    }

    public int[] request(int base1size, int indexforsize, String type) throws java.lang.RuntimeException,
            java.lang.OutOfMemoryError {

        if (base1size < 0 || indexforsize < 0) throw new RuntimeException();

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
                Vector<int[]> sizeref = typeref.elementAt(base1size);
                correctsize = true;

                boolean correctindex = false;
                while (correctindex == false) {
                    try {
                        int[] requested = sizeref.elementAt(indexforsize);
                        correctindex = true;

                        return requested;
                    } catch(ArrayIndexOutOfBoundsException e) {
                        sizeref.add(create(lookup,base1size));
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e){
                typeref.add(new Vector<int[]>());
            }
        }
        return null;
    }

    private int [] create(int type,int base1size) throws java.lang.OutOfMemoryError,RuntimeException {
        if (type == 0) return totalrand(base1size);
        else if (type == 1) return totaldup(base1size);
        else if (type == 2) return almostsortdup(false,base1size);
        else if (type == 3) return sortdup(false, base1size);
        else if (type == 4) return sortdup(true, base1size);
        else if (type == 5) return almostsortdup(true,base1size);
        else if (type == 6) return randup(base1size);
        else if (type == 7) return almostsortuniq(false,base1size);
        else if (type == 8) return sortuniq(false,base1size);
        else if (type == 9) return sortuniq(true,base1size);
        else if (type == 10) return almostsortuniq(true,base1size);
        else if (type == 11) return randuniq(base1size);
        else throw new RuntimeException();
    }

    private int [] totalrand(int base1size) throws java.lang.OutOfMemoryError{
        int size = (int)Math.pow(1.5,base1size);
        int [] arr = new int[size];

        for(int i = 0;i<size;++i) {
            arr[i] = rand.nextInt();
        }

        return arr;
    }

    private int [] totaldup(int base1size) throws java.lang.OutOfMemoryError{
        int size = (int)Math.pow(1.5,base1size);
        int [] arr = new int[size];

        int randnum = rand.nextInt();
        for(int i = 0;i<size;++i) {
            arr[i] = randnum;
        }

        return arr;
    }

    private int [] rev(int [] arr) throws java.lang.OutOfMemoryError {
        for (int i = 0, j = arr.length-1;i<j;++i,--j) {
          int swap = arr[i];
          arr[i] = arr[j];
          arr[j] = swap;
        }
        return arr;
    }

    private int [] almostsortdup(boolean reverse,int base1size) throws java.lang.OutOfMemoryError{
        int [] arr = sortdup(reverse,base1size);

        for (int i=0;i<arr.length;++i) {
            int random = rand.nextInt(100) + 1;
            int random2 = rand.nextInt(arr.length);
            if (random < 15) {
                int to_swap = arr[random2];
                arr[random2] = arr[i];
                arr[i] = to_swap;
            }
        }
        return arr;
    }

    private int [] sortuniq(boolean reverse, int base1size) throws java.lang.OutOfMemoryError{
        int size = (int)Math.pow(1.5,base1size);
        int [] arr = new int [size];


        long choosefrom = (long)Math.pow(2,32) - (size - 1);
        long last_added = 0;

        for(int i = 0; i<size; ++i) {
            last_added += Math.floorMod(rand.nextLong(), (choosefrom - last_added)) + 1;
            ++choosefrom;
            int to_int = (int)(last_added - (Math.pow(2,32)-Math.pow(2,31) + 1));
            arr[i] = to_int;
        }

        if (reverse == true) rev(arr);
        return arr;
    }

    private int [] randuniq(int base1size) throws java.lang.OutOfMemoryError{
       int [] arr = sortuniq(false,base1size);

       for(int i = 0;i<arr.length;++i) {
           int swapindex = rand.nextInt(arr.length);
           int swap = arr[swapindex];
           arr[swapindex] = arr[i];
           arr[i] = swap;
       }
       return arr;
    }

    private int [] almostsortuniq(boolean reverse,int base1size) throws java.lang.OutOfMemoryError{
        int [] arr = sortuniq(reverse,base1size);

        for (int i=0;i<arr.length;++i) {
            int random = rand.nextInt(100) + 1;
            int random2 = rand.nextInt(arr.length);
            if (random < 15) {
                int to_swap = arr[random2];
                arr[random2] = arr[i];
                arr[i] = to_swap;
            }
        }
        return arr;
    }

    private int [] randup(int base1size) throws java.lang.OutOfMemoryError {

        int [] arr = sortdup(false,base1size);

       for(int i = 0;i<arr.length;++i) {
           int swapindex = rand.nextInt(arr.length);
           int swap = arr[swapindex];
           arr[swapindex] = arr[i];
           arr[i] = swap;
       }
       return arr;
    }

    private int [] sortdup(boolean reverse,int base1size) throws java.lang.OutOfMemoryError{
        int[] arr = sortuniq(reverse, base1size);

        for (int i = 0; i < (arr.length - 1); ++i)
            if (Math.floorMod(rand.nextInt(), 3) == 1) arr[i + 1] = arr[i];

        return arr;
    }
}
