package models;

import exceptions.CSVInvalidoException;
import interfaces.Documentable;

public class Reporte implements Documentable {
    private String id;
    private String titulo;
    private String contenido;

    public Reporte(String id, String titulo, String contenido) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id no puede ser nulo o vacio");
        }
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El titulo no puede ser nulo o vacio");
        }
        if (contenido == null || contenido.isBlank()) {
            throw new IllegalArgumentException("El contenido no puede ser nulo o vacio");
        }

        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    @Override
    public String generarResumen() {
        return "Reporte [" + id + "] " + titulo;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("=== Reporte ===");
        System.out.println("ID: " + id);
        System.out.println("Titulo: " + titulo);
        System.out.println("Contenido: " + contenido);
    }

    @Override
    public String toString() {
        return "Reporte{id='" + id + "', titulo='" + titulo + "', contenido='" + contenido + "'}";
    }

    @Override
    public String documentar() {
        return "Reporte [" + id + "] - " + titulo + "\n" + contenido;
    }

    public String toCSV() {
        return String.join(";",
                escaparCSV(id),
                escaparCSV(titulo),
                escaparCSV(contenido));
    }

    public static Reporte fromCSV(String linea) throws CSVInvalidoException {
        if (linea == null || linea.isBlank())
            throw new CSVInvalidoException("Línea CSV vacía o nula.");

        String[] partes = linea.split(";", -1);

        if (partes.length < 3)
            throw new CSVInvalidoException("Formato CSV inválido para Reporte.");

        try {
            return new Reporte(
                    desescaparCSV(partes[0]),
                    desescaparCSV(partes[1]),
                    desescaparCSV(partes[2]));
        } catch (IllegalArgumentException e) {
            throw new CSVInvalidoException("Error al procesar Reporte desde CSV.");
        }
    }

    private static String escaparCSV(String valor) {
        if (valor == null)
            return "\"\"";
        return "\"" + valor.replace("\"", "\"\"") + "\"";
    }

    private static String desescaparCSV(String valor) {
        if (valor == null)
            return "";
        String v = valor.trim();
        if (v.startsWith("\"") && v.endsWith("\""))
            v = v.substring(1, v.length() - 1);
        return v.replace("\"\"", "\"");
    }
}