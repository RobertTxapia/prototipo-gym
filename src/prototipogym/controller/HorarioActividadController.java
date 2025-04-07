package prototipogym.controller;
import prototipogym.model.HorarioActividad;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class HorarioActividadController {

    // Guardar o actualizar horario
    public static void guardarHorario(HorarioActividad horario) throws IOException {
        File archivo = new File("data/horarios_actividades.txt");
        StringBuilder contenido = new StringBuilder();
        boolean existe = false;

        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith(horario.getId() + ";")) {
                        // Actualizar línea existente
                        contenido.append(horario.getId()).append(";")
                                .append(horario.getDia()).append(";")
                                .append(horario.getHora()).append(";")
                                .append(horario.getIdActividad()).append("\n");
                                
                        existe = true;
                    } else {
                        contenido.append(linea).append("\n");
                    }
                }
            }
        }

        if (!existe) {
            contenido.append(horario.getId()).append(";")
                    .append(horario.getDia()).append(";")
                    .append(horario.getHora()).append(";")
                    .append(horario.getIdActividad()).append("\n");
                    
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(contenido.toString());
        }
    }

    public static boolean existeHorario(String idHorario) throws IOException {
        File file = new File("data/horarios_actividades.txt");
        if (!file.exists()) return false;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(";");
                if (datos[0].equals(idHorario)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Validar referencias
    public static boolean validarRelaciones(String idActividad) throws IOException {
        return existeEnArchivo("data/actividades.txt", idActividad);
                
    }

    private static boolean existeEnArchivo(String ruta, String id) throws IOException {
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
        File fNuevo = new File("data/activiArchivo.txt"); 
        File fAntiguo = new File("data/horarios_activades.txt"); 

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
