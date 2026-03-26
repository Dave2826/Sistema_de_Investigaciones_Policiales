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

    private CasoRepository repository = new CasoRepository();

    // =========================
    // CARGAR DATOS (AL INICIAR)
    // =========================
    public void cargarDatos(Caso caso) {

        List<Persona> personas = repository.cargarPersonas();

        for (Persona p : personas) {
            caso.agregarPersona(p);
        }
    }

    // =========================
    // AGREGAR PERSONA
    // =========================
    public void agregarPersona(Caso caso, Persona persona)
            throws CasoCerradoException, ElementoDuplicadoException {

        // VALIDACIÓN: CASO CERRADO
        if ("CERRADO".equalsIgnoreCase(caso.getEstado())) {
            throw new CasoCerradoException(
                    "No se puede agregar personas porque el caso " + caso.getIdCaso() + " está cerrado.");
        }

        if (caso == null) {
            throw new IllegalArgumentException("El caso no puede ser nulo.");
        }
        if (persona == null) {
            throw new IllegalArgumentException("La persona no puede ser nula.");
        }

        // VALIDACIÓN: DUPLICADOS
        for (Persona p : caso.getPersonas()) {
            if (p.getId().equals(persona.getId())) {
                throw new ElementoDuplicadoException(
                        "La persona con ID " + persona.getId() + " ya está registrada en el caso.");
            }
        }

        caso.agregarPersona(persona);

        // GUARDAR AUTOMÁTICAMENTE
        repository.guardarPersonas(caso.getPersonas());
    }

    // =========================
    // AGREGAR EVIDENCIA
    // =========================
    public void agregarEvidencia(Caso caso, Evidencia evidencia)
            throws CasoCerradoException, ElementoDuplicadoException {

        // VALIDACIÓN: CASO CERRADO
        if ("CERRADO".equalsIgnoreCase(caso.getEstado())) {
            throw new CasoCerradoException(
                    "No se puede agregar evidencia porque el caso " + caso.getIdCaso() + " está cerrado.");
        }

        if (caso == null) {
            throw new IllegalArgumentException("El caso no puede ser nulo.");
        }
        if (evidencia == null) {
            throw new IllegalArgumentException("La evidencia no puede ser nula.");
        }

        // VALIDACIÓN: DUPLICADOS
        for (Evidencia e : caso.getEvidencias()) {
            if (e.getIdEvidencia().equals(evidencia.getIdEvidencia())) {
                throw new ElementoDuplicadoException(
                        "La evidencia con ID " + evidencia.getIdEvidencia() + " ya está registrada en el caso.");
            }
        }

        caso.agregarEvidencia(evidencia);
    }

    // =========================
    // BUSCAR PERSONA
    // =========================
    public Persona buscarPersonaPorId(Caso caso, String id) {

        if (caso == null) {
            throw new IllegalArgumentException("El caso no puede ser nulo.");
        }

        for (Persona p : caso.getPersonas()) {
            if (p.getId().equals(id)) {
                return p;
            }
        }

        return null;
    }

    // =========================
    // BUSCAR EVIDENCIA
    // =========================
    public Evidencia buscarEvidenciaPorId(Caso caso, String idEvidencia) {

        if (caso == null) {
            throw new IllegalArgumentException("El caso no puede ser nulo.");
        }

        for (Evidencia e : caso.getEvidencias()) {
            if (e.getIdEvidencia().equals(idEvidencia)) {
                return e;
            }
        }

        return null;
    }

    // =========================
    // FILTRAR EVIDENCIAS
    // =========================
    public List<Evidencia> filtrarEvidenciasPorEstado(Caso caso, String estado) {

        List<Evidencia> resultado = new ArrayList<>();

        for (Evidencia e : caso.getEvidencias()) {
            if (e.getEstado().equalsIgnoreCase(estado)) {
                resultado.add(e);
            }
        }

        return resultado;
    }

    // =========================
    // FILTRAR PERSONAS
    // =========================
    public List<Persona> filtrarPersonasPorRol(Caso caso, String rol) {

        List<Persona> resultado = new ArrayList<>();

        for (Persona p : caso.getPersonas()) {
            if (p.getRol().equalsIgnoreCase(rol)) {
                resultado.add(p);
            }
        }

        return resultado;
    }

    // =========================
    // ELIMINAR EVIDENCIA
    // =========================
    public boolean eliminarEvidenciaPorId(Caso caso, String idEvidencia)
            throws CasoCerradoException {

        // VALIDACIÓN: CASO CERRADO
        if ("CERRADO".equalsIgnoreCase(caso.getEstado())) {
            throw new CasoCerradoException(
                    "No se puede eliminar evidencia porque el caso " + caso.getIdCaso() + " está cerrado.");
        }

        Iterator<Evidencia> iterator = caso.getEvidenciasInternas().iterator();

        while (iterator.hasNext()) {
            Evidencia e = iterator.next();

            if (e.getIdEvidencia().equals(idEvidencia)) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }
}