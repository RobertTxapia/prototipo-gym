package prototipogym.controller;

import prototipogym.model.Usuario;
import prototipogym.util.mantenimientos.FileManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

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
    
   public void ModificaDatos(String LineaAntigua, String nuevaLinea) {
        boolean encontrado = false;
        File fNuevo = new File("data/archivoCte1.txt"); // Archivo temporal
        File fAntiguo = new File("data/usuarios.txt"); // Archivo original

        try {
            if (fAntiguo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(fAntiguo));
                BufferedWriter bw = new BufferedWriter(new FileWriter(fNuevo));

                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println("Leyendo línea: " + linea);
                    // Si encontramos la línea antigua, escribimos la nueva
                    if (linea.trim().equalsIgnoreCase(LineaAntigua.trim())) {
                        encontrado = true;
                        bw.write(nuevaLinea);
                    } else {
                        bw.write(linea); // Escribimos la línea original
                    }
                    bw.newLine(); // Agregamos un salto de línea
                }

                br.close();
                bw.close();

                // Si se encontró la línea, reemplazamos el archivo original
                if (encontrado) {
                    fAntiguo.delete(); // Eliminamos el archivo original
                    fNuevo.renameTo(fAntiguo); // Renombramos el archivo nuevo
                } else {
                    fNuevo.delete(); // Eliminamos el archivo temporal si no hubo cambios
                    
                }
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error al modificar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    
    
}
