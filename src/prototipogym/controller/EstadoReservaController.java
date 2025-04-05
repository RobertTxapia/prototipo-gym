package prototipogym.model;
import prototipogym.model.EstadoReserva;
import java.io.*;

public class EstadoReservaController {

    // Guardar o actualizar estado
    public static void guardarEstado(EstadoReserva estado) throws IOException {
        File archivo = new File("data/estado_reservas.txt");
        StringBuilder contenido = new StringBuilder();
        boolean encontrado = false;

        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith(estado.getId() + ";")) {
                        contenido.append(estado.getId()).append(";").append(estado.getEstado()).append("\n");
                        encontrado = true;
                    } else {
                        contenido.append(linea).append("\n");
                    }
                }
            }
        }

        if (!encontrado) {
            contenido.append(estado.getId()).append(";").append(estado.getEstado()).append("\n");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(contenido.toString());
        }
    }

    // Validar ID único
    public static boolean existeEstado(String id) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data/estado_reservas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(id + ";")) return true;
            }
        }
        return false;
    }
}
