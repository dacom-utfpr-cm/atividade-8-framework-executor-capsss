package exercicio1;

import java.util.concurrent.Callable;

public class MaiorElemento implements Callable<Integer>{
    int[] vetor;
    int inicio;
    int fim;
    
    public MaiorElemento(int[] vetor, int inicio, int fim){
        this.vetor = vetor;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Override
    public Integer call() throws Exception {
        int maior = 0;
        for(int i=inicio; i<fim; i++){
            if(vetor[i] > maior){
                maior = vetor[i];
            }
        }
        return maior;
    }
    
    
}
