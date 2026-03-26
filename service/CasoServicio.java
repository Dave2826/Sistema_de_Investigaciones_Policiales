package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exceptions.CasoCerradoException;
import exceptions.ElementoDuplicadoException;
import models.Caso;
import models.Evidencia;
import models.Persona;

public class CasoServicio {

    // AGREGAR PERSONA
    public void agregarPersona(Caso caso, Persona persona)
            throws CasoCerradoException, ElementoDuplicadoException {

        if (caso == null) {
            throw new IllegalArgumentException("El caso no puede ser nulo.");
        }
        if (persona == null) {
            throw new IllegalArgumentException("La persona no puede ser nula.");
        }

        for (Persona p : caso.getPersonas()) {
            if (p.getId().equals(persona.getId())) {
                throw new ElementoDuplicadoException("La persona con ID " + persona.getId() + " ya está registrada en el caso.");
            }
        }

        caso.agregarPersona(persona);
    }

    // AGREGAR EVIDENCIA
    public void agregarEvidencia(Caso caso, Evidencia evidencia)
            throws CasoCerradoException, ElementoDuplicadoException {

        if (caso == null) {
            throw new IllegalArgumentException("El caso no puede ser nulo.");
        }
        if (evidencia == null) {
            throw new IllegalArgumentException("La evidencia no puede ser nula.");
        }

        for (Evidencia e : caso.getEvidencias()) {
            if (e.getIdEvidencia().equals(evidencia.getIdEvidencia())) {
                throw new ElementoDuplicadoException("La evidencia con ID " + evidencia.getIdEvidencia() + " ya está registrada en el caso.");
            }
        }

        caso.agregarEvidencia(evidencia);
    }

    // BUSCAR PERSONA POR ID
    public Persona buscarPersonaPorId(Caso caso, String id) {
        if (caso == null) {
            throw new IllegalArgumentException("El caso no puede ser nulo.");
        }
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El ID no puede ser nulo o vacío.");
        }

        for (Persona p : caso.getPersonas()) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    // BUSCAR EVIDENCIA POR ID
    public Evidencia buscarEvidenciaPorId(Caso caso, String idEvidencia) {
        if (caso == null) {
            throw new IllegalArgumentException("El caso no puede ser nulo.");
        }
        if (idEvidencia == null || idEvidencia.isBlank()) {
            throw new IllegalArgumentException("El ID de la evidencia no puede ser nulo o vacío.");
        }

        for (Evidencia e : caso.getEvidencias()) {
            if (e.getIdEvidencia().equals(idEvidencia)) {
                return e;
            }
        }
        return null;
    }

    // FILTRAR EVIDENCIAS POR ESTADO
    public List<Evidencia> filtrarEvidenciasPorEstado(Caso caso, String estado) {
        if (caso == null) {
            throw new IllegalArgumentException("El caso no puede ser nulo.");
        }
        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("El estado no puede ser nulo o vacío.");
        }

        List<Evidencia> resultado = new ArrayList<>();

        for (Evidencia e : caso.getEvidencias()) {
            if (e.getEstado().equalsIgnoreCase(estado)) {
                resultado.add(e);
            }
        }

        return resultado;
    }

    // FILTRAR PERSONAS POR ROL
    public List<Persona> filtrarPersonasPorRol(Caso caso, String rol) {
        if (caso == null) {
            throw new IllegalArgumentException("El caso no puede ser nulo.");
        }
        if (rol == null || rol.isBlank()) {
            throw new IllegalArgumentException("El rol no puede ser nulo o vacío.");
        }

        List<Persona> resultado = new ArrayList<>();

        for (Persona p : caso.getPersonas()) {
            if (p.getRol().equalsIgnoreCase(rol)) {
                resultado.add(p);
            }
        }

        return resultado;
    }

    // ELIMINAR EVIDENCIA POR ID (USANDO ITERATOR)
    public boolean eliminarEvidenciaPorId(Caso caso, String idEvidencia)
            throws CasoCerradoException {

        if (caso == null) {
            throw new IllegalArgumentException("El caso no puede ser nulo.");
        }
        if (idEvidencia == null || idEvidencia.isBlank()) {
            throw new IllegalArgumentException("El ID de la evidencia no puede ser nulo o vacío.");
        }

        if ("CERRADO".equalsIgnoreCase(caso.getEstado())) {
            throw new CasoCerradoException("No se puede eliminar evidencia porque el caso " + caso.getIdCaso() + " está cerrado.");
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