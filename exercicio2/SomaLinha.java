package exercicio2;

import java.util.concurrent.Callable;

public class SomaLinha implements Callable<Integer>{
    int[][] matriz;
    int linhaInicial;
    int linhaFinal;
    
    public SomaLinha(int[][] matriz, int linhaInicial, int linhaFinal){
        this.matriz = matriz;
        this.linhaInicial = linhaInicial;
        this.linhaFinal = linhaFinal;
    }

    @Override
    public Integer call() throws Exception {
        int soma = 0;
        for(int i=linhaInicial; i<linhaFinal; i++){
            for(int j=0; j<matriz[i].length; j++){
                soma += matriz[i][j];
            }
        }
        return soma;
    }
    
    
}
