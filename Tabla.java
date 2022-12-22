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
        frame.setSize(700, 700);

        JPanel inicio1 = new JPanel();
        JPanel inicio2 = new JPanel();

        FlowLayout linea = new FlowLayout();

        inicio1.setLayout(linea);

        frame.getContentPane().add(BorderLayout.NORTH, inicio1);

        JLabel etfilas = new JLabel("FILAS");
        JTextField numfilas = new JTextField();
        numfilas.setColumns(2);

        JLabel etcol = new JLabel("COLUMNAS");
        JTextField numcol = new JTextField();
        numcol.setColumns(2);

        JButton selec = new JButton("Seleccionar");
        selec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFilas(Integer.parseInt(numfilas.getText()));
                setColumns(Integer.parseInt(numcol.getText()));

                GridLayout combos = new GridLayout(n,m);
                inicio2.setLayout(combos);

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        JComboBox opciones = new JComboBox<Integer>();
                        opciones.add(1);
                        opciones.add(2);
                        opciones.add(3);
                        opciones.add(4);
                        opciones.add(5);
                        opciones.add(6);
                        opciones.add(7);
                        opciones.add(8);
                        opciones.add(9);

                        combos.add(opciones);
                    }
                }
            }
        });

        JButton next = new JButton("Siguiente");

        inicio1.add(etfilas);
        inicio1.add(numfilas);
        inicio1.add(etcol);
        inicio1.add(numcol);
        inicio1.add(selec);
        inicio1.add(next);
        next.setVisible(false);
        inicio1.setVisible(true);

        JPanel juego = new JPanel();
        GridLayout matriz = new GridLayout(n * 2 - 1, m * 2 - 1);
        juego.setLayout(matriz);
        juego.setVisible(false);

        int fila = 0, col = 0;

        for (int i = 0; i < (n * 2) - 1; i++) {
            for (int j = 0; j < (m * 2) - 1; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    Boton newbutton = new Boton(new JButton(String.valueOf(radomNum())), fila, col);
                    botones.add(newbutton);
                    juego.add(newbutton.getBoton(), i, j);

                    col++;
                } else {
                    JLabel label = new JLabel(" ");
                    juego.add(label, i, j);
                }
            }
            col = 0;
        }

        frame.getContentPane().add(BorderLayout.CENTER, juego);
        frame.setVisible(true);

    }

    public int radomNum() {
        return (int) (Math.random() * 9 + 1);
    }

    public void setFilas(int filas){
        this.n = filas;
    }

    public void setColumns(int col){
        this.m = col;
    }
}

