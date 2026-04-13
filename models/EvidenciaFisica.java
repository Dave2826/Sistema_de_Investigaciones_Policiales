package models;

import exceptions.CSVInvalidoException;

public class EvidenciaFisica extends Evidencia {

    private String objeto;
    private double peso;
    private String dimensiones;
    private String ubicacionAlmacen;

    public EvidenciaFisica(String idEvidencia, String descripcion, String fechaRecoleccion, String lugarRecoleccion,
            String estado, String objeto, double peso, String dimensiones, String ubicacionAlmacen) {
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
    public String toCSV() {
        return "FISICA;" +
                getIdEvidencia() + ";" +
                getDescripcion() + ";" +
                getFechaRecoleccion() + ";" +
                getLugarRecoleccion() + ";" +
                getEstado() + ";" +
                objeto + ";" +
                peso + ";" +
                dimensiones + ";" +
                ubicacionAlmacen;
    }

    public static EvidenciaFisica fromCSV(String linea) throws CSVInvalidoException {
        if (linea == null || linea.isBlank())
            throw new CSVInvalidoException("Línea CSV vacía o nula.");

        String[] partes = linea.split(";");

        if (partes.length != 10)
            throw new CSVInvalidoException("Formato CSV inválido para EvidenciaFisica.");

        try {
            String id = partes[1];
            String descripcion = partes[2];
            String fecha = partes[3];
            String lugar = partes[4];
            String estado = partes[5];
            String objeto = partes[6];
            double peso = Double.parseDouble(partes[7]);
            String dimensiones = partes[8];
            String ubicacion = partes[9];

            return new EvidenciaFisica(id, descripcion, fecha, lugar, estado, objeto, peso, dimensiones, ubicacion);

        } catch (NumberFormatException e) {
            throw new CSVInvalidoException("Error al convertir el peso.");
        }
    }

    @Override
    public String getTipo() {
        return "Fisica";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(formatoBase());
        sb.append("  |------------------------------------------|\n");
        sb.append(String.format("  |  %-14s: %-24s|\n", "Objeto", objeto));
        sb.append(String.format("  |  %-14s: %-24s|\n", "Peso", peso + " kg"));
        sb.append(String.format("  |  %-14s: %-24s|\n", "Dimensiones", dimensiones));
        sb.append(String.format("  |  %-14s: %-24s|\n", "Ubicacion", ubicacionAlmacen));
        sb.append(formatoCierre());
        return sb.toString();
    }
}
