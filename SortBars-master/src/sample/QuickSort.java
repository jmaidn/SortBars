package sample;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

// Java program for implementation of QuickSort
class QuickSort implements Runnable{
    IntegerProperty[] arrInt;
    IntegerProperty ArrayIndex;
    int sizeValid;
    private static long delay;
    private static BooleanProperty sldAccess;

    void rest() throws InterruptedException {
        Thread.sleep(delay);
    }
    public static void setDelay(int time){
        delay  = time;
    }

    static void getAccessProperty(BooleanProperty Access){sldAccess = Access;}

    QuickSort(IntegerProperty[] arrInpt, IntegerProperty ArrayIndex,int sizeValid) {
        arrInt = arrInpt;
        this.ArrayIndex = ArrayIndex;
        this.sizeValid = sizeValid;
    }

    int partition(int low, int high) throws InterruptedException {
        int pivot = arrInt[high].getValue();

        int i = (low-1); // index of smaller element

        for (int j=low; j<high; j++) {
            // If current element is smaller than the pivot
            if (arrInt[j].getValue() < pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arrInt[i].getValue();
                ArrayIndex.set(i);
                rest();
                arrInt[i].set(arrInt[j].getValue());
                ArrayIndex.set(j);
                rest();
                arrInt[j].set(temp);

            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arrInt[i+1].getValue();
        ArrayIndex.set(i+1);
        rest();
        arrInt[i+1].set(arrInt[high].getValue());
        ArrayIndex.set(high);
        arrInt[high].set(temp);
        //rest();

        return i+1;
    }



    void sort(int low, int high) throws InterruptedException {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(low, high);

            // Recursively sort elements before
            // partition and after partition
            sort( low, pi-1);
            sort( pi+1, high);
        }
    }
    public void setParams(IntegerProperty[] arrInpt, IntegerProperty ArrayIndex,int sizeValid) {
        arrInt = arrInpt;
        this.ArrayIndex = ArrayIndex;
        this.sizeValid = sizeValid;
    }
    // Driver program
    public  void run() {
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
/*This code is contributed by Rajat Mishra */
