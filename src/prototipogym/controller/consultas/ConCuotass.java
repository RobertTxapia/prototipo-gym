package prototipogym.controller.consultas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import prototipogym.model.DetalleCuota;
import prototipogym.util.mantenimientos.FileManager;

public class ConCuotass {

    public static List<DetalleCuota> getTodasCuotas() throws IOException {
        List<DetalleCuota> cuotas = new ArrayList<>();
        File archivo = new File("data/detalle_cuota.txt"); // Cambio de archivo
        List<String> lineas = FileManager.leerArchivo(archivo);

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos.length >= 5) {
                DetalleCuota detalle = new DetalleCuota(
                    datos[0].trim(),   // ID Cuota
                    Integer.parseInt(datos[1].trim()), // Secuencia
                    datos[2].trim(),   // Concepto
                    Double.parseDouble(datos[3].trim()), // Valor
                    Integer.parseInt(datos[4].trim()) // ID CobroCuota
                );
                cuotas.add(detalle);
            }
        }
        return cuotas;
    }

    // Métodos de filtrado ajustados (ejemplo: filtrar por ID Cuota)
    public static List<DetalleCuota> filtrarCuotasPorId(String idCuota) throws IOException {
        List<DetalleCuota> cuotas = getTodasCuotas();
        List<DetalleCuota> filtradas = new ArrayList<>();
        for (DetalleCuota detalle : cuotas) {
            if (detalle.getIdCuota().equals(idCuota)) {
                filtradas.add(detalle);
            }
        }
        return filtradas;
    }

}

