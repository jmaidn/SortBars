package sample;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

class MergeSort implements Runnable {
    IntegerProperty[] arrInt;
    IntegerProperty ArrayIndex;
    int sizeValid;
    private static long delay;
    private static BooleanProperty sldAccess;

    public static void setDelay(int time){
        delay  = time;
    }

    void rest() throws InterruptedException {
            Thread.sleep(delay);
    }
    static void getAccessProperty(BooleanProperty Access){sldAccess = Access;}

    MergeSort(IntegerProperty[] arrInpt, IntegerProperty ArrayIndex,int sizeValid) {
        arrInt = arrInpt;
        this.ArrayIndex = ArrayIndex;
        this.sizeValid = sizeValid;

    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int l, int m, int r)throws InterruptedException {

        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arrInt[l + i].getValue();
        for (int j = 0; j < n2; ++j)
            R[j] = arrInt[m + 1 + j].getValue();


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        this.ArrayIndex.set(k);
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arrInt[k].set(L[i]);
                i++;
            } else {
                arrInt[k].set(R[j]);
                j++;
            }
            k++;
            if(k<=sizeValid)
             this.ArrayIndex.set(k);
            rest();
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arrInt[k].set( L[i]);
            i++;
            k++;
            if(k<=sizeValid)
             this.ArrayIndex.set(k);
            rest();
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arrInt[k].set(R[j]);;
            j++;
            k++;
            if(k<=sizeValid)
                this.ArrayIndex.set(k);
            rest();
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sort(int l, int r)throws InterruptedException {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(l, m);
            sort(m + 1, r);

            // Merge the sorted halves
            merge( l, m, r);
        }
    }

    /* A utility function to print array of size n */
     void printArrayValid() {
        int n = sizeValid;
        for (int i = 0; i < n; ++i)
            System.out.print(arrInt[i].get() + " ");
        System.out.println();
    }

    public void setParams(IntegerProperty[] arrInpt, IntegerProperty ArrayIndex,int sizeValid) {
        arrInt = arrInpt;
        this.ArrayIndex = ArrayIndex;
        this.sizeValid = sizeValid;
    }
    @Override
    public void run() {
        sldAccess.setValue(true);
        try {
            sort(0,sizeValid);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            sldAccess.setValue(false);
        }

    }

}