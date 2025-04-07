package prototipogym.controller;

import prototipogym.model.ReservaActividad;

import java.io.*;
import java.util.*;

public class ReservaActividadController {
    private static final String ARCHIVO = "data/reservaActividades.txt";

    // Guardar reserva (devuelve boolean)
    public static boolean guardarReserva(ReservaActividad reserva) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(reservaToCSV(reserva));
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
            return false;
        }
    }

    // Verificar si existe la reserva
    public static boolean existeReserva(int idReserva) throws IOException {
        File file = new File(ARCHIVO);
        if (!file.exists()) return false;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(";");
                if (Integer.parseInt(datos[0]) == idReserva) return true;
            }
        }
        return false;
    }

    // Validar relaciones con otras tablas
    public static boolean validarRelaciones(String idCliente, String idActividad,
                                            String idHorario, String idEstado) throws IOException {
        return ClienteController.existeCliente(idCliente) &&
                ActividadController.existeActividad(idActividad) &&
                HorarioActividadController.existeHorario(idHorario) &&
                EstadoReservaController.existeEstado(idEstado);
    }

    // Convertir objeto a CSV
    private static String reservaToCSV(ReservaActividad r) {
        return String.join(";",
                String.valueOf(r.getIdReservaActividad()),
                r.getFechaReserva(),
                r.getFechaBaja(),
                r.getIdEstadoReserva(),
                r.getIdCliente(),
                r.getIdActividad(),
                r.getIdHorarioActividad()
        );
    }
}