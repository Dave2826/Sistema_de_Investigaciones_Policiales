import exceptions.CasoCerradoException;
import exceptions.ElementoDuplicadoException;
import models.Caso;
import models.Detective;
import models.Entrevista;
import models.Evidencia;
import models.EvidenciaDigital;
import models.EvidenciaFisica;
import models.EvidenciaForense;
import models.Persona;
import models.Reporte;
import models.Sospechoso;
import models.Testigo;
import models.Victima;
import service.CasoServicio;

public class Main {

    public static void main(String[] args) {

        CasoServicio servicio = new CasoServicio();
        servicio.cargarDatos();

        try {
            // --- CASO ---
            Caso caso = new Caso("C001", "Homicidio en Av. Central", "ABIERTO");
            servicio.agregarCaso(caso);

            // --- PERSONAS ---
            Detective detective = new Detective("D01", "Laura Mendez", 38, "Homicidios", "Inspectora");
            Sospechoso sospechoso = new Sospechoso("S01", "Carlos Ruiz", 29, "Antecedentes por robo", 7);
            Victima victima = new Victima("V01", "Miguel Torres", 45, "Fallecido", "Homicidio");
            Testigo testigo = new Testigo("T01", "Ana Gomez", 32, "Vio al sospechoso huir", false);

            servicio.agregarPersona("C001", detective);
            servicio.agregarPersona("C001", sospechoso);
            servicio.agregarPersona("C001", victima);
            servicio.agregarPersona("C001", testigo);

            // --- EVIDENCIAS ---
            EvidenciaFisica ev1 = new EvidenciaFisica("E01", "Cuchillo", "2026-03-01",
                    "Escena del crimen", "Activa", "Cuchillo de cocina", 0.3, "20x3cm", "Bodega A");

            EvidenciaDigital ev2 = new EvidenciaDigital("E02", "Video camara", "2026-03-01",
                    "Edificio contiguo", "Activa", "MP4", "a1b2c3d4e5f6", 150.5);

            EvidenciaForense ev3 = new EvidenciaForense("E03", "Muestra de sangre", "2026-03-01",
                    "Escena del crimen", "En analisis", "ADN", "Laboratorio Central", "Pendiente");

            servicio.agregarEvidencia("C001", ev1);
            servicio.agregarEvidencia("C001", ev2);
            servicio.agregarEvidencia("C001", ev3);

            // --- ENTREVISTA Y REPORTE ---
            Entrevista entrevista = new Entrevista(
                    "EN01", "2026-03-02", "Sospechoso nervioso durante interrogatorio",
                    detective, sospechoso);

            Reporte reporte = new Reporte(
                    "R01", "Reporte inicial", "Se inicia investigacion por homicidio en Av. Central.");

            caso.agregarEntrevista(entrevista);
            caso.agregarReporte(reporte);

            // ========================================
            // IMPRESION ESTRUCTURADA
            // ========================================
            System.out.println("========================================");
            System.out.println("  SISTEMA DE INVESTIGACIONES POLICIALES ");
            System.out.println("========================================");

            caso.mostrarDetalles();

            System.out.println("\n--- Polimorfismo: rol de cada persona ---");
            for (Persona p : caso.getPersonas())
                System.out.println("  " + p.getNombre() + " -> " + p.getRol());

            System.out.println("\n--- Busqueda por ID: S01 ---");
            Persona encontrada = servicio.buscarPersonaPorId("C001", "S01");
            if (encontrada != null)
                System.out.println("  Encontrado: " + encontrada);

            System.out.println("\n--- Busqueda evidencia por ID: E01 ---");
            Evidencia evEncontrada = servicio.buscarEvidenciaPorId("C001", "E01");
            if (evEncontrada != null)
                evEncontrada.mostrarDetalles();

            System.out.println("\n--- Filtro personas: Testigo ---");
            for (Persona p : servicio.filtrarPersonasPorRol("C001", "Testigo"))
                System.out.println("  " + p);

            System.out.println("\n--- Filtro evidencias: Activa ---");
            for (Evidencia e : servicio.filtrarEvidenciasPorEstado("C001", "Activa"))
                System.out.println("  " + e);

            System.out.println("\n--- Eliminar evidencia E02 ---");
            boolean eliminada = servicio.eliminarEvidenciaPorId("C001", "E02");
            System.out.println(eliminada ? "  Eliminada correctamente." : "  No encontrada.");

            System.out.println("\n--- Prueba excepcion: persona duplicada ---");
            servicio.agregarPersona("C001", detective);

        } catch (CasoCerradoException e) {
            System.out.println("Error caso cerrado: " + e.getMessage());
        } catch (ElementoDuplicadoException e) {
            System.out.println("Error duplicado: " + e.getMessage());
        }
    }
}