package prototipogym.controller;

import prototipogym.model.Usuario;
import prototipogym.util.mantenimientos.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UsuarioController {
    public static boolean guardarUsuario(Usuario usuario) {
        // 1. Validar campos obligatorios
        if (usuario.getLogin().isEmpty() || usuario.getPassword().isEmpty()) {
            return false;
        }

        // 2. Verificar si el usuario ya existe en usuarios.txt
        if (existeUsuario(usuario.getLogin())) {
            return false; // No permite duplicados
        }

        // 3. Guardar en el archivo usuarios.txt
        String linea = usuario.getLogin() + ";"
                + usuario.getPassword() + ";"
                + usuario.getNivelAcceso() + ";"
                + usuario.getNombre() + ";"
                + usuario.getApellidos() + ";"
                + usuario.getCorreo();

        FileManager.escribirLinea(new File("data/usuarios.txt"), linea);
        return true;
    }

    // Método para verificar si un usuario existe
    public static boolean existeUsuario(String login) {
        File archivoUsuarios = new File("data/usuarios.txt");

        if (!archivoUsuarios.exists()) {
            return false; // El archivo no existe, por lo que no hay usuarios
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].equals(login)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
