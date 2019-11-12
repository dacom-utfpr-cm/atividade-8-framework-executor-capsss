/*
 * Carlos Alexandre Peron dos Santos
 * 
 * Faça um programa que localize o maior valor em um vetor.
 * Divida o programa em tarefas que localizam o maior valor em um segmento do vetor.
 * O programa deve possibilitar especificar o número de tarefas e o número de threads para resolver o problema.
 */
package exercicio1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercicio1 {

    public static void main(String[] args) {
        int[] vetor = {5,7,9,1,8,3,4,6,2};
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("informe o numero de threads");
        int quantasThreads = scanner.nextInt();
        System.out.println("informe o numero de tarefas");
        int quantasTarefas = scanner.nextInt();
        ArrayList<Integer> maiores = new ArrayList();
        int intervalo = 1;
        
        if(quantasTarefas > vetor.length){
            System.out.println("o vetor possui apenas " + vetor.length + " elementos. " + (quantasTarefas - vetor.length) + " threads em excesso.");
            quantasTarefas = vetor.length;
        } else {
            intervalo = vetor.length / quantasTarefas;  
        }
        
        ExecutorService executorService = Executors.newFixedThreadPool(quantasThreads);
        for(int i=0; i<quantasTarefas; i++){
            MaiorElemento maior;
            if(i == (quantasTarefas - 1)){
                maior = new MaiorElemento(vetor, i*intervalo, vetor.length);
            } else {
                maior = new MaiorElemento(vetor, i*intervalo, (i*intervalo) + intervalo);
            }
            
            Future<Integer> future = executorService.submit(maior);
            try {
                maiores.add(future.get());
                System.out.println("maiores elementos temporarios de cada subvetor: " + maiores);
            } catch (InterruptedException | ExecutionException e){
                System.out.println(e);
            }
        }
        
        
        Integer resultado = 0;
        resultado = maiores.stream().map((valor) -> valor).reduce(resultado, Integer::max);
        System.out.println("maior elemento: " + resultado);
        
        executorService.shutdown();
    }
    
}
