package prototipogym.controller;

import prototipogym.model.ActividadReserva;
import java.io.*;

public class ActividadReservaController {

    // Guardar o actualizar reserva de actividad
    public static void guardarActividadReserva(ActividadReserva reserva) throws IOException {
        File archivo = new File("data/actividad_reservas.txt");
        StringBuilder contenido = new StringBuilder();
        boolean existe = false;

        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith(reserva.getIdReservaActividad() + ";")) {
                        contenido.append(reserva.toString()).append("\n");
                        existe = true;
                    } else {
                        contenido.append(linea).append("\n");
                    }
                }
            }
        }

        if (!existe) {
            contenido.append(reserva.toString()).append("\n");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(contenido.toString());
        }
    }

    // Validar cupos disponibles en la actividad
    public static boolean validarCuposDisponibles(String idActividad) throws IOException {
        int cuposMaximos = 0;
        int reservasActivas = 0;

        // Obtener cupos máximos de la actividad
        try (BufferedReader br = new BufferedReader(new FileReader("data/actividades.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(idActividad + ";")) {
                    String[] datos = linea.split(";");
                    cuposMaximos = Integer.parseInt(datos[5]); // Suponiendo que posición 5 es cupos
                    break;
                }
            }
        }

        // Contar reservas activas
        try (BufferedReader br = new BufferedReader(new FileReader("data/actividad_reservas.txt"))) {
            reservasActivas = (int) br.lines()
                    .filter(linea -> linea.contains(";" + idActividad + ";") && !linea.contains("CANCELADA"))
                    .count();
        }

        return reservasActivas < cuposMaximos;
    }

    // Validar cliente activo y sin mora
    public static boolean validarCliente(String idCliente) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data/clientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(idCliente + ";")) {
                    String[] datos = linea.split(";");
                    boolean activo = Boolean.parseBoolean(datos[9]); // Campo Status_cliente
                    double balance = Double.parseDouble(datos[12]); // Campo Balance_Cliente
                    return activo && balance <= 0;
                }
            }
        }
        return false;
    }
}
