package prototipogym.view.movimientos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import prototipogym.controller.ClienteController;

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

    private void IDClienteFocusLost(java.awt.event.FocusEvent evt) {
        try {
            Cliente cliente = ClienteController.obtenerCliente(IDCliente.getText());
            if (cliente != null) {
                TextNombre.setText(cliente.getNombre() + " " + cliente.getApellidoPat());
                TextCuota.setText(String.valueOf(cliente.getValorCuota()));

                // Cargar tabla con cobros pendientes
                DefaultTableModel model = (DefaultTableModel) Tabla.getModel();
                model.setRowCount(0); // Limpiar tabla

                List<Cobro> cobros = CobroController.obtenerCobrosPendientes(IDCliente.getText());
                for (Cobro c : cobros) {
                    model.addRow(new Object[]{
                            c.getId(),          // ID Cobro (Columna 0)
                            "",                 // Secuencia (vacío inicialmente - Columna 1)
                            c.getConcepto(),    // Concepto (Columna 2)
                            c.getValorCobro(),  // Valor (Columna 3)
                            c.getId()           // ID Cobro Cuota (Columna 4)
                    });
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar datos del cliente",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void Limpiar(){
        Text_ID.setText("");
        TextNombre.setText("");
        TextCuota.setText("");
        TextFecha.setText("");

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
                new Object[][]{},
                new String[]{
                        "ID Cobro", "Sec", "Concepto", "Valor Cuota", "ID Cobro Cuota" // 5 columnas
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class,
                    java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false // Todas las columnas no editables
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 144, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(274, 274, 274)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addGap(32, 32, 32)
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
        TextFecha.setText(FORMATO_FECHA.format(new Date())); //Arreglar fecha si exite el cliente
    }//GEN-LAST:event_Text_IDActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        instanciass = null;
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_LimpiarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // Validar campos obligatorios
        if(Text_ID.getText().isEmpty() || IDCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe completar ID de Cuota y ID de Cliente",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener datos del cliente
        Cliente cliente;
        try {
            cliente = ClienteController.obtenerCliente(IDCliente.getText());
            if(cliente == null) {
                JOptionPane.showMessageDialog(this,
                        "Cliente no encontrado",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al leer datos del cliente: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar tipo de cliente y estado
        if(cliente.getTipoCliente() != 0 && !cliente.isStatus()) {
            JOptionPane.showMessageDialog(this,
                    "Solo socios activos pueden generar cuotas",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener cobros de la tabla
        DefaultTableModel model = (DefaultTableModel) Tabla.getModel();
        List<Cobro> cobros = new ArrayList<>();
        double totalCuota = 0.0;

        for(int i = 0; i < model.getRowCount(); i++) {
            try {
                // Obtener datos de todas las columnas
                int idCobro = Integer.parseInt(model.getValueAt(i, 4).toString()); // Columna 4: ID Cobro Cuota
                String concepto = model.getValueAt(i, 2).toString(); // Columna 2: Concepto
                double valor = Double.parseDouble(model.getValueAt(i, 3).toString()); // Columna 3: Valor Cuota

                Cobro cobro = new Cobro(
                        idCobro,
                        new Date(),
                        Integer.parseInt(IDCliente.getText()),
                        valor,
                        concepto
                );
                cobros.add(cobro);
                totalCuota += valor;

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Formato incorrecto en la fila " + (i+1),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Validar cobros seleccionados
        if(cobros.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe agregar al menos un concepto",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Cobro> cobrosProcesados = new ArrayList<>(); // Para manejar reversiones
        boolean transaccionExitosa = false;

        try {
            // Guardar encabezado de cuota
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/encabezado_cuota.txt", true))) {
                writer.write(String.join(";",
                        Text_ID.getText(),
                        FORMATO_FECHA.format(new Date()),
                        IDCliente.getText(),
                        "pendiente"
                ));
                writer.newLine();
            }

            // Guardar detalle y actualizar estados
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/detalle_cuota.txt", true))) {
                int secuencia = 1;
                for(Cobro cobro : cobros) {
                    // Guardar detalle
                    writer.write(String.join(";",
                            Text_ID.getText(),
                            String.valueOf(secuencia++),
                            cobro.getConcepto(),
                            String.valueOf(cobro.getValorCobro()),
                            String.valueOf(cobro.getId())
                    ));
                    writer.newLine();

                    // Actualizar estado del cobro
                    if (!CobroController.actualizarEstadoCobro(cobro.getId(), true)) {
                        throw new IOException("Error al actualizar cobro ID: " + cobro.getId());
                    }
                    cobrosProcesados.add(cobro); // Registrar para posible reversión
                }
            }

            // Actualizar balance del cliente
            double nuevoBalance = cliente.getBalance() - totalCuota;
            if (!ClienteController.actualizarBalanceCliente(IDCliente.getText(), nuevoBalance)) {
                throw new IOException("Error al actualizar balance del cliente");
            }

            transaccionExitosa = true;
            JOptionPane.showMessageDialog(this,
                    "Cuota guardada exitosamente!",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            // Revertir cambios si falla la transacción
            for (Cobro cobro : cobrosProcesados) {
                CobroController.actualizarEstadoCobro(cobro.getId(), false);
            }
            JOptionPane.showMessageDialog(this,
                    "Error al guardar cuota: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);

        } finally {
            if (transaccionExitosa) {
                Limpiar();
            }
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
