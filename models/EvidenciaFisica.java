package models;

public class EvidenciaFisica extends Evidencia {

    private String objeto;
    private double peso;
    private String dimensiones;
    private String ubicacionAlmacen;

    public EvidenciaFisica(String idEvidencia, String descripcion, String fechaRecoleccion, String lugarRecoleccion,String estado, String objeto, double peso, String dimensiones, String ubicacionAlmacen) {

        super(idEvidencia, descripcion, fechaRecoleccion, lugarRecoleccion, estado);

        if (objeto == null || objeto.isBlank())
            throw new IllegalArgumentException("El objeto no puede ser nulo o vacío.");
        if (peso <= 0)
            throw new IllegalArgumentException("El peso debe ser mayor a 0.");
        if (dimensiones == null || dimensiones.isBlank())
            throw new IllegalArgumentException("Las dimensiones no pueden ser nulas o vacías.");
        if (ubicacionAlmacen == null || ubicacionAlmacen.isBlank())
            throw new IllegalArgumentException("La ubicación de almacenamiento no puede ser nula o vacía.");

        this.objeto = objeto;
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.ubicacionAlmacen = ubicacionAlmacen;
    }

    public String getObjeto() {
        return objeto;
    }

    public double getPeso() {
        return peso;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public String getUbicacionAlmacen() {
        return ubicacionAlmacen;
    }

    @Override
    public String documentar() {
        return "Documento de evidencia fisica generado.";
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nObjeto: " + objeto +
                "\nPeso: " + peso + " kg" +
                "\nDimensiones: " + dimensiones +
                "\nUbicación: " + ubicacionAlmacen;
    }
}