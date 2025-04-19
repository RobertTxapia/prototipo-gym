package prototipogym.controller.procesos;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CobroController {
    private static final String FILE_PATH = "data/cobros.txt";
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    public static boolean guardarCobro(String id, String fecha, String idCliente, String valor, String concepto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String linea = id + ";" + fecha + ";" + idCliente + ";" + valor + ";" + concepto;
            bw.write(linea);
            bw.newLine();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static String fechaActual() {
        return formatoFecha.format(new Date());
    }
}
