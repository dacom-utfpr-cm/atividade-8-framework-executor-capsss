/*
 * Carlos Alexandre Peron dos Santos
 * 
 * Faça um programa que calcule a soma dos elementos de uma matriz MxN.
 * Divida o programa em tarefas que somam as linhas.
 * O programa deve possibilitar especificar o número de threads para resolver o problema.
 */
package exercicio2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercicio2 {

    public static void main(String[] args) {
        int[ ][ ] matriz = {    { 1, 2, 3, 4, 5 },
                                { 6, 7, 8, 9, 10 },
                                { 11, 12, 13, 14, 15 },
                                {  16, 17, 18, 19, 20 }
                                                        };
        
        System.out.println("informe o numero de threads");
        Scanner scanner = new Scanner(System.in);
        int quantasThreads = scanner.nextInt();
        ArrayList<Integer> somas = new ArrayList();
        int intervalo = 1;
        
        if(quantasThreads > matriz.length){
            System.out.println("a matriz possui apenas " + matriz.length + " linhas. " + (quantasThreads - matriz.length) + " threads em excesso.");
            quantasThreads = matriz.length;
        } else {
            intervalo = matriz.length / quantasThreads;  
        }
        
        
        ExecutorService executorService = Executors.newFixedThreadPool(quantasThreads);
        for(int i=0; i<quantasThreads; i++){
            SomaLinha soma;
            if(i == (quantasThreads - 1)){
                soma = new SomaLinha(matriz, i*intervalo, matriz.length);
            } else {
                soma = new SomaLinha(matriz, i*intervalo, (i*intervalo) + intervalo);
            }
            
            Future<Integer> future = executorService.submit(soma);
            try {
                somas.add(future.get());
                System.out.println("soma temporaria de cada linha: " + somas);
            } catch (InterruptedException | ExecutionException e){
                System.out.println(e);
            }
        }
        
        
        Integer resultado = 0;
        resultado = somas.stream().map((valor) -> valor).reduce(resultado, Integer::sum);
        System.out.println("soma total: " + resultado);
        
        executorService.shutdown();
        
    }
    
}
