package prototipogym.controller;

import prototipogym.model.Sala;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SalaController {

    // Guardar o actualizar sala
    public static boolean guardarSala(Sala sala) throws IOException {
        List<String> lineas = leerTodasLasSalas();
        String nuevaLinea = sala.getId() + ";"
                + sala.getNombre() + ";"
                + sala.getDescripcion() + ";"
                + sala.getIdLocalizacion();

        // Buscar si existe el ID
        boolean existe = false;
        for (int i = 0; i < lineas.size(); i++) {
            String[] datos = lineas.get(i).split(";");
            if (datos[0].equals(String.valueOf(sala.getId()))) {
                lineas.set(i, nuevaLinea); // Actualizar
                existe = true;
                break;
            }
        }

        if (!existe) lineas.add(nuevaLinea); // Crear nuevo

        // Escribir todas las líneas
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/salas.txt"))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        }
        return true;
    }

    // Validar existencia de localización
    public static boolean existeLocalizacion(int idLocalizacion) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data/localizaciones.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(idLocalizacion + ";")) return true;
            }
        }
        return false;
    }

    public static List<String> leerTodasLasSalas() throws IOException {
        List<String> lineas = new ArrayList<>();
        File archivo = new File("data/salas.txt");
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) lineas.add(linea);
            }
        }
        return lineas;
    }
}
