package models;

public class Detective extends Persona {

    private String especialidad;
    private String rango;

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

    public String getEspecialidad() {
        return especialidad;
    }

    public String getRango() {
        return rango;
    }

    @Override
    public String getRol() {
        return "Detective";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", especialidad: " + especialidad +
                ", rango: " + rango;
    }
}