import java.util.ArrayList;
import java.util.List;

import exceptions.CasoCerradoException;
import exceptions.ElementoDuplicadoException;
import models.Caso;
import models.Detective;
import models.Entrevista;
import models.Evidencia;
import models.EvidenciaFisica;
import models.Persona;
import models.Reporte;
import models.Sospechoso;
import service.CasoServicio;

public class Main {
    public static void main(String[] args) {

        Caso caso = new Caso("C1", "Caso prueba", "ABIERTO");
        CasoServicio servicio = new CasoServicio();

        // CARGAR DATOS DESDE CSV
        servicio.cargarDatos(caso);

        try {
            // Crear objetos
            Detective detective = new Detective("D01", "Juan Perez", 35, "Homicidios", "Inspector");
            Sospechoso sospechoso = new Sospechoso("S01", "Carlos Lopez", 28, "Antecedentes por robo", 3);

            EvidenciaFisica evidencia1 = new EvidenciaFisica(
                    "E01", "Cuchillo", "2026-03-01", "Escena del crimen",
                    "Activa", "Cuchillo de cocina", 0.5, "30x5cm", "Bodega A");

            EvidenciaFisica evidencia2 = new EvidenciaFisica(
                    "E02", "Pistola", "2026-03-02", "Vehiculo sospechoso",
                    "Resguardada", "Arma corta", 1.2, "20x10cm", "Bodega B");

            Entrevista entrevista = new Entrevista(
                    "EN01", "2026-03-02", "El sospechoso mostró nerviosismo", detective, sospechoso);

            Reporte reporte = new Reporte(
                    "R01", "Reporte inicial", "Se abre la investigación del caso.");

            // ALTAS
            servicio.agregarPersona(caso, detective);
            servicio.agregarPersona(caso, sospechoso);
            servicio.agregarEvidencia(caso, evidencia1);
            servicio.agregarEvidencia(caso, evidencia2);

            caso.agregarEntrevista(entrevista);
            caso.agregarReporte(reporte);

            // POLIMORFISMO
            System.out.println("=== POLIMORFISMO PERSONAS ===");
            List<Persona> personas = new ArrayList<>();
            personas.add(detective);
            personas.add(sospechoso);

            for (Persona p : personas) {
                System.out.println(p.getNombre() + " -> " + p.getRol());
            }

            // BÚSQUEDA
            System.out.println("\n=== BUSQUEDA DE PERSONA ===");
            Persona personaEncontrada = servicio.buscarPersonaPorId(caso, "S01");
            if (personaEncontrada != null) {
                System.out.println("Persona encontrada: " + personaEncontrada.getNombre() + " (" + personaEncontrada.getRol() + ")");
            } else {
                System.out.println("No se encontró la persona.");
            }

            System.out.println("\n=== BUSQUEDA DE EVIDENCIA ===");
            Evidencia evidenciaEncontrada = servicio.buscarEvidenciaPorId(caso, "E01");
            if (evidenciaEncontrada != null) {
                System.out.println("Evidencia encontrada:");
                evidenciaEncontrada.mostrarDetalles();
            }

            // FILTROS
            System.out.println("\n=== FILTRO DE PERSONAS POR ROL ===");
            List<Persona> sospechosos = servicio.filtrarPersonasPorRol(caso, "Sospechoso");
            for (Persona p : sospechosos) {
                System.out.println(p);
            }

            System.out.println("\n=== FILTRO DE EVIDENCIAS POR ESTADO ===");
            List<Evidencia> evidenciasActivas = servicio.filtrarEvidenciasPorEstado(caso, "Activa");
            for (Evidencia e : evidenciasActivas) {
                System.out.println(e);
            }

            // ELIMINAR
            System.out.println("\n=== ELIMINAR EVIDENCIA ===");
            boolean eliminada = servicio.eliminarEvidenciaPorId(caso, "E02");
            if (eliminada) {
                System.out.println("La evidencia E02 fue eliminada correctamente.");
            }

            // EXCEPCIÓN
            System.out.println("\n=== PRUEBA DE EXCEPCION: DUPLICADO ===");
            servicio.agregarEvidencia(caso, evidencia1);

        } catch (CasoCerradoException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (ElementoDuplicadoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}