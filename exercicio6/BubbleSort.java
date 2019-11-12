/*
 * implementação obtida em: https://stackabuse.com/sorting-algorithms-in-java/
*/
package exercicio6;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class BubbleSort implements Callable<String>{
    int []array;
    
    public BubbleSort(int[] array){
        this.array = array;
    }
    
    @Override
    public String call(){
        boolean sorted = false;
        int temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i+1]) {
                    temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    sorted = false;
                }
            }
        }
        
        return ("bubble sort: " + Arrays.toString(array));
    }
}
