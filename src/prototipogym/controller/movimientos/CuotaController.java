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

        /*public static List<Cobro> obtenerCobrosPendientes(String idCliente) {
         List<Cobro> cobrosPendientes = new ArrayList<>();
         SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
         try (Scanner scanner = new Scanner(new File("data/cobros.txt"))) {
             while (scanner.hasNextLine()) {
                 String[] datos = scanner.nextLine().split(";");
                 // Filtrar por ID de cliente y status "false"
                 if (datos.length >= 6 
                     && datos[2].equals(idCliente) 
                     && datos[5].equalsIgnoreCase("false")) { 
                     Cobro cobro = new Cobro(
                         Integer.parseInt(datos[0]),
                         formatoFecha.parse(datos[1]),
                         Integer.parseInt(datos[2]),
                         Double.parseDouble(datos[3]),
                         datos[4],
                         Boolean.parseBoolean(datos[5])
                     );
                     cobrosPendientes.add(cobro);
                 }
             }
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
         return cobrosPendientes;
     }*/
    
   public static List<Cobro> obtenerCobrosPendientes(String idCliente) {
    List<Cobro> cobrosPendientes = new ArrayList<>();
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    
    // Obtener cobros ya incluidos en cuotas
    Set<Integer> cobrosEnCuotas = new HashSet<>();
    try (Scanner scanner = new Scanner(new File("data/detalle_cuota.txt"))) {
        while (scanner.hasNextLine()) {
            String[] campos = scanner.nextLine().split(";");
            if (campos.length >= 5) {
                cobrosEnCuotas.add(Integer.parseInt(campos[4])); // ID Cobro
            }
        }
    } catch (FileNotFoundException e) {
        // Si el archivo no existe, no hay cobros registrados
    }
    
    // Obtener cobros pendientes del cliente
    try (Scanner scanner = new Scanner(new File("data/cobros.txt"))) {
        while (scanner.hasNextLine()) {
            String[] datos = scanner.nextLine().split(";");
            if (datos.length >= 6 
                && datos[2].equals(idCliente) 
                && datos[5].equalsIgnoreCase("false")
                && !cobrosEnCuotas.contains(Integer.parseInt(datos[0]))) {
                
                Cobro cobro = new Cobro(
                    Integer.parseInt(datos[0]),
                    formatoFecha.parse(datos[1]),
                    Integer.parseInt(datos[2]),
                    Double.parseDouble(datos[3]),
                    datos[4],
                    Boolean.parseBoolean(datos[5])
                );
                cobrosPendientes.add(cobro);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, 
            "Error al leer cobros pendientes: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
    return cobrosPendientes;
}

    public static boolean guardarCuota(String idCuota, String idCliente, List<Cobro> cobros) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedWriter encabezadoWriter = new BufferedWriter(new FileWriter(ENCABEZADO_FILE, true));
             BufferedWriter detalleWriter = new BufferedWriter(new FileWriter(DETALLE_FILE, true))) {

            double valorTotal = cobros.stream().mapToDouble(Cobro::getValorCobro).sum();
            String encabezado = String.join(";",
                           idCuota,
                           sdf.format(new Date()),
                           idCliente,
                           String.valueOf(valorTotal),
                           "false"                  
                   );
            encabezadoWriter.write(encabezado);
            encabezadoWriter.newLine();
     
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar cuota", "Error", JOptionPane.ERROR_MESSAGE);
            
        }
        return false;
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
        try (BufferedReader br = new BufferedReader(new FileReader(ENCABEZADO_FILE));
             BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos[0].equals(idCuota) && campos.length >= 5) {
                    campos[4] = String.valueOf(estado); 
                    linea = String.join(";", campos);
                }
                bw.write(linea + "\n");
            }
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
                if (campos[0].equals(idCuota) && campos.length >= 5) { 
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
