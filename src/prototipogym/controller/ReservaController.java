package prototipogym.controller;

import prototipogym.model.Reserva;
import java.io.*;
import javax.swing.JOptionPane;

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
    
    public static boolean existeReserva(String id) {
    File archivo = new File(ARCHIVO);
    if (!archivo.exists()) return false;

    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.startsWith(id + ";")) {
                return true;
            }
        }
    } catch (IOException e) {
        System.err.println("Error al verificar existencia de reserva: " + e.getMessage());
    }
    return false;
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
    
    public void ModificaDatos(String LineaAntigua, String nuevaLinea) {
        boolean encontrado = false;
        File fNuevo = new File("data/resrArchivo.txt"); 
        File fAntiguo = new File("data/reservas.txt"); 

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

                // Si se encontró la línea, reemplazamos el archivo original
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
