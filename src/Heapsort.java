public class Heapsort {
    public static int[] heapsort(int [] input) {

        if (input == null) return null;

        int size = input.length;
       for(int i = (size/2)-1;i>=0;--i)  {
          heapify(input,i,size-1);
       }
       while(size>1) {
           int temp = input[0];
           input[0] = input[size-1];
           input[size-1] = temp;
           --size;

           heapify(input,0,size-1);
       }

       return input;
    }

    public static void heapify(int []input, int start, int end) {

        int left = start*2 + 1;
        int right = start*2 + 2;

        boolean l = true;
        boolean r = true;

        if (left > end) l = false;
        if (right > end) r = false;

        if (!r && !l) return;
        if (r && l) {
            if (input[right] > input[left]) l = false;
            else r = false;
        }
        if (r && input[right] > input[start]) {
            int temp = input[right];
            input[right] = input[start];
            input[start] = temp;
            heapify(input,2*start + 2,end);
        }
        if (l && input[left] > input[start]) {
            int temp = input[left];
            input[left] = input[start];
            input[start] = temp;
            heapify(input,2*start + 1,end);
        }
    }
}
