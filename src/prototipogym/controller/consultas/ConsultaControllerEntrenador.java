package prototipogym.controller.consultas;

import prototipogym.model.Entrenador;
import prototipogym.util.mantenimientos.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaControllerEntrenador {
    public static List<Entrenador> getTodosEntrenadores() throws IOException {
        List<Entrenador> entrenadores = new ArrayList<>();
        List<String> lineas = FileManager.leerArchivo(new File("data/entrenadores.txt"));

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos.length < 5) continue;

            // Usa el constructor vacío y los setters
            Entrenador e = new Entrenador();
            e.setId(datos[0]);
            e.setNombre(datos[1]);
            e.setApellido(datos[2]);
            e.setTelefono(datos[3]);
            e.setCorreo(datos[4]);
            entrenadores.add(e);
        }
        return entrenadores;
    }
}