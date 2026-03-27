package models;

import exceptions.CSVInvalidoException;

public abstract class Persona {
    // Atributos
    private String id;
    private String nombre;
    private int edad;

    // constructor con validaciones
    public Persona(String id, String nombre, int edad) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID no puede ser nulo o vacío");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }

        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    // Setters con validaciones
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID no puede ser nulo o vacío. Coloque un ID válido.");
        }
        this.id = id;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        this.edad = edad;
    }

    // Método abstracto para obtener el rol de la persona
    public abstract String getRol();

    // toString()
    @Override
    public String toString() {
        return "Persona [ id: " + id
                + ", nombre: " + nombre
                + ", edad: " + edad + "]";
    }

    // Método toCSV()
    public abstract String toCSV();

    public static Persona fromCSV(String linea) throws CSVInvalidoException {
        if (linea == null || linea.isBlank())
            throw new CSVInvalidoException("Línea vacía");

        String prefijo = linea.split(",")[0].toUpperCase();

        switch (prefijo) {
            case "DETECTIVE":
                return Detective.fromCSV(linea);
            case "TESTIGO":
                return Testigo.fromCSV(linea);
            case "VICTIMA":
                return Victima.fromCSV(linea);
            case "SOSPECHOSO":
                return Sospechoso.fromCSV(linea);
            default:
                throw new CSVInvalidoException("Tipo desconocido: " + prefijo);
        }
    }
}
