package prototipogym.controller.consultas;

import prototipogym.model.Cobro;
import prototipogym.util.mantenimientos.FileManager;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ConCobroRango {

    public static List<Cobro> getTodosCobros() throws IOException, ParseException {
        List<Cobro> cobros = new ArrayList<>();
        File archivo = new File("data/cobros.txt");

        List<String> lineas = FileManager.leerArchivo(archivo);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos.length >= 6) {
                Cobro cobro = new Cobro(
                        Integer.parseInt(datos[0].trim()),                  // ID
                        sdf.parse(datos[1].trim()),                         // Fecha
                        Integer.parseInt(datos[2].trim()),                  // ID Cliente
                        Double.parseDouble(datos[3].trim()),                // Valor Cobro
                        datos[4].trim(),                                    // Concepto
                        Boolean.parseBoolean(datos[5].trim())               // Status
                );
                cobros.add(cobro);
            }
        }
        return cobros;
    }

    /*public static List<Cobro> filtrarCobros(Date fechaInicio, Date fechaFin) throws IOException, ParseException {
        List<Cobro> cobros = getTodosCobros();
        return cobros.stream()
                .filter(c -> !c.getFecha().before(fechaInicio) && !c.getFecha().after(fechaFin))
                .toList();
    }*/
    
   public static List<Cobro> filtrarCobros(Date fechaInicio, Date fechaFin) throws IOException, ParseException {
    List<Cobro> cobros = getTodosCobros();

    fechaInicio = resetHora(fechaInicio);
    fechaFin = resetHora(fechaFin);

    List<Cobro> filtrados = new ArrayList<>();
    for (Cobro c : cobros) {
        Date fechaCobro = resetHora(c.getFecha());
        if (!fechaCobro.before(fechaInicio) && !fechaCobro.after(fechaFin)) {
            filtrados.add(c);
        }
    }

    return filtrados;
    }
    public static List<Cobro> filtrarCobrosPorCliente(int idCliente) throws IOException, ParseException {
    List<Cobro> cobros = getTodosCobros();
    List<Cobro> filtrados = new ArrayList<>();

    for (Cobro c : cobros) {
        if (c.getIdCliente() == idCliente) {
            filtrados.add(c);
        }
    }

    return filtrados;
}
    
    public static List<Cobro> filtrarCobrosPorFechaYCliente(Date fechaInicio, Date fechaFin, int idCliente) throws IOException, ParseException {
    List<Cobro> cobros = getTodosCobros();
    fechaInicio = resetHora(fechaInicio);
    fechaFin = resetHora(fechaFin);

    List<Cobro> filtrados = new ArrayList<>();
    for (Cobro c : cobros) {
        Date fechaCobro = resetHora(c.getFecha());
        if (!fechaCobro.before(fechaInicio) && !fechaCobro.after(fechaFin) && c.getIdCliente() == idCliente) {
            filtrados.add(c);
        }
    }

    return filtrados;
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