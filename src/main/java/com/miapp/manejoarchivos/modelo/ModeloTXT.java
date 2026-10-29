package com.miapp.manejoarchivos.modelo;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ModeloTXT {

    private static final String RUTA = "datos/datos.txt";


    public void escribir(String contenido) throws IOException {
        // FileWriter(path, false) : sobrescribe | FileWriter(path, true) : agrega (append)
        try (FileWriter fw = new FileWriter(RUTA, StandardCharsets.UTF_8, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            // Escribe cada línea del texto
            String[] lineas = contenido.split("\n");
            for (String linea : lineas) {
                pw.println(linea);   // println agrega salto de línea automáticamente
            }
        }
        // El try-with-resources cierra pw → bw → fw automáticamente
    }

   
    public String leer() throws IOException {
        StringBuilder sb = new StringBuilder();

        try (FileReader fr = new FileReader(RUTA, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(fr)) {

            String linea;
            // readLine() retorna null cuando llega al final del archivo
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
        }
        return sb.toString();
    }

    public boolean existe() {
        return new File(RUTA).exists();
    }
}
