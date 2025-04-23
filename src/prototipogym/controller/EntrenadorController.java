package prototipogym.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import prototipogym.model.Entrenador;
import prototipogym.util.mantenimientos.FileManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

public class EntrenadorController {
    public static boolean guardarEntrenador(Entrenador entrenador) {
        String linea = entrenador.getId() + ";"
                + entrenador.getNombre() + ";"
                + entrenador.getApellido() + ";"
                + entrenador.getTelefono() + ";"
                + entrenador.getCorreo() + ";";

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
    
     public void ModificaDatos(String LineaAntigua, String nuevaLinea) {
        boolean encontrado = false;
        File fNuevo = new File("data/entreArchivo.txt"); 
        File fAntiguo = new File("data/entrenadores.txt"); 

        try {
            if (fAntiguo.exists()) {
                BufferedWriter bw;
                try (BufferedReader br = new BufferedReader(new FileReader(fAntiguo))) {
                    bw = new BufferedWriter(new FileWriter(fNuevo));
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        System.out.println("Leyendo linea: " + linea);
                        // Si encontramos la linea antigua, escribimos la nueva
                        if (linea.trim().equals(LineaAntigua.trim())) {
                            encontrado = true;
                            bw.write(nuevaLinea);
                        } else {
                            bw.write(linea); 
                        }
                        bw.newLine();
                    }
                }
                bw.close();

                if (encontrado) {
                    fAntiguo.delete(); 
                    fNuevo.renameTo(fAntiguo);
                } else {
                    fNuevo.delete(); 
                    
                }
            } 
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}


