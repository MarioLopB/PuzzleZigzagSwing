import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Boton {
    
    private JButton boton;
    private int fila, col;

    public Boton(JButton boton, int fila, int col){
        this.boton = boton;
        this.fila = fila;
        this.col = col;

        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                boton.setEnabled(false);
            }
        });
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
