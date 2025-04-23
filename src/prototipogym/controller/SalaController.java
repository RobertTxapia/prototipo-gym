package prototipogym.controller;

import prototipogym.model.Sala;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class SalaController {
    public static boolean guardarSala(Sala sala) throws IOException {
        List<String> lineas = leerTodasLasSalas();
        String nuevaLinea = sala.getId() + ";"
                + sala.getNombre() + ";"
                + sala.getDescripcion() + ";"
                + sala.getIdLocalizacion();

        boolean existe = false;
        for (int i = 0; i < lineas.size(); i++) {
            String[] datos = lineas.get(i).split(";");
            if (datos[0].equals(String.valueOf(sala.getId()))) {
                lineas.set(i, nuevaLinea); // Actualizar
                existe = true;
                break;
            }
        }

        if (!existe) lineas.add(nuevaLinea);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/salas.txt"))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        }
        return true;
    }

    public static boolean existeSala(String idSala) throws IOException {
        File file = new File("data/salas.txt");
        if (!file.exists()) return false;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(";");
                if (datos[0].equals(idSala)) return true;
            }
        }
        return false;
    }

    public static boolean existeLocalizacion(int idLocalizacion) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data/localizaciones.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(idLocalizacion + ";")) 
                    return true;
            }
        }
        return false;
    }

    public static List<String> leerTodasLasSalas() throws IOException {
        List<String> lineas = new ArrayList<>();
        File archivo = new File("data/salas.txt");
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) lineas.add(linea);
            }
        }
        return lineas;
    }
    
    public void ModificaDatos(String LineaAntigua, String nuevaLinea) {
        boolean encontrado = false;
        File fNuevo = new File("data/salaArchivo.txt"); 
        File fAntiguo = new File("data/salas.txt"); 

        try {
            if (fAntiguo.exists()) {
                BufferedWriter bw;
                try (BufferedReader br = new BufferedReader(new FileReader(fAntiguo))) {
                    bw = new BufferedWriter(new FileWriter(fNuevo));
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        System.out.println("Leyendo linea: " + linea);
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
