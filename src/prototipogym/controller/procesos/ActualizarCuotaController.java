package prototipogym.controller.procesos;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;
import prototipogym.controller.ClienteController;
import prototipogym.model.Cliente;
import prototipogym.model.Cobro;
import prototipogym.model.DetalleCuota;
import prototipogym.model.EncabezadoCuota;

public class ActualizarCuotaController {
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    // Método actualizado para recibir dos fechas
    public static boolean actualizarCuotas(Date fechaInicio, Date fechaFinal) {
        try {
            // Validar que las fechas no sean nulas
            if (fechaInicio == null || fechaFinal == null) {
                JOptionPane.showMessageDialog(null,
                        "Seleccione ambas fechas (inicio y fin)",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return false;
            }

            // Leer encabezados de cuotas no procesadas
            List<EncabezadoCuota> cuotas = new ArrayList<>();
            try (Scanner scanner = new Scanner(new File("data/encabezado_cuota.txt"))) {
                while (scanner.hasNextLine()) {
                    String[] datos = scanner.nextLine().split(";");
                    if (datos.length >= 5 && datos[4].equalsIgnoreCase("false")) { // Campo 4: Status_Cuota
                        Date fechaCuota = formatoFecha.parse(datos[1]); // Campo 1: Fecha

                        // Validar si la fecha está dentro del rango
                        if (!fechaCuota.before(fechaInicio) && !fechaCuota.after(fechaFinal)) {
                            cuotas.add(new EncabezadoCuota(
                                    datos[0], // ID_Cuota
                                    fechaCuota,
                                    datos[2], // ID_Cliente
                                    Double.parseDouble(datos[3]), // Valor_Total
                                    Boolean.parseBoolean(datos[4]) // Status_Cuota
                            ));
                        }
                    }
                }
            }

            if (cuotas.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "No hay cuotas pendientes en el rango de fechas seleccionado.",
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return false;
            }

            // Procesar cada cuota en el rango
            for (EncabezadoCuota cuota : cuotas) {
                List<DetalleCuota> detalles = CobroController.obtenerDetallesCuota(cuota.getIdCuota());
                double totalPagado = 0;
                boolean todosPagados = true;

                // Verificar cobros asociados
                for (DetalleCuota detalle : detalles) {
                    Cobro cobro = CobroController.obtenerCobro(detalle.getIdCobroCuota());
                    if (cobro == null || !cobro.isStatus()) {
                        todosPagados = false;
                        break;
                    }
                    totalPagado += cobro.getValorCobro();
                }

                // Si todos los cobros están pagados
                if (todosPagados) {
                    // 1. Actualizar estado de la cuota a true
                    CobroController.actualizarEstadoEncabezado(cuota.getIdCuota(), true);

                    // 2. Actualizar balance del cliente
                    Cliente cliente = ClienteController.obtenerCliente(cuota.getIdCliente());
                    if (cliente != null) {
                        double nuevoBalance = cliente.getBalance() - totalPagado;
                        ClienteController.actualizarBalanceCliente(cuota.getIdCliente(), nuevoBalance);
                    }

                    // 3. Si Valor_Cobro es 0, marcar como true (requisito X)
                    if (totalPagado == 0) {
                        CobroController.actualizarEstadoCobro(cuota.getIdCuota(), true);
                    }
                }
            }

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error crítico: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }
}