package prototipogym.controller.procesos;
import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import prototipogym.controller.ClienteController;

import prototipogym.model.Cobro;
import prototipogym.model.Cliente;
import prototipogym.model.EncabezadoCuota;
import prototipogym.model.DetalleCuota;
import prototipogym.util.mantenimientos.FileManager;
import prototipogym.view.procesos.Cobros;


public class CobroController {
    private static final String ENCABEZADO_FILE = "data/encabezado_cuota.txt";
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
                if (datos.length == 6 && datos[2].equals(idCliente) && datos[5].equals("false")) {
                    Date fecha = formatoFecha.parse(datos[1]);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fecha);
                    if ((cal.get(Calendar.MONTH) + 1 == Integer.parseInt(mes)) &&
                            (cal.get(Calendar.YEAR) == Integer.parseInt(año))) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    

    public static boolean actualizarCuota(String idCuota) {
        try {
            // Verificar estado de la cuota
            EncabezadoCuota encabezado = obtenerEncabezadoCuota(idCuota);
            if (encabezado == null || encabezado.isStatus()) {
                JOptionPane.showMessageDialog(null, "Cuota no existe o ya fue procesada", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Obtener detalles de la cuota
            List<DetalleCuota> detalles = obtenerDetallesCuota(idCuota);
            double totalPagado = 0;
            boolean todosPagados = true;

            for (DetalleCuota detalle : detalles) {
                Cobro cobro = CobroController.obtenerCobro(detalle.getIdCobroCuota());
                if (cobro == null || !cobro.isStatus()) {
                    todosPagados = false;
                } else {
                    totalPagado += cobro.getValorCobro();
                }
            }

            if (todosPagados) {
                // Actualizar estado de la cuota
                actualizarEstadoEncabezado(idCuota, true);
                // Actualizar balance del cliente
                Cliente cliente = ClienteController.obtenerCliente(encabezado.getIdCliente());
                ClienteController.actualizarBalanceCliente(cliente.getIdCliente(), cliente.getBalance() - totalPagado);
                JOptionPane.showMessageDialog(null, "Cuota actualizada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No todos los cobros están pagados", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
            public static boolean actualizarEstadoCobro(int idCobro, boolean estado) {
            // Implementación para actualizar el estado en cobros.txt
            try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
                 BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"))) {

                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] campos = linea.split(";");
                    if (campos[0].equals(String.valueOf(idCobro))) {
                        campos[5] = String.valueOf(estado); // Campo "status"
                        linea = String.join(";", campos);
                    }
                    bw.write(linea + "\n");
                }

                // Reemplazar archivo original
                new File(FILE_PATH).delete();
                new File("temp.txt").renameTo(new File(FILE_PATH));
                return true;

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    
    public static void actualizarEstadoEncabezado(String idCuota, boolean estado) {
        try (BufferedReader br = new BufferedReader(new FileReader(ENCABEZADO_FILE));
             BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos[0].equals(idCuota)) {
                    campos[3] = String.valueOf(estado); // Asumiendo que el campo 3 es el estado
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

    public static EncabezadoCuota obtenerEncabezadoCuota(String idCuota) {
        try (Scanner scanner = new Scanner(new File(ENCABEZADO_FILE))) {
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

    public static Cobro obtenerCobro(int idCobro) {
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(";");
                if (datos[0].equals(String.valueOf(idCobro))) {
                    return new Cobro(
                        Integer.parseInt(datos[0]),
                        formatoFecha.parse(datos[1]),
                        Integer.parseInt(datos[2]),
                        Double.parseDouble(datos[3]),
                        datos[4],
                        Boolean.parseBoolean(datos[5]) // <--- Leer el estado desde el archivo
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<DetalleCuota> obtenerDetallesCuota(String idCuota) {
        List<DetalleCuota> detalles = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("data/detalle_cuota.txt"))) {
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
    public void ModificaDatos(String LineaAntigua, String nuevaLinea) {
        boolean encontrado = false;
        File fNuevo = new File("data/ClienArchivo.txt"); 
        File fAntiguo = new File("data/cobros.txt"); 

        try {
            if (fAntiguo.exists()) {
                BufferedWriter bw;
                try (BufferedReader br = new BufferedReader(new FileReader(fAntiguo))) {
                    bw = new BufferedWriter(new FileWriter(fNuevo));
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        System.out.println("Leyendo linea: " + linea);
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
