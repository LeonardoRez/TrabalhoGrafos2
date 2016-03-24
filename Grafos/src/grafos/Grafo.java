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
    private List<Aresta> E;
    private int modV;
    private int modE;

    public Grafo() {
        V = new ArrayList<Integer>();
        E = new ArrayList<Aresta>();
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
                        System.out.println("[");
                        break;
                    case 93:
                        System.out.println("]");
                        break;
                    case 45:
                        System.out.println("-1");
                        c = s.read();
                        break;
                    default:
                        if(c>47 && c<58){
                            String temp = ""+c;
                            while((c = s.read())!= 32){
                                temp += c;
                            }
                            System.out.println(temp);
                        }
                }
                       
            }
        }catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    
}
