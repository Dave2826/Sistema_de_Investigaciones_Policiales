package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exceptions.CasoCerradoException;
import exceptions.ElementoDuplicadoException;
import models.Caso;
import models.Evidencia;
import models.Persona;
import persistence.CasoRepository;

public class CasoServicio {

    private List<Caso> casos = new ArrayList<>();
    private CasoRepository repository = new CasoRepository();

    // CARGAR DATOS
    public void cargarDatos() {
        casos = repository.cargarCasos();
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
        repository.guardarPersonas(caso.getPersonas());
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
                return true;
            }
        }
        return false;
    }
}