package JuegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used as View Craps class
 *
 * @version V.1.0.0 date:06/12/2021
 * @autor Esteban Andres Hernandez Cortes-esteban.cortes@correounivalle.edu.co
 */
public class GUI extends JFrame {
    public static final String MENSAJE_INICIO = "Bienvenido a GameCraps\nOprime el boton lanzar para iniciar el juego\nReglas\n" +
            "si tu tiro de salida es 7 u 11, ganas con Natural\n" +
            "si tu tiro de salida es 2,3 o 12, pierdes con Craps\n" +
            "si sacas otro valor cualquiera, establecerás punto\n" +
            "estado valor podras seguir lanzando los dados\n" +
            "pero ahora ganaras si sacas nuevamente el valor punto\n" +
            "sin que previamente hayas sacado 7";

    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon imageDado;
    private JSeparator separator;
    private JTextArea resultadosDados, mensajeSalida;
    private Escucha escucha;
    private ModelCraps modelCraps;

    /**
     * Constructor of GUI class
     */
    public GUI() {
        initGUI();

        //Default JFrame configuration
        this.setTitle("CrapsGame");
        //this.setSize(1300,600);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa de Juego", Color.GRAY);

        this.add(headerProject, BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout
        imageDado = new ImageIcon(getClass().getResource("/recursos/dado.png"));
        dado1 = new JLabel(imageDado);
        dado2 = new JLabel(imageDado);

        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);
        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300, 180));
        panelDados.setBorder(BorderFactory.createTitledBorder("sus dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);

        this.add(panelDados, BorderLayout.CENTER);
        mensajeSalida = new JTextArea(16, 31);
        mensajeSalida.setText(MENSAJE_INICIO);

        JScrollPane scroll = new JScrollPane(mensajeSalida);


        panelResultados = new JPanel();
        panelResultados.setBorder(BorderFactory.createTitledBorder("Que debes hacer"));
        panelResultados.add(scroll);
        panelResultados.setPreferredSize(new Dimension(360, 180));
        this.add(panelResultados, BorderLayout.EAST);


        resultadosDados = new JTextArea(4, 31);
        separator = new JSeparator();
        separator.setPreferredSize(new Dimension(380, 7));
        separator.setBackground(Color.GRAY);


    }

    /**
     * Main process of the Java program
     *
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            modelCraps.CalcularTiro();
            int[] caras = modelCraps.getCaras();
            imageDado = new ImageIcon(getClass().getResource("/recursos/" + caras[0] + ".png"));
            dado1.setIcon(imageDado);
            imageDado = new ImageIcon(getClass().getResource("/recursos/" + caras[1] + ".png"));
            dado2.setIcon(imageDado);
            panelResultados.removeAll();
            panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));
            panelResultados.add(resultadosDados);
            panelResultados.add(separator);
            panelResultados.add(mensajeSalida);
            resultadosDados.setText(modelCraps.getEstadotoString()[0]);
            mensajeSalida.setRows(4);
            mensajeSalida.setText(modelCraps.getEstadotoString()[1]);

            modelCraps.determinarJuego();
            revalidate();
            repaint();
        }
    }
}
