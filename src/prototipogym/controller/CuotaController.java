package prototipogym.controller;

import prototipogym.model.DetalleCuota;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

    public class CuotaController {

    private static final String ARCHIVO = "data/detalle_cuota.txt";
    private static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy");

    public static boolean guardarDetalle(DetalleCuota detalle) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            String linea = detalle.getIdCuota() + "," +
                    FORMATO_FECHA.format(detalle.getFechaCuota()) + "," +
                    detalle.getIdCliente() + "," +
                    detalle.getValorTotal() + "," +
                    detalle.isStatus() + "," +
                    detalle.getSecuencia() + "," +
                    detalle.getConcepto() + "," +
                    detalle.getValorCuota() + "," +
                    detalle.getIdCobroCuota();
            writer.write(linea);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<DetalleCuota> cargarDetalles() {
        ArrayList<DetalleCuota> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                String idCuota = datos[0];
                Date fecha = FORMATO_FECHA.parse(datos[1]);
                String idCliente = datos[2];
                double valorTotal = Double.parseDouble(datos[3]);
                boolean status = Boolean.parseBoolean(datos[4]);
                int secuencia = Integer.parseInt(datos[5]);
                String concepto = datos[6];
                double valor = Double.parseDouble(datos[7]);
                String idCobro = datos[8];

                DetalleCuota d = new DetalleCuota(idCuota, fecha, idCliente, valorTotal, status, secuencia, concepto, valor, idCobro);
                lista.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
