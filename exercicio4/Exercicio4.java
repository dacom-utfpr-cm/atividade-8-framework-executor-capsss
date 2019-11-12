/*
 * Carlos Alexandre Peron dos Santos
 * 
 * Faça um programa que periodicamente monitore mudanças em um conjunto de arquivos especificados.
 * Se ocorreram mudanças, o programa deve registrá-las em um arquivo de log.
 */
package exercicio4;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exercicio4 {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        String path = "resources";
        ThreadQueMonitoraArquivos t = new ThreadQueMonitoraArquivos(path);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        
        ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(t, 5, TimeUnit.SECONDS);
	//ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);
        
        try {
            System.out.println(scheduledFuture.get());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Exercicio4.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        scheduledExecutorService.shutdown();
    }
    
}
