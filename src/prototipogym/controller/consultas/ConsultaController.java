package prototipogym.controller.consultas;
import prototipogym.model.Usuario;
import prototipogym.util.mantenimientos.FileManager;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

public class ConsultaController {

    public static List<Usuario> getUsuariosPorNivel(int nivel) throws IOException {
        return getTodosUsuarios().stream()
                .filter(u -> u.getNivelAcceso() == nivel)
                .collect(Collectors.toList());
    }

    public static List<Usuario> getTodosUsuarios() throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        List<String> lineas = FileManager.leerArchivo(new File("data/usuarios.txt"));

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            Usuario u = new Usuario();
            u.setLogin(datos[0]);
            u.setNombre(datos[3]);
            u.setApellidos(datos[4]);
            u.setCorreo(datos[5]);
            u.setNivelAcceso(Integer.parseInt(datos[2]));
            usuarios.add(u);
        }
        return usuarios;
    }
}
