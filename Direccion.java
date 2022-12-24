import java.util.*;

public class Direccion {
    public int num;
    public int fila, col;
    public int visited = 0;

    /**
     * Constructor de la clase Dirección.
     * 
     * @param fila
     * @param col
     * @param num
     */
    public Direccion(int fila, int col, int num){
        this.fila = fila;
        this.col = col;
        this.num = num;
    }

    /**
     * Establece la dirección de un nodo y, por tanto, si ha sido visitado
     * 
     * @param visited
     */
    public void setVisited(int visited){
        this.visited = visited;
    }
    
}
