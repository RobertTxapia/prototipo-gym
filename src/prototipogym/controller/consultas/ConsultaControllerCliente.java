package prototipogym.controller.consultas;

import prototipogym.model.Cliente;
import prototipogym.util.mantenimientos.FileManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaControllerCliente {

    // Obtener todos los clientes desde el archivo
    public static List<Cliente> getTodosClientes() throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        File archivo = new File("data/clientes.txt");

        List<String> lineas = FileManager.leerArchivo(archivo);

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos.length >= 14) {
                Cliente cliente = new Cliente(
                        datos[0].trim(),  // ID
                        datos[1].trim(),  // Nombre
                        datos[2].trim(),  // ApellidoPat
                        datos[3].trim(),  // ApellidoMat
                        datos[4].trim(),  // Dirección
                        datos[5].trim(),  // FechaNac
                        datos[6].trim(),  // Teléfono
                        datos[7].trim(),  // Celular
                        datos[8].trim(),  // FechaIngreso
                        Boolean.parseBoolean(datos[9].trim()),  // Status
                        Integer.parseInt(datos[10].trim()),  // TipoCliente
                        datos[11].trim(),  // Correo
                        Double.parseDouble(datos[12].trim()),  // Balance
                        Double.parseDouble(datos[13].trim())   // ValorCuota
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    // Filtrar por nombre/ID y tipo de cliente
    public static List<Cliente> filtrarClientes(String busqueda, String tipoFiltro) throws IOException {
        List<Cliente> clientes = getTodosClientes();

        return clientes.stream()
                .filter(c ->
                        (c.getIdCliente().toLowerCase().contains(busqueda) ||
                                c.getNombre().toLowerCase().contains(busqueda)) &&
                                cumpleFiltroTipo(c, tipoFiltro)
                )
                .collect(Collectors.toList());
    }

    private static boolean cumpleFiltroTipo(Cliente cliente, String tipoFiltro) {
        return switch (tipoFiltro) {
            case "Socio Activo" -> cliente.getTipoCliente() == 1 && cliente.isStatus();
            case "Invitado" -> cliente.getTipoCliente() == 0;
            case "Activo" -> cliente.isStatus();
            case "Pasivo" -> !cliente.isStatus();
            default -> true; // Todos los tipos
        };
    }
}
