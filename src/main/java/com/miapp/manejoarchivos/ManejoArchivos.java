package com.miapp.manejoarchivos;

import com.miapp.manejoarchivos.vista.VistaCSV;
import com.miapp.manejoarchivos.vista.VistaJSON;
import com.miapp.manejoarchivos.vista.VistaTXT;
import com.miapp.manejoarchivos.vista.VistaXML;

import javax.swing.*;
import java.awt.*;

public class ManejoArchivos {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {

            // ── Ventana principal ──────────────────────────────────
            JFrame menu = new JFrame("Manejo de Archivos — POO");
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menu.setLayout(new BorderLayout(10, 10));
            menu.getContentPane().setBackground(new Color(52, 73, 94));

            // Título
            JLabel titulo = new JLabel("Selecciona el tipo de archivo", SwingConstants.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 18));
            titulo.setForeground(new Color(236, 240, 241));
            titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
            menu.add(titulo, BorderLayout.NORTH);

            // Panel de botones
            JPanel panelBotones = new JPanel(new GridLayout(4, 1, 0, 12));
            panelBotones.setBackground(new Color(52, 73, 94));
            panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

            // Definición de cada botón: texto, color, vista a abrir
            Object[][] opciones = {
                {"Archivo TXT  — texto plano",     new Color(41, 128, 185), (Runnable) () -> new VistaTXT().setVisible(true)},
                {"Archivo JSON — objetos",          new Color(39, 174, 96),   (Runnable) () -> new VistaJSON().setVisible(true)},
                {"Archivo XML  — etiquetas",        new Color(192, 57, 43),  (Runnable) () -> new VistaXML().setVisible(true)},
                {"Archivo CSV  — tabla de datos",   new Color(211, 84, 0),  (Runnable) () -> new VistaCSV().setVisible(true)},
            };

            for (Object[] op : opciones) {
                JButton btn = new JButton((String) op[0]);
                btn.setBackground((Color) op[1]);
                btn.setForeground(Color.WHITE);
                btn.setFont(new Font("Arial", Font.BOLD, 14));
                btn.setFocusPainted(false);
                btn.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                Runnable accion = (Runnable) op[2];
                btn.addActionListener(e -> accion.run());

                panelBotones.add(btn);
            }

            menu.add(panelBotones, BorderLayout.CENTER);

            menu.setSize(380, 420);
            menu.setLocationRelativeTo(null); // centrar en pantalla
            menu.setVisible(true);
        });
    }
}