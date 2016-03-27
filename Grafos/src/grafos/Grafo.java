package grafos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Grafo {

    private ConjuntoDeVertices V;
    private ConjuntoDeArestas E;
    private int modV;
    private int modE;
    private int[][] laplaciana;

    public Grafo() {
        V = new ConjuntoDeVertices();
        E = new ConjuntoDeArestas();
        modE = 0;
        modV = 0;
        laplaciana = null;
    }

    public int getModE() {
        return modE;
    }

    public int getModV() {
        return modV;
    }

    public void lerTxtLaplaciana(String caminho) {
        BufferedReader s;
        try {
            s = new BufferedReader(new FileReader(caminho));
            int i = 0, j = 1;//coordenadas da matriz
            int c;//numero que armazena o caractere (é lido como inteiro) (ascii)
            while ((c = s.read()) != -1) { //lê de caractere em caractere até o fim do arquivo

                switch (c) {
                    case 91://se achar [ (começo de linha)
                        j = 1; // simboliza a colunoa
                        i++;//simboliza a linha
                        break;
                    case 45://se achar um - (de -1)
                        E.addAresta(i, j); //adiciona aresta com as coordenadas da matriz
                        s.read(); //lê mais um caractere para pular o 1
                        j++;
                        break;
                    default:
                        if (c > 47 && c < 58) { //se achar um número de 0 a 9
                            char cTemp = (char) c; //o caractere é lido pelo seu valor inteiro. Aqui a gente armazena em "cTemp" como caractere...
                            String temp = "" + cTemp; // ...para depois jogar em uma string
                            while ((c = s.read()) != 32 && c != 93 && c != 45) { //aqui é para concatenar o resto do número (caso tenha mais de um dígito)
                                cTemp = (char) c;
                                temp += cTemp;
                            }
                            if (j == i) {
                                V.addVertice(new Vertice(i));
                            }
                            j++;
                        }
                }//fim switch 
            }//fim while (fim do arquivo)
            System.out.println("quantidade de vértices " + (i));
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void lerManual() {
        Scanner s = new Scanner(System.in);
        System.out.println("Digite a quantidade de vértices");
        this.modV = s.nextInt();
        int v1, v2;
        System.out.print("v1: ");
        v1 = s.nextInt();
        while (v1 != (-1)) {
            System.out.print("v2: ");
            v2 = s.nextInt();

            E.addAresta(v1, v2);//adiciona a aresta

            System.out.println("Digite o valor de v1 ou -1 para sair");
            v1 = s.nextInt();
        }

    }

    public void limparDados() {
        V = new ConjuntoDeVertices();
        E = new ConjuntoDeArestas();
        modE = 0;
        modV = 0;
    }

    public void printArestas() {
        E.printLista();
    }

    public void printVertices() {
        V.printLista();
    }

}
