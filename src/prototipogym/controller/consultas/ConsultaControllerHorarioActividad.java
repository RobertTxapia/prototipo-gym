package prototipogym.controller.consultas;

import prototipogym.controller.consultas.ConsultaControllerActividad;
import prototipogym.model.Actividad;
import prototipogym.model.HorarioActividad;
import prototipogym.util.mantenimientos.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static prototipogym.controller.consultas.ConsultaControllerActividad.getTodasActividades;

public class ConsultaControllerHorarioActividad {
    public static List<HorarioActividad> getTodosHorarios() throws IOException {
        List<HorarioActividad> horarios = new ArrayList<>();
        File archivo = new File("data/horarios_actividades.txt");

        List<String> lineas = FileManager.leerArchivo(archivo);

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos.length >= 4) {
                HorarioActividad horario = new HorarioActividad(
                        datos[0].trim(),
                        datos[1].trim(),
                        datos[2].trim(),
                        datos[3].trim()
                );
                horarios.add(horario);
            }
        }
        return horarios;
    }

    public static Actividad getActividadPorId(String id) throws IOException {
        return getTodasActividades()
                .stream()
                .filter(a -> String.valueOf(a.getId()).equals(id))
                .findFirst()
                .orElse(null);
    }
}
