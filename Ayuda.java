import java.util.*;

import javax.swing.*;

public class Ayuda {
    private int n, m;
    private ArrayList<JTextField> casillas;

    public Ayuda(int n, int m, ArrayList<JTextField> casillas) {
        this.n = n;
        this.m = m;
        this.casillas = casillas;
    }

    public void Help() {
        Scanner sc = new Scanner(System.in);
        String line;
        StringTokenizer data;
        int min = 0;
        int max = 0;
        int numrows, numcols = 0;
        boolean inputok = true;

        try {
            ArrayList<String> col;
            ArrayList<ArrayList<String>> row = new ArrayList<>();
            int counter = 0;
            boolean ok = true;

            try {
                for (int i = 0; i < n; i++) {
                    col = new ArrayList<String>();
                    for (int j = 0; j < m; j++) {
                        String valor = casillas.get(counter).getText();

                        if (Integer.parseInt(valor) < 9 && Integer.parseInt(valor) > 0
                                && valor != "") {
                            col.add(valor);
                            counter++;
                            ok = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Números deben ser menores que 9 y mayores que 0.");
                            ok = false;
                            break;
                        }
                    }
                    row.add(col);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Entrada incorrecta.");
                ok = false;
            }

            numcols = m;
            numrows = n;

            ZigZag partida = new ZigZag(numrows, numcols);

            if (partida.isNumColEqual(row)) {
                for (int i = 0; i < numrows; i++) {
                    for (int j = 0; j < numcols; j++) {
                        Direccion elem = new Direccion(i, j, Integer.parseInt(row.get(i).get(j)));
                        partida.addElem(elem);
                    }
                }

                if (partida.isInputOk() && inputok) {
                    partida.Max();
                    partida.Min();

                    if (partida.max != partida.min || partida.getSizeOptions() != 1) {
                        partida.zizagVueltaAtras();

                        partida.imprimeSoluciones();
                    } else {
                        System.out.println("1\n" + partida.max);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Entrada incorrecta.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Entrada incorrecta.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Entrada incorrecta.");
        }
    }
}
