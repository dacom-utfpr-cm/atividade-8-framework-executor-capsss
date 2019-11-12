package exercicio4;

import com.sun.xml.internal.ws.util.StringUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ThreadQueMonitoraArquivos implements Callable<String>{
    private final String path;
    private String anterior;
    private String atual;
    
    public ThreadQueMonitoraArquivos(String path) throws FileNotFoundException, IOException{
        this.path = path;
        
        File diretorio = new File(this.path);
        File[] filesList = diretorio.listFiles();
        for(File f : filesList){
            if(f.isFile() && !"resources/log".equals(f.toString())){
                BufferedReader arquivo = new BufferedReader(new FileReader(f));
                String lixo;
                while ((lixo = arquivo.readLine()) != null){
                    System.out.println(lixo);
                    this.anterior += lixo + "\n";
                }
            }
        }
    }

    @Override
    public String call() throws FileNotFoundException, IOException {
        File diretorio = new File(this.path);
        File[] filesList = diretorio.listFiles();
        for(File f : filesList){
            if(f.isFile() && !"resources/log".equals(f.toString())){
                BufferedReader arquivo = new BufferedReader(new FileReader(f));
                String lixo;
                while ((lixo = arquivo.readLine()) != null){
                    System.out.println(lixo);
                    this.atual += lixo;
                }
            }
        }
        
        if (!this.anterior.equals(this.atual)){
            File file = new File("resources/log");
            try (FileWriter fr = new FileWriter(file, true)) {
                fr.write("\nmudancas: " + atual + "\n");
            }
            return "Alguem mexeu, da uma olhada no log";
        } else {
            return "Pode ficar tranquilo que os arquivos estao intactos";
        }
    }


    
}
