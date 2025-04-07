package prototipogym.controller;
import prototipogym.model.EstadoReserva;
import java.io.*;

public class EstadoReservaController {
    public static boolean guardarEstado(EstadoReserva estado) {
        try {
            if (existeEstado(estado.getId())) return false;

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/estado_reservas.txt", true))) {
                bw.write(estado.toString());
                bw.newLine();
                return true;
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
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
