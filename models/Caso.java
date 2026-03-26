package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List; 

public class Caso {

    private String idCaso;
    private String titulo;
    private String estado;

    private List<Persona>     personas;
    private List<Evidencia>   evidencias;
    private List<Entrevista>  entrevistas;
    private List<Reporte>     reportes; 

    public Caso(String idCaso, String titulo, String estado) throws IllegalArgumentException {
        if (idCaso == null || idCaso.isBlank())
            throw new IllegalArgumentException("El ID del caso no puede ser nulo o vacío.");
        if (titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
        if (estado == null || estado.isBlank())
            throw new IllegalArgumentException("El estado no puede ser nulo o vacío.");

        this.idCaso = idCaso;
        this.titulo = titulo;
        this.estado = estado;

        this.personas    = new ArrayList<>();
        this.evidencias  = new ArrayList<>();
        this.entrevistas = new ArrayList<>();
        this.reportes    = new ArrayList<>();
    }

    public String getIdCaso() { return idCaso; }
    public String getTitulo() { return titulo; }
    public String getEstado() { return estado; }

    public List<Persona>    getPersonas()    { return Collections.unmodifiableList(personas); }
    public List<Evidencia> getEvidencias() {return Collections.unmodifiableList(evidencias);}
    public List<Evidencia> getEvidenciasInternas() {return evidencias;}
    public List<Entrevista> getEntrevistas() { return Collections.unmodifiableList(entrevistas); }
    public List<Reporte>    getReportes()    { return Collections.unmodifiableList(reportes); }


    public void agregarPersona(Persona p) {
        personas.add(p);
    }

    public void agregarEvidencia(Evidencia e) {
    evidencias.add(e);
    }

    public void agregarEntrevista(Entrevista e) {
        if (e != null) entrevistas.add(e);
    }

    public void agregarReporte(Reporte r) {
        if (r != null) reportes.add(r);
    }

    public String generarResumen() {
        String resumen = "===== RESUMEN DEL CASO =====\n";
        resumen += "ID    : " + idCaso + "\n";
        resumen += "Título: " + titulo + "\n";
        resumen += "Estado: " + estado + "\n";

        resumen += "\n-- Personas involucradas (" + personas.size() + ") --\n";
        for (Persona p : personas) {
            resumen += "  * " + p.getNombre() + " [Rol: " + p.getRol() + "]\n";
        }

        resumen += "\n-- Evidencias (" + evidencias.size() + ") --\n";
        for (Evidencia e : evidencias) {
            resumen += "  * " + e.generarResumen() + "\n";
        }

        resumen += "\n-- Entrevistas (" + entrevistas.size() + ") --\n";
        for (Entrevista ent : entrevistas) {
            resumen += "  * " + ent.generarResumen() + "\n";
        }

        resumen += "\n-- Reportes (" + reportes.size() + ") --\n";
        for (Reporte r : reportes) {
            resumen += "  * " + r.getTitulo() + "\n";
        }

        return resumen;
    }

    public void mostrarDetalles() {
        System.out.println(generarResumen());
        if (!entrevistas.isEmpty()) {
            System.out.println("\n===== DETALLE DE ENTREVISTAS =====");
            for (Entrevista ent : entrevistas) ent.mostrarDetalles();
        }
        if (!evidencias.isEmpty()) {
            System.out.println("\n===== DETALLE DE EVIDENCIAS =====");
            for (Evidencia e : evidencias) e.mostrarDetalles();
        }
    }

    @Override
    public String toString() {
        return "Caso{" + "idCaso='" + idCaso + '\'' + ", titulo='" + titulo + '\'' +
               ", estado='" + estado + '\'' + ", personas=" + personas.size() +
               ", evidencias=" + evidencias.size() + ", entrevistas=" + entrevistas.size() +
               ", reportes=" + reportes.size() + '}';
    }
}