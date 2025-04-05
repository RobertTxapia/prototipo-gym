package prototipogym.controller;

import prototipogym.model.Cliente;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // Guardar/Actualizar cliente
    public static void guardarCliente(Cliente cliente) throws IOException {
        File archivo = new File("data/clientes.txt");
        StringBuilder contenido = new StringBuilder();
        boolean existe = false;

        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith(cliente.getId() + ";")) {
                        // Actualizar registro
                        contenido.append(serializarCliente(cliente)).append("\n");
                        existe = true;
                    } else {
                        contenido.append(linea).append("\n");
                    }
                }
            }
        }

        if (!existe) {
            contenido.append(serializarCliente(cliente)).append("\n");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(contenido.toString());
        }
    }

    private static String serializarCliente(Cliente c) {
        return String.join(";",
                c.getId(),
                c.getNombre(),
                c.getApellidoPat(),
                c.getApellidoMat(),
                c.getDireccion(),
                sdf.format(c.getFechaNac()),
                c.getTelefono(),
                c.getCelular(),
                sdf.format(c.getFechaIngreso()),
                String.valueOf(c.isStatus()),
                String.valueOf(c.getTipoCliente()),
                c.getCorreo(),
                String.valueOf(c.getBalance()),
                String.valueOf(c.getValorCuota())
        );
    }

    // Validar tipo de cliente
    public static boolean validarTipoCliente(int tipo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data/tipos_cliente.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(tipo + ";")) return true;
            }
        }
        return false;
    }

    // Eliminar cliente (solo si balance <= 0)
    public static boolean eliminarCliente(String id) throws IOException {
        File archivo = new File("data/clientes.txt");
        if (!archivo.exists()) return false; // Si el archivo no existe, retornar error

        List<String> lineas = new ArrayList<>();
        boolean clienteEncontrado = false;
        double balance = 0.0;

        // 1. Leer todas las líneas y buscar el cliente
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(id + ";")) {
                    // Extraer balance (posición 12 en el arreglo)
                    String[] datos = linea.split(";");
                    balance = Double.parseDouble(datos[12]);
                    clienteEncontrado = true;
                }
                lineas.add(linea); // Guardar todas las líneas
            }
        }

        // 2. Validar si se puede eliminar
        if (!clienteEncontrado) {
            return false; // Cliente no existe
        } else if (balance > 0) {
            return false; // Balance pendiente
        }

        // 3. Reconstruir contenido excluyendo al cliente
        StringBuilder contenido = new StringBuilder();
        for (String linea : lineas) {
            if (!linea.startsWith(id + ";")) {
                contenido.append(linea).append("\n");
            }
        }

        // 4. Sobrescribir el archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(contenido.toString());
        }

        return true;
    }
}
