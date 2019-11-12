/*
 * Carlos Alexandre Peron dos Santos
 * 
 * Faça um programa que execute três algoritmos de ordenação para um conjunto de valores
 * e exiba o resultado apenas do algoritmo que finalizar primeiro (use invokeAny).
 */
package exercicio6;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exercicio6 {
    
    public static void main(String[] args) {
        int[] array = {5,4,1,7,3,8,0,2,9,6};
        
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Set<Callable<String>> callables = new HashSet<>();
        
        InsertionSort insertion = new InsertionSort(array);
        SelectionSort selection = new SelectionSort(array);
        BubbleSort bubble = new BubbleSort(array);
        callables.add(insertion);
        callables.add(selection);
        callables.add(bubble);
        
        try {
            System.out.println(executorService.invokeAny(callables));
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Exercicio6.class.getName()).log(Level.SEVERE, null, ex);
        }
        executorService.shutdown();
    }
    
}
