import javax.swing.*;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.*;

public class Resolver {
    private ArrayList<Boton> solucion = new ArrayList<Boton>();
    private ArrayList<Boton> rehacer = new ArrayList<Boton>();
    private ArrayList<Boton> botones;
    private ArrayList<Flecha> flechas;
    private int min, max;

    public void resuelve(int fila, int col) {
        Boton current = searchBoton(botones, fila, col);
        boolean isok = false;

        if (solucion.size() == 0) {
            if (current.getFila() == 0 && current.getCol() == 0) {
                current.getBoton().setEnabled(false);
                solucion.add(current);
                isok = true;
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
                if (current.getFila() - 2 == previous.getFila() && current.getCol() - 2 == previous.getCol()) {
                    // Diagonal Arriba Izquierda
                    if (searchBoton(botones, current.getFila(), current.getCol() - 2).getVisited() != 3
                            && searchBoton(botones, current.getFila() - 2, current.getCol()).getVisited() != 6) {
                        current.getBoton().setEnabled(false);
                        current.setVisited(1);
                        solucion.add(current);
                        isok = true;
                        searchFlecha(flechas, current.getFila() - 1, current.getCol() - 1).getFlecha().setText("\\");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error de cruce");
                    }
                } else if (current.getFila() - 2 == previous.getFila() && current.getCol() == previous.getCol()) {
                    // Arriba
                    current.getBoton().setEnabled(false);
                    current.setVisited(2);
                    solucion.add(current);
                    isok = true;
                    searchFlecha(flechas, current.getFila() - 1, current.getCol()).getFlecha().setText("|");
                    ;
                } else if (current.getFila() - 2 == previous.getFila() && current.getCol() + 2 == previous.getCol()) {
                    // Diagonal Arriba Derecha
                    if (searchBoton(botones, current.getFila(), current.getCol() + 2).getVisited() != 1
                            && searchBoton(botones, current.getFila() - 2, current.getCol()).getVisited() != 8) {
                        current.getBoton().setEnabled(false);
                        current.setVisited(3);
                        solucion.add(current);
                        isok = true;
                        searchFlecha(flechas, current.getFila() - 1, current.getCol() + 1).getFlecha().setText("/");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error de cruce");
                    }
                } else if (current.getFila() == previous.getFila() && current.getCol() - 2 == previous.getCol()) {
                    // Izquierda
                    current.getBoton().setEnabled(false);
                    current.setVisited(4);
                    solucion.add(current);
                    isok = true;
                    searchFlecha(flechas, current.getFila(), current.getCol() - 1).getFlecha().setText("-");
                } else if (current.getFila() == previous.getFila() && current.getCol() + 2 == previous.getCol()) {
                    // Derecha
                    current.getBoton().setEnabled(false);
                    current.setVisited(5);
                    solucion.add(current);
                    isok = true;
                    searchFlecha(flechas, current.getFila(), current.getCol() + 1).getFlecha().setText("-");
                } else if (current.getFila() + 2 == previous.getFila() && current.getCol() - 2 == previous.getCol()) {
                    // Diagonal Abajo Izquierda
                    if (searchBoton(botones, current.getFila(), current.getCol() - 2).getVisited() != 8
                            && searchBoton(botones, current.getFila() + 2, current.getCol()).getVisited() != 2) {
                        current.getBoton().setEnabled(false);
                        current.setVisited(6);
                        solucion.add(current);
                        isok = true;
                        searchFlecha(flechas, current.getFila() + 1, current.getCol() - 1).getFlecha().setText("/");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error de cruce");
                    }
                } else if (current.getFila() + 2 == previous.getFila() && current.getCol() == previous.getCol()) {
                    // Abajo
                    current.getBoton().setEnabled(false);
                    current.setVisited(7);
                    solucion.add(current);
                    isok = true;
                    searchFlecha(flechas, current.getFila() + 1, current.getCol()).getFlecha().setText("|");
                } else if (current.getFila() + 2 == previous.getFila() && current.getCol() + 2 == previous.getCol()) {
                    // Diagonal Abajo Derecha
                    if (searchBoton(botones, current.getFila(), current.getCol() + 2).getVisited() != 6
                            && searchBoton(botones, current.getFila() + 2, current.getCol()).getVisited() != 3) {
                        current.getBoton().setEnabled(false);
                        current.setVisited(8);
                        solucion.add(current);
                        isok = true;
                        searchFlecha(flechas, current.getFila() + 1, current.getCol() + 1).getFlecha().setText("\\");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error de cruce");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Casilla demasiado lejana");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Valor incorrecto");
            }

        }

        if(current.isFinal() && isok && solucion.size() == botones.size()){
            JOptionPane.showMessageDialog(null, "Has ganado.");
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
    public Boton searchBoton(ArrayList<Boton> list, int fila, int col) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFila() == fila && list.get(i).getCol() == col) {
                return list.get(i);
            }
        }

        return null;
    }

    public Flecha searchFlecha(ArrayList<Flecha> list, int fila, int col) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFila() == fila && list.get(i).getCol() == col) {
                return list.get(i);
            }
        }

        return null;
    }

    public void setBotones(ArrayList<Boton> botones) {
        this.botones = botones;
    }

    public void setFlechas(ArrayList<Flecha> flechas) {
        this.flechas = flechas;
    }

    public void removeLast() {
        if (solucion.size() != 0) {
            Boton current = solucion.get(solucion.size() - 1);
            rehacer.add(current);
            current.getBoton().setEnabled(true);

            switch (current.getVisited()) {
                case 1:
                    searchFlecha(flechas, current.getFila() - 1, current.getCol() - 1).getFlecha().setText("");
                    break;
                case 2:
                    searchFlecha(flechas, current.getFila() - 1, current.getCol()).getFlecha().setText("");
                    break;
                case 3:
                    searchFlecha(flechas, current.getFila() - 1, current.getCol() + 1).getFlecha().setText("");
                    break;
                case 4:
                    searchFlecha(flechas, current.getFila(), current.getCol() - 1).getFlecha().setText("");
                    break;
                case 5:
                    searchFlecha(flechas, current.getFila(), current.getCol() + 1).getFlecha().setText("");
                    break;
                case 6:
                    searchFlecha(flechas, current.getFila() + 1, current.getCol() - 1).getFlecha().setText("");
                    break;
                case 7:
                    searchFlecha(flechas, current.getFila() + 1, current.getCol()).getFlecha().setText("");
                    break;
                case 8:
                    searchFlecha(flechas, current.getFila() + 1, current.getCol() + 1).getFlecha().setText("");
                    break;
            }

            current.setVisited(0);

            solucion.remove(solucion.get(solucion.size() - 1));
        } else {
            JOptionPane.showMessageDialog(null, "No se puede desahacer.");
        }
    }

    public void reHacer() {
        if (solucion.size() != botones.size() && rehacer.size() != 0) {
            Boton current = rehacer.get(rehacer.size() - 1);
            current.getBoton().setEnabled(false);

            switch (current.getVisited()) {
                case 1:
                    searchFlecha(flechas, current.getFila() - 1, current.getCol() - 1).getFlecha().setText("\\");
                    break;
                case 2:
                    searchFlecha(flechas, current.getFila() - 1, current.getCol()).getFlecha().setText("|");
                    break;
                case 3:
                    searchFlecha(flechas, current.getFila() - 1, current.getCol() + 1).getFlecha().setText("/");
                    break;
                case 4:
                    searchFlecha(flechas, current.getFila(), current.getCol() - 1).getFlecha().setText("-");
                    break;
                case 5:
                    searchFlecha(flechas, current.getFila(), current.getCol() + 1).getFlecha().setText("-");
                    break;
                case 6:
                    searchFlecha(flechas, current.getFila() + 1, current.getCol() - 1).getFlecha().setText("/");
                    break;
                case 7:
                    searchFlecha(flechas, current.getFila() + 1, current.getCol()).getFlecha().setText("|");
                    break;
                case 8:
                    searchFlecha(flechas, current.getFila() + 1, current.getCol() + 1).getFlecha().setText("/");
                    break;
            }

            solucion.add(current);

            rehacer.remove(rehacer.get(rehacer.size() - 1));
        } else {
            JOptionPane.showMessageDialog(null, "No se puede reahacer.");
        }
    }
}
