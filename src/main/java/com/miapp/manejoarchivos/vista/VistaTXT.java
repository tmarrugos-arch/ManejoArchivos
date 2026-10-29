package com.miapp.manejoarchivos.vista;


import com.miapp.manejoarchivos.controlador.ControladorTXT;
import javax.swing.*;
import java.awt.*;

public class VistaTXT extends JFrame {

    // Componentes
    private final JTextArea  txtEntrada  = new JTextArea(5, 40);
    private final JTextArea  txtSalida   = new JTextArea(7, 40);
    private final JButton    btnGuardar  = new JButton("Guardar TXT");
    private final JButton    btnLeer     = new JButton("Leer TXT");

    private ControladorTXT controlador;

    public VistaTXT() {
        super("Archivos TXT — Manejo de Archivos");
        initUI();
        controlador = new ControladorTXT(this);
        conectarEventos();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
        getContentPane().setBackground(new Color(250, 250, 250));

        // Panel superior — título
        JLabel titulo = new JLabel("  Manejo de Archivos TXT (.txt)", SwingConstants.LEFT);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true);
        titulo.setBackground(new Color(41, 128, 185));  // azul TXT
        titulo.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        add(titulo, BorderLayout.NORTH);

        // Panel central — entradas
        JPanel centro = new JPanel(new BorderLayout(5, 5));
        centro.setBorder(BorderFactory.createEmptyBorder(8, 10, 4, 10));
        centro.setBackground(new Color(250, 250, 250));

        txtEntrada.setFont(new Font("Arial", Font.PLAIN, 13));
        txtEntrada.setLineWrap(true);
        txtEntrada.setBorder(BorderFactory.createTitledBorder("Escribe el contenido a guardar:"));
        centro.add(new JScrollPane(txtEntrada), BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelBotones.setBackground(new Color(250, 250, 250));
        btnGuardar.setBackground(new Color(41, 128, 185));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 12));
        btnLeer.setBackground(new Color(52, 73, 94));
        btnLeer.setForeground(Color.WHITE);
        btnLeer.setFont(new Font("Arial", Font.BOLD, 12));
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLeer);
        centro.add(panelBotones, BorderLayout.SOUTH);
        add(centro, BorderLayout.CENTER);

        // Panel inferior — resultado
        txtSalida.setEditable(false);
        txtSalida.setFont(new Font("Arial", Font.PLAIN, 12));
        txtSalida.setBackground(Color.WHITE);
        txtSalida.setForeground(new Color(33, 33, 33));
        txtSalida.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        JScrollPane scroll = new JScrollPane(txtSalida);
        scroll.setBorder(BorderFactory.createTitledBorder("Contenido del archivo:"));
        scroll.setPreferredSize(new Dimension(480, 160));
        add(scroll, BorderLayout.SOUTH);

        pack();
    }

    private void conectarEventos() {
        btnGuardar.addActionListener(e -> controlador.guardar());
        btnLeer.addActionListener(e -> controlador.leer());
    }


    public String getTextoEntrada()        { return txtEntrada.getText(); }
    public void   mostrarResultado(String t) { txtSalida.setText(t); }
    public void   mostrarMensaje(String m)  {
        JOptionPane.showMessageDialog(this, m, "TXT", JOptionPane.INFORMATION_MESSAGE);
    }
}
