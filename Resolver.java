import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Resolver {
    private ArrayList<Boton> solucion;
    private ArrayList<Boton> botones;

    public Resolver (){
        this.solucion = new ArrayList<>();
    }

    public void resuelve(Boton current){
        if(solucion.size()==0 && current.getFila() == 0 && current.getCol() == 0){
            current.getBoton().setEnabled(false);
            solucion.add(current);
        } else if(){

        } else{
            JOptionPane.showMessageDialog(null, "Puzzle debe empezar arriba izquierda");
        }
    }
}
