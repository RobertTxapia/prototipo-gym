package prototipogym.controller;

import prototipogym.model.Reserva;
import java.io.*;

public class ReservaController {
    private static final String ARCHIVO = "data/reservas.txt";
    public static boolean guardarReserva(Reserva reserva) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(reservaToCSV(reserva));
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
            return false;
        }
    }

    private static String reservaToCSV(Reserva r) {
        return String.join(";",
                r.getIdReserva(),
                r.getIdSala(), // Campo añadido
                r.getIdCliente(),
                r.getFechaReserva(),
                r.getIdHorario(),
                r.getIdEstado()
        );
    }

    public static boolean validarRelaciones(int idCliente, int idSala, int idHorario, int idEstado) throws IOException {
        return existeEnArchivo("data/clientes.txt", idCliente)
                && existeEnArchivo("data/salas.txt", idSala)
                && existeEnArchivo("data/horarios_actividades.txt", idHorario)
                && existeEnArchivo("data/estado_reservas.txt", idEstado);
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
