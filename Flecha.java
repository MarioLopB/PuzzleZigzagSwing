import java.awt.*;
import javax.swing.*;

public class Flecha {
    private JLabel flecha = new JLabel(" ");
    private int fila, col;

    public Flecha(int fila, int col){
        this.fila = fila;
        this.col = col;
    }

    public JLabel getFlecha(){
        return flecha;
    }

    public int getFila(){
        return fila;
    }

    public int getCol(){
        return col;
    }
}
