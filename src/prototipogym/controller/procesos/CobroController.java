package prototipogym.controller.procesos;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import prototipogym.model.Cobro;

public class CobroController {
    private static final String FILE_PATH = "data/cobros.txt";
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    public static boolean guardarCobro(String id, String fecha, String idCliente, String valor, String concepto, String status) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // Formato: ID;Fecha;ID_Cliente;Valor;Concepto;Status
            String linea = String.join(";",
                    id,
                    fecha,
                    idCliente,
                    valor,
                    concepto,
                    status // Añadir el estado
            );
            bw.write(linea);
            bw.newLine();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static List<Cobro> obtenerCobrosPendientes(String idCliente) {
        List<Cobro> cobros = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(";");
                if (datos.length == 6 &&
                        datos[2].equals(idCliente) &&
                        datos[5].equalsIgnoreCase("false")) {

                    Cobro cobro = new Cobro(
                            Integer.parseInt(datos[0]),
                            formatoFecha.parse(datos[1]),
                            Integer.parseInt(datos[2]),
                            Double.parseDouble(datos[3]),
                            datos[4]
                    );
                    cobros.add(cobro);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al leer cobros: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return cobros;
    }

    public static boolean actualizarEstadoCobro(int idCobro, boolean estado) {
        boolean success = false;
        File tempFile = new File("data/cobros_temp.txt");
        File originalFile = new File(FILE_PATH);

        try (BufferedReader br = new BufferedReader(new FileReader(originalFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length == 6 && Integer.parseInt(campos[0]) == idCobro) {
                    campos[5] = String.valueOf(estado);
                    linea = String.join(";", campos);
                    success = true;
                }
                bw.write(linea + System.lineSeparator());
            }

            if (success) {
                originalFile.delete();
                tempFile.renameTo(originalFile);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al actualizar estado: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return success;
    }
}
