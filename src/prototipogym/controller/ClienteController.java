package prototipogym.controller;

import prototipogym.model.Cliente;

import java.io.*;
import java.util.*;

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
        File file = new File(ARCHIVO);
        if (!file.exists()) return null;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(";");
                if (datos[0].equals(idCliente)) {
                    return new Cliente(
                            datos[0],   // idCliente
                            datos[1],   // nombre
                            datos[2],   // apellidoPat
                            datos[3],   // apellidoMat
                            datos[4],   // direccion
                            datos[5],   // fechaNac
                            datos[6],   // telefono
                            datos[7],   // celular
                            datos[8],   // fechaIngreso
                            Boolean.parseBoolean(datos[9]),  // status
                            Integer.parseInt(datos[10]),      // tipoCliente
                            datos[11],  // correo
                            Double.parseDouble(datos[12]),   // balance
                            Double.parseDouble(datos[13])    // valorCuota
                    );
                }
            }
        }
        return null;
    }

    // Validar si un cliente tiene balance pendiente (>0)
    public static boolean tieneBalancePendiente(String idCliente) throws IOException {
        Cliente cliente = obtenerCliente(idCliente);
        return cliente != null && cliente.getBalance() > 0;
    }
    
    
    
}
