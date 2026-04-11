package models;

import exceptions.CSVInvalidoException;

public class Detective extends Persona {

    // Atributos
    private String especialidad;
    private String rango;

    // Constructor con validaciones
    public Detective(String id, String nombre, int edad, String especialidad, String rango) {
        super(id, nombre, edad);

        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }
        if (rango == null || rango.trim().isEmpty()) {
            throw new IllegalArgumentException("El rango es obligatorio");
        }

        this.especialidad = especialidad;
        this.rango = rango;
    }

    // Getters
    public String getEspecialidad() {
        return especialidad;
    }

    public String getRango() {
        return rango;
    }

    // getRol()
    @Override
    public String getRol() {
        return "Detective";
    }

    // toString()
    @Override
    public String toString() {
        return super.toString() +
                ", especialidad: " + especialidad +
                ", rango: " + rango;
    }

    public String toCSV() {
        return "DETECTIVE;" + getId() + ";" + getNombre() + ";" + getEdad() + ";" + especialidad + ";" + rango;
    }

    public static Detective fromCSV(String linea) throws CSVInvalidoException {
        String[] partes = linea.split(";");

        if (partes.length < 6) {
            throw new CSVInvalidoException("Datos incompletos para Detective" + linea);
        }
        try {
            return new Detective(
                    partes[1], // ID
                    partes[2], // Nombre
                    Integer.parseInt(partes[3]), // Edad
                    partes[4], // Especialidad
                    partes[5] // Rango
            );
        } catch (NumberFormatException e) {
            throw new CSVInvalidoException("Error al convertir número en Detective: " + linea);
        }
    }
}
