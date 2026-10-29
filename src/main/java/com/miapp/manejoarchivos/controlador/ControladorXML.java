package com.miapp.manejoarchivos.controlador;

import com.miapp.manejoarchivos.modelo.ModeloXML;
import com.miapp.manejoarchivos.vista.VistaXML;



/**
 * CONTROLADOR — XML
 */
public class ControladorXML {

    private final ModeloXML modelo;
    private final VistaXML  vista;

    public ControladorXML(VistaXML vista) {
        this.modelo = new ModeloXML();
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
            vista.mostrarMensaje("Nodo XML agregado en datos/datos.xml");
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
            vista.mostrarMensaje("Error al leer XML: " + e.getMessage());
        }
    }
}
