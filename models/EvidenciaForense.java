package models;

import exceptions.CSVInvalidoException;

public class EvidenciaForense extends Evidencia {

    private String tipoAnalisis;
    private String laboratorio;
    private String resultado;

    public EvidenciaForense(String idEvidencia, String descripcion, String fechaRecoleccion, String lugarRecoleccion, String estado, String tipoAnalisis, String laboratorio, String resultado) {
        super(idEvidencia, descripcion, fechaRecoleccion, lugarRecoleccion, estado);

        if (tipoAnalisis == null || tipoAnalisis.isBlank())
            throw new IllegalArgumentException("El tipo de análisis no puede ser nulo o vacío.");
        if (laboratorio == null || laboratorio.isBlank())
            throw new IllegalArgumentException("El laboratorio no puede ser nulo o vacío.");
        if (resultado == null || resultado.isBlank())
            throw new IllegalArgumentException("El resultado no puede ser nulo o vacío.");

        this.tipoAnalisis = tipoAnalisis;
        this.laboratorio = laboratorio;
        this.resultado = resultado;
    }

    public String getTipoAnalisis() { return tipoAnalisis; }
    public String getLaboratorio() { return laboratorio; }
    public String getResultado() { return resultado; }

    public void setResultado(String resultado) {
        if (resultado == null || resultado.isBlank())
            throw new IllegalArgumentException("El resultado no puede ser nulo o vacío.");
        this.resultado = resultado;
    }

    @Override
    public String documentar() {
        return "Reporte forense creado.";
    }

    @Override
    public String toCSV() {
        return "FORENSE," +
               getIdEvidencia() + "," +
               getDescripcion() + "," +
               getFechaRecoleccion() + "," +
               getLugarRecoleccion() + "," +
               getEstado() + "," +
               tipoAnalisis + "," +
               laboratorio + "," +
               resultado;
    }

    public static EvidenciaForense fromCSV(String linea) throws CSVInvalidoException {
        if (linea == null || linea.isBlank())
            throw new CSVInvalidoException("Línea CSV vacía o nula.");

        String[] partes = linea.split(",");

        if (partes.length != 9)
            throw new CSVInvalidoException("Formato CSV inválido para EvidenciaForense.");

        String id = partes[1];
        String descripcion = partes[2];
        String fecha = partes[3];
        String lugar = partes[4];
        String estado = partes[5];
        String tipoAnalisis = partes[6];
        String laboratorio = partes[7];
        String resultado = partes[8];

        return new EvidenciaForense(id, descripcion, fecha, lugar, estado, tipoAnalisis, laboratorio, resultado);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTipo Análisis: " + tipoAnalisis +
                "\nLaboratorio: " + laboratorio +
                "\nResultado: " + resultado;
    }
}
