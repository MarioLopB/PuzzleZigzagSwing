import java.awt.*;
import javax.swing.*;

public class Flecha {
    private JLabel flecha = new JLabel(" ", SwingConstants.CENTER);
    private int fila, col;

    /**
     * 
     * @param fila
     * @param col
     * 
     * Constructor de flecha
     */
    public Flecha(int fila, int col){
        this.fila = fila;
        this.col = col;

        flecha.setFont(new Font("Times New Roman", Font.BOLD, 50));
    }

    /**
     * 
     * @return jlabel que contiene la flecha
     */
    public JLabel getFlecha(){
        return flecha;
    }

    /**
     * 
     * @return fila
     */
    public int getFila(){
        return fila;
    }

    /**
     * 
     * @return columna
     */
    public int getCol(){
        return col;
    }
}
