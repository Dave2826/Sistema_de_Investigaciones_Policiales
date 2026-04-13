package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import exceptions.CSVInvalidoException;
import models.Caso;
import models.Entrevista;
import models.Evidencia;
import models.Persona;
import models.Reporte;

public class CasoRepository {

    private static final String ARCHIVO_PERSONAS = "personas.csv";
    private static final String ARCHIVO_CASOS = "casos.csv";
    private static final String ARCHIVO_EVIDENCIAS = "evidencias.csv";
    private static final String ARCHIVO_ENTREVISTAS = "entrevistas.csv";
    private static final String ARCHIVO_REPORTES = "reportes.csv";

    // ===================== PERSONAS =====================

    public void guardarPersonas(String idCaso, List<Persona> personas) {
        List<String> lineas = cargarTodasLasLineas(ARCHIVO_PERSONAS);
        lineas.removeIf(l -> l.startsWith(idCaso + "|"));
        for (Persona p : personas) {
            lineas.add(idCaso + "|" + p.toCSV());
        }
        escribirLineas(ARCHIVO_PERSONAS, lineas);
    }

    public List<Persona> cargarPersonas(String idCaso) {
        List<Persona> lista = new ArrayList<>();
        List<String> lineas = cargarTodasLasLineas(ARCHIVO_PERSONAS);

        for (String linea : lineas) {
            if (linea.startsWith(idCaso + "|")) {
                String datosPersona = linea.substring(idCaso.length() + 1);
                try {
                    lista.add(Persona.fromCSV(datosPersona));
                } catch (CSVInvalidoException e) {
                    System.out.println("Línea inválida ignorada: " + linea);
                }
            }
        }
        return lista;
    }

    // ===================== CASOS =====================

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
                } catch (CSVInvalidoException e) {
                    System.out.println("Línea inválida ignorada: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer casos: " + e.getMessage());
        }

        return lista;
    }

    // ===================== EVIDENCIAS =====================

    public void guardarEvidencias(String idCaso, List<Evidencia> evidencias) {
        List<String> lineas = cargarTodasLasLineas(ARCHIVO_EVIDENCIAS);
        lineas.removeIf(l -> l.startsWith(idCaso + "|"));

        for (Evidencia e : evidencias) {
            lineas.add(idCaso + "|" + e.toCSV());
        }

        escribirLineas(ARCHIVO_EVIDENCIAS, lineas);
    }

    public List<Evidencia> cargarEvidencias(String idCaso) {
        List<Evidencia> lista = new ArrayList<>();
        List<String> lineas = cargarTodasLasLineas(ARCHIVO_EVIDENCIAS);

        for (String linea : lineas) {
            if (linea.startsWith(idCaso + "|")) {
                String datosEvidencia = linea.substring(idCaso.length() + 1);
                try {
                    lista.add(Evidencia.fromCSV(datosEvidencia));
                } catch (CSVInvalidoException e) {
                    System.out.println("Evidencia inválida ignorada: " + linea);
                }
            }
        }

        return lista;
    }

    // ===================== ENTREVISTAS =====================

    public void guardarEntrevistas(String idCaso, List<Entrevista> entrevistas) {
        List<String> lineas = cargarTodasLasLineas(ARCHIVO_ENTREVISTAS);
        lineas.removeIf(l -> l.startsWith(idCaso + "|"));

        // Se usa "|" como separador externo para evitar conflicto con ";" usado en CSV
        // interno
        for (Entrevista ent : entrevistas) {
            lineas.add(idCaso + "|" + ent.toCSV());
        }

        escribirLineas(ARCHIVO_ENTREVISTAS, lineas);
    }

    public List<String[]> cargarEntrevistasCrudas(String idCaso) {
        List<String[]> lista = new ArrayList<>();
        List<String> lineas = cargarTodasLasLineas(ARCHIVO_ENTREVISTAS);

        for (String linea : lineas) {
            if (linea.startsWith(idCaso + "|")) {
                String datosEntrevista = linea.substring(idCaso.length() + 1);
                String[] partes = datosEntrevista.split(";", -1);

                // Desescapar cada valor en la capa de persistencia
                for (int i = 0; i < partes.length; i++) {
                    partes[i] = desescaparCSV(partes[i]);
                }

                lista.add(partes);
            }
        }

        return lista;
    }

    // ===================== REPORTES =====================

    public void guardarReportes(String idCaso, List<Reporte> reportes) {
        List<String> lineas = cargarTodasLasLineas(ARCHIVO_REPORTES);
        lineas.removeIf(l -> l.startsWith(idCaso + "|"));

        for (Reporte r : reportes) {
            lineas.add(idCaso + "|" + r.toCSV());
        }

        escribirLineas(ARCHIVO_REPORTES, lineas);
    }

    public List<Reporte> cargarReportes(String idCaso) {
        List<Reporte> lista = new ArrayList<>();
        List<String> lineas = cargarTodasLasLineas(ARCHIVO_REPORTES);

        for (String linea : lineas) {
            if (linea.startsWith(idCaso + "|")) {
                String datosReporte = linea.substring(idCaso.length() + 1);
                try {
                    lista.add(Reporte.fromCSV(datosReporte));
                } catch (CSVInvalidoException e) {
                    System.out.println("Reporte inválido ignorado: " + linea);
                }
            }
        }

        return lista;
    }

    // ===================== UTILIDADES =====================

    private List<String> cargarTodasLasLineas(String archivo) {
        List<String> lineas = new ArrayList<>();
        File f = new File(archivo);

        if (!f.exists())
            return lineas;

        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.isBlank())
                    lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer " + archivo + ": " + e.getMessage());
        }

        return lineas;
    }

    private void escribirLineas(String archivo, List<String> lineas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir " + archivo + ": " + e.getMessage());
        }
    }

    private static String desescaparCSV(String valor) {
        if (valor == null)
            return "";
        String v = valor.trim();
        if (v.startsWith("\"") && v.endsWith("\""))
            v = v.substring(1, v.length() - 1);
        return v.replace("\"\"", "\"");
    }
}
