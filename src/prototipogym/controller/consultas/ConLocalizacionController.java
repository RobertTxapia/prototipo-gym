package prototipogym.controller.consultas;

import prototipogym.model.Localizacion;
import prototipogym.util.mantenimientos.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConLocalizacionController {
    public static List<Localizacion> getTodasLocalizaciones() throws IOException {
        List<Localizacion> localizaciones = new ArrayList<>();
        File archivo = new File("data/localizaciones.txt");

        List<String> lineas = FileManager.leerArchivo(archivo);

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos.length >= 2) {
                Localizacion loc = new Localizacion();
                loc.setId(Integer.parseInt(datos[0].trim()));
                loc.setTipo(datos[1].trim());
                localizaciones.add(loc);
            }
        }

        return localizaciones;
    }
}

