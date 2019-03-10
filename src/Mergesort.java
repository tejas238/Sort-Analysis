public class Mergesort {

    private static void merge(int lower[], int upper[], int whole[]){
        int i=0;
        int j=0;
        int k=0;
        int llen=lower.length;
        int ulen=upper.length;
        while (i<llen && j<ulen){
            if (lower[i]<=upper[j]){
                whole[k] = lower[i];
                i = i+1;
            }
            else{
                whole[k] = upper[j];
                j = j+1;
            }
            k = k+1;
        }
        //after one list is exhausted copy remaining list
        if (i == llen){
            for (int x=j;x<ulen;++x){
                whole[k] = upper[x];
                k = k+1;
            }

        }
        else{
            for (int x=i;x<llen;++x){
                whole[k] = lower[x];
                k = k+1;
            }
        }
    }
    public static int[] mergesort(int whole[]){
        int len=whole.length;
        if (len == 1) return whole;
        //this function separates the list into 2
        int [] lower, upper;
        lower = new int[len/2];//not sure if it works for large numbers?
        if (len%2 !=0) upper = new int[len/2 + 1];//not sure if it works for large numbers?
        else upper = new int[len/2];
        for (int i=0;i<len/2;++i){
            lower[i]=whole[i];
        }
        int up=0;
        for (int i=len/2;i<len;++i){
            upper[up]=whole[i];
            up+=1;
        }
        mergesort(lower);
        mergesort(upper);
        merge(lower,upper,whole);

        return whole;
    }
}
