package models;

public class Testigo extends Persona {

    // Atributos
    private String declaracion;
    private Boolean protegido;

    // Constructor con validaciones
    public Testigo(String id, String nombre, int edad, String declaracion, Boolean protegido) {
        super(id, nombre, edad);

        if (declaracion == null || declaracion.isBlank())
            throw new IllegalArgumentException("La declaración no puede ser nula o vacía.");

        if (protegido == null)
            throw new IllegalArgumentException("El estado de protección no puede ser nulo.");

        this.declaracion = declaracion;
        this.protegido = protegido;
    }

    // Getters
    public String getDeclaracion() {
        return declaracion;
    }

    public Boolean getProtegido() {
        return protegido;
    }

    // Rol
    @Override
    public String getRol() {
        return "Testigo";
    }

    // toString
    @Override
    public String toString() {
        return super.toString() +
                ", declaracion: " + declaracion +
                ", protegido: " + protegido;
    }

    // =========================
    // PERSISTENCIA (CSV)
    // =========================

    public String toCSV() {
        return "TESTIGO," + getId() + "," + getNombre() + "," + getEdad() + "," + declaracion + "," + protegido;
    }

    public static Testigo fromCSV(String linea) {
        String[] partes = linea.split(",");

        if (partes.length < 6) {
            throw new IllegalArgumentException("Datos incompletos para Testigo");
        }

        String id = partes[1];
        String nombre = partes[2];

        int edad;
        try {
            edad = Integer.parseInt(partes[3]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Edad inválida en CSV");
        }

        String declaracion = partes[4];
        Boolean protegido = Boolean.parseBoolean(partes[5]);

        return new Testigo(id, nombre, edad, declaracion, protegido);
    }
}