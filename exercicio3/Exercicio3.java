/*
 * Carlos Alexandre Peron dos Santos
 * 
 * Faça um programa concorrente para multiplicar duas matrizes.
 */
package exercicio3;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercicio3 {
    
    private static int[] extrairColuna(int[][] matriz, int qualColuna){
        int[] coluna = new int[matriz[0].length];
        int pos = 0;
        for(int i=0; i<matriz[0].length; i++){
            coluna[pos++] = matriz[i][qualColuna];
        }
        return coluna;
    }
    
    public static void main(String[] args) {
        int[ ][ ] a = {     {1, 2, 3},
                            {6, 7, 8},
                            {11, 12, 13}
                                        };
        
        int[ ][ ] b = {     {7, 8, 9},
                            {4, 5, 6},
                            {1, 2, 3}
                                        };
        
        ArrayList<Integer> valores = new ArrayList();
        
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        MultiplicacaoMatrizParcial parcial;
        for(int i=0; i<a.length; i++){
            for(int j=0; j<b[0].length; j++){
                parcial = new MultiplicacaoMatrizParcial(a[i], extrairColuna(b, j));
                Future<Integer> future = executorService.submit(parcial);
                try {
                    Integer valorTemporario = future.get();
                    valores.add(valorTemporario);
                    System.out.println("thread calculou -> linha " + i + " coluna " + j + ": " + valorTemporario);
                } catch (InterruptedException | ExecutionException e){
                    System.out.println(e);
                }
            }
        }
        
        for(int i=0; i<valores.size(); i++){
            if(i % (a.length) == 0){
                System.out.println("");
            }
            System.out.print(valores.get(i) + " ");
        }
        System.out.println("");
        executorService.shutdown();
    }
    
}
