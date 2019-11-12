/*
 * implementação obtida em: https://stackabuse.com/sorting-algorithms-in-java/
*/
package exercicio6;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class SelectionSort implements Callable<String>{
    int []array;
    
    public SelectionSort(int[] array){
        this.array = array;
    }
    
    @Override
    public String call(){
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while(j >= 0 && current < array[j]) {
                array[j+1] = array[j];
                j--;
            }
            // at this point we've exited, so j is either -1
            // or it's at the first element where current >= a[j]
            array[j+1] = current;
        }
        
        return ("selection sort: " + Arrays.toString(array));
    }
}
