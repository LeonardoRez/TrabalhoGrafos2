package grafos;

import java.util.ArrayList;
import java.util.List;

public class ConjuntoDeVertices {

    private List<Vertice> lista;
    private int controladorID;
    private ConjuntoDeArestas referencia;

    public ConjuntoDeVertices() {
        lista = new ArrayList<>();
        controladorID = 1;
        referencia = null;
    }

    public void setReferencia(ConjuntoDeArestas arestas) {
        this.referencia = arestas;
    }

    public void addVertice(Vertice v) {//adiciona um vertice se ele não estiver na lista

        if (confereVertice(v.getVertice())) {
            System.out.println("O vertice " + v.getVertice() + " já existe na lista!");
        } else {
            lista.add(v);
        }
    }

    public boolean confereVertice(String v) {//procura o vertice na lista
        int i;
        for (i = 0; i < lista.size(); i++) {
            if (lista.get(i).getVertice().equalsIgnoreCase(v)) {
                return true;//se já estiver na lista
            }
        }
        return false; //se não estiver na lista
    }

    public void printLista() {
        String s = "V = {";
        int quant = lista.size();
        for (int i = 0; i < (quant - 1); i++) {
            s += lista.get(i).getVertice() + ";";
        }
        s += lista.get((quant - 1)).getVertice() + "}";
        System.out.println(s);
    }

    public void printVizinhos(String vertice) {
        Vertice v = null;
        for (Vertice vert : lista) { //procurando vertice na lista
            if (vert.getVertice().equalsIgnoreCase(vertice)) {
                v = vert;
            }
        }
        if (v == null) {
            System.out.println("Esse vértice não existe");
        } else {//se o vértice existir:
            String vizinhos = "";
            int quant = referencia.getListaArestas().size();
            for (int i = 0; i < (quant); i++) {
                if (referencia.getListaArestas().get(i).getV1() == v.getId()) {
                    vizinhos += (referencia.getListaArestas().get(i).getV2() + ";");

                } else if (referencia.getListaArestas().get(i).getV2() == v.getId()) {
                    vizinhos += (referencia.getListaArestas().get(i).getV1() + ";");
                }
            }
            String vizinhosFinal = "Vizinhança de " + vertice + " ={";
            for (int i = 0; i < (vizinhos.length() - 1); i++) {
                vizinhosFinal += vizinhos.charAt(i);
            }
            vizinhosFinal += "}";
            System.out.println(vizinhosFinal);
        }
    }

    public void printGraus() {
        System.out.println("Grau dos vértices:");
        for (Vertice v : lista) {
            System.out.println("Vertice " + v.getVertice() + "= " + v.getGrau());
        }
    }

    public int getModV() {
        return lista.size();
    }

    public void setGraus() {
        int quant = referencia.getListaArestas().size();
        lista.stream().forEach((v) -> {
            v.setGrau(0);
            for (int i = 0; i < (quant); i++) {
                if (referencia.getListaArestas().get(i).getV1() == v.getId()) {
                    v.setGrau(v.getGrau() + 1);

                } else if (referencia.getListaArestas().get(i).getV2() == v.getId()) {
                    v.setGrau(v.getGrau() + 1);
                }
            }
        });
    }
    
    public int getGrau(String vertice){
        for(Vertice v:lista){
            if(v.getVertice().equalsIgnoreCase(vertice))
                return v.getGrau();
        }
        return 0;
    }
}
