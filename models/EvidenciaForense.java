package models;

public class EvidenciaForense extends Evidencia {

    private String tipoAnalisis;
    private String laboratorio;
    private String resultado;

    public EvidenciaForense(String idEvidencia, String descripcion, String fechaRecoleccion, String lugarRecoleccion,String estado, String tipoAnalisis, String laboratorio, String resultado) {

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

    public String getTipoAnalisis() {
        return tipoAnalisis;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        if (resultado == null || resultado.isBlank()) {
            throw new IllegalArgumentException("El resultado no puede ser nulo o vacío.");
        }
        this.resultado = resultado;
    }

    @Override
    public String documentar() {
        return "Reporte forense creado.";
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTipo Análisis: " + tipoAnalisis +
                "\nLaboratorio: " + laboratorio +
                "\nResultado: " + resultado;
    }
}