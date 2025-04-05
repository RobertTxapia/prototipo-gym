package prototipogym.controller;
import prototipogym.model.Actividad;
import java.io.*;

public class ActividadController {

    // Guardar o actualizar actividad
    public static void guardarActividad(Actividad actividad) throws IOException {
        File archivo = new File("data/actividades.txt");
        StringBuilder contenido = new StringBuilder();

        // Leer archivo existente
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                boolean existe = false;
                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith(actividad.getId() + ";")) {
                        linea = actividad.getId() + ";"
                                + actividad.getNombre() + ";"
                                + actividad.getDescripcion() + ";"
                                + actividad.getIdLocalizacion() + ";"
                                + actividad.getIdEntrenador();
                        existe = true;
                    }
                    contenido.append(linea).append("\n");
                }
                if (!existe) {
                    contenido.append(actividad.getId()).append(";")
                            .append(actividad.getNombre()).append(";")
                            .append(actividad.getDescripcion()).append(";")
                            .append(actividad.getIdLocalizacion()).append(";")
                            .append(actividad.getIdEntrenador()).append("\n");
                }
            }
        } else {
            contenido.append(actividad.getId()).append(";")
                    .append(actividad.getNombre()).append(";")
                    .append(actividad.getDescripcion()).append(";")
                    .append(actividad.getIdLocalizacion()).append(";")
                    .append(actividad.getIdEntrenador()).append("\n");
        }

        // Escribir todo el contenido
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(contenido.toString());
        }
    }

    // Validar existencia de IDs relacionados
    public static boolean validarRelaciones(int idLocalizacion, int idEntrenador) throws IOException {
        return existeEnArchivo("data/localizaciones.txt", idLocalizacion)
                && existeEnArchivo("data/entrenadores.txt", idEntrenador);
    }

    private static boolean existeEnArchivo(String ruta, int id) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(id + ";")) return true;
            }
        }
        return false;
    }
}
