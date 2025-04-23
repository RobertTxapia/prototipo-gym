package prototipogym.view.movimientos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import prototipogym.controller.ClienteController;
import prototipogym.controller.movimientos.CuotaController;

import prototipogym.controller.procesos.CobroController;
import prototipogym.model.Cliente;
import prototipogym.model.Cobro;
import prototipogym.model.EncabezadoCuota;

public class Cuotas extends javax.swing.JFrame {
   private static Cuotas instanciass;
    public static final SimpleDateFormat FORMATO_FECHA= new SimpleDateFormat("dd/MM/yyyy");//sirve para la feccha
   public static SimpleDateFormat clockFormat=new SimpleDateFormat("h:mm a");
   public Cuotas() {
        initComponents();
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            instanciass = null;
            dispose();
        }
        });
        IDCliente.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            IDClienteFocusLost(evt);
        }
    });

   }

    public static Cuotas getInstancia(){
        if (instanciass == null){
            instanciass = new Cuotas();
            getInstancia().setVisible(true);
        }
        return instanciass;
    }

//    private void Text_IDFocusLost(java.awt.event.FocusEvent evt) {
//        String idCuota = Text_ID.getText().trim();
//        if (!idCuota.isEmpty()) {
//            cargarDetallesCuota(idCuota);
//        }
//    }

    private void Text_IDFocusLost(java.awt.event.FocusEvent evt) {
        String idCuota = Text_ID.getText().trim();
        if (idCuota.isEmpty()) return;

        try (Scanner scanner = new Scanner(new File("data/encabezado_cuota.txt"))) {
            boolean encontrado = false;
            while (scanner.hasNextLine()) {
                String[] campos = scanner.nextLine().split(";");
                if (campos[0].equals(idCuota)) {
                    encontrado = true;
                    if (campos[3].equalsIgnoreCase("false")) {
                        etiqueta.setText("Modificando");
                        TextFecha.setText(campos[1]);
                        IDCliente.setText(campos[2]);
                        cargarDetallesCuota(idCuota);
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Cuota ya procesada", "Error", JOptionPane.ERROR_MESSAGE);
                        Text_ID.setText(""); // Limpiar campo
                    }
                    break;
                }
            }
            if (!encontrado) {
                etiqueta.setText("Creando");
                TextFecha.setText(FORMATO_FECHA.format(new Date())); // Nueva fecha
            }
        } catch (FileNotFoundException ex) {
            etiqueta.setText("Creando");
            TextFecha.setText(FORMATO_FECHA.format(new Date()));
        }
    }

    private void IDClienteFocusLost(java.awt.event.FocusEvent evt) {
        String idCliente = IDCliente.getText().trim();
        if (idCliente.isEmpty()) {
            return;
        }

        if (!idCliente.isEmpty()) {
            cargarDetallesCuota(Text_ID.getText().trim()); // <-- Cargar detalles de la cuota actual
        }

        try {
            // Obtener datos del cliente usando ClienteController
            Cliente cliente = ClienteController.obtenerCliente(idCliente);

            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCamposCliente();
                return;
            }

            // Validar que sea Socio Activo (TipoCliente = 0, Status = true)
            if (cliente.getTipoCliente() != 1 || !cliente.isStatus()) {
                JOptionPane.showMessageDialog(this,
                    "El cliente no es Socio Activo",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                limpiarCamposCliente();
                return;
            }

            // Autocompletar campos
            TextNombre.setText(cliente.getNombre());
            TextCuota.setText(String.valueOf(cliente.getValorCuota()));
            //TextFecha.setText();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Error al leer datos del cliente: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            limpiarCamposCliente();
        }
    }

    private void limpiarCamposCliente() {
        TextNombre.setText("");
        TextCuota.setText("");
        IDCliente.setText("");
    }

    private void cargarDetallesCuota(String idCuota) {
        DefaultTableModel model = (DefaultTableModel) Tabla.getModel();
        model.setRowCount(0);

        try {
            String idCliente = obtenerIdClienteDeEncabezado(idCuota);
            try (Scanner scanner = new Scanner(new File("data/detalle_cuota.txt"))) {
                while (scanner.hasNextLine()) {
                    String[] campos = scanner.nextLine().split(";");
                    if (campos.length >= 5 && campos[0].equals(idCuota)) {
                        model.addRow(new Object[]{
                                idCliente,          // ID Cliente (desde encabezado)
                                campos[1],          // Secuencia
                                campos[2],          // Concepto
                                campos[3],          // Valor
                                campos[4]          // ID Cobro
                        });
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar detalles: " + ex.getMessage());
        }
    }

    private String obtenerIdClienteDeEncabezado(String idCuota) {
        try (Scanner scanner = new Scanner(new File("data/encabezado_cuota.txt"))) {
            while (scanner.hasNextLine()) {
                String[] campos = scanner.nextLine().split(";");
                if (campos.length >= 3 && campos[0].equals(idCuota)) {
                    return campos[2]; // ID Cliente está en la posición 2
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error al leer encabezados: " + ex.getMessage());
        }
        return "N/A";
    }

    private void guardarFilasSeleccionadas() {
        int[] filasSeleccionadas = Tabla.getSelectedRows();

        if (filasSeleccionadas.length == 0) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar al menos una fila", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) Tabla.getModel();
        Set<String> idsSeleccionados = new HashSet<>();

        // Recolectar los IDs de cobros seleccionados (columna 4)
        for (int fila : filasSeleccionadas) {
            String idCobro = model.getValueAt(fila, 4).toString();
            idsSeleccionados.add(idCobro);
        }

        File archivoCobros = new File("data/cobros.txt");
        File archivoTemporal = new File("data/temp_cobros.txt");

        try (
            BufferedReader reader = new BufferedReader(new FileReader(archivoCobros));
            BufferedWriter writerTemporal = new BufferedWriter(new FileWriter(archivoTemporal));
            BufferedWriter writerDetalle = new BufferedWriter(new FileWriter("data/detalle_cuota.txt", true))
        ) {
            String linea;
            int secuencia = 1;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length > 0 && idsSeleccionados.contains(partes[0])) {
                    // Si el ID de cobro está seleccionado, escribir en detalle_cuota.txt
                    String idCuota = Text_ID.getText(); // Usar el ID de cuota actual
                    String concepto = partes[2];  // Columna 2: Concepto
                    String valor = partes[3];     // Columna 3: Valor

                    // Escribir el detalle en el archivo
                    writerDetalle.write(String.join(";", idCuota, String.valueOf(secuencia++), concepto, valor, partes[0]));
                    writerDetalle.newLine();
                } else {
                    // Si no está seleccionado, escribirlo en el archivo temporal
                    writerTemporal.write(linea);
                    writerTemporal.newLine();
                }
            }

            // Reemplazar el archivo original con el archivo temporal
            reader.close();
            writerTemporal.close();
            archivoCobros.delete();
            archivoTemporal.renameTo(archivoCobros);

            // Eliminar las filas seleccionadas del JTable (desde atrás para evitar errores de índice)
            for (int i = filasSeleccionadas.length - 1; i >= 0; i--) {
                model.removeRow(filasSeleccionadas[i]);
            }

            JOptionPane.showMessageDialog(this, "Filas guardadas y eliminadas correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al procesar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Limpiar(){
        Text_ID.setText("");
        TextNombre.setText("");
        TextCuota.setText("");
        TextFecha.setText("");
        etiqueta.setText("");
        DefaultTableModel model = (DefaultTableModel) Tabla.getModel();
        model.setRowCount(0);
        IDCliente.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Text_ID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TextFecha = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        IDCliente = new javax.swing.JTextField();
        TextNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TextCuota = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        etiqueta = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7), 3));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID Cuota");

        Text_ID.setBackground(new java.awt.Color(200, 200, 200));
        Text_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Text_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_IDActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fecha");

        TextFecha.setEditable(false);
        TextFecha.setBackground(new java.awt.Color(200, 200, 200));
        TextFecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/business-color_handout_icon-icons.com_53455.png"))); // NOI18N
        jLabel3.setText("Cuotas");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID Cliente");

        IDCliente.setBackground(new java.awt.Color(200, 200, 200));
        IDCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        IDCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDClienteActionPerformed(evt);
            }
        });

        TextNombre.setEditable(false);
        TextNombre.setBackground(new java.awt.Color(200, 200, 200));
        TextNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Valor Cuota");

        TextCuota.setEditable(false);
        TextCuota.setBackground(new java.awt.Color(200, 200, 200));
        TextCuota.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Cliente", "Secuencia", "Concepto", "Valor Cuota", "ID Cobro Cuota"
            }
        ));
        jScrollPane1.setViewportView(Tabla);

        btnGuardar.setBackground(new java.awt.Color(255, 193, 7));
        btnGuardar.setText("Guardar");
        btnGuardar.setBorderPainted(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        Limpiar.setBackground(new java.awt.Color(255, 193, 7));
        Limpiar.setText("Limpiar");
        Limpiar.setBorderPainted(false);
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 193, 7));
        jButton3.setText("Volver");
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        etiqueta.setBackground(new java.awt.Color(51, 51, 51));
        etiqueta.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 130, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Text_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(TextCuota)
                        .addComponent(IDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TextFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(253, 253, 253))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(Text_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(IDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(TextCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(Limpiar)
                            .addComponent(jButton3))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void IDClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDClienteActionPerformed

    private void Text_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_IDActionPerformed
        Text_ID.requestFocus();

    }//GEN-LAST:event_Text_IDActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        instanciass = null;
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_LimpiarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       // Validación de campos
        if (Text_ID.getText().isEmpty() || IDCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos obligatorios faltantes", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Verificar existencia del cliente y su estado
            Cliente cliente = ClienteController.obtenerCliente(IDCliente.getText());
            if (cliente == null || cliente.getTipoCliente() != 1 || !cliente.isStatus()) {
                JOptionPane.showMessageDialog(this, "Cliente no válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener cobros pendientes del cliente
            List<Cobro> cobrosPendientes = CuotaController.obtenerCobrosPendientes(IDCliente.getText());
            if (cobrosPendientes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay cobros pendientes", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Guardar encabezado de cuota
            String idCuota = Text_ID.getText();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/encabezado_cuota.txt", true))) {
                writer.write(String.join(";",
                        idCuota,
                        new SimpleDateFormat("dd/MM/yyyy").format(new Date()),
                        IDCliente.getText(),
                        "false"
                ));
                writer.newLine();
            }

            // Guardar detalles y actualizar cobros
            double total = 0;
            int secuencia = 1;
            try (BufferedWriter detalleWriter = new BufferedWriter(new FileWriter("data/detalle_cuota.txt", true))) {
                for (Cobro cobro : cobrosPendientes) {
                    detalleWriter.write(String.join(";",
                            idCuota,
                            String.valueOf(secuencia++),
                            cobro.getConcepto(),
                            String.valueOf(cobro.getValorCobro()),
                            String.valueOf(cobro.getId())
                    ));
                    detalleWriter.newLine();
                    total += cobro.getValorCobro();
                    CobroController.actualizarEstadoCobro(cobro.getId(), true);
                }
            }

            ClienteController.actualizarBalanceCliente(IDCliente.getText(), cliente.getBalance() - total);
            JOptionPane.showMessageDialog(this, "Cuota registrada exitosamente");
            Limpiar();
            cargarDetallesCuota(Text_ID.getText().trim());
            double nuevoBalance = cliente.getBalance() - total;
            
            if (!ClienteController.actualizarBalanceCliente(IDCliente.getText(), nuevoBalance)) {
                throw new IOException("Error al actualizar balance del cliente");
            }
            
            guardarFilasSeleccionadas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_btnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDCliente;
    private javax.swing.JButton Limpiar;
    private javax.swing.JTable Tabla;
    private javax.swing.JTextField TextCuota;
    private javax.swing.JTextField TextFecha;
    private javax.swing.JTextField TextNombre;
    private javax.swing.JTextField Text_ID;
    private javax.swing.JButton btnGuardar;
    private java.awt.Label etiqueta;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
