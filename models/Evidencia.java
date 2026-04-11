package models;

import interfaces.Documentable;
import exceptions.CSVInvalidoException;

public abstract class Evidencia implements Documentable {

    private String idEvidencia;
    private String descripcion;
    private String fechaRecoleccion;
    private String lugarRecoleccion;
    private String estado;

    public Evidencia(String idEvidencia, String descripcion, String fechaRecoleccion, String lugarRecoleccion,
            String estado) {
        if (idEvidencia == null || idEvidencia.isBlank())
            throw new IllegalArgumentException("El ID de la evidencia no puede ser nulo o vacío.");
        if (descripcion == null || descripcion.isBlank())
            throw new IllegalArgumentException("La descripción no puede ser nula o vacía.");
        if (fechaRecoleccion == null || fechaRecoleccion.isBlank())
            throw new IllegalArgumentException("La fecha de recolección no puede ser nula o vacía.");
        if (lugarRecoleccion == null || lugarRecoleccion.isBlank())
            throw new IllegalArgumentException("El lugar de recolección no puede ser nulo o vacío.");
        if (estado == null || estado.isBlank())
            throw new IllegalArgumentException("El estado no puede ser nulo o vacío.");

        this.idEvidencia = idEvidencia;
        this.descripcion = descripcion;
        this.fechaRecoleccion = fechaRecoleccion;
        this.lugarRecoleccion = lugarRecoleccion;
        this.estado = estado;
    }

    public String getIdEvidencia() {
        return idEvidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaRecoleccion() {
        return fechaRecoleccion;
    }

    public String getLugarRecoleccion() {
        return lugarRecoleccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank())
            throw new IllegalArgumentException("La descripción no puede ser nula o vacía.");
        this.descripcion = descripcion;
    }

    public void setFechaRecoleccion(String fechaRecoleccion) {
        if (fechaRecoleccion == null || fechaRecoleccion.isBlank())
            throw new IllegalArgumentException("La fecha de recolección no puede ser nula o vacía.");
        this.fechaRecoleccion = fechaRecoleccion;
    }

    public void setLugarRecoleccion(String lugarRecoleccion) {
        if (lugarRecoleccion == null || lugarRecoleccion.isBlank())
            throw new IllegalArgumentException("El lugar de recolección no puede ser nulo o vacío.");
        this.lugarRecoleccion = lugarRecoleccion;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.isBlank())
            throw new IllegalArgumentException("El estado no puede ser nulo o vacío.");
        this.estado = estado;
    }

    @Override
    public String generarResumen() {
        return "Evidencia [" + idEvidencia + "] - " + descripcion + " (" + estado + ")";
    }

    public void mostrarDetalles() {
        System.out.println(toString());
    }

    public String toCSV() {
        return idEvidencia + ";" +
                descripcion + ";" +
                fechaRecoleccion + ";" +
                lugarRecoleccion + ";" +
                estado;
    }

    public static Evidencia fromCSV(String linea) throws CSVInvalidoException {
        if (linea == null || linea.isBlank())
            throw new CSVInvalidoException("Línea CSV vacía o nula.");

        String prefijo = linea.split(";")[0].toUpperCase();

        switch (prefijo) {
            case "FISICA":
                return EvidenciaFisica.fromCSV(linea);
            case "DIGITAL":
                return EvidenciaDigital.fromCSV(linea);
            case "FORENSE":
                return EvidenciaForense.fromCSV(linea);
            default:
                throw new CSVInvalidoException("Tipo de evidencia desconocido: " + prefijo);
        }
    }

    @Override
    public String toString() {
        return "ID: " + idEvidencia +
                "\nDescripcion: " + descripcion +
                "\nFecha: " + fechaRecoleccion +
                "\nLugar: " + lugarRecoleccion +
                "\nEstado: " + estado;
    }

    public abstract String documentar();
}
