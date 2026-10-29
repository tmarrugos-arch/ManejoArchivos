package com.miapp.manejoarchivos.controlador;

import com.miapp.manejoarchivos.modelo.ModeloJSON;
import com.miapp.manejoarchivos.vista.VistaJSON;


public class ControladorJSON {

    private final ModeloJSON modelo;
    private final VistaJSON  vista;

    public ControladorJSON(VistaJSON vista) {
        this.modelo = new ModeloJSON();
        this.vista  = vista;
    }

    public void guardar() {
        String nombre  = vista.getNombre();
        String edadStr = vista.getEdad();
        String carrera = vista.getCarrera();

        if (nombre.isEmpty() || edadStr.isEmpty() || carrera.isEmpty()) {
            vista.mostrarMensaje("Completa todos los campos.");
            return;
        }
        try {
            int edad = Integer.parseInt(edadStr);
            modelo.escribir(nombre, edad, carrera);
            vista.mostrarMensaje("Registro JSON guardado en datos/datos.json");
            vista.limpiarCampos();
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("La edad debe ser un número entero.");
        } catch (Exception e) {
            vista.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    public void leer() {
        try {
            vista.mostrarResultado(modelo.leer());
        } catch (Exception e) {
            vista.mostrarMensaje("Error al leer JSON: " + e.getMessage());
        }
    }

    public void limpiar() {
        try {
            modelo.limpiar();
            vista.mostrarMensaje("Archivo JSON limpiado.");
        } catch (Exception e) {
            vista.mostrarMensaje("Error: " + e.getMessage());
        }
    }
}
