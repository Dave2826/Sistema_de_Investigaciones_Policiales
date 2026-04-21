package main;

import java.util.Scanner;
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

    // Limpia la consola ejecutando el comando cls de Windows.
    private static void limpiarConsola() {
        try {
            new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO()
                    .start()
                    .waitFor();
        } catch (Exception e) {
            // Fallback: imprime lineas en blanco si falla
            for (int i = 0; i < 50; i++)
                System.out.println();
        }
    }

    // Pausa la ejecucion hasta que el usuario presione Enter.
    private static void pausar(Scanner sc) {
        System.out.println("\nPresione Enter para continuar...");
        sc.nextLine();
    }

    // Lee la opcion del menu como texto para evitar InputMismatchException.
    private static int leerOpcionMenu(Scanner sc) {
        String entrada = sc.nextLine().trim();

        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CasoServicio servicio = new CasoServicio();
        servicio.cargarDatos();

        int opcion = -1;

        while (opcion != 0) {
            limpiarConsola();
            System.out.println("\n========================================");
            System.out.println("  SISTEMA DE INVESTIGACIONES POLICIALES ");
            System.out.println("========================================");
            System.out.println("1. Generar caso de demostracion");
            System.out.println("2. Ver detalles del caso");
            System.out.println("3. Eliminar caso");
            System.out.println("4. Buscar persona por ID");
            System.out.println("5. Filtrar personas por rol");
            System.out.println("6. Filtrar evidencias por estado");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = leerOpcionMenu(sc);

            switch (opcion) {

                case 1:
                    try {
                        if (servicio.buscarCasoPorId("C001") != null) {
                            System.out.println("\nEl caso C001 ya existe.");
                            break;
                        }

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

                        servicio.agregarEntrevista("C001", entrevista);
                        servicio.agregarReporte("C001", reporte);

                        System.out.println("\nCaso C001 generado exitosamente.");

                    } catch (CasoCerradoException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (ElementoDuplicadoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pausar(sc);
                    break;

                case 2:
                    System.out.print("\nIngrese el ID del caso: ");
                    String idVer = sc.nextLine();
                    Caso casoVer = servicio.buscarCasoPorId(idVer);

                    if (casoVer == null) {
                        System.out.println("\nNo se encontro el caso con ID: " + idVer);
                    } else {
                        casoVer.mostrarDetalles();

                        System.out.println("\n--- Rol de cada persona ---");
                        for (Persona p : casoVer.getPersonas())
                            System.out.println("  " + p.getNombre() + " -> " + p.getRol());
                    }
                    pausar(sc);
                    break;

                case 3:
                    System.out.print("\nIngrese el ID del caso a eliminar: ");
                    String idEliminar = sc.nextLine();
                    servicio.eliminarCaso(idEliminar);
                    System.out.println("\nCaso " + idEliminar + " eliminado correctamente.");
                    pausar(sc);
                    break;

                case 4:
                    System.out.print("\nIngrese el ID del caso: ");
                    String idCasoBuscar = sc.nextLine();
                    System.out.print("\nIngrese el ID de la persona: ");
                    String idPersona = sc.nextLine();

                    Persona encontrada = servicio.buscarPersonaPorId(idCasoBuscar, idPersona);
                    if (encontrada != null) {
                        System.out.println("\nEncontrado: " + encontrada);
                    } else {
                        System.out.println("\nNo se encontro la persona con ID: " + idPersona);
                    }
                    pausar(sc);
                    break;

                case 5:
                    System.out.print("\nIngrese el ID del caso: ");
                    String idCasoFiltro = sc.nextLine();
                    System.out.print("\nIngrese el rol (Detective, Sospechoso, Testigo, Victima): ");
                    String rol = sc.nextLine();

                    java.util.List<Persona> personasFiltradas = servicio.filtrarPersonasPorRol(idCasoFiltro, rol);
                    if (personasFiltradas.isEmpty()) {
                        System.out.println("\nNo se encontraron personas con el rol: " + rol);
                    } else {
                        for (Persona p : personasFiltradas)
                            System.out.println("  " + p);
                    }
                    pausar(sc);
                    break;

                case 6:
                    System.out.print("\nIngrese el ID del caso: ");
                    String idCasoEv = sc.nextLine();
                    System.out.print("\nIngrese el estado (Activa, En analisis, etc.): ");
                    String estado = sc.nextLine();

                    java.util.List<Evidencia> evidenciasFiltradas = servicio.filtrarEvidenciasPorEstado(idCasoEv,
                            estado);
                    if (evidenciasFiltradas.isEmpty()) {
                        System.out.println("\nNo se encontraron evidencias con estado: " + estado);
                    } else {
                        System.out.println("\n  Resultados (" + evidenciasFiltradas.size() + "):\n");
                        int num = 1;
                        for (Evidencia e : evidenciasFiltradas) {
                            System.out.println("  #" + num);
                            System.out.println(e);
                            System.out.println();
                            num++;
                        }
                    }
                    pausar(sc);
                    break;

                case 0:
                    System.out.println("\nSaliendo del sistema...");
                    break;

                default:
                    System.out.println("\nEntrada invalida. Ingrese un numero del 0 al 6.");
                    pausar(sc);
                    break;
            }
        }

        sc.close();
    }
}
