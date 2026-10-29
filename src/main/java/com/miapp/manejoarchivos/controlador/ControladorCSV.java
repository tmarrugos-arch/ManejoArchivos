package com.miapp.manejoarchivos.controlador;

import com.miapp.manejoarchivos.modelo.ModeloCSV;
import com.miapp.manejoarchivos.vista.VistaCSV;



/**
 * CONTROLADOR — CSV
 */
public class ControladorCSV {

    private final ModeloCSV modelo;
    private final VistaCSV  vista;

    public ControladorCSV(VistaCSV vista) {
        this.modelo = new ModeloCSV();
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
            vista.mostrarMensaje("Fila CSV agregada en datos/datos.csv");
            vista.limpiarCampos();
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("La edad debe ser un número entero.");
        } catch (Exception e) {
            vista.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    public void leer() {
        try {
            vista.mostrarResultado(modelo.leerTexto());
        } catch (Exception e) {
            vista.mostrarMensaje("Error al leer CSV: " + e.getMessage());
        }
    }
}
