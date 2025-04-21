package prototipogym.controller.procesos;

import prototipogym.model.Cliente;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import prototipogym.controller.ClienteController;

public class ActualizarCuotaController {
    private static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy");
    
    public boolean procesarActualizacion(String fechaInicioStr, String fechaFinalStr) throws Exception {
        Date fechaInicio = FORMATO_FECHA.parse(fechaInicioStr);
        Date fechaFinal = FORMATO_FECHA.parse(fechaFinalStr);
        
        List<String> cuotasProcesadas = new ArrayList<>();
        
        try (Scanner scanner = new Scanner(new File("data/encabezado_cuota.txt"))) {
            while (scanner.hasNextLine()) {
                String[] campos = scanner.nextLine().split(";");
                if (campos.length >= 4 && campos[3].equalsIgnoreCase("false")) {
                    Date fechaCuota = FORMATO_FECHA.parse(campos[1]);
                    if (!fechaCuota.before(fechaInicio) && !fechaCuota.after(fechaFinal)) {
                        actualizarCobrosCuota(campos[0], campos[2]);
                        cuotasProcesadas.add(campos[0]);
                    }
                }
            }
        }
        
        // Actualizar status de cuotas
        if (!cuotasProcesadas.isEmpty()) {
            actualizarArchivoEncabezado(cuotasProcesadas);
            return true;
        }
        return false;
    }

    private void actualizarCobrosCuota(String idCuota, String idCliente) throws Exception {
        File archivoCobros = new File("data/cobros.txt");
        List<String> nuevasLineas = new ArrayList<>();
        double totalPagado = 0;

        try (Scanner scanner = new Scanner(archivoCobros)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] campos = linea.split(";");
                if (campos.length >= 5 && campos[4].equals(idCuota) && campos[3].equalsIgnoreCase("false")) {
                    double valor = Double.parseDouble(campos[2]);
                    totalPagado += valor;
                    campos[2] = "0.00"; 
                    campos[3] = "true";
                    linea = String.join(";", campos);
                }
                nuevasLineas.add(linea);
            }
        }

        // Escribir cambios
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCobros))) {
            for (String linea : nuevasLineas) {
                writer.write(linea);
                writer.newLine();
            }
        }

        // Actualizar balance del cliente
        Cliente cliente = ClienteController.obtenerCliente(idCliente);
        if (cliente != null) {
            cliente.setBalance(cliente.getBalance() - totalPagado);
            ClienteController.actualizarCliente(cliente);
        }
    }

    private void actualizarArchivoEncabezado(List<String> cuotasProcesadas) throws IOException {
        List<String> lineas = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("data/encabezado_cuota.txt"))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] campos = linea.split(";");
                if (cuotasProcesadas.contains(campos[0])) {
                    campos[3] = "true";
                    linea = String.join(";", campos);
                }
                lineas.add(linea);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/encabezado_cuota.txt"))) {
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
        }
    }
}