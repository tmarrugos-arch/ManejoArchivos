package com.miapp.manejoarchivos.modelo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ModeloCSV {

    private static final String RUTA      = "datos/datos.csv";
    private static final String CABECERA  = "nombre,edad,carrera";
    private static final String SEPARADOR = ",";

    
    public void escribir(String nombre, int edad, String carrera) throws IOException {
        boolean esNuevo = !new File(RUTA).exists();

        // FileWriter(ruta, true) : modo append (no borra el contenido anterior)
        try (FileWriter fw = new FileWriter(RUTA, StandardCharsets.UTF_8, true);
             PrintWriter pw = new PrintWriter(fw)) {

            if (esNuevo) {
                pw.println(CABECERA);  // Escribir cabecera solo si es archivo nuevo
            }
            // Fila de datos: nombre,edad,carrera
            pw.println(nombre + SEPARADOR + edad + SEPARADOR + carrera);
        }
    }

   
    public List<String[]> leerFilas() throws IOException {
        List<String[]> filas = new ArrayList<>();
        if (!new File(RUTA).exists()) return filas;

        try (FileReader fr = new FileReader(RUTA, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(fr)) {

            String linea;
            boolean primero = true;
            while ((linea = br.readLine()) != null) {
                if (primero) { primero = false; continue; }  // saltar cabecera
                if (!linea.trim().isEmpty()) {
                    filas.add(linea.split(SEPARADOR));         // split por coma
                }
            }
        }
        return filas;
    }

 
    public String leerTexto() throws IOException {
        if (!new File(RUTA).exists()) return "El archivo datos.csv no existe todavía.";

        StringBuilder sb = new StringBuilder();
        List<String[]> filas = leerFilas();
        sb.append("Total de registros: ").append(filas.size()).append("\n\n");
        sb.append(String.format("%-15s %-6s %-15s%n", "NOMBRE", "EDAD", "CARRERA")); //Significa que -15s Texto en 15 caracteres, a la izquierda, -6 Texto en 6 caracteres, a la izquierda
        
        sb.append("-".repeat(40)).append("\n");

        for (String[] fila : filas) {
            if (fila.length >= 3) {
                sb.append(String.format("%-15s %-6s %-15s%n",
                        fila[0].trim(), fila[1].trim(), fila[2].trim()));
            }
        }
        return sb.toString();
    }

    public boolean existe() {
        return new File(RUTA).exists();
    }
}
/*
sb.append(String.format("%-15s %-6s %-15s%n", "NOMBRE", "EDAD", "CARRERA"));
Es una línea que construye una tabla de texto alineada en columnas. Tiene dos partes:

String.format(...) funciona como una plantilla donde defines el ancho de cada columna:

%-15s : texto alineado a la izquierda, ocupando exactamente 15 caracteres. Si el texto es más corto, rellena con espacios.
%-6s : texto alineado a la izquierda en 6 caracteres.
%n : salto de línea (equivale a \n pero compatible con todos los sistemas operativos).

La s significa que va a insertar un String ahí. El - es el que hace la alineación a la izquierda; sin él sería a la derecha.*/