import java.awt.*;

import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;

import java.awt.event.*;
import java.lang.annotation.IncompleteAnnotationException;
import java.util.*;

public class Tabla {
    public JFrame frame;
    private int n, m;
    private ArrayList<Boton> botones;
    private ArrayList<JTextField> casillas;
    private JPanel inicio1, inicio2, inicio3, juego;

    public Tabla() {
        botones = new ArrayList<>();
        casillas = new ArrayList<>();
        initialize();
    }

    public void initialize() {
        frame = new JFrame("Tabla");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

        inicio1 = new JPanel();

        inicio2 = new JPanel();

        inicio3 = new JPanel();

        FlowLayout linea = new FlowLayout();

        inicio1.setLayout(linea);

        frame.getContentPane().add(BorderLayout.NORTH, inicio1);

        JLabel etfilas = new JLabel("FILAS");
        JTextField numfilas = new JTextField();
        numfilas.setColumns(2);

        JLabel etcol = new JLabel("COLUMNAS");
        JTextField numcol = new JTextField();
        numcol.setColumns(2);

        JButton next = new JButton("Siguiente");

        JButton selec = new JButton("Seleccionar");
        selec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(inicio2);
                frame.getContentPane().remove(inicio3);
                inicio3 = new JPanel(new FlowLayout());
                inicio2 = new JPanel();
                frame.repaint();
                frame.validate();

                setFilas(Integer.parseInt(numfilas.getText()));
                setColumns(Integer.parseInt(numcol.getText()));

                if (n <= 10 && n > 0 && m <= 10 && m > 0) {

                    GridLayout datos = new GridLayout(n, m);
                    inicio2.setLayout(datos);

                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            JTextField t = new JTextField(" ");
                            casillas.add(t);
                            inicio2.add(t);
                        }
                    }

                    frame.getContentPane().add(inicio2, BorderLayout.CENTER);

                    frame.repaint();
                    frame.validate();

                    inicio3.add(next);

                    frame.getContentPane().add(inicio3, BorderLayout.SOUTH);

                    frame.repaint();
                    frame.validate();

                } else {
                    JOptionPane.showMessageDialog(null, "Entrada de filas o columnas incorrecta");
                }

            }
        });

        inicio1.add(etfilas);
        inicio1.add(numfilas);
        inicio1.add(etcol);
        inicio1.add(numcol);
        inicio1.add(selec);
        inicio1.setVisible(true);

        juego = new JPanel();

        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();

                juego = new JPanel();
                GridLayout matriz = new GridLayout(n * 2 - 1, m * 2 - 1);
                juego.setLayout(matriz);
                frame.validate();
                frame.repaint();

                int fila = 0, col = 0, counter = 0;;

                //Creo el panel con los botones del juego.
                for (int i = 0; i < (n * 2) - 1; i++) {
                    for (int j = 0; j < (m * 2) - 1; j++) {
                        if (i % 2 == 0 && j % 2 == 0) {
                            String valor = casillas.get(counter).getText();
                            Boton newbutton = new Boton(new JButton(valor), fila, col);
                            botones.add(newbutton);
                            juego.add(newbutton.getBoton(), i, j);
                            counter++;
                            col++;
                        } else {
                            JLabel label = new JLabel(" ");
                            juego.add(label, i, j);
                        }
                    }
                    col = 0;
                    if(i%2 != 0){
                        fila++;
                    }
                }

                frame.getContentPane().add(BorderLayout.CENTER, juego);
                frame.validate();
                frame.repaint();
                frame.setVisible(true);
            }
        });

    }

    public int radomNum() {
        return (int) (Math.random() * 9 + 1);
    }

    public void setFilas(int filas) {
        this.n = filas;
    }

    public void setColumns(int col) {
        this.m = col;
    }
}
