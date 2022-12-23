import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Boton {
    
    private JButton boton;
    private int fila, col;
    private Resolver intento = new Resolver();

    public Boton(JButton boton, int fila, int col){
        this.boton = boton;
        this.fila = fila;
        this.col = col;

        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                intento.resuelve(new Boton(boton, fila, col));
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
