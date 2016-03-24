/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

/**
 *
 * @author aluno
 */
public class Grafos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Aresta teste = new Aresta(1, 2);
//        System.out.println(teste.toString());
        Grafo g = new Grafo();
        g.lerTxtLaplaciana("C:/Users/Leonardo/Desktop/teste.txt");
    }
    
}
