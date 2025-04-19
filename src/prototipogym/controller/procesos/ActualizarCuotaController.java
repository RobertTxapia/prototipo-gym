package prototipogym.controller.procesos;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ActualizarCuotaController {
    private static final String ARCHIVO_DETALLE = "data/detalle_cuota.txt";
    private static final SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    public static boolean actualizarFechas(Date fechaInicio, Date fechaFinal) {
        try {
            File archivo = new File(ARCHIVO_DETALLE);
            if (!archivo.exists()) return false;

            List<String> lineasActualizadas = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 5) {
                    partes[3] = formato.format(fechaInicio);
                    partes[4] = formato.format(fechaFinal);
                    linea = String.join(";", partes);
                }
                lineasActualizadas.add(linea);
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            for (String nuevaLinea : lineasActualizadas) {
                bw.write(nuevaLinea);
                bw.newLine();
            }
            bw.close();

            return true;

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}