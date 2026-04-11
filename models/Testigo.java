package models;

import exceptions.CSVInvalidoException;

public class Testigo extends Persona {

    // Atributos
    private String declaracion;
    private boolean protegido;

    // Constructor con validaciones
    public Testigo(String id, String nombre, int edad, String declaracion, boolean protegido) {
        super(id, nombre, edad);

        if (declaracion == null || declaracion.isBlank())
            throw new IllegalArgumentException("La declaración no puede ser nula o vacía.");

        this.declaracion = declaracion;
        this.protegido = protegido;
    }

    // Getters
    public String getDeclaracion() {
        return declaracion;
    }

    public boolean getProtegido() {
        return protegido;
    }

    // getRol()
    @Override
    public String getRol() {
        return "Testigo";
    }

    // toString()
    @Override
    public String toString() {
        return super.toString() +
                ", declaracion: " + declaracion +
                ", protegido: " + protegido;
    }

    // PERSISTENCIA (CSV)
    public String toCSV() {
        return "TESTIGO;" + getId() + ";" + getNombre() + ";" + getEdad() + ";" + declaracion + ";" + protegido;
    }

    // fromCSV()
    public static Testigo fromCSV(String linea) throws CSVInvalidoException {
        String[] partes = linea.split(";");

        if (partes.length < 6) {
            throw new CSVInvalidoException("Datos incompletos para Testigo" + linea);
        }
        try {
            return new Testigo(
                    partes[1], // ID
                    partes[2], // Nombre
                    Integer.parseInt(partes[3]), // Edad
                    partes[4], // Declaración
                    Boolean.parseBoolean(partes[5]) // Protegido
            );
        } catch (NumberFormatException e) {
            throw new CSVInvalidoException("Error al convertir número en Testigo: " + linea);
        }
    }
}
