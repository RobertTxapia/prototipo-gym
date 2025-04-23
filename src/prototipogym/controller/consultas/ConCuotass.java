package prototipogym.controller.consultas;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import prototipogym.model.EncabezadoCuota;
import prototipogym.util.mantenimientos.FileManager;

public class ConCuotass {

    public static List<EncabezadoCuota> getTodasCuotas() throws IOException, ParseException {
        List<EncabezadoCuota> cuotas = new ArrayList<>();
        File archivo = new File("data/encabezado_cuota.txt");
        List<String> lineas = FileManager.leerArchivo(archivo);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            /*if (datos.length >= 5) {
                EncabezadoCuota cuota = new EncabezadoCuota(
                    datos[0].trim(),                           // ID Cuota
                    sdf.parse(datos[1].trim()),                // Fecha
                    datos[2].trim(),                           // ID Cliente
                    Double.parseDouble(datos[3].trim()),       // Valor
                    Boolean.parseBoolean(datos[4].trim())      // Status
                );
                cuotas.add(cuota);
            }*/
            if (datos.length >= 4) {
            double valor = datos.length >= 5 ? Double.parseDouble(datos[3].trim()) : 0.0;
            boolean status = datos.length == 5 ? Boolean.parseBoolean(datos[4].trim()) : false;

            EncabezadoCuota cuota = new EncabezadoCuota(
                datos[0].trim(),
                sdf.parse(datos[1].trim()),
                datos[2].trim(),
                valor,
                status
            );
            cuotas.add(cuota);
        }
        }
        return cuotas;
    }

    public static List<EncabezadoCuota> filtrarCuotas(Date inicio, Date fin) throws IOException, ParseException {
        List<EncabezadoCuota> cuotas = getTodasCuotas();
        inicio = resetHora(inicio);
        fin = resetHora(fin);

        List<EncabezadoCuota> filtradas = new ArrayList<>();
        for (EncabezadoCuota cuota : cuotas) {
            Date fecha = resetHora(cuota.getFechaCuota());
            if (!fecha.before(inicio) && !fecha.after(fin)) {
                filtradas.add(cuota);
            }
        }
        return filtradas;
    }

    public static List<EncabezadoCuota> filtrarCuotasPorCliente(String idCliente) throws IOException, ParseException {
        List<EncabezadoCuota> cuotas = getTodasCuotas();
        List<EncabezadoCuota> filtradas = new ArrayList<>();

        for (EncabezadoCuota cuota : cuotas) {
            if (cuota.getIdCliente().equals(idCliente)) {
                filtradas.add(cuota);
            }
        }
        return filtradas;
    }

    public static List<EncabezadoCuota> filtrarCuotasPorFechaYCliente(Date inicio, Date fin, String idCliente) throws IOException, ParseException {
        List<EncabezadoCuota> cuotas = getTodasCuotas();
        inicio = resetHora(inicio);
        fin = resetHora(fin);

        List<EncabezadoCuota> filtradas = new ArrayList<>();
        for (EncabezadoCuota cuota : cuotas) {
            Date fecha = resetHora(cuota.getFechaCuota());
            if (!fecha.before(inicio) && !fecha.after(fin) && cuota.getIdCliente().equals(idCliente)) {
                filtradas.add(cuota);
            }
        }
        return filtradas;
    }

    private static Date resetHora(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}

