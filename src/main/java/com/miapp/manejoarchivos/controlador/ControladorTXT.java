package com.miapp.manejoarchivos.controlador;

import com.miapp.manejoarchivos.modelo.ModeloTXT;
import com.miapp.manejoarchivos.vista.VistaTXT;


public class ControladorTXT {

    private final ModeloTXT modelo;
    private final VistaTXT  vista;

    public ControladorTXT(VistaTXT vista) {
        this.modelo = new ModeloTXT();
        this.vista  = vista;
    }

     public void guardar() {
        String texto = vista.getTextoEntrada();
        if (texto == null || texto.trim().isEmpty()) {
            vista.mostrarMensaje("Por favor escribe algo antes de guardar.");
            return;
        }
        try {
            modelo.escribir(texto);
            vista.mostrarMensaje("Archivo guardado correctamente en datos/datos.txt");
        } catch (Exception e) {
            vista.mostrarMensaje("Error al guardar: " + e.getMessage());
        }
    }

     public void leer() {
        try {
            String contenido = modelo.leer();
            vista.mostrarResultado(contenido);
        } catch (Exception e) {
            vista.mostrarMensaje("Error al leer: " + e.getMessage());
        }
    }
}
