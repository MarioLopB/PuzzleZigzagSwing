import java.awt.*;
import javax.swing.*;

public class Flecha {
    private JLabel flecha = new JLabel(" ", SwingConstants.CENTER);
    private int fila, col;

    public Flecha(int fila, int col){
        this.fila = fila;
        this.col = col;

        flecha.setFont(new Font("Times New Roman", Font.BOLD, 40));
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
