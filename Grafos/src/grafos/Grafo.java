package grafos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grafo {
    private List<Integer> V;
    
    private int modV;
    private int modE;

    public Grafo() {
        V = new ArrayList<Integer>();
        
        modE = 0;
        modV = 0;
    }

    public int getModE() {
        return modE;
    }

    public int getModV() {
        return modV;
    }
    public void lerTxtLaplaciana(String caminho){
        BufferedReader s;
        try {
            s = new BufferedReader(new FileReader(caminho));
            int c;
            while ((c = s.read()) != -1){
                switch(c){
                    case 91:
                        System.out.print("[ ");
                        break;
                    case 93:
                        System.out.print("]");
                        break;
                    case 45:
                        System.out.print("-1 ");
                        s.read();
                        break;
                    default:
                        if(c>47 && c<58){
                            char cTemp = (char) c;
                            String temp = ""+cTemp;
                            while((c = s.read())!= 32 && c != 93){
                                cTemp = (char) c;
                                temp += cTemp;
                            }
                            System.out.print(temp+" ");
                            if(c == 93)
                                System.out.println("]");
                        }
                        break;
                }
                       
            }
        }catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    
}
