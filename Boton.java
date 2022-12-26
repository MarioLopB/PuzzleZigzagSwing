import javax.swing.*;
import javax.swing.event.UndoableEditListener;

import java.awt.*;
import java.awt.event.*;

public class Boton {
    
    private JButton boton;
    private int fila, col, visited;
    private Resolver intento;
    private boolean last = false;

    public Boton(JButton boton, int fila, int col, Resolver intento){
        this.boton = boton;
        this.fila = fila;
        this.col = col;
        this.intento = intento;

        //Acción del botón de juego
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    intento.resuelve(fila, col);
            }
        });
    }

    /**
     * 
     * @return boton
     */
    public JButton getBoton(){
        return boton;
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
     * @return col
     */
    public int getCol(){
        return col;
    }

    /**
     * 
     * @return valor del boton
     */
    public int getNum(){
        return Integer.parseInt(boton.getText());
    }

    /**
     * 
     * @return visited
     */
    public int getVisited(){
        return visited;
    }

    /**
     * Establece la variable visited
     * 
     * @param visited
     */
    public void setVisited(int visited){
        this.visited = visited;
    }

    /**
     * Estable que es el último boton
     */
    public void setFinal(){
        this.last = true;
    }

    /**
     * 
     * @return last
     */
    public boolean isFinal(){
        return this.last;
    }
    
}
