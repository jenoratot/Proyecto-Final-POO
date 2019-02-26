package Vista;

import Mundo.Obstaculo;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VentanaPregunta extends JDialog implements ActionListener {

    private JPanel contenido, pregPanel, opcPanel;
    private JLabel respuestas;
    private JTextArea pregunta;
    private Obstaculo obs;
    private JButton boton;
    private JComboBox resPos;

    public VentanaPregunta(Obstaculo obs, int index) {
        this.obs = obs;
        contenido = new JPanel();
        pregPanel = new JPanel();
        opcPanel = new JPanel();

        contenido.setBackground(Color.black);
        contenido.setLayout(new GridLayout(0, 2));
        pregPanel.setBackground(Color.black);
        opcPanel.setBackground(Color.black);

        //
        //pregPanel.setLayout(new GridLayout(4, 1));
        

        if (index == 0) {
            pregPanel.setLayout(new GridLayout(4,1));
            JLabel instrLabel = new JLabel(" INSTRUCCIONES ");
            instrLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
            instrLabel.setForeground(Color.white);
            JTextArea instrucciones = new JTextArea(" > Los investigadores del caso después un largo tiempo encontraron la guarida del Hacker con una seguridad basada en la programación, para poder rebasar dicha seguridad tienes que usar la POO. Buena suerte!!! ");
            instrucciones.setFont(new Font("Tahoma", Font.PLAIN, 10));
            instrucciones.setForeground(Color.white);
            instrucciones.setWrapStyleWord(true);
            instrucciones.setLineWrap(true);
            instrucciones.setOpaque(false);
            instrucciones.setEditable(false);
            instrucciones.setFocusable(false);
            pregPanel.add(instrLabel);
            pregPanel.add(instrucciones);
        }else{
            pregPanel.setLayout(new GridLayout(2,1));
        }

        JLabel nombreLabel = new JLabel(" " + obs.getNombreObstaculo());
        nombreLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
        nombreLabel.setForeground(Color.white);

        pregunta = new JTextArea(" > Responde, " + obs.getPregunta());
        pregunta.setFont(new Font("Tahoma", Font.PLAIN, 10));
        pregunta.setForeground(Color.white);
        pregunta.setWrapStyleWord(true);
        pregunta.setLineWrap(true);
        pregunta.setOpaque(false);
        pregunta.setEditable(false);
        pregunta.setFocusable(false);

        pregPanel.add(nombreLabel);
        pregPanel.add(pregunta);
        contenido.add(pregPanel);

        //opcPanel.setBorder(BorderFactory.createTitledBorder("Respuestas"));	     
        String posiblesResp[] = this.obs.getRespuestasPosibles();
        opcPanel.setLayout(new GridLayout(3, 1));
        respuestas = new JLabel("Respuestas:");
        respuestas.setFont(new Font("Tahoma", Font.BOLD, 14));
        respuestas.setForeground(Color.white);
        opcPanel.add(respuestas);
        JPanel opcRespuestas = new JPanel();
        opcRespuestas.setLayout(new GridLayout(posiblesResp.length, 1));
        opcRespuestas.setBackground(Color.BLACK);
        for (int i = 0; i < posiblesResp.length; i++) {
            JLabel respuesta = new JLabel((i + 1) + "- " + posiblesResp[i]);
            respuesta.setFont(new Font("Tahoma", Font.PLAIN, 14));
            respuesta.setForeground(Color.white);
            opcRespuestas.add(respuesta);
        }
        //combopanel
        JPanel resp = new JPanel();
        resp.setBackground(Color.BLACK);
        JLabel elgRespLabel = new JLabel();
        elgRespLabel = new JLabel("Elige una respuesta:");
        elgRespLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        elgRespLabel.setForeground(Color.white);
        resp.add(elgRespLabel);
        resPos = new JComboBox();
        for (int j = 0; j < posiblesResp.length; j++) {
            resPos.addItem((j + 1));
        }
        //resPos.addItemListener(this);
        resp.add(resPos);

        boton = new JButton("Responder");
        boton.addActionListener(this);
        resp.add(boton);

        opcPanel.add(opcRespuestas);
        opcPanel.add(resp);
        contenido.add(opcPanel);

        add(contenido);
        setUndecorated(true);
        setAlwaysOnTop(true);
        //Datos de Ventana        
        this.setModal(true);
        this.setResizable(false);
        this.setBounds(500, 95, 700, 500);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton) {
            int item = this.resPos.getSelectedIndex();
            if (this.obs.esCorrecta(item)) {
                this.obs.setResuelto(true);
            }
            dispose();
        }
    }

}
