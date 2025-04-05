package prototipogym.controller;

import prototipogym.model.Entrenador;
import prototipogym.util.mantenimientos.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EntrenadorController {
    public static boolean guardarEntrenador(Entrenador entrenador) {
        String linea = entrenador.getId() + ""
                + entrenador.getNombre() + ""
                + entrenador.getApellido() + ""
                + entrenador.getTelefono() + ""
                + entrenador.getCorreo() + "";

        if(existeEntrenador(entrenador.getId())) {
            FileManager.actualizarLinea(new File("data/entrenadores.txt"), entrenador.getId(), linea);
        }else {
            FileManager.escribirLinea(new File("data/entrenadores.txt"), linea);
        }
        return true;
    }

    public static boolean existeEntrenador(String id) {
        return FileManager.existeId(new File("data/entrenadores.txt"), id);
    }

    public static List<String> obtenerTodosEntrenadores() throws IOException{
        return FileManager.leerArchivo(new File("data/entrenadores.txt"));
    }
}
