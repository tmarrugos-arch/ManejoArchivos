package com.miapp.manejoarchivos.vista;


import com.miapp.manejoarchivos.controlador.ControladorJSON;
import javax.swing.*;
import java.awt.*;

public class VistaJSON extends JFrame {

    private final JTextField txtNombre  = new JTextField(20);
    private final JTextField txtEdad    = new JTextField(5);
    private final JTextField txtCarrera = new JTextField(20);
    private final JTextArea  txtSalida  = new JTextArea(8, 42);
    private final JButton    btnGuardar = new JButton("Guardar JSON");
    private final JButton    btnLeer    = new JButton("Leer JSON");
    private final JButton    btnLimpiar = new JButton("Limpiar");

    private ControladorJSON controlador;

    public VistaJSON() {
        super("Archivos JSON — Manejo de Archivos");
        initUI();
        controlador = new ControladorJSON(this);
        conectarEventos();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
        getContentPane().setBackground(new Color(250, 250, 250));

        // Título
        JLabel titulo = new JLabel("  Manejo de Archivos JSON (.json)", SwingConstants.LEFT);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true);
        titulo.setBackground(new Color(39, 174, 96));  // verde JSON
        titulo.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        add(titulo, BorderLayout.NORTH);

        // Formulario
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(new Color(250, 250, 250));
        form.setBorder(BorderFactory.createEmptyBorder(10, 12, 6, 12));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        String[] labels = {"Nombre:", "Edad:", "Carrera:"};
        JTextField[] fields = {txtNombre, txtEdad, txtCarrera};
        Font labelFont = new Font("Arial", Font.BOLD, 12);

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(labelFont);
            form.add(lbl, gbc);
            gbc.gridx = 1;
            fields[i].setFont(new Font("Arial", Font.PLAIN, 12));
            form.add(fields[i], gbc);
        }

        // Botones
        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panelBtn.setBackground(new Color(250, 250, 250));
        btnGuardar.setBackground(new Color(39, 174, 96)); btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 12));
        btnLeer.setBackground(new Color(52, 73, 94)); btnLeer.setForeground(Color.WHITE);
        btnLeer.setFont(new Font("Arial", Font.BOLD, 12));
        btnLimpiar.setFont(new Font("Arial", Font.PLAIN, 12));
        panelBtn.add(btnGuardar); panelBtn.add(btnLeer); panelBtn.add(btnLimpiar);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        form.add(panelBtn, gbc);
        add(form, BorderLayout.CENTER);

        // Área de resultado
        txtSalida.setEditable(false);
        txtSalida.setFont(new Font("Arial", Font.PLAIN, 12));
        txtSalida.setBackground(Color.WHITE);
        txtSalida.setForeground(new Color(33, 33, 33));
        JScrollPane scroll = new JScrollPane(txtSalida);
        scroll.setBorder(BorderFactory.createTitledBorder("Contenido del archivo JSON:"));
        scroll.setPreferredSize(new Dimension(460, 180));
        add(scroll, BorderLayout.SOUTH);

        pack();
    }

    private void conectarEventos() {
        btnGuardar.addActionListener(e -> controlador.guardar());
        btnLeer.addActionListener(e -> controlador.leer());
        btnLimpiar.addActionListener(e -> controlador.limpiar());
    }

    // ── Accesores para el Controlador ──
    public String getNombre()               { return txtNombre.getText().trim(); }
    public String getEdad()                 { return txtEdad.getText().trim(); }
    public String getCarrera()              { return txtCarrera.getText().trim(); }
    public void   mostrarResultado(String t){ txtSalida.setText(t); }
    public void   limpiarCampos()           { txtNombre.setText(""); txtEdad.setText(""); txtCarrera.setText(""); }
    public void   mostrarMensaje(String m)  {
        JOptionPane.showMessageDialog(this, m, "JSON", JOptionPane.INFORMATION_MESSAGE);
    }
}
