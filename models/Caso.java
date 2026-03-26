package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Caso {

    private String idCaso;
    private String titulo;
    private String estado;

    private List<Persona>    personas;
    private List<Evidencia>  evidencias;
    private List<Entrevista> entrevistas;
    private List<Reporte>    reportes;

    public Caso(String idCaso, String titulo, String estado) {
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
    public List<Evidencia>  getEvidencias()  { return Collections.unmodifiableList(evidencias); }
    public List<Evidencia> getEvidenciasInternas() { return evidencias; }
    public List<Entrevista> getEntrevistas() { return Collections.unmodifiableList(entrevistas); }
    public List<Reporte>    getReportes()    { return Collections.unmodifiableList(reportes); }

    public void agregarPersona(Persona p) {
        if (p == null)
            throw new IllegalArgumentException("La persona no puede ser nula.");
        personas.add(p);
    }

    public void agregarEvidencia(Evidencia e) {
        if (e == null)
            throw new IllegalArgumentException("La evidencia no puede ser nula.");
        evidencias.add(e);
    }

    public void agregarEntrevista(Entrevista e) {
        if (e == null)
            throw new IllegalArgumentException("La entrevista no puede ser nula.");
        entrevistas.add(e);
    }

    public void agregarReporte(Reporte r) {
        if (r == null)
            throw new IllegalArgumentException("El reporte no puede ser nulo.");
        reportes.add(r);
    }

    public String generarResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== RESUMEN DEL CASO =====\n");
        sb.append("ID    : ").append(idCaso).append("\n");
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Estado: ").append(estado).append("\n");

        sb.append("\n-- Personas involucradas (").append(personas.size()).append(") --\n");
        for (Persona p : personas)
            sb.append("  * ").append(p.getNombre()).append(" [Rol: ").append(p.getRol()).append("]\n");

        sb.append("\n-- Evidencias (").append(evidencias.size()).append(") --\n");
        for (Evidencia e : evidencias)
            sb.append("  * ").append(e.generarResumen()).append("\n");

        sb.append("\n-- Entrevistas (").append(entrevistas.size()).append(") --\n");
        for (Entrevista ent : entrevistas)
            sb.append("  * ").append(ent.generarResumen()).append("\n");

        sb.append("\n-- Reportes (").append(reportes.size()).append(") --\n");
        for (Reporte r : reportes)
            sb.append("  * ").append(r.generarResumen()).append("\n");

        return sb.toString();
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

    public String toCSV() {
        return String.join(";",
            escaparCSV(idCaso),
            escaparCSV(titulo),
            escaparCSV(estado),
            String.valueOf(personas.size()),
            String.valueOf(evidencias.size()),
            String.valueOf(entrevistas.size()),
            String.valueOf(reportes.size())
        );
    }

    public static Caso fromCSV(String linea) {
        if (linea == null || linea.isBlank())
            throw new IllegalArgumentException("La línea CSV no puede ser nula o vacía.");

        String[] partes = linea.split(";", -1);   
        if (partes.length < 3)
            throw new IllegalArgumentException(
                "Formato CSV inválido: se esperaban al menos 3 columnas, se encontraron " + partes.length + ".");

        String idCaso = desescaparCSV(partes[0]);
        String titulo = desescaparCSV(partes[1]);
        String estado = desescaparCSV(partes[2]);
    
        return new Caso(idCaso, titulo, estado);
    }

    private static String escaparCSV(String valor) {
        if (valor == null) return "\"\"";
        return "\"" + valor.replace("\"", "\"\"") + "\"";
    }

    private static String desescaparCSV(String valor) {
        if (valor == null) return "";
        String v = valor.trim();
        if (v.startsWith("\"") && v.endsWith("\""))
            v = v.substring(1, v.length() - 1);
        return v.replace("\"\"", "\"");
    }

    @Override
public String toString() {
    return "Caso{"
        + "idCaso='"       + idCaso             + '\''
        + ", titulo='"     + titulo             + '\''
        + ", estado='"     + estado             + '\''
        + ", personas="    + personas.size()
        + ", evidencias="  + evidencias.size()
        + ", entrevistas=" + entrevistas.size()
        + ", reportes="    + reportes.size()
        + '}';
    }
}