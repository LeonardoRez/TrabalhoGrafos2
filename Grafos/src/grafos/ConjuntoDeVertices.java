package grafos;

import java.util.ArrayList;
import java.util.List;

public class ConjuntoDeVertices {

    private List<Vertice> lista;
    private int controladorID;

    public ConjuntoDeVertices() {
        lista = new ArrayList<>();
        controladorID = 1;
    }

    public void addVertice(Vertice v) {//adiciona um vertice se ele não estiver na lista

        if (confereVertice(v)) {
            System.out.println("O vertice "+v.getVertice()+" já existe na lista!");
        } else {
            lista.add(v);
        }
    }

    public boolean confereVertice(Vertice v) {//procura o vertice na lista
        int i;
        for (i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == v.getId()) {
                return true;//se já estiver na lista
            }
        }
        return false; //se não estiver na lista
    }

    public void printLista() {
        String s = "V = {";
        for(int i=0;i<lista.size();i++){
            s+=lista.get(i).getVertice();
            if(i==(lista.size()-1))
                s+="}";
            else
                s+=";";
        }
        System.out.println(s);
    }
}
