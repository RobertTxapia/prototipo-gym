package prototipogym.controller;
import prototipogym.model.EstadoReserva;
import java.io.*;
import javax.swing.JOptionPane;

public class EstadoReservaController {
    public static boolean guardarEstado(EstadoReserva estado) {
        try {
            if (existeEstado(estado.getId())) return false;

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/estado_reservas.txt", true))) {
                bw.write(estado.toString());
                bw.newLine();
                return true;
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    // Validar ID único
    public static boolean existeEstado(String id) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data/estado_reservas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(id + ";")) return true;
            }
        }
        return false;
    }
    
    public void ModificaDatos(String LineaAntigua, String nuevaLinea) {
        boolean encontrado = false;
        
        File fNuevo = new File("data/estaArchivo.txt"); 
        File fAntiguo = new File("data/estado_reservas.txt"); 

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
