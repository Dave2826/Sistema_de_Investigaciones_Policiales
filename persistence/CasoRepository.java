package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import exceptions.CSVInvalidoException;
import models.Caso;
import models.Persona;

public class CasoRepository {

    private static final String ARCHIVO_PERSONAS = "personas.csv";
    private static final String ARCHIVO_CASOS = "casos.csv";

    // PERSONAS
    public void guardarPersonas(List<Persona> personas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_PERSONAS))) {
            for (Persona p : personas) {
                writer.write(p.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar personas: " + e.getMessage());
        }
    }

    public List<Persona> cargarPersonas() {
        List<Persona> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_PERSONAS);
        if (!archivo.exists())
            return lista;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_PERSONAS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                try {
                    lista.add(Persona.fromCSV(linea));
                } catch (CSVInvalidoException e) {
                    System.out.println("Línea inválida ignorada: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer personas: " + e.getMessage());
        }
        return lista;
    }

    // CASOS
    public void guardarCasos(List<Caso> casos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_CASOS))) {
            for (Caso c : casos) {
                writer.write(c.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar casos: " + e.getMessage());
        }
    }

    public List<Caso> cargarCasos() {
        List<Caso> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_CASOS);
        if (!archivo.exists())
            return lista;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_CASOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                try {
                    lista.add(Caso.fromCSV(linea));
                } catch (Exception e) {
                    System.out.println("Línea inválida ignorada: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer casos: " + e.getMessage());
        }
        return lista;
    }
}