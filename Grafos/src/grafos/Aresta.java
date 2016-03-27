package grafos;
public class Aresta implements Comparable<Aresta>{
    private int V1;
    private int V2;

    public Aresta(int V1, int V2) {
        this.V1 = V1;
        this.V2 = V2;
    }

    public int getV1() {
        return V1;
    }

    public void setV1(int V1) {
        this.V1 = V1;
    }

    public int getV2() {
        return V2;
    }

    public void setV2(int V2) {
        this.V2 = V2;
    }

    @Override
    public String toString() {
        return "("+V1+","+V2+")";
    }

    @Override
    public int compareTo(Aresta a) { //implementa o Comparable e sobrescreve esse método para que 
        if(this.V1==a.getV1())      //a lista de Aresta possa ser ordenada pelo método Collections.sort()
            return V2 - a.getV2();
        return V1- a.getV1();
    }
    
    
    
}