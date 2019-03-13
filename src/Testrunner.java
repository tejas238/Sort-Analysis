/* Testrunner.java class implementation last edited 3/2/19. This file runs all the tests
   for the three sorting algorithms and outputs .csv files with data points for input size and
   execution time.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Testrunner {
    private Testdata data; //data object used to generate psuedo random arrays in various configurations

    //The main function that runs all testcases for the three algorithms i.e. 5 times for each size
    //for each input data type for each algorithm. There is no set limit on how large the size for
    //generating arrays can reach provided no exception is hit for the reasons specified on line 49
    public void run() {
        try { //see Testdata.java class for details om each data type it serves
            String[] input = {"totalrand", "totaldup", "almostsortdup", "sortdup", "revsortdup",
                    "almostrevsortdup", "randup", "almostsortuniq", "sortuniq", "revsortuniq", "almostrevsortuniq", "randuniq"};
            String[]algo = {"MergeSort","HeapSort","QuickSort"};

            for (int k = 0; k < 3; ++k) { //For each of the three algorithms

                for (int i = 0; i < input.length; ++i) { //For all input data types

                    BufferedWriter bw = new BufferedWriter(new FileWriter("./dataout/"+algo[k] + "_" + input[i] + ".csv"));
                    boolean exceptionhit = false; //exception hits occur due to various reasons
                    int size = 0; //floor(1.5^(size)) represents actual integer array size

                    while (!exceptionhit) { //increments size until an exception is hit
                        try {
                            long sum = 0;
                            long avg = 0;
                            for (int j = 0; j < 5; ++j) { //repeat calculation for 5 arrays of size 'size'
                                Testdata data = new Testdata(); //a new testdata object for every array minimizes RAM usage
                                long getdata = System.nanoTime(); //benchmark time taken to allocate array
                                int[] arr = data.request(size, 0, input[i]); //array of 'size' in returned as per input[i]
                                long gotdata = System.nanoTime();
                                if (gotdata - getdata > 10e9) throw new RuntimeException(); //5 seconds max to get array
                                sum += exec(arr,k); //exec returns time taken to sort array
                                System.out.print(algo[k] + "_" + input[i] + "_" + size + "_" + j + " Done! ");
                            }
                            System.out.println();
                            avg = sum / 5;
                            bw.write(size + "," + avg);
                            bw.newLine();
                            bw.flush();
                            ++size;
                        }
                        catch (RuntimeException ex) { //exception is reached in three cases: data retrieval > 5 sec
                                                //Out of memory error on heap due to an array too large
                                                //> 0.5 sec per benchmark
                            exceptionhit = true;
                        }
                        catch (Error e) {
                            exceptionhit = true;
                        }
                    }
                    bw.close();
                }
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        catch(UnsortedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //This function runs a particular input for a particular algorithm for 0.5 seconds and
    //returns the minimum execution time from those runs. If any one execution lasts more than
    //0.5 seconds the execution is aborted and exception raised. Similarly, if any one execution
    //reports 0 nanoseconds of time taken, a counter is started for all future executions and executions
    //measured for a further 0.5 seconds before returning the average time taken per '0' second execution
    public long exec(int [] input, int choice) throws RuntimeException, UnsortedException{
        double alldiff,counter,thisdiff,loopstart,mindiff;
        loopstart = counter = alldiff = thisdiff = 0;
        mindiff = 10e9;
        int [] sorted_arr = null;
        double thistart, thisend;
        boolean waszero = false;

        loopstart = System.nanoTime();
        while(alldiff < 5e8) {
           thistart = System.nanoTime();
           switch (choice) {
               case 0:
                   sorted_arr = Mergesort.mergesort(input);//mergesort
                   break;
               case 1:
                   sorted_arr = Heapsort.heapsort(input); //heapsort
                   break;
               case 2:
                   sorted_arr = Quicksort.quicksort(input) ; //quicksort
                   break;
           }
           thisend = System.nanoTime();
           thisdiff = thisend - thistart;
          // System.out.println(thisdiff);

           if (thisdiff > 10e9) {
               System.out.println("LARGE TIME");
               throw new RuntimeException();
           }

           if(thisdiff == 0 && waszero == false) { //everything reset for the first '0' nanosecond execution
               counter = 1;
               waszero = true;
               loopstart = System.nanoTime(); //0.5 seconds of execution measured from here onwards
           } else if (waszero)
               ++counter;
           else {
               if (thisdiff < mindiff) mindiff = thisdiff;
           }
           alldiff = thisend-loopstart; //alldiff measures time taken since loop execution or first zero second measurement
        }

        if (!confirm(sorted_arr)) //if unsorted array is detected due to faulty algorithm
            throw new UnsortedException();

        if(waszero) return (long) (alldiff/counter); //returns the average of all runs in case we encounter a 0 s execution
        else return (long) mindiff;
    }

    //placeholder function to be replaced b sorting functions
    /*public int[] placeholder(int [] input) {

        int val = (int)(Math.log(input.length)/Math.log(1.5));

        for(int i = 0;i<input.length;++i) {
            input[i] = i;
        }

        return input;
    }*/

    //checks if output is in sorted order
    public boolean confirm(int [] to_check) {

        if (to_check.length == 1) return true;
        for (int i = 1; i < to_check.length; ++i)
            if (to_check[i] < to_check[i - 1]) return false;

        return true;
    }
}
