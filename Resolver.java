import javax.swing.*;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Resolver {
    private ArrayList<Boton> solucion = new ArrayList<>();
    private int min, max;

    public void resuelve(Boton current) {
        if (solucion.size() == 0) {
            if (current.getFila() == 0 && current.getCol() == 0) {
                current.getBoton().setEnabled(false);
                solucion.add(current);
            } else {
                JOptionPane.showMessageDialog(null, "Puzzle debe empezar arriba izquierda");
            }

        } else {
            Boton previous = solucion.get(solucion.size() - 1);
            int num = previous.getNum();

            if (num == max) {
                num = min;
            } else {
                num++;
            }

            if (current.getNum() == num && current.getNum() != previous.getNum()) {
                if (current.getFila() - 1 == previous.getFila() && current.getCol() - 1 == previous.getCol()) {
                    // Diagonal Arriba Izquierda
                    if (search(solucion, current.getFila(), current.getCol() - 1).getVisited() != 3
                            && search(solucion, current.getFila() - 1, current.getCol()).getVisited() != 6) {
                        current.getBoton().setEnabled(false);
                        current.setVisited(1);
                        solucion.add(current);
                    }
                } else if (current.getFila() - 1 == previous.getFila() && current.getCol() == previous.getCol()) {
                    // Arriba
                    current.getBoton().setEnabled(false);
                    current.setVisited(2);
                    solucion.add(current);
                } else if (current.getFila() - 1 == previous.getFila() && current.getCol() + 1 == previous.getCol()) {
                    // Diagonal Arriba Derecha
                    if (search(solucion, current.getFila(), current.getCol() + 1).getVisited() != 1
                            && search(solucion, current.getFila() - 1, current.getCol()).getVisited() != 8) {
                        current.getBoton().setEnabled(false);
                        current.setVisited(3);
                        solucion.add(current);
                    }
                } else if (current.getFila() == previous.getFila() && current.getCol() == previous.getCol() + 1) {
                    // Izquierda
                    current.getBoton().setEnabled(false);
                    current.setVisited(4);
                    solucion.add(current);
                } else if (current.getFila() == previous.getFila() && current.getCol() + 1 == previous.getCol()) {
                    // Derecha
                    current.getBoton().setEnabled(false);
                    current.setVisited(5);
                    solucion.add(current);
                } else if (current.getFila() + 1 == previous.getFila() && current.getCol() - 1 == previous.getCol()) {
                    // Diagonal Abajo Izquierda
                    if (search(solucion, current.getFila(), current.getCol() - 1).getVisited() != 8
                            && search(solucion, current.getFila() + 1, current.getCol()).getVisited() != 1) {
                        current.getBoton().setEnabled(false);
                        current.setVisited(6);
                        solucion.add(current);
                    }
                } else if (current.getFila() + 1 == previous.getFila() && current.getCol() == previous.getCol()) {
                    // Abajo
                    current.getBoton().setEnabled(false);
                    current.setVisited(7);
                    solucion.add(current);
                } else if (current.getFila() + 1 == previous.getFila() && current.getCol() + 1 == previous.getCol()) {
                    // Diagonal Abajo Derecha
                    if (search(solucion, current.getFila(), current.getCol() + 1).getVisited() != 6
                            && search(solucion, current.getFila() + 1, current.getCol()).getVisited() != 3) {
                        current.getBoton().setEnabled(false);
                        current.setVisited(8);
                        solucion.add(current);
                    }
                }
            }

        }
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Busca una casilla dentro de la lista indicada en función de sus coordenadas.
     * 
     * @param list
     * @param fila
     * @param col
     * @return
     */
    public Boton search(ArrayList<Boton> list, int fila, int col) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFila() == fila && list.get(i).getCol() == col) {
                return list.get(i);
            }
        }

        return null;
    }
}
