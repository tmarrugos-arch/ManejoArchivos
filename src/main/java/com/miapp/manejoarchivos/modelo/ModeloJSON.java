package com.miapp.manejoarchivos.modelo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class ModeloJSON {

    private static final String RUTA = "datos/datos.json";

   
    public void escribir(String nombre, int edad, String carrera) throws IOException {
        // Cargar array existente (o crear uno nuevo si el archivo no existe)
        JSONArray array;
        File f = new File(RUTA);
        if (f.exists()) {
            String contenido = new String(Files.readAllBytes(Paths.get(RUTA)), StandardCharsets.UTF_8);
            array = new JSONArray(contenido);
        } else {
            array = new JSONArray();
        }

        // Crear objeto estudiante y agregarlo al arreglo
        JSONObject estudiante = new JSONObject();
        estudiante.put("nombre",  nombre);
        estudiante.put("edad",    edad);
        estudiante.put("carrera", carrera);
        array.put(estudiante);

        // Guardar en disco con indentación de 4 espacios (legible)
        try (FileWriter fw = new FileWriter(RUTA, StandardCharsets.UTF_8)) {
            fw.write(array.toString(4));
        }
    }


    public String leer() throws IOException {
        if (!new File(RUTA).exists()) {
            return "El archivo datos.json no existe todavía.";
        }

        String contenido = new String(Files.readAllBytes(Paths.get(RUTA)), StandardCharsets.UTF_8);
        JSONArray array  = new JSONArray(contenido);

        StringBuilder sb = new StringBuilder();
        sb.append("Total de registros: ").append(array.length()).append("\n\n");

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            sb.append("Registro ").append(i + 1).append(":\n");
            sb.append("  Nombre : ").append(obj.getString("nombre")).append("\n");
            sb.append("  Edad   : ").append(obj.getInt("edad")).append("\n");
            sb.append("  Carrera: ").append(obj.getString("carrera")).append("\n\n");
        }
        return sb.toString();
    }

    /** Elimina todos los registros (vacía el archivo con arreglo vacío) */
    public void limpiar() throws IOException {
        try (FileWriter fw = new FileWriter(RUTA, StandardCharsets.UTF_8)) {
            fw.write("[]");
        }
    }

    public boolean existe() {
        return new File(RUTA).exists();
    }
}
