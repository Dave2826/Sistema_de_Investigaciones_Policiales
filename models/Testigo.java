package models;

public class Testigo extends Persona {

    private String declaracion;
    private Boolean protegido;

    public Testigo(String id, String nombre, int edad, String declaracion, Boolean protegido) {
        super(id, nombre, edad);

        if (declaracion == null || declaracion.isBlank())
            throw new IllegalArgumentException("La declaración no puede ser nula o vacía.");

        if (protegido == null)
            throw new IllegalArgumentException("El estado de protección no puede ser nulo.");

        this.declaracion = declaracion;
        this.protegido = protegido;
    }

    public String getDeclaracion() {
        return declaracion;
    }

    public Boolean getProtegido() {
        return protegido;
    }

    @Override
    public String getRol() {
        return "Testigo";
    }

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

        return new Testigo(
                partes[1],
                partes[2],
                Integer.parseInt(partes[3]),
                partes[4],
                Boolean.parseBoolean(partes[5])
        );
    }
}