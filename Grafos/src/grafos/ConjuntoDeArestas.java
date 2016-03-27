package grafos;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConjuntoDeArestas {//classe que lida com a lista de arestas, para que uma aresta não seja adicionada repetidamente

    private List<Aresta> lista;

    public ConjuntoDeArestas() {
        lista = new ArrayList<>();
    }

    public void addAresta(int v1, int v2) { //adiciona uma aresta com os vértices v1 e v2 se a lista já não tiver. 
        int i;                              //(não adiciona (1,2) se já tiver (2,1) ou (1,2) na lista, por exemplo.
        for (i = 0; i < lista.size(); i++) {
            if ((v1 == lista.get(i).getV1() && v2 == lista.get(i).getV2()) || (v2 == lista.get(i).getV1() && v1 == lista.get(i).getV2())) //                throw new InvalidParameterException("A aresta já foi adicionada");
            {
                break;
            }
        }
        if (i == lista.size()) {
            if (v1 < v2) {
                lista.add(new Aresta(v1, v2));
            } else {
                lista.add(new Aresta(v2, v1));
            }
        }
    }

    public void sort() {
        Collections.sort(lista);
    }

    public void printLista() {
        String s = "E = {";
        int tam = lista.size();
        for (int i=0;i<tam;i++) {
            s+=lista.get(i).toString();
            if(i<(tam-1))
                s+=";";
            else
                s+="}";
                
        }
        System.out.println(s);
    }
}
