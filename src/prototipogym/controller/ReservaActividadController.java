package prototipogym.controller;

import prototipogym.model.ReservaActividad;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ReservaActividadController {
    private static final String ARCHIVO = "data/reservaActividades.txt";

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

    public static boolean existeReserva(String idReserva) throws IOException {
    File file = new File(ARCHIVO);
    if (!file.exists()) return false;

    try (Scanner scanner = new Scanner(file)) {
        while (scanner.hasNextLine()) {
            String[] datos = scanner.nextLine().split(";");
            if (datos.length > 0 && datos[0].equals(idReserva)) {
                return true;
            }
        }
    }
    return false;
}


    public static boolean validarRelaciones(String idCliente, String idActividad,
                                            String idHorario, String idEstado) throws IOException {
        return ClienteController.existeCliente(idCliente) &&
                ActividadController.existeActividad(idActividad) &&
                HorarioActividadController.existeHorario(idHorario) &&
                EstadoReservaController.existeEstado(idEstado);
    }

    private static String reservaToCSV(ReservaActividad r) {
        return String.join(";",
                String.valueOf(r.getId()),
                r.getFechaReserva(),
                r.getFechaBaja(),
                r.getIdEstadoReserva(),
                r.getIdCliente(),
                r.getIdActividad(),
                r.getIdHorarioActividad()
        );
    }

    
    
    public void ModificaDatos(String LineaAntigua, String nuevaLinea) {
        boolean encontrado = false;
        File fNuevo = new File("data/aaaArchivo.txt"); 
        File fAntiguo = new File("data/reservaActividades.txt"); 

        try {
            if (fAntiguo.exists()) {
                BufferedWriter bw;
                try (BufferedReader br = new BufferedReader(new FileReader(fAntiguo))) {
                    bw = new BufferedWriter(new FileWriter(fNuevo));
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        System.out.println("Leyendo linea: " + linea);
                        // Si encontramos la linea antigua, escribimos la nueva
                        if (linea.trim().equals(LineaAntigua.trim())) {
                            encontrado = true;
                            bw.write(nuevaLinea);
                        } else {
                            bw.write(linea); 
                        }
                        bw.newLine();
                    }
                }
                bw.close();

                if (encontrado) {
                    fAntiguo.delete(); 
                    fNuevo.renameTo(fAntiguo);
                } else {
                    fNuevo.delete(); 
                    
                }
            } 
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
}