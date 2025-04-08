package prototipogym.controller.consultas;

import prototipogym.model.Actividad;
import prototipogym.util.mantenimientos.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaControllerActividad {
    public static List<Actividad> getTodasActividades() throws IOException {
        List<Actividad> actividades = new ArrayList<>();
        File archivo = new File("data/actividades.txt"); // Ruta del archivo

        List<String> lineas = FileManager.leerArchivo(archivo);

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos.length >= 5) {
                Actividad act = new Actividad();
                act.setId(Integer.parseInt(datos[0].trim()));
                act.setNombre(datos[1].trim());
                act.setDescripcion(datos[2].trim());
                act.setIdLocalizacion(Integer.parseInt(datos[3].trim()));
                act.setIdEntrenador(Integer.parseInt(datos[4].trim()));
                actividades.add(act);
            }
        }
        return actividades;
    }
}
