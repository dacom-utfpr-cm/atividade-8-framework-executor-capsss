package exercicio3;

import java.util.concurrent.Callable;

public class MultiplicacaoMatrizParcial implements Callable<Integer>{
    int[] linha, coluna;
    public MultiplicacaoMatrizParcial(int[] linha, int[] coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    @Override
    public Integer call() throws Exception {
        Integer soma = 0;
        for(int i=0; i<linha.length; i++){
            soma += linha[i] * coluna [i];
        }
        return soma;
    }
    
    
    
    
    
}
