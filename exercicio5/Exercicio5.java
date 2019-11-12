/*
 * Carlos Alexandre Peron dos Santos
 * 
 * Faça um programa que possibilite agendar uma tarefa para ser executada em um horário específico.
 */

package exercicio5;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exercicio5 {

    public static void main(String[] args) {
        System.out.println("informe quantos segundos para executar a tarefa");
        Scanner scanner = new Scanner(System.in);
        int quantosSegundos = scanner.nextInt();
        
        ThreadQueFazAlgoExtremamenteUtilVoceNaoTemIdeia t = new ThreadQueFazAlgoExtremamenteUtilVoceNaoTemIdeia();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(t, quantosSegundos, TimeUnit.SECONDS);
        
        try {
            System.out.println(scheduledFuture.get());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Exercicio5.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        scheduledExecutorService.shutdown();
    }
    
}
