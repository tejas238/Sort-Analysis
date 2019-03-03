/* Testrunner.java class implementation last edited 3/2/19. This file runs all the tests
   for the three sorting algorithms and outputs .csv files with data points for input size and
   execution time.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Testrunner {
    private Testdata data; //data object used to generate psuedo random arrays in various configurations

    public void run() {
        try {
            String[] input = {"totalrand", "totaldup", "almostsortdup", "sortdup", "revsortdup",
                    "almostrevsortdup", "randup", "almostsortuniq", "sortuniq", "revsortuniq", "almostrevsortuniq", "randuniq"};
            String[]algo = {"MergeSort","HeapSort","QuickSort"};

            for (int k = 0; k < 3; ++k) {

                for (int i = 0; i < input.length; ++i) {

                    BufferedWriter bw = new BufferedWriter(new FileWriter("./"+algo[k] + "_" + input[i] + ".csv"));
                    boolean exceptionhit = false; //exception hits occur due to various reasons
                    int size = 0; //floor(1.5^(size)) represents actual integer array size

                    while (!exceptionhit) {
                        try {
                            long sum = 0;
                            long avg = 0;
                            for (int j = 0; j < 5; ++j) { //repeat calculation for 5 arrays of size 'size'
                                Testdata data = new Testdata(); //a new testdata object for every array minimizes RAM usage
                                long getdata = System.nanoTime(); //benchmark time taken to allocate array
                                int[] arr = data.request(size, 0, input[i]);
                                long gotdata = System.nanoTime();
                                if (gotdata - getdata > 5e9) throw new RuntimeException(); //5 seconds max to get array
                                sum += exec(arr,k); //exec returns time taken to sort array
                                System.out.print(algo[k] + "_" + input[i] + "_" + size + "_" + j + " Done! ");
                            }
                            System.out.println();
                            avg = sum / 5;
                            bw.write(size + "," + avg);
                            bw.newLine();
                            bw.flush();
                            ++size;
                        } catch (Throwable t) { //exception is reached in three cases: data retrieval > 5 sec
                                                //Out of memory error on heap due to an array too large
                                                //
                            exceptionhit = true;
                        }
                    }
                    bw.close();
                }
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public long exec(int [] input, int choice) throws RuntimeException{
        double alldiff,counter,thisdiff,loopstart,mindiff;
        loopstart = counter = alldiff = thisdiff = 0;
        mindiff = 5e8;
        int [] sorted_arr = null;
        double thistart, thisend;
        boolean waszero = false;

        loopstart = System.nanoTime();
        while(alldiff < 5e8) {
           thistart = System.nanoTime();
           switch (choice) {
               case 0:
                   sorted_arr = placeholder(input);
                   break;
               case 1:
                   sorted_arr = placeholder(input);
                   break;
               case 2:
                   sorted_arr = placeholder(input);
                   break;
           }
           thisend = System.nanoTime();
           thisdiff = thisend - thistart;

           if (thisdiff > 5e8) {
               System.out.println("LARGE TIME");
               throw new RuntimeException();
           }

           if(thisdiff == 0 && waszero == false) {
               counter = 1;
               waszero = true;
               loopstart = System.nanoTime();
           } else if (waszero)
               ++counter;
           else {
               if (thisdiff < mindiff) mindiff = thisdiff;
           }
           alldiff = thisend-loopstart;
        }

        if (!confirm(sorted_arr)) {
            System.out.println("UNSORTED INPUT");
            throw new RuntimeException();
        }

        if(waszero) return (long) (alldiff/counter);
        else return (long) mindiff;
    }

    public int[] placeholder(int [] input) {

        int val = (int)(Math.log(input.length)/Math.log(1.5));

        for(int i = 0;i<input.length;++i) {
            input[i] = i;
        }

        return input;
    }

    public boolean confirm(int [] to_check) {

        boolean sorted = true;
       if (to_check.length == 1) return sorted;
       for (int i=1;i<to_check.length;++i)
           if (to_check[i] < to_check[i - 1]) sorted = false;

       return sorted;
    }
}
