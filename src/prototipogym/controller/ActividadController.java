package prototipogym.controller;
import prototipogym.model.Actividad;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ActividadController {

    // Guardar o actualizar actividad
    public static void guardarActividad(Actividad actividad) throws IOException {
        File archivo = new File("data/actividades.txt");
        StringBuilder contenido = new StringBuilder();

        // Leer archivo existente
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                boolean existe = false;
                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith(actividad.getId() + ";")) {
                        linea = actividad.getId() + ";"
                                + actividad.getNombre() + ";"
                                + actividad.getDescripcion() + ";"
                                + actividad.getIdLocalizacion() + ";"
                                + actividad.getIdEntrenador();
                        existe = true;
                    }
                    contenido.append(linea).append("\n");
                }
                if (!existe) {
                    contenido.append(actividad.getId()).append(";")
                            .append(actividad.getNombre()).append(";")
                            .append(actividad.getDescripcion()).append(";")
                            .append(actividad.getIdLocalizacion()).append(";")
                            .append(actividad.getIdEntrenador()).append("\n");
                }
            }
        } else {
            contenido.append(actividad.getId()).append(";")
                    .append(actividad.getNombre()).append(";")
                    .append(actividad.getDescripcion()).append(";")
                    .append(actividad.getIdLocalizacion()).append(";")
                    .append(actividad.getIdEntrenador()).append("\n");
        }

        // Escribir todo el contenido
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(contenido.toString());
        }
    }

    public static boolean existeActividad(String idActividad) throws IOException {
        File file = new File("data/actividades.txt");
        if (!file.exists()) return false;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(";");
                if (datos[0].equals(idActividad)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Validar existencia de IDs relacionados
    public static boolean validarRelaciones(int idLocalizacion, int idEntrenador) throws IOException {
        return existeEnArchivo("data/localizaciones.txt", idLocalizacion)
                && existeEnArchivo("data/entrenadores.txt", idEntrenador);
    }

    private static boolean existeEnArchivo(String ruta, int id) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(id + ";")) return true;
            }
        }
        return false;
    }
    
    public void ModificaDatos(String LineaAntigua, String nuevaLinea) {
        boolean encontrado = false;
        File fNuevo = new File("data/actiArchivo.txt"); 
        File fAntiguo = new File("data/actividades.txt"); 

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
