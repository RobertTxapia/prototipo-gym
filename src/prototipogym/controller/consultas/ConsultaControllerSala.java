package prototipogym.controller.consultas;

import prototipogym.model.Sala;
import prototipogym.util.mantenimientos.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaControllerSala {
    public static List<Sala> getTodasSalas() throws IOException {
        List<Sala> salas = new ArrayList<>();
        File archivo = new File("data/salas.txt");

        List<String> lineas = FileManager.leerArchivo(archivo);

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos.length >= 4) {
                Sala sala = new Sala();
                sala.setId(Integer.parseInt(datos[0].trim()));
                sala.setNombre(datos[1].trim());
                sala.setDescripcion(datos[2].trim());
                sala.setIdLocalizacion(Integer.parseInt(datos[3].trim()));
                salas.add(sala);
            }
        }
        return salas;
    }
}
