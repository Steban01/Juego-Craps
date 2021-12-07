package JuegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI_Gridbaglayout extends JFrame {
    public static final String MENSAJE_INICIO = "Bienvenido a GameCraps\nOprime el boton lanzar para iniciar el juego\nReglas:\n" +
            "-Si tu tiro de salida es 7 u 11, ganas con Natural\n" +
            "-Si tu tiro de salida es 2,3 o 12, pierdes con Craps\n" +
            "-Si sacas otro valor cualquiera, establecerás punto\n" +
            "estado valor podras seguir lanzando los dados\n" +
            "pero ahora ganaras si sacas nuevamente el valor punto\n" +
            "sin que previamente hayas sacado 7";

    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar, ayuda, salir;
    private JPanel panelDados;
    private ImageIcon imageDado;
    private JTextArea resultadosDados, mensajeSalida;
    private Escucha escucha;
    private ModelCraps modelCraps;

    public GUI_Gridbaglayout() {
        initGUI();
        //Default JFrame configuration
        this.setTitle("CrapsGame");
        this.setUndecorated(true);


        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initGUI() {
        //Set up JFrame Container's Layout

        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Create Listener Object and Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa de Juego", Color.gray);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject, constraints); //Change this line if you change JFrame Container's Layout

        ayuda = new JButton("Ayuda");
        ayuda.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(ayuda, constraints);

        salir = new JButton("Salir");
        salir.addActionListener(escucha);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(salir, constraints);

        imageDado = new ImageIcon(getClass().getResource("/recursos/dado.png"));
        dado1 = new JLabel(imageDado);
        dado2 = new JLabel(imageDado);

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300, 180));
        panelDados.setBorder(BorderFactory.createTitledBorder("sus dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        add(panelDados, constraints);

        resultadosDados = new JTextArea(4, 31);
        resultadosDados.setBorder(BorderFactory.createTitledBorder("Resultados"));
        resultadosDados.setText("Lanza los dados");
        resultadosDados.setBackground(null);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        add(resultadosDados, constraints);

        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        add(lanzar, constraints);

        mensajeSalida = new JTextArea(4, 31);
        mensajeSalida.setText("Usa el boton Ayuda para ver las reglas del juego");
        mensajeSalida.setBorder(BorderFactory.createTitledBorder("Mensajes"));
        mensajeSalida.setBackground(null);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        add(mensajeSalida, constraints);


    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GUI_Gridbaglayout miProjectGUI = new GUI_Gridbaglayout();
        });
    }

    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == lanzar) {
                modelCraps.CalcularTiro();
                int[] caras = modelCraps.getCaras();
                imageDado = new ImageIcon(getClass().getResource("/recursos/" + caras[0] + ".png"));
                dado1.setIcon(imageDado);
                imageDado = new ImageIcon(getClass().getResource("/recursos/" + caras[1] + ".png"));
                dado2.setIcon(imageDado);
                modelCraps.determinarJuego();
                resultadosDados.setText(modelCraps.getEstadotoString()[0]);
                mensajeSalida.setText(modelCraps.getEstadotoString()[1]);
            } else {
                if (e.getSource() == ayuda) {
                    JOptionPane.showMessageDialog(null, MENSAJE_INICIO);

                } else {
                    System.exit(0);
                }

            }
        }
    }
}
