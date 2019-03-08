import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Quicksort {
/*
    public static void main(String[] args) {
	    int list[] = {9,1,6,5787687,8,3,2,8,56,23423,675,343};
	    quicksort(list);
        System.out.println(Arrays.toString(list));
    }
    */

    public static int[] quicksort(int[] list) {
        quicksort(list, 0, list.length - 1);
	return list;
    }

    private static void quicksort(int[] list, int start, int end) {
        if(end <= start)
            return;

        int p = partition(list, start, end);
        quicksort(list, start, p -1);
        quicksort(list, p + 1,  end);

    }

    private static int partition(int[] list, int start, int end){
        //randomize the pivot
        int part = ThreadLocalRandom.current().nextInt(start, end);
        int temp = list[start];
        list[start] = list[part];
        list[part] = temp;

        int i = start + 1;
        int j = end;
        while(true) {
            for(; list[i] < list[start]; i++) {
                if (i == end)
                    break;
            }
            for(; list[j] > list[start]; j--) {
                if(j == start)
                    break;

            }
            if( i >= j)
                break;
            temp = list[i];
            list[i] = list[j];
            list[j] = temp;
        }
        if(j != start) {
            temp = list[j];
            list[j] = list[start];
            list[start] = temp;
        }
        return j;
    }
}
