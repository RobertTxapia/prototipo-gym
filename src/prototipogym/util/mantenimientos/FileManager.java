package prototipogym.util.mantenimientos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<String> leerArchivo(File archivo) {
        List<String> lineas = new ArrayList<>();
        if (!archivo.exists()) {
            System.err.println("El archivo no existe: " + archivo.getPath());
            return lineas;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return lineas;
    }

    public static void escribirLinea(File archivo, String linea) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void actualizarLinea(File archivo, String idBuscar, String nuevaLinea) {
        List<String> lineas = leerArchivo(archivo);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineas) {
                String[] datos = linea.split(";");
                if (datos[0].equals(idBuscar)) {
                    bw.write(nuevaLinea);
                } else {
                    bw.write(linea);
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al actualizar el archivo: " + e.getMessage());
        }
    }

    public static void eliminarLinea(File archivo, String idBuscar) {
        List<String> lineas = leerArchivo(archivo);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineas) {
                String[] datos = linea.split(";");
                if (!datos[0].equals(idBuscar)) {
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar del archivo: " + e.getMessage());
        }
    }

    public static boolean existeId(File archivo, String id) {
        List<String> lineas = leerArchivo(archivo);
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos[0].equals(id)) {
                return true;
            }
        }
        return false;
    }
}
