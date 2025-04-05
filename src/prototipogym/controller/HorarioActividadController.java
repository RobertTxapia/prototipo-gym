package prototipogym.controller;
import prototipogym.model.HorarioActividad;
import java.io.*;

public class HorarioActividadController {

    // Guardar o actualizar horario
    public static void guardarHorario(HorarioActividad horario) throws IOException {
        File archivo = new File("data/horarios_actividades.txt");
        StringBuilder contenido = new StringBuilder();
        boolean existe = false;

        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith(horario.getId() + ";")) {
                        // Actualizar línea existente
                        contenido.append(horario.getId()).append(";")
                                .append(horario.getDia()).append(";")
                                .append(horario.getHora()).append(";")
                                .append(horario.getIdActividad()).append(";")
                                .append(horario.getIdSala()).append("\n");
                        existe = true;
                    } else {
                        contenido.append(linea).append("\n");
                    }
                }
            }
        }

        if (!existe) {
            contenido.append(horario.getId()).append(";")
                    .append(horario.getDia()).append(";")
                    .append(horario.getHora()).append(";")
                    .append(horario.getIdActividad()).append(";")
                    .append(horario.getIdSala()).append("\n");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(contenido.toString());
        }
    }

    // Validar referencias
    public static boolean validarRelaciones(String idActividad, String idSala) throws IOException {
        return existeEnArchivo("data/actividades.txt", idActividad)
                && existeEnArchivo("data/salas.txt", idSala);
    }

    private static boolean existeEnArchivo(String ruta, String id) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(id + ";")) return true;
            }
        }
        return false;
    }
}
