import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.annotation.IncompleteAnnotationException;
import java.util.*;

public class Tabla {
    public JFrame frame;
    private int n, m;
    private ArrayList<Boton> botones;

    public Tabla(int n, int m) {
        botones = new ArrayList<>();
        this.n = n;
        this.m = m;
        initialize();
    }

    public void initialize() {
        frame = new JFrame("Tabla");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel panel1 = new JPanel();
        GridLayout matriz = new GridLayout(n, m);
        panel1.setLayout(matriz);

        for (int i = 0; i < (n * 2) - 1; i++) {
            for (int j = 0; j < (m * 2) - 1; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    Boton newbutton = new Boton(new JButton(String.valueOf(radomNum())), i, j);
                    botones.add(newbutton);
                    panel1.add(newbutton.getBoton());
                } else{
                    JLabel label = new JLabel(" ");
                    panel1.add(label);
                }
            }
        }

        frame.getContentPane().add(BorderLayout.CENTER, panel1);
        frame.setVisible(true);

    }

    public int radomNum() {
        return (int) (Math.random() * 9 + 1);
    }

}