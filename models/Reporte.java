package models;

import interfaces.Documentable;

public class Reporte implements Documentable {
    private String id;
    private String titulo;
    private String contenido;

    public Reporte(String id, String titulo, String contenido) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El id no puede ser nulo o vacio");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("El titulo no puede ser nulo o vacio");
        }
        if (contenido == null || contenido.isEmpty()) {
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

    // Nuevo
    @Override
    public String documentar() {
        return "Reporte [" + id + "] - " + titulo + "\n" + contenido;
    }

    public String toCSV() {
        return id + "," + titulo + "," + contenido;
    }

    public static Reporte fromCSV(String csv) {
        String[] partes = csv.split(",");
        return new Reporte(partes[0], partes[1], partes[2]);
    }
}
