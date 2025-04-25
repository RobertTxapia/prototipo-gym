package prototipogym.controller.procesos;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import prototipogym.controller.ClienteController;
import prototipogym.model.Cliente;

public class CobroController {
    private static final String FILE_PATH = "data/cobros.txt";
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    
     public static boolean guardarCobro(
        String id, 
        String fecha, 
        String idCliente, 
        String valor, 
        String concepto, 
        String status
    ) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String valorFormateado = String.format("%.2f", Double.parseDouble(valor)); 
            String linea = String.join(";", id, fecha, idCliente, valorFormateado, concepto, status);
            
            bw.write(linea);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar cobro: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static boolean existeCobroMensual(String idCliente, String mes, String año) {
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(";");
                if (datos.length >= 6 &&
                        datos[2].equals(idCliente) &&
                        datos[1].endsWith("/" + mes + "/" + año)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return false;
    }
    
    
    public static boolean generarCobrosMensuales(Date fechaDesde) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
            String mes = sdf.format(fechaDesde).split("/")[0];
            String año = sdf.format(fechaDesde).split("/")[1];

            List<Cliente> socios = ClienteController.obtenerSociosActivos();
            if (socios.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay socios activos", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            for (Cliente socio : socios) {
                String idCliente = socio.getIdCliente();

                // Validar cobro duplicado
                if (existeCobroMensual(idCliente, mes, año)) {
                    JOptionPane.showMessageDialog(null, "Cobro para " + idCliente + " en " + mes + "/" + año + " ya existe",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                // Generar nuevo cobro
                int nuevoId = getNextCobroId();
                String fechaCobro = "30/" + mes + "/" + año; // Fecha fija día 30

                guardarCobro(
                        String.valueOf(nuevoId),
                        fechaCobro,
                        idCliente,
                        String.valueOf(socio.getValorCuota()),
                        "Cuota mensual " + mes,
                        "false"
                );

                // Actualizar balance
                double nuevoBalance = socio.getBalance() + socio.getValorCuota();
                ClienteController.actualizarBalanceCliente(idCliente, nuevoBalance);
            }
            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private static int getNextCobroId() {
        int maxId = 0;
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(";");
                if (!datos[0].isEmpty()) {
                    int currentId = Integer.parseInt(datos[0]);
                    if (currentId > maxId) maxId = currentId;
                }
            }
        } catch (FileNotFoundException e) {
            return 1;
        }
        return maxId + 1;
    }

    public static boolean actualizarEstadoCobro(int idCobro, boolean estado) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
             BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length >= 6 && campos[0].equals(String.valueOf(idCobro))) {
                    campos[5] = String.valueOf(estado); 
                    linea = String.join(";", campos);
                }
                bw.write(linea + "\n");
            }
            new File(FILE_PATH).delete();
            new File("temp.txt").renameTo(new File(FILE_PATH));
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}