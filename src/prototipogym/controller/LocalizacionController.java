package prototipogym.controller;

import prototipogym.model.Localizacion;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LocalizacionController {
    public static boolean guardarLocalizacion(Localizacion loc) throws IOException {
        File archivo = new File("data/localizaciones.txt");
        boolean existe = existeLocalizacion(loc.getId());

        if (!existe) {
            // Guardar nuevo registro
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
                bw.write(loc.getId() + ";" + loc.getTipo());
                bw.newLine();
            }
        } else {
            // Actualizar registro existente
            actualizarLocalizacionEnArchivo(loc);
        }
        return true;
    }

    // Método para actualizar sin archivo temporal
    private static void actualizarLocalizacionEnArchivo(Localizacion loc) throws IOException {
        File archivo = new File("data/localizaciones.txt");
        List<String> lineas = new ArrayList<>();

        // Leer todas las líneas
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(loc.getId() + ";")) {
                    linea = loc.getId() + ";" + loc.getTipo(); // Actualizar línea
                }
                lineas.add(linea);
            }
        }

        // Sobrescribir el archivo original
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        }
    }

    public static boolean existeLocalizacion(int id) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data/localizaciones.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(id + ";")) return true;
            }
        }
        return false;
    }
}
