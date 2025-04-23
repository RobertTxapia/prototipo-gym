package prototipogym.controller.consultas;
import prototipogym.model.Usuario;
import prototipogym.util.mantenimientos.FileManager;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

public class ConsultaController {


    // Método unificado para filtrar por nivel (incluyendo "Todos")
    public static List<Usuario> getUsuariosFiltrados(String filtro) throws IOException {
        List<Usuario> usuarios = getTodosUsuarios();
        
        if (filtro.equalsIgnoreCase("Todos")) {
            return usuarios;
        } else {
            int nivel = Integer.parseInt(filtro);
            return usuarios.stream()
                    .filter(u -> u.getNivelAcceso() == nivel)
                    .collect(Collectors.toList());
        }
    }

    // Método existente para obtener todos los usuarios
    public static List<Usuario> getTodosUsuarios() throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        List<String> lineas = FileManager.leerArchivo(new File("data/usuarios.txt"));

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos.length >= 6) {
                Usuario u = new Usuario();
                u.setLogin(datos[0].trim());
                u.setNombre(datos[3].trim());
                u.setApellidos(datos[4].trim());
                u.setCorreo(datos[5].trim());
                u.setNivelAcceso(Integer.parseInt(datos[2].trim()));
                usuarios.add(u);
            }
        }
        return usuarios;
    }
}
