import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Tabla{
    public JFrame frame;
    private ArrayList<Boton> botones;

    public Tabla(){
        
    }

    public Tabla(int n, int m){
        frame = new JFrame("Tabla");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);

        JPanel panel1 = new JPanel();

        for(int i=0; i<n; i++){
            for(int j = 0; j < m; j++){
                Boton newbutton = new Boton(new JButton(String.valueOf(radomNum())),i,j);
                botones.add(newbutton);
                panel1.add(newbutton.getBoton());
            }
        }

        frame.getContentPane().add(BorderLayout.CENTER, panel1);
        frame.setVisible(true);
        
    }

    public int radomNum(){
        return (int)(Math.random()*9+1);
    }

    
}