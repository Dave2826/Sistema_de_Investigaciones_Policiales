package models;

public class Sospechoso extends Persona {
    //Atributos
    private String antecedentes;
    private int nivelSospecha;

    //Constructor con validaciones
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

    //Getters
    public String getAntecedentes() {
        return antecedentes;
    }

    public int getNivelSospecha() {
        return nivelSospecha;
    }


    //Setter con validación
    public void setAntecedentes(String antecedentes) {
        if(antecedentes == null || antecedentes.trim().isEmpty()) {
            throw new IllegalArgumentException("Los antecedentes no pueden ser nulos o vacíos. Coloque los antecedentes del sospechoso.");
        }
        this.antecedentes = antecedentes;
    }

    public void setNivelSospecha(int nivelSospecha) {
        if (nivelSospecha < 0 || nivelSospecha > 10) {
            throw new IllegalArgumentException("El nivel de sospecha debe estar entre 0 y 10");
        }
        this.nivelSospecha = nivelSospecha;
    }

    //Override de getRol()
    @Override
    public String getRol() {
        return "Sospechoso";
    }

    //Override de toString()
    @Override
    public String toString() {
        return super.toString() + ", antecedentes: " + antecedentes + ", nivel de sospecha: " + nivelSospecha;
    }

    //Método toCSV()
    @Override
    public String toCSV() {
        return "Sospechoso" + "," + getId() + "," + getNombre() + "," + getEdad() + "," + antecedentes + "," + nivelSospecha;
    }

    //fromCSV()
    public static Sospechoso fromCSV(String linea) {
        String[] partes = linea.split(",", -1);
        if(partes.length != 6) {
            throw new IllegalArgumentException("Formato CSV inválido para Sospechoso." + linea);
        }
        return new Sospechoso(partes[1].trim(), partes[2].trim(), Integer.parseInt(partes[3].trim()), partes[4].trim(), Integer.parseInt(partes[5].trim()));
    }
}
