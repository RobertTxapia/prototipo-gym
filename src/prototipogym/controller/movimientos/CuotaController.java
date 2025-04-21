package prototipogym.controller.movimientos;

import prototipogym.controller.procesos.CobroController;
import prototipogym.model.Cliente;
import prototipogym.model.Cobro;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;
import prototipogym.controller.ClienteController;
import prototipogym.model.EncabezadoCuota;
import prototipogym.model.DetalleCuota;

public class CuotaController {
    private static final String ENCABEZADO_FILE = "data/encabezado_cuota.txt";
    private static final String DETALLE_FILE = "data/detalle_cuota.txt";

    public static Cliente obtenerDatosCliente(String idCliente) {
        try {
            return ClienteController.obtenerCliente(idCliente);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al leer datos del cliente", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static List<Cobro> obtenerCobrosPendientes(String idCliente) {
        List<Cobro> cobrosPendientes = new ArrayList<>();
        // Añadir formato de fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 

        try (Scanner scanner = new Scanner(new File("data/cobros.txt"))) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(";"); // <--- Usar comillas dobles

                if (datos.length >= 6 
                    && datos[2].equals(idCliente) 
                    && datos[5].equalsIgnoreCase("false")) {

                    // Crear objeto Cobro con todos los parámetros
                    Cobro cobro = new Cobro(
                        Integer.parseInt(datos[0]),       // id
                        formatoFecha.parse(datos[1]),     // fecha
                        Integer.parseInt(datos[2]),       // idCliente
                        Double.parseDouble(datos[3]),     // valor
                        datos[4],                         // concepto
                        Boolean.parseBoolean(datos[5])    // status
                    );
                    cobrosPendientes.add(cobro);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return cobrosPendientes;
}

    public static boolean guardarCuota(String idCuota, String idCliente, List<Cobro> cobros) {
        try (BufferedWriter encabezadoWriter = new BufferedWriter(new FileWriter(ENCABEZADO_FILE, true));
             BufferedWriter detalleWriter = new BufferedWriter(new FileWriter(DETALLE_FILE, true))) {

            // Guardar encabezado
            String encabezado = String.join(";",
                    idCuota,
                    new Date().toString(),
                    idCliente,
                    "false"
            );
            encabezadoWriter.write(encabezado);
            encabezadoWriter.newLine();

            // Guardar detalles
            int secuencia = 1;
            for (Cobro cobro : cobros) {
                String detalle = String.join(";",
                        idCuota,
                        String.valueOf(secuencia++),
                        cobro.getConcepto(),
                        String.valueOf(cobro.getValorCobro()),
                        String.valueOf(cobro.getId())
                );
                detalleWriter.write(detalle);
                detalleWriter.newLine();
            }

            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar cuota", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public static boolean actualizarCuota(String idCuota) {
        try {
            EncabezadoCuota encabezado = obtenerEncabezadoCuota(idCuota);
            if (encabezado == null || encabezado.isStatus()) {
                JOptionPane.showMessageDialog(null, "Cuota ya procesada", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            List<DetalleCuota> detalles = obtenerDetallesCuota(idCuota);
            double totalPagado = 0;
            for (DetalleCuota detalle : detalles) {
                Cobro cobro = CobroController.obtenerCobro(detalle.getIdCobroCuota());
                if (cobro != null && cobro.isStatus()) { 
                    totalPagado += cobro.getValorCobro();
                }
            }

            actualizarEstadoEncabezado(idCuota, true); 
            ClienteController.actualizarBalanceCliente(
                encabezado.getIdCliente(), 
                encabezado.getValorTotal() - totalPagado 
            );

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

     private static void actualizarEstadoEncabezado(String idCuota, boolean estado) {
        // Implementación para actualizar el estado en encabezado_cuota.txt
        try (BufferedReader br = new BufferedReader(new FileReader(ENCABEZADO_FILE));
             BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos[0].equals(idCuota)) {
                    campos[3] = String.valueOf(estado);
                    linea = String.join(";", campos);
                }
                bw.write(linea + "\n");
            }
            // Reemplazar archivo
            new File(ENCABEZADO_FILE).delete();
            new File("temp.txt").renameTo(new File(ENCABEZADO_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
       private static EncabezadoCuota obtenerEncabezadoCuota(String idCuota) {
           try (Scanner scanner = new Scanner(new File("data/encabezado_cuota.txt"))) {
               while (scanner.hasNextLine()) {
                   String[] campos = scanner.nextLine().split(";");
                   if (campos[0].equals(idCuota)) {
                       return new EncabezadoCuota(
                           campos[0],
                           new SimpleDateFormat("dd/MM/yyyy").parse(campos[1]),
                           campos[2],
                           Double.parseDouble(campos[3]),
                           Boolean.parseBoolean(campos[4])
                       );
                   }
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
           return null;
       }

    private static List<DetalleCuota> obtenerDetallesCuota(String idCuota) {
        List<DetalleCuota> detalles = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(DETALLE_FILE))) {
            while (scanner.hasNextLine()) {
                String[] campos = scanner.nextLine().split(";");
                if (campos[0].equals(idCuota)) {
                    detalles.add(new DetalleCuota(
                        campos[0],
                        Integer.parseInt(campos[1]),
                        campos[2],
                        Double.parseDouble(campos[3]),
                        Integer.parseInt(campos[4])
                    ));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return detalles;
    }
}
