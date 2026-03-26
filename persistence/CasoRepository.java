package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import models.Persona;
import models.Detective;
import models.Testigo;
import models.Victima;
import models.Sospechoso;

public class CasoRepository {

    private static final String ARCHIVO = "personas.csv";

    public void guardarPersonas(List<Persona> personas) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {

            for (Persona p : personas) {
                writer.write(p.toCSV());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public List<Persona> cargarPersonas() {

        List<Persona> lista = new ArrayList<>();

        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            return lista;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {

            String linea;

            while ((linea = reader.readLine()) != null) {

                try {
                    if (linea.startsWith("DETECTIVE")) {
                        lista.add(Detective.fromCSV(linea));
                    } else if (linea.startsWith("TESTIGO")) {
                        lista.add(Testigo.fromCSV(linea));
                    } else if (linea.startsWith("VICTIMA")) {
                        lista.add(Victima.fromCSV(linea));
                    } else if (linea.startsWith("SOSPECHOSO")) {
                        lista.add(Sospechoso.fromCSV(linea));
                    } else {
                        System.out.println("Tipo desconocido en línea: " + linea);
                    }
                } catch (Exception e) {
                    System.out.println("Línea inválida ignorada: " + linea);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }

        return lista;
    }
}