package tNomeArquivo;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class tNomeArquivo {
	
	
	
public static void main(String[] args){

        
    	try{
	
    		URL url = new URL("http://localhost:9999/TESTE-1.0/RelatorioDespesas");  
    		HttpURLConnection connection =  
    		    (HttpURLConnection) url.openConnection();  
    		  
    		    FileOutputStream out = new FileOutputStream("relatorio.pdf");  
    		    InputStream in = connection.getInputStream();  
    		  
    		    byte[] conteudo = new byte[in.available()];  
    		      
    		    in.read(conteudo);  
    		    in.close();  
    		    out.write(conteudo);  
    		    out.close();  
    		
    	} catch(MalformedURLException e){
            System.out.println("Erro ao criar URL. Formato inv�lido.");
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
}
}

    		
    		
    		
    		
        
   