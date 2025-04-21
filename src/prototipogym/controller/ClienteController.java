package prototipogym.controller;

import prototipogym.model.Cliente;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import javax.swing.JOptionPane;

public class ClienteController {
    private static final String ARCHIVO = "data/clientes.txt";

    // Guardar cliente y devolver éxito/fallo
    public static boolean guardarCliente(Cliente cliente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(clienteToCSV(cliente));
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar cliente: " + e.getMessage());
            return false;
        }
    }

    // Verificar si un cliente existe por ID
    public static boolean existeCliente(String idCliente) throws IOException {
        File file = new File(ARCHIVO);
        if (!file.exists()) return false;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(";");
                if (datos[0].equals(idCliente)) {
                    return true;
                }
            }
        }
        return false;
    }



    // Convertir objeto Cliente a formato CSV
    private static String clienteToCSV(Cliente c) {
        return String.join(";",
                c.getIdCliente(),
                c.getNombre(),
                c.getApellidoPat(),
                c.getApellidoMat(),
                c.getDireccion(),
                c.getFechaNac(),
                c.getTelefono(),
                c.getCelular(),
                c.getFechaIngreso(),
                String.valueOf(c.isStatus()),
                String.valueOf(c.getTipoCliente()),
                c.getCorreo(),
                String.valueOf(c.getBalance()),
                String.valueOf(c.getValorCuota())
        );
    }

    // Obtener cliente por ID (útil para modificaciones)
    public static Cliente obtenerCliente(String idCliente) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data/Clientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].equals(idCliente)) {
                    return new Cliente(
                            datos[0],  // ID
                            datos[1],  // Nombre
                            datos[2],  // Primer apellido
                            datos[3],  // Segundo apellido
                            datos[4],  // Dirección
                            datos[5],  // Fecha de nacimiento
                            datos[6],  // Teléfono
                            datos[7],  // Celular
                            datos[8],  // Fecha de ingreso
                            Boolean.parseBoolean(datos[9]),  // Status
                            Integer.parseInt(datos[10]),     // TipoCliente
                            datos[11],  // Correo
                            Double.parseDouble(datos[12]),   // Balance
                            Double.parseDouble(datos[13])    // Valor Cuota
                    );
                }
            }
        }
        return null; // Cliente no encontrado
    }

    // Validar si un cliente tiene balance pendiente (>0)
    public static boolean tieneBalancePendiente(String idCliente) throws IOException {
        Cliente cliente = obtenerCliente(idCliente);
        return cliente != null && cliente.getBalance() > 0;
    }
    
    public void ModificaDatos(String LineaAntigua, String nuevaLinea) {
        boolean encontrado = false;
        File fNuevo = new File("data/ClienArchivo.txt"); 
        File fAntiguo = new File("data/clientes.txt"); 

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



    public static boolean actualizarBalanceCliente(String idCliente, double nuevoBalance) {
        String ARCHIVO = "data/clientes.txt";
        boolean encontrado = false;
        File tempFile = new File("data/clientes_temp.txt");
        File originalFile = new File(ARCHIVO);

        try (BufferedReader br = new BufferedReader(new FileReader(originalFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";", -1);
                if (campos.length >= 14 && campos[0].equals(idCliente)) {
                    campos[12] = String.format("%.2f", nuevoBalance);
                    linea = String.join(";", campos);
                    encontrado = true;
                }
                bw.write(linea + "\n");
            }

            bw.close();
            br.close();

            if (encontrado) {
                if (originalFile.delete()) {
                    Files.move(
                        tempFile.toPath(), 
                        originalFile.toPath(), 
                        StandardCopyOption.REPLACE_EXISTING
                    );
                } else {
                    System.err.println("Error al eliminar el archivo original.");
                    tempFile.delete();
                    return false;
                }
            } else {
                tempFile.delete();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar balance: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return encontrado;
    }

    public static List<Cliente> obtenerSociosActivos() throws IOException {
        List<Cliente> socios = new ArrayList<>();
        File file = new File(ARCHIVO);
        if (!file.exists()) return socios;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(";");
                if (datos.length >= 14) {
                    boolean status = Boolean.parseBoolean(datos[9]);
                    int tipo = Integer.parseInt(datos[10]);
                    if (tipo == 1 && status) {
                        Cliente cliente = new Cliente(
                                datos[0], datos[1], datos[2], datos[3], datos[4],
                                datos[5], datos[6], datos[7], datos[8], status,
                                tipo, datos[11], Double.parseDouble(datos[12]),
                                Double.parseDouble(datos[13])
                        );
                        socios.add(cliente);
                    }
                }
            }
        }
        return socios;
    }
}
