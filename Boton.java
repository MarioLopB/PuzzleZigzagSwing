import javax.swing.*;

public class Boton {
    private JButton boton;
    private int fila, col;

    public Boton(JButton boton, int fila, int col){
        this.boton = boton;
        this.fila = fila;
        this.col = col;
    }

    public JButton getBoton(){
        return boton;
    }

    public int getFila(){
        return fila;
    }

    public int getCol(){
        return col;
    }
}
