package models;

public class Victima extends Persona {
    private String estado;
    private String tipoDelito;

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

    public String getEstado() {
        return estado;
    }

    public String getTipoDelito() {
        return tipoDelito;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado no puede ser nulo o vacío");
        }
        this.estado = estado;
    }

    @Override
    public String getRol() {
        return "Víctima";
    }

    @Override
    public String toString() {
        return super.toString() + ", estado: " + estado + ", tipo de delito: " + tipoDelito;
    }
}
