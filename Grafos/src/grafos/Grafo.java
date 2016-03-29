package grafos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Grafo {

    private ConjuntoDeVertices V;
    private ConjuntoDeArestas E;
    private int modV;
    private int modE;

    public Grafo() {
        V = new ConjuntoDeVertices();
        E = new ConjuntoDeArestas();
        V.setReferencia(E);
        modE = 0;
        modV = 0;
    }

    public int getModE() {
        return modE;
    }

    public int getModV() {
        return modV;
    }

    public boolean lerTxtLaplaciana() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o caminho do arquivo:");
        String caminho = scanner.nextLine();
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
                                Vertice vTemp = new Vertice(i);
                                vTemp.setGrau(Integer.parseInt(temp));
                                V.addVertice(vTemp);
                            }
                            j++;
                        }
                }//fim switch 
            }//fim while (fim do arquivo)
//            System.out.println("quantidade de vértices " + (i));
            modE = E.getModE();
            modV = V.getModV();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado");
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public void lerManual() {
        limparDados();
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("Quantidade de vertices:");
            modV = s.nextInt();
        } while (modV <= 0);
        for (int i = 1; i <= modV; i++) {
            V.addVertice(new Vertice(i));
        }
        System.out.println("O programa irá ler as arestas do grafo.\n--->Só são permitidos números<---");
        int v1, v2;
        System.out.print("v1: ");
        v1 = s.nextInt();
        while (v1 != (-1)) {
            System.out.print("v2: ");
            v2 = s.nextInt();
            if (V.confereVertice(Integer.toString(v1)) && V.confereVertice(Integer.toString(v2))) {
                E.addAresta(v1, v2);//adiciona a aresta
            }
            System.out.println("Digite o valor de v1 ou -1 para sair");
            System.out.print("v1: ");
            v1 = s.nextInt();
        }
        this.modE = E.getModE();
        setGraus();

    }

    public void limparDados() {
        V = new ConjuntoDeVertices();
        E = new ConjuntoDeArestas();
        V.setReferencia(E);
        modE = 0;
        modV = 0;
    }

    public void printArestas() {
        E.printLista();
    }

    public void printVertices() {
        V.printLista();
    }

    public void printVizinhos(String vertice) {
        V.printVizinhos(vertice);
    }
    public void printGrauVertice(String v){
        System.out.println("Grau do vértice "+v+": "+V.getGrau(v));
    }

    public void printGrauDosVertices() {
        V.printGraus();
    }

    public void setGraus() {
        V.setGraus();
    }

    public void printAdijacencia() {
        System.out.print("---------Matriz de Adjacência---------");
        for (int i = 1; i <= modV; i++) {
            System.out.print("\n|");
            for (int j = 1; j <= modV; j++) {
                if (E.saoAdjacentes(i, j)) {
                    System.out.print(" 1 ");
                } else {
                    System.out.print(" 0 ");
                }
            }
            System.out.print("|");
        }
        System.out.println("\n\n\n");
    }

    public void printDiagonal() {
        System.out.print("---------Matriz Diagonal---------");
        for (int i = 1; i <= modV; i++) {
            System.out.print("\n|");
            for (int j = 1; j <= modV; j++) {
                if (i == j) {
                    System.out.print(" " + V.getGrau("" + i) + " ");
                } else {
                    System.out.print(" 0 ");
                }
            }
            System.out.print("|");
        }
        System.out.println("\n\n\n");
    }

    public void printLaplaciana() {
        System.out.print("---------Matriz Laplaciana---------");
        for (int i = 1; i <= modV; i++) {
            System.out.print("\n|");
            for (int j = 1; j <= modV; j++) {
                if (i == j) {
                    System.out.print(" " + V.getGrau("" + i) + " ");
                } else if (E.saoAdjacentes(i, j)) {
                    System.out.print("-1 ");
                } else {
                    System.out.print(" 0 ");
                }
            }
            System.out.print("|");
        }
        System.out.println("\n\n\n");
    }

    public void printIncidencia() {
        System.out.print("---------Matriz de Incidência---------");
        for (int i = 1; i <= modV; i++) { //for que cuida das linhas
            System.out.print("\n Vértice: " + i + "\t|"); //pula uma linha, escreve o vértice e coloca um "|" para fazer a barra da matriz
            for (int j = 1; j <= modE; j++) { // for que cuida das colunas
                if (i == E.getListaArestas().get(j - 1).getV1() || i == E.getListaArestas().get(j - 1).getV2()) //se a aresta tiver o vértice, imprime 1
                {
                    System.out.print(" 1 ");
                } else //senão, imprime 0
                {
                    System.out.print(" 0 ");
                }
            }
            System.out.print("|"); // coloca uma | no fim de cada linha
        }
        System.out.println("\nAs colunas estão na mesma ordem que o conjunto E:"); //legenda para as colunas
        printArestas(); //imprime as arestas em ordem
        System.out.println("\n\n\n");
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("==============INTERPRETADOR DE GRAFOS v1.0==============");
            System.out.println("Escolha uma opção: ");
            System.out.println("0 - sair");
            System.out.println("1 - digitar o caminho do arquivo");
            System.out.println("2 - Entrar com os dados manualmente");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    while(!lerTxtLaplaciana()){}
                    opcao = 0;
                    break;
                case 2:
                    lerManual();
                    opcao = 0;
                    break;
                default:
                    System.out.println("opção inválida");

            }
        } while (opcao != 0);
        do {
            System.out.println("==============INTERPRETADOR DE GRAFOS v1.0==============");
            System.out.println("Escolha uma opção:");
            System.out.println("0 - Sair");
            System.out.println("1 - Ver informações do grafo:");
            System.out.println("2 - Ver matriz de adjacência");
            System.out.println("3 - Ver matriz laplaciana");
            System.out.println("4 - Ver matriz diagonal");
            System.out.println("5 - Ver matriz de incidência");
            System.out.println("6 - Conferir grau do vértice");
            System.out.println("7 - Mostrar vizinhança do vértice");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 0: 
                    break;
                case 1:
                    printVertices();
                    printArestas();
                    System.out.println("|E| = "+getModE());
                    System.out.println("|V| = "+getModV());
                    break;
                case 2:
                    printAdijacencia();
                    break;
                case 3:
                    printLaplaciana();
                    break;
                case 4:
                    printDiagonal();
                    break;
                case 5:
                    printIncidencia();
                    break;
                case 6:
                    Scanner s1 = new Scanner(System.in);
                    System.out.print("Vertice:");
                    String vertice = s1.nextLine();
                    System.out.println("");
                    printGrauVertice(vertice);
                    break;
                case 7:
                    Scanner s2 = new Scanner(System.in);
                    System.out.print("Vertice:");
                    String vertice2 = s2.nextLine();
                    System.out.println("");
                    printVizinhos(vertice2);
                    break;
                default:
                    System.out.println("Opção inválida!");

            }
        } while (opcao != 0);

    }

}
