package com.miapp.manejoarchivos.modelo;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class ModeloXML {

    private static final String RUTA = "datos/datos.xml";

  
    public void escribir(String nombre, int edad, String carrera)
            throws ParserConfigurationException, TransformerException, Exception {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder        db  = dbf.newDocumentBuilder();
        Document doc;

        File f = new File(RUTA);
        if (f.exists()) {
            // Cargar documento existente
            doc = db.parse(f);
            doc.getDocumentElement().normalize();
        } else {
            // Crear documento nuevo con raíz <estudiantes>
            doc = db.newDocument();
            Element raiz = doc.createElement("estudiantes");
            doc.appendChild(raiz);
        }

        // Crear nodo <estudiante> con sus hijos
        Element raiz       = doc.getDocumentElement();
        Element estudiante = doc.createElement("estudiante");

        Element eNombre  = doc.createElement("nombre");  eNombre.setTextContent(nombre);
        Element eEdad    = doc.createElement("edad");    eEdad.setTextContent(String.valueOf(edad));
        Element eCarrera = doc.createElement("carrera"); eCarrera.setTextContent(carrera);

        estudiante.appendChild(eNombre);
        estudiante.appendChild(eEdad);
        estudiante.appendChild(eCarrera);
        raiz.appendChild(estudiante);

        // Serializar el árbol DOM de vuelta a texto XML en el disco
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");       // tildes y ñ
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");           // indentar
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(new DOMSource(doc), new StreamResult(new File(RUTA)));
    }

  
    public String leer() throws Exception {
        if (!new File(RUTA).exists()) {
            return "El archivo datos.xml no existe todavía.";
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = dbf.newDocumentBuilder().parse(new File(RUTA));
        doc.getDocumentElement().normalize();

        NodeList lista = doc.getElementsByTagName("estudiante");
        StringBuilder sb = new StringBuilder();
        sb.append("Total de registros: ").append(lista.getLength()).append("\n\n");

        for (int i = 0; i < lista.getLength(); i++) {
            Element e = (Element) lista.item(i);
            sb.append("Registro ").append(i + 1).append(":\n");
            sb.append("  Nombre : ").append(e.getElementsByTagName("nombre").item(0).getTextContent()).append("\n");
            sb.append("  Edad   : ").append(e.getElementsByTagName("edad").item(0).getTextContent()).append("\n");
            sb.append("  Carrera: ").append(e.getElementsByTagName("carrera").item(0).getTextContent()).append("\n\n");
        }
        return sb.toString();
    }

    public boolean existe() {
        return new File(RUTA).exists();
    }
}
