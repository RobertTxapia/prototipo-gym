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
            // Guardar nuevo registro
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
                bw.write(loc.getId() + ";" + loc.getTipo());
                bw.newLine();
            }
        } else {
            // Actualizar registro existente
            actualizarLocalizacionEnArchivo(loc);
        }
        return true;
    }

    // Método para actualizar sin archivo temporal
    private static void actualizarLocalizacionEnArchivo(Localizacion loc) throws IOException {
        File archivo = new File("data/localizaciones.txt");
        List<String> lineas = new ArrayList<>();

        // Leer todas las líneas
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(loc.getId() + ";")) {
                    linea = loc.getId() + ";" + loc.getTipo(); // Actualizar línea
                }
                lineas.add(linea);
            }
        }

        // Sobrescribir el archivo original
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

                // Si se encontró la línea, reemplazamos el archivo original
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
