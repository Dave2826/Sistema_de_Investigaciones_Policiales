package models;

import exceptions.CSVInvalidoException;

public class Victima extends Persona {
    // Atributos
    private String estado;
    private String tipoDelito;

    // Constructor con validaciones
    public Victima(String id, String nombre, int edad, String estado, String tipoDelito) {
        super(id, nombre, edad);

        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado no puede ser nulo o vacío");
        }
        if (tipoDelito == null || tipoDelito.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de delito no puede ser nulo o vacío");
        }

        this.estado = estado;
        this.tipoDelito = tipoDelito;
    }

    // Getters
    public String getEstado() {
        return estado;
    }

    public String getTipoDelito() {
        return tipoDelito;
    }

    // Setter con validación
    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado no puede ser nulo o vacío");
        }
        this.estado = estado;
    }

    // Override getRol()
    @Override
    public String getRol() {
        return "Víctima";
    }

    // uso del toCSV()
    @Override
    public String toCSV() {
        return "VICTIMA," + getId() + "," + getNombre() + "," + getEdad() + "," + estado + "," + tipoDelito;
    }

    // fromCSV()
    public static Victima fromCSV(String linea) throws CSVInvalidoException {
        String[] partes = linea.split(",", -1);
        if (partes.length != 6) {
            throw new CSVInvalidoException("Formato CSV inválido para la víctima." + linea);
        }
        try {
            return new Victima(partes[1], // ID
                    partes[2], // Nombre
                    Integer.parseInt(partes[3]), // Edad
                    partes[4], // Estado
                    partes[5]); // Tipo de delito
        } catch (NumberFormatException e) {
            throw new CSVInvalidoException("Error al convertir número en Víctima: " + linea);
        }

    }

    // Uso del toString()
    @Override
    public String toString() {
        return "Víctima" + ", estado: " + estado + ", tipo de delito: " + tipoDelito;
    }
}
