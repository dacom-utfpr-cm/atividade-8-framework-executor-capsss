package exercicio5;

import java.util.concurrent.Callable;

public class ThreadQueFazAlgoExtremamenteUtilVoceNaoTemIdeia implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("olha esse retorno...");
        return "...extremamente util";
    }
    
}
