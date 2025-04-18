package prototipogym.util.auth;
import prototipogym.model.Usuario;

import java.io.*;

public class AuthManager {
    private static Usuario usuarioLoquedo;
    
    //use el throws IOExpection para poder usarlo en el login por si hay algun error en los archivos
    public static boolean validarUsuario(String login, String password) throws IOException {
        File archivoUsuarios = new File("data/usuarios.txt");

        try(
            FileReader fr = new FileReader(archivoUsuarios);
            BufferedReader br = new BufferedReader(fr);

        ) {
            String lineas;
            while ((lineas = br.readLine()) != null) {
                String[] datos = lineas.split(";");
                if(datos.length >= 6 && datos[0].equals(login) && datos[1].equals(password)) {
                    usuarioLoquedo = new Usuario(datos[0], datos[1], Integer.parseInt(datos[2]), datos[3], datos[4], datos[5]);
                    return true;
                }
            }
            // to esos catch lo autocompleto el IDE por un error que tenia el FileReader. No me preguntes que hace
        }catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    // retornar el nivel de acceso del usuario autenticado
    public static int getNivelAcceso(String login, String password) {
        return (usuarioLoquedo != null) ? usuarioLoquedo.getNivelAcceso() : -1;
    }
}
