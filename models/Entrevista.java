package models;
import interfaces.Documentable;
public class Entrevista implements Documentable {

    private String id;
    private String fecha;
    private String notas;
    private Detective detective;
    private Persona entrevistado;

    public Entrevista(String id, String fecha, String notas, Detective detective, Persona entrevistado) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("El ID de la entrevista no puede ser nulo o vacío.");
        if (fecha == null || fecha.isBlank())
            throw new IllegalArgumentException("La fecha no puede ser nula o vacía.");
        if (detective == null)
            throw new IllegalArgumentException("El detective no puede ser nulo.");
        if (entrevistado == null)
            throw new IllegalArgumentException("La persona entrevistada no puede ser nula.");

        this.id = id;
        this.fecha = fecha;
        this.notas = notas;
        this.detective = detective;
        this.entrevistado = entrevistado;
    }

    public String getId() { return id; }
    public String getFecha() { return fecha; }
    public String getNotas() { return notas; }
    public Detective getDetective() { return detective; }
    public Persona getEntrevistado() { return entrevistado; }

    @Override
    public String generarResumen() {
        return "Entrevista [ID: " + id + "] | Fecha: " + fecha +
               " | Persona entrevistada: " + entrevistado.getNombre() +
               " | Detective a cargo: " + detective.getNombre();
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("========== DETALLES DE ENTREVISTA ==========");
        System.out.println("ID          : " + id);
        System.out.println("Fecha       : " + fecha);
        System.out.println("Notas       : " + notas);
        System.out.println("Detective   : " + detective.getNombre());
        System.out.println("Entrevistado: " + entrevistado.getNombre());
        System.out.println("=============================================");
    }

    @Override
public String documentar() {
    return "Entrevista [" + id + "] - Fecha: " + fecha +
           "\nDetective: " + detective.getNombre() +
           "\nEntrevistado: " + entrevistado.getNombre() +
           "\nNotas: " + (notas != null ? notas : "Sin notas");
}

    @Override
    public String toString() {
        return "Entrevista{" +
               "id='" + id + '\'' +
               ", fecha='" + fecha + '\'' +
               ", notas='" + notas + '\'' +
               ", detective=" + detective.getNombre() +
               ", entrevistado=" + entrevistado.getNombre() +
               '}';
    }
}