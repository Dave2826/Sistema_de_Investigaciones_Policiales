package models;

public class Sospechoso extends Persona {
    private String antecedentes;
    private int nivelSospecha;

    public Sospechoso(String id, String nombre, int edad, String antecedentes, int nivelSospecha) {
        super(id, nombre, edad);

        if (antecedentes == null || antecedentes.trim().isEmpty()) {
            throw new IllegalArgumentException("Los antecedentes no pueden ser nulos o vacíos");
        }
        if (nivelSospecha < 0 || nivelSospecha > 10) {
            throw new IllegalArgumentException("El nivel de sospecha debe estar entre 0 y 10");
        }
        this.antecedentes = antecedentes;
        this.nivelSospecha = nivelSospecha;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public int getNivelSospecha() {
        return nivelSospecha;
    }

    public void setNivelSospecha(int nivelSospecha) {
        if (nivelSospecha < 0 || nivelSospecha > 10) {
            throw new IllegalArgumentException(
                    "El nivel de sospecha no puede ser menor que 0 ni mayor que 10. Ingrese un valor válido.");
        }
        this.nivelSospecha = nivelSospecha;
    }

    @Override
    public String getRol() {
        return "Sospechoso";
    }

    @Override
    public String toString() {
        return super.toString() + ", antecedentes: " + antecedentes + ", nivel de sospecha: " + nivelSospecha;
    }
}
