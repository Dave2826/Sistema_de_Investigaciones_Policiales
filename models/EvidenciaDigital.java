package models;

public class EvidenciaDigital extends Evidencia {

    private String formato;
    private String hashMD5;
    private double tamanoArchivo;

    public EvidenciaDigital(String idEvidencia, String descripcion, String fechaRecoleccion, String lugarRecoleccion, String estado, String formato, String hashMD5, double tamanoArchivo) {
        super(idEvidencia, descripcion, fechaRecoleccion, lugarRecoleccion, estado);

        if (formato == null || formato.isBlank())
            throw new IllegalArgumentException("El formato no puede ser nulo o vacío.");
        if (hashMD5 == null || hashMD5.isBlank())
            throw new IllegalArgumentException("El hash MD5 no puede ser nulo o vacío.");
        if (tamanoArchivo <= 0)
            throw new IllegalArgumentException("El tamaño del archivo debe ser mayor a 0.");

        this.formato = formato;
        this.hashMD5 = hashMD5;
        this.tamanoArchivo = tamanoArchivo;
    }

    public String getFormato() {
        return formato;
    }

    public String getHashMD5() {
        return hashMD5;
    }

    public double getTamanoArchivo() {
        return tamanoArchivo;
    }

    @Override
    public String documentar() {
        return "Evidencia Digital\n" +
                "Formato: " + formato +
                "\nHash MD5: " + hashMD5 +
                "\nTamaño Archivo: " + tamanoArchivo + " MB";
    }

    @Override
    public String toCSV() {
        return getIdEvidencia() + "," +
               getDescripcion() + "," +
               getFechaRecoleccion() + "," +
               getLugarRecoleccion() + "," +
               getEstado() + "," +
               formato + "," +
               hashMD5 + "," +
               tamanoArchivo;
    }

    public static EvidenciaDigital fromCSV(String linea) {
        if (linea == null || linea.isBlank()) {
            throw new IllegalArgumentException("Línea CSV vacía o nula.");
        }

        String[] partes = linea.split(",");

        if (partes.length != 8) {
            throw new IllegalArgumentException("Formato CSV inválido para EvidenciaDigital.");
        }

        try {
            String id = partes[0];
            String descripcion = partes[1];
            String fecha = partes[2];
            String lugar = partes[3];
            String estado = partes[4];
            String formato = partes[5];
            String hash = partes[6];
            double tamano = Double.parseDouble(partes[7]);

            return new EvidenciaDigital(id, descripcion, fecha, lugar, estado, formato, hash, tamano);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error al convertir el tamaño del archivo.");
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nFormato: " + formato +
                "\nHash MD5: " + hashMD5 +
                "\nTamaño Archivo: " + tamanoArchivo + " MB";
    }
}
