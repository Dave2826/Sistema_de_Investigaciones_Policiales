package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exceptions.CasoCerradoException;
import exceptions.ElementoDuplicadoException;
import models.Caso;
import models.Detective;
import models.Entrevista;
import models.Evidencia;
import models.Persona;
import models.Reporte;
import persistence.CasoRepository;

public class CasoServicio {

    private List<Caso> casos = new ArrayList<>();
    private CasoRepository repository = new CasoRepository();

    // CARGAR DATOS (reconstruye casos con personas, evidencias, entrevistas y reportes)
    public void cargarDatos() {
        casos = repository.cargarCasos();

        for (Caso caso : casos) {
            String id = caso.getIdCaso();

            // Cargar personas
            List<Persona> personas = repository.cargarPersonas(id);
            for (Persona p : personas) {
                caso.agregarPersona(p);
            }

            // Cargar evidencias
            List<Evidencia> evidencias = repository.cargarEvidencias(id);
            for (Evidencia e : evidencias) {
                caso.agregarEvidencia(e);
            }

            // Cargar reportes
            List<Reporte> reportes = repository.cargarReportes(id);
            for (Reporte r : reportes) {
                caso.agregarReporte(r);
            }

            // Cargar entrevistas (necesitan detective y entrevistado del caso)
            List<String[]> entrevistasCrudas = repository.cargarEntrevistasCrudas(id);
            for (String[] partes : entrevistasCrudas) {
                try {
                    if (partes.length < 5) continue;
                    String idDetective = desescaparCSV(partes[3]);
                    String idEntrevistado = desescaparCSV(partes[4]);

                    Detective detective = null;
                    Persona entrevistado = null;

                    for (Persona p : caso.getPersonas()) {
                        if (p.getId().equals(idDetective) && p instanceof Detective)
                            detective = (Detective) p;
                        if (p.getId().equals(idEntrevistado))
                            entrevistado = p;
                    }

                    if (detective != null && entrevistado != null) {
                        String lineaCSV = String.join(";", partes);
                        Entrevista ent = Entrevista.fromCSV(lineaCSV, detective, entrevistado);
                        caso.agregarEntrevista(ent);
                    }
                } catch (Exception e) {
                    System.out.println("Error al cargar entrevista: " + e.getMessage());
                }
            }
        }
    }

    // AGREGAR CASO
    public void agregarCaso(Caso caso) {
        if (caso == null)
            throw new IllegalArgumentException("El caso no puede ser nulo.");
        casos.add(caso);
        repository.guardarCasos(casos);
    }

    // BUSCAR CASO
    public Caso buscarCasoPorId(String idCaso) {
        for (Caso c : casos) {
            if (c.getIdCaso().equals(idCaso))
                return c;
        }
        return null;
    }

    public List<Caso> listarCasos() {
        return casos;
    }

    // AGREGAR PERSONA
    public void agregarPersona(String idCaso, Persona persona)
            throws CasoCerradoException, ElementoDuplicadoException {

        Caso caso = buscarCasoPorId(idCaso);
        if (caso == null)
            throw new IllegalArgumentException("No existe un caso con ID: " + idCaso);

        if ("CERRADO".equalsIgnoreCase(caso.getEstado()))
            throw new CasoCerradoException("El caso " + idCaso + " está cerrado.");

        if (persona == null)
            throw new IllegalArgumentException("La persona no puede ser nula.");

        for (Persona p : caso.getPersonas()) {
            if (p.getId().equals(persona.getId()))
                throw new ElementoDuplicadoException(
                        "La persona con ID " + persona.getId() + " ya está registrada.");
        }

        caso.agregarPersona(persona);
        repository.guardarPersonas(idCaso, caso.getPersonas());
    }

    // AGREGAR EVIDENCIA
    public void agregarEvidencia(String idCaso, Evidencia evidencia)
            throws CasoCerradoException, ElementoDuplicadoException {

        Caso caso = buscarCasoPorId(idCaso);
        if (caso == null)
            throw new IllegalArgumentException("No existe un caso con ID: " + idCaso);

        if ("CERRADO".equalsIgnoreCase(caso.getEstado()))
            throw new CasoCerradoException("El caso " + idCaso + " está cerrado.");

        if (evidencia == null)
            throw new IllegalArgumentException("La evidencia no puede ser nula.");

        for (Evidencia e : caso.getEvidencias()) {
            if (e.getIdEvidencia().equals(evidencia.getIdEvidencia()))
                throw new ElementoDuplicadoException(
                        "La evidencia con ID " + evidencia.getIdEvidencia() + " ya está registrada.");
        }

        caso.agregarEvidencia(evidencia);
        repository.guardarEvidencias(idCaso, caso.getEvidencias());
    }

    // AGREGAR ENTREVISTA
    public void agregarEntrevista(String idCaso, Entrevista entrevista)
            throws CasoCerradoException {

        Caso caso = buscarCasoPorId(idCaso);
        if (caso == null)
            throw new IllegalArgumentException("No existe un caso con ID: " + idCaso);

        if ("CERRADO".equalsIgnoreCase(caso.getEstado()))
            throw new CasoCerradoException("El caso " + idCaso + " está cerrado.");

        if (entrevista == null)
            throw new IllegalArgumentException("La entrevista no puede ser nula.");

        caso.agregarEntrevista(entrevista);
        repository.guardarEntrevistas(idCaso, caso.getEntrevistas());
    }

    // AGREGAR REPORTE
    public void agregarReporte(String idCaso, Reporte reporte)
            throws CasoCerradoException {

        Caso caso = buscarCasoPorId(idCaso);
        if (caso == null)
            throw new IllegalArgumentException("No existe un caso con ID: " + idCaso);

        if ("CERRADO".equalsIgnoreCase(caso.getEstado()))
            throw new CasoCerradoException("El caso " + idCaso + " está cerrado.");

        if (reporte == null)
            throw new IllegalArgumentException("El reporte no puede ser nulo.");

        caso.agregarReporte(reporte);
        repository.guardarReportes(idCaso, caso.getReportes());
    }

    // BUSCAR PERSONA
    public Persona buscarPersonaPorId(String idCaso, String id) {
        Caso caso = buscarCasoPorId(idCaso);
        if (caso == null)
            return null;
        for (Persona p : caso.getPersonas()) {
            if (p.getId().equals(id))
                return p;
        }
        return null;
    }

    // BUSCAR EVIDENCIA
    public Evidencia buscarEvidenciaPorId(String idCaso, String idEvidencia) {
        Caso caso = buscarCasoPorId(idCaso);
        if (caso == null)
            return null;
        for (Evidencia e : caso.getEvidencias()) {
            if (e.getIdEvidencia().equals(idEvidencia))
                return e;
        }
        return null;
    }

    // FILTRAR PERSONAS
    public List<Persona> filtrarPersonasPorRol(String idCaso, String rol) {
        List<Persona> resultado = new ArrayList<>();
        Caso caso = buscarCasoPorId(idCaso);
        if (caso == null)
            return resultado;
        for (Persona p : caso.getPersonas()) {
            if (p.getRol().equalsIgnoreCase(rol))
                resultado.add(p);
        }
        return resultado;
    }

    // FILTRAR EVIDENCIAS
    public List<Evidencia> filtrarEvidenciasPorEstado(String idCaso, String estado) {
        List<Evidencia> resultado = new ArrayList<>();
        Caso caso = buscarCasoPorId(idCaso);
        if (caso == null)
            return resultado;
        for (Evidencia e : caso.getEvidencias()) {
            if (e.getEstado().equalsIgnoreCase(estado))
                resultado.add(e);
        }
        return resultado;
    }

    // ELIMINAR EVIDENCIA
    public boolean eliminarEvidenciaPorId(String idCaso, String idEvidencia)
            throws CasoCerradoException {

        Caso caso = buscarCasoPorId(idCaso);
        if (caso == null)
            throw new IllegalArgumentException("No existe un caso con ID: " + idCaso);

        if ("CERRADO".equalsIgnoreCase(caso.getEstado()))
            throw new CasoCerradoException("El caso " + idCaso + " está cerrado.");

        Iterator<Evidencia> iterator = caso.getEvidenciasInternas().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getIdEvidencia().equals(idEvidencia)) {
                iterator.remove();
                repository.guardarEvidencias(idCaso, caso.getEvidencias());
                return true;
            }
        }
        return false;
    }

    // UTILIDAD
    private static String desescaparCSV(String valor) {
        if (valor == null) return "";
        String v = valor.trim();
        if (v.startsWith("\"") && v.endsWith("\""))
            v = v.substring(1, v.length() - 1);
        return v.replace("\"\"", "\"");
    }
}