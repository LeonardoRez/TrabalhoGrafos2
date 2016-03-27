package grafos;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

    private String vertice;
    private int id;
    private List<Vertice> vizinhanca;

    public Vertice(String vertice, int id) {
        this.vertice = vertice;
        this.id = id;
        vizinhanca = new ArrayList<>();
    }

    public Vertice(int id) {
        this.vertice = "" + id;
        this.id = id;
        vizinhanca = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getVertice() {
        return vertice;
    }

    public void addVizinho(Vertice vizinho) {//adiciona um vizinho se ele não estiver na lista "vizinhanca"

        if (confereVizinho(vizinho)) {
            System.out.println("O vertice "+vizinho.getVertice()+" já é vizinho de "+this.vertice+"!");
        } else {
            vizinhanca.add(vizinho);
        }
    }

    public boolean confereVizinho(Vertice vizinho) {//procura o vizinho na lista vizinhanca
        int i;
        for (i = 0; i < vizinhanca.size(); i++) {
            if (vizinhanca.get(i).getId() == vizinho.getId()) {
                return true;//se já estiver na lista
            }
        }
        return false; //se não estiver na lista
    }

    public void printVizinhos() { //imprime a vizinhança
        for (Vertice v : vizinhanca) {
            System.out.println("" + v.getVertice());
        }
    }

}
