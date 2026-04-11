package models;

import exceptions.CSVInvalidoException;

public class EvidenciaDigital extends Evidencia {

    private String formato;
    private String hashMD5;
    private double tamanoArchivo;

    public EvidenciaDigital(String idEvidencia, String descripcion, String fechaRecoleccion, String lugarRecoleccion,
            String estado, String formato, String hashMD5, double tamanoArchivo) {
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
        return "DIGITAL;" +
                getIdEvidencia() + ";" +
                getDescripcion() + ";" +
                getFechaRecoleccion() + ";" +
                getLugarRecoleccion() + ";" +
                getEstado() + ";" +
                formato + ";" +
                hashMD5 + ";" +
                tamanoArchivo;
    }

    public static EvidenciaDigital fromCSV(String linea) throws CSVInvalidoException {
        if (linea == null || linea.isBlank())
            throw new CSVInvalidoException("Línea CSV vacía o nula.");

        String[] partes = linea.split(";");

        if (partes.length != 9)
            throw new CSVInvalidoException("Formato CSV inválido para EvidenciaDigital.");

        try {
            String id = partes[1];
            String descripcion = partes[2];
            String fecha = partes[3];
            String lugar = partes[4];
            String estado = partes[5];
            String formato = partes[6];
            String hash = partes[7];
            double tamano = Double.parseDouble(partes[8]);

            return new EvidenciaDigital(id, descripcion, fecha, lugar, estado, formato, hash, tamano);

        } catch (NumberFormatException e) {
            throw new CSVInvalidoException("Error al convertir el tamaño del archivo.");
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
