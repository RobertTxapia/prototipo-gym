package prototipogym.controller;

import prototipogym.model.Localizacion;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LocalizacionController {
    public static boolean guardarLocalizacion(Localizacion loc) throws IOException {
        File archivo = new File("data/localizaciones.txt");
        boolean existe = existeLocalizacion(loc.getId());

        if (!existe) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
                bw.write(loc.getId() + ";" + loc.getTipo());
                bw.newLine();
            }
        } else {
            actualizarLocalizacionEnArchivo(loc);
        }
        return true;
    }

    // Método para actualizar sin archivo temporal
    private static void actualizarLocalizacionEnArchivo(Localizacion loc) throws IOException {
        File archivo = new File("data/localizaciones.txt");
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(loc.getId() + ";")) {
                    linea = loc.getId() + ";" + loc.getTipo(); 
                }
                lineas.add(linea);
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        }
    }

    public static boolean existeLocalizacion(int id) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data/localizaciones.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(id + ";")) return true;
            }
        }
        return false;
    }
    
    public void ModificaDatos(String LineaAntigua, String nuevaLinea) {
        boolean encontrado = false;
        File fNuevo = new File("data/locaArchivo.txt"); 
        File fAntiguo = new File("data/localizaciones.txt"); 

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
