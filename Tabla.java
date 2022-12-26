import java.awt.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.xml.validation.Validator;

import java.awt.event.*;
import java.lang.annotation.IncompleteAnnotationException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Flow;

public class Tabla {
    public JFrame frame;
    private int n, m;
    private ArrayList<Boton> botones;
    private ArrayList<JTextField> casillas;
    private ArrayList<Flecha> flechas;
    private JPanel inicio1, inicio2, inicio3, juego;
    private JLabel etfilas, etcol;
    private JTextField numfilas, numcol;
    private JMenuBar menubar;
    private JButton selec, next;
    private JMenu menu1;
    private UndoManager manager;
    private JMenuItem fichero, ayuda, inicio, deshacer, rehacer;
    private Resolver partida;
    private Ayuda asist;
    public boolean isInicio = false;;

    public Tabla() {
        initialize();
    }

    /**
     * Inicia la aplicación
     */
    public void initialize() {
        frame = new JFrame("ZigZag");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

        menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        menu1 = new JMenu("Opciones");
        menubar.add(menu1);

        fichero = new JMenuItem("Cargar fichero");
        menu1.add(fichero);
        ayuda = new JMenuItem("Ayuda");
        menu1.add(ayuda);
        inicio = new JMenuItem("Inicio");
        menu1.add(inicio);
        deshacer = new JMenuItem("Deshacer");
        menu1.add(deshacer);
        rehacer = new JMenuItem("Rehacer");
        menu1.add(rehacer);

        // Item para cargar ficheros
        fichero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarFichero();
            }
        });

        // Item para deshacer acción
        deshacer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    if(isInicio){
                        manager.undo();
                    } else{
                        partida.removeLast();
                    }
                } catch(Exception d){
                    JOptionPane.showMessageDialog(null, "No se puede deshacer.");
                }
            }
        });

        // Item para rehacer action
        rehacer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    if(isInicio){
                        manager.redo();
                    } else{
                        partida.reHacer();
                    }
                } catch(Exception d){
                    JOptionPane.showMessageDialog(null, "No se puede rehacer.");
                }
            }
        });

        // Boton de ayuda
        ayuda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final SwingWorker worker = new SwingWorker() {

                    @Override
                    protected Object doInBackground() throws Exception {
                        Help();
                        return null;
                    }

                };

                worker.execute();
            }
        });

        inicio1 = new JPanel();

        inicio2 = new JPanel();

        inicio3 = new JPanel();

        FlowLayout linea = new FlowLayout();

        inicio1.setLayout(linea);

        frame.getContentPane().add(BorderLayout.NORTH, inicio1);

        etfilas = new JLabel("FILAS");
        numfilas = new JTextField();
        numfilas.setColumns(2);

        etcol = new JLabel("COLUMNAS");
        numcol = new JTextField();
        numcol.setColumns(2);

        next = new JButton("Siguiente");

        selec = new JButton("Seleccionar");
        selec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearIncio(numfilas, numcol, next);
            }
        });

        inicio1.add(etfilas);
        inicio1.add(numfilas);
        inicio1.add(etcol);
        inicio1.add(numcol);
        inicio1.add(selec);
        inicio1.setVisible(true);

        // Vuelve al inicio
        inicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volverInicio();
            }
        });

        juego = new JPanel();

        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaJuego();
            }
        });

    }

    /**
     * Establece número de filas
     * 
     * @param filas
     */
    public void setFilas(int filas) {
        this.n = filas;
    }

    /**
     * Establece número de columnas
     * 
     * @param col
     */
    public void setColumns(int col) {
        this.m = col;
    }

    /**
     * Crea la entrada de datos del puzzle con texfields
     * 
     * @param numfilas
     * @param numcol
     * @param next
     */
    public void crearIncio(JTextField numfilas, JTextField numcol, JButton next) {
        manager = new UndoManager();

        frame.getContentPane().remove(inicio2);
        frame.getContentPane().remove(inicio3);
        inicio3 = new JPanel(new FlowLayout());
        inicio2 = new JPanel();
        frame.repaint();
        frame.validate();
        casillas = new ArrayList<JTextField>();

        try {
            setFilas(Integer.parseInt(numfilas.getText()));
            setColumns(Integer.parseInt(numcol.getText()));

            if (n <= 10 && n > 0 && m <= 10 && m > 0) {

                GridLayout datos = new GridLayout(n, m);
                inicio2.setLayout(datos);

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        JTextField t = new JTextField("");
                        casillas.add(t);
                        t.getDocument().addUndoableEditListener(new UndoableEditListener() {
                            @Override
                            public void undoableEditHappened(UndoableEditEvent e) {
                                manager.addEdit(e.getEdit()); 
                            }
                            
                        });
                        inicio2.add(t);
                    }
                }

                isInicio = true;

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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida");
        }
    }

    /**
     * Genera el menu de entrada de filas y columnas del inicio.
     */
    public void volverInicio() {
        frame.getContentPane().removeAll();
        isInicio = false;
        inicio1 = new JPanel();
        FlowLayout linea = new FlowLayout();
        inicio1.setLayout(linea);

        frame.validate();
        frame.repaint();

        inicio1.add(etfilas);
        inicio1.add(numfilas);
        inicio1.add(etcol);
        inicio1.add(numcol);
        inicio1.add(selec);
        inicio1.setVisible(true);

        frame.getContentPane().add(BorderLayout.NORTH, inicio1);
        frame.validate();
        frame.repaint();
    }

    /**
     * Crea los botones del juego.
     */
    public void ventanaJuego() {
        botones = new ArrayList<Boton>();
        flechas = new ArrayList<Flecha>();

        String[][] valores = new String[n][m];
        int counter = 0;
        boolean ok = true;

        try {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    String valor = casillas.get(counter).getText();

                    if (Integer.parseInt(valor) < 9 && Integer.parseInt(valor) > 0
                            && valor != "") {
                        valores[i][j] = casillas.get(counter).getText();
                        counter++;
                        ok = true;
                        isInicio = false;
                    } else {
                        ok = false;
                        isInicio = true;
                        break;
                    }
                }
            }

            if (!ok) {
                JOptionPane.showMessageDialog(null, "Números deben ser menores que 9 y mayores que 0.");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Entrada incorrecta.");
            ok = false;
        }

        if (ok) {
            frame.getContentPane().removeAll();

            juego = new JPanel();
            GridLayout matriz = new GridLayout(n * 2 - 1, m * 2 - 1);
            juego.setLayout(matriz);
            frame.validate();
            frame.repaint();

            partida = new Resolver();

            int fila = 0, col = 0;

            // Creo el panel con los botones del juego.
            for (int i = 0; i < (n * 2) - 1; i++) {
                for (int j = 0; j < (m * 2) - 1; j++) {
                    if (i % 2 == 0 && j % 2 == 0) {
                        Boton newbutton = new Boton(new JButton(valores[fila][col]), i, j, partida);

                        if (i == (n * 2) - 2 && j == (m * 2) - 2) {
                            newbutton.setFinal();
                        }

                        botones.add(newbutton);
                        juego.add(newbutton.getBoton());
                        counter++;
                        col++;
                    } else {
                        Flecha flecha = new Flecha(i, j);
                        flechas.add(flecha);
                        juego.add(flecha.getFlecha());
                    }
                }
                col = 0;
                if (i % 2 != 0) {
                    fila++;
                }
            }

            partida.setBotones(botones);
            partida.setFlechas(flechas);
            partida.setMax(Max());
            partida.setMin(Min());

            frame.getContentPane().add(BorderLayout.CENTER, juego);
            frame.validate();
            frame.repaint();
            frame.setVisible(true);
        }
    }

    /**
     * Carga el tablero e inicia el juego.
     */
    public void cargarFichero() {
        casillas = new ArrayList<JTextField>();
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File("."));

        int respuesta = fileChooser.showOpenDialog(null);// seleciona archivo

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

            try (Scanner sc = new Scanner(file)) {
                String line;
                StringTokenizer data;
                boolean inputok = true;

                ArrayList<String> col = new ArrayList<String>();
                ArrayList<ArrayList<String>> row = new ArrayList<ArrayList<String>>();

                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    data = new StringTokenizer(line);
                    col = new ArrayList<>();

                    int space = 0;
                    for (int i = 0; i < line.length() && inputok; i++) {
                        if (line.charAt(i) == ' ') {
                            if (i == 0 || i == line.length() - 1) {
                                inputok = false;
                            }
                            space++;
                        } else if (space == 1) {
                            space = 0;
                        } else if (space == 2) {
                            inputok = false;
                        }
                    }

                    while (data.hasMoreTokens()) {
                        int num = Integer.parseInt(data.nextToken());

                        col.add(String.valueOf(num));
                    }
                    m = col.size();
                    row.add(col);
                }

                n = row.size();

                if (inputok) {
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            JTextField t = new JTextField(row.get(i).get(j));
                            casillas.add(t);
                        }
                    }

                    ventanaJuego();
                } else {
                    JOptionPane.showMessageDialog(null, "Información incorrecta.");
                }

            } catch (NumberFormatException | FileNotFoundException e1) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, "Fichero incompatible");
            }
        }
    }

    /**
     * Establece el número maximo de la tabla.
     */
    public int Max() {
        int max = 0;
        for (int i = 0; i < botones.size(); i++) {
            if (botones.get(i).getNum() > max) {
                max = botones.get(i).getNum();
            }
        }

        return max;
    }

    /**
     * Establece el número máximo de la tabla.
     */
    public int Min() {
        int min = botones.get(0).getNum();
        for (int i = 0; i < botones.size(); i++) {
            if (botones.get(i).getNum() < min) {
                min = botones.get(i).getNum();
            }
        }

        return min;
    }

    /**
     * Llama a las clases de ayuda y genera las soluciones posibles.
     */
    public void Help() {
        if (casillas.size() != 0) {
            asist = new Ayuda(n, m, casillas);
            asist.Help();
        } else {
            JOptionPane.showMessageDialog(null, "No hay datos");
        }

    }
}
