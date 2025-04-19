package prototipogym.controller.movimientos;

import prototipogym.model.EncabezadoCuota;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EncabezadoCuotaController {
    private static final String ARCHIVO = "encabezado_cuota.txt";
    private static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy");

    public static boolean guardarEncabezado(EncabezadoCuota encabezado) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            String linea = encabezado.getIdCuota() + ";" +
                    FORMATO_FECHA.format(encabezado.getFechaCuota()) + ";" +
                    encabezado.getIdCliente() + ";" +
                    encabezado.getValorTotal() + ";" +
                    encabezado.isStatus();
            writer.write(linea);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<EncabezadoCuota> cargarEncabezados() {
        ArrayList<EncabezadoCuota> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                String idCuota = datos[0];
                Date fecha = FORMATO_FECHA.parse(datos[1]);
                String idCliente = datos[2];
                double valor = Double.parseDouble(datos[3]);
                boolean status = Boolean.parseBoolean(datos[4]);

                EncabezadoCuota e = new EncabezadoCuota(idCuota, fecha, idCliente, valor, status);
                lista.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}