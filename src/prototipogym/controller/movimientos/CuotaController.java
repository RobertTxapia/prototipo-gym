package prototipogym.controller.movimientos;

import prototipogym.model.Cliente;
import prototipogym.model.Cobro;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import prototipogym.controller.ClienteController;

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
        try (Scanner scanner = new Scanner(new File("data/cobros.txt"))) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(";");
                if(datos[2].equals(idCliente) && datos[4].equals("false")) {
                    cobrosPendientes.add(new Cobro(
                            Integer.parseInt(datos[0]),
                            new Date(datos[1]),
                            Integer.parseInt(datos[2]),
                            Double.parseDouble(datos[3]),
                            datos[4]
                    ));
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Archivo de cobros no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
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
}
