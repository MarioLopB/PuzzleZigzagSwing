import javax.swing.*;
import javax.swing.event.UndoableEditListener;

import java.awt.*;
import java.awt.event.*;

public class Boton {
    
    private JButton boton;
    private int fila, col, visited;
    private Resolver intento;

    public Boton(JButton boton, int fila, int col, Resolver intento){
        this.boton = boton;
        this.fila = fila;
        this.col = col;
        this.intento = intento;

        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    intento.resuelve(fila, col);
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

    public int getNum(){
        return Integer.parseInt(boton.getText());
    }

    public int getVisited(){
        return visited;
    }

    public void setVisited(int visited){
        this.visited = visited;
    }
    
}
