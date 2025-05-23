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
            Text_IDFocusLost(evt);
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

    private void Text_IDFocusLost(java.awt.event.FocusEvent evt) {
        String idCuota = Text_ID.getText().trim();
        if (idCuota.isEmpty()) return;

        try (Scanner scanner = new Scanner(new File("data/encabezado_cuota.txt"))) {
            boolean encontrado = false;
            while (scanner.hasNextLine()) {
                String[] campos = scanner.nextLine().split(";");
                if (campos[0].equals(idCuota)) {
                    encontrado = true;
                    if (campos[4].equalsIgnoreCase("false")) {
                        etiqueta.setText("Modificando");
                        TextFecha.setText(campos[1]);
                        IDCliente.setText(campos[2]);
                        cargarDetallesCuota(idCuota);
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Cuota ya procesada", "Error", JOptionPane.ERROR_MESSAGE);
                        Text_ID.setText(""); 
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
       /* String idCliente = IDCliente.getText().trim();
        if (idCliente.isEmpty()) {
            return;
        }

        if (!idCliente.isEmpty()) {
            cargarDetallesCuota(Text_ID.getText().trim());
        }

        try {
            Cliente cliente = ClienteController.obtenerCliente(idCliente);

            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCamposCliente();
                return;
            }

            if (cliente.getTipoCliente() != 1 || !cliente.isStatus()) {
                JOptionPane.showMessageDialog(this,
                    "El cliente no es Socio Activo",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                limpiarCamposCliente();
                return;
            }

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
        }*/
       
       
        String idCliente = IDCliente.getText().trim();
    if (idCliente.isEmpty()) {
        return;
    }

    try {
        Cliente cliente = ClienteController.obtenerCliente(idCliente);

        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCamposCliente();
            return;
        }

        if (cliente.getTipoCliente() != 1 || !cliente.isStatus()) {
            JOptionPane.showMessageDialog(this,
                "El cliente no es Socio Activo",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            limpiarCamposCliente();
            return;
        }

        TextNombre.setText(cliente.getNombre());
        TextCuota.setText(String.valueOf(cliente.getValorCuota()));

        // Cargar cobros pendientes en la tabla
        cargarCobrosPendientes(idCliente);

    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this,
            "Error al leer datos del cliente: " + ex.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        limpiarCamposCliente();
    }
}

private void cargarCobrosPendientes(String idCliente) {
    DefaultTableModel model = (DefaultTableModel) Tabla.getModel();
    model.setRowCount(0); // Limpiar tabla antes de cargar

    List<Cobro> cobrosPendientes = CuotaController.obtenerCobrosPendientes(idCliente);
    if (cobrosPendientes.isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "No hay cobros pendientes para este cliente",
            "Información",
            JOptionPane.INFORMATION_MESSAGE
        );
        return;
    }

    int secuencia = 1;
    for (Cobro cobro : cobrosPendientes) {
        model.addRow(new Object[]{
            idCliente,
            secuencia++,
            cobro.getConcepto(),
            cobro.getValorCobro(),
            cobro.getId()
        });
    }
       
    }

    private void limpiarCamposCliente() {
        TextNombre.setText("");
        TextCuota.setText("");
        IDCliente.setText("");
    }

    /*private void cargarDetallesCuota(String idCuota) {
        DefaultTableModel model = (DefaultTableModel) Tabla.getModel();
        model.setRowCount(0);

        try {
            String idCliente = obtenerIdClienteDeEncabezado(idCuota);
            try (Scanner scanner = new Scanner(new File("data/detalle_cuota.txt"))) {
                while (scanner.hasNextLine()) {
                    String[] campos = scanner.nextLine().split(";");
                    if (campos.length >= 5 && campos[0].equals(idCuota)) {
                        model.addRow(new Object[]{
                                idCliente,
                                campos[1],
                                campos[2],
                                campos[3],
                                campos[4]
                        });
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar detalles: " + ex.getMessage());
        }
    }*/
    
    private void cargarDetallesCuota(String idCuota) {
    DefaultTableModel model = (DefaultTableModel) Tabla.getModel();
    model.setRowCount(0); // Limpiar la tabla antes de cargar

    try {
        String idCliente = obtenerIdClienteDeEncabezado(idCuota);
        if (idCliente.equals("N/A")) return;
        
        // Cargar solo cobros pendientes no incluidos en otras cuotas
        List<Cobro> cobrosPendientes = CuotaController.obtenerCobrosPendientes(idCliente);
        Set<Integer> cobrosEnCuota = obtenerCobrosEnCuota(idCuota);
        
        int secuencia = 1;
        for (Cobro cobro : cobrosPendientes) {
            if (!cobrosEnCuota.contains(cobro.getId())) {
                model.addRow(new Object[]{
                    idCliente,
                    secuencia++,
                    cobro.getConcepto(),
                    cobro.getValorCobro(),
                    cobro.getId()
                });
            }
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar detalles: " + ex.getMessage());
    }
}

private Set<Integer> obtenerCobrosEnCuota(String idCuota) {
    Set<Integer> cobros = new HashSet<>();
    try (Scanner scanner = new Scanner(new File("data/detalle_cuota.txt"))) {
        while (scanner.hasNextLine()) {
            String[] campos = scanner.nextLine().split(";");
            if (campos.length >= 5 && campos[0].equals(idCuota)) {
                cobros.add(Integer.parseInt(campos[4])); // ID Cobro
            }
        }
    } catch (FileNotFoundException ex) {
        // Si el archivo no existe, no hay cobros registrados
          JOptionPane.showMessageDialog(this, "No hay cobros" + ex.getMessage());
    }
    return cobros;
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
    DefaultTableModel model = (DefaultTableModel) Tabla.getModel();
    int[] filasSeleccionadas = Tabla.getSelectedRows();

    if (filasSeleccionadas.length == 0) {
        JOptionPane.showMessageDialog(this, 
            "Debes seleccionar al menos una fila", 
            "Advertencia", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        String idCliente = IDCliente.getText();
        Cliente cliente = ClienteController.obtenerCliente(idCliente);
        
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double totalARestar = 0;
        
        // Procesar cada fila seleccionada (en orden inverso para evitar problemas con los índices)
        for (int i = filasSeleccionadas.length - 1; i >= 0; i--) {
            int fila = filasSeleccionadas[i];
            
            // Obtener datos de la fila
            String idCobroStr = model.getValueAt(fila, 4).toString();
            double valor = Double.parseDouble(model.getValueAt(fila, 3).toString());
            
            // Actualizar estado del cobro
            int idCobro = Integer.parseInt(idCobroStr);
            CobroController.actualizarEstadoCobro(idCobro, true);
            
            // Acumular total a restar
            totalARestar += valor;
            
            // Eliminar fila de la tabla
            model.removeRow(fila);
        }

        // Actualizar balance del cliente
        double nuevoBalance = cliente.getBalance() - totalARestar;
        if (nuevoBalance < 0) {
            JOptionPane.showMessageDialog(this, 
                "El cliente no tiene suficiente saldo", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ClienteController.actualizarBalanceCliente(idCliente, nuevoBalance);
        
        JOptionPane.showMessageDialog(this, 
            "Cuota procesada correctamente", 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, 
            "Error al procesar: " + ex.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
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
      /*if (Text_ID.getText().isEmpty() || IDCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos obligatorios faltantes", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Cliente cliente = ClienteController.obtenerCliente(IDCliente.getText());
            if (cliente == null || cliente.getTipoCliente() != 1 || !cliente.isStatus()) {
                JOptionPane.showMessageDialog(this, "Cliente no válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<Cobro> cobrosPendientes = CuotaController.obtenerCobrosPendientes(IDCliente.getText());
            if (cobrosPendientes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay cobros pendientes", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double valorTotal = cobrosPendientes.stream().mapToDouble(Cobro::getValorCobro).sum();
            CuotaController.guardarCuota(Text_ID.getText(), IDCliente.getText(), cobrosPendientes);

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

            if (nuevoBalance < 0) {
                JOptionPane.showMessageDialog(this,
                        "El cliente no tiene saldo suficiente para cubrir los cobros",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (!ClienteController.actualizarBalanceCliente(IDCliente.getText(), nuevoBalance)) {
                throw new IOException("Error al actualizar balance del cliente");
            }
             
            guardarFilasSeleccionadas();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }*/
       
       if (Text_ID.getText().isEmpty() || IDCliente.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Campos obligatorios faltantes", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        Cliente cliente = ClienteController.obtenerCliente(IDCliente.getText());
        if (cliente == null || cliente.getTipoCliente() != 1 || !cliente.isStatus()) {
            JOptionPane.showMessageDialog(this, "Cliente no válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) Tabla.getModel();
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay cobros pendientes", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Procesar solo las filas seleccionadas
        int[] filasSeleccionadas = Tabla.getSelectedRows();
        if (filasSeleccionadas.length == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione al menos un cobro", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double total = 0;
        String idCuota = Text_ID.getText();
        
        // Guardar encabezado si no existe
        if (!existeEncabezadoCuota(idCuota)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/encabezado_cuota.txt", true))) {
                writer.write(String.join(";",
                        idCuota,
                        new SimpleDateFormat("dd/MM/yyyy").format(new Date()),
                        IDCliente.getText(),
                        String.valueOf(total),
                        "false"
                ));
                writer.newLine();
            }
        }

        // Procesar cobros seleccionados
        try (BufferedWriter detalleWriter = new BufferedWriter(new FileWriter("data/detalle_cuota.txt", true))) {
            for (int fila : filasSeleccionadas) {
                int idCobro = Integer.parseInt(model.getValueAt(fila, 4).toString());
                double valor = Double.parseDouble(model.getValueAt(fila, 3).toString());
                
                detalleWriter.write(String.join(";",
                        idCuota,
                        String.valueOf(fila + 1), // Secuencia
                        model.getValueAt(fila, 2).toString(), // Concepto
                        String.valueOf(valor),
                        String.valueOf(idCobro)
                ));
                detalleWriter.newLine();
                
                total += valor;
                CobroController.actualizarEstadoCobro(idCobro, true);
                model.removeRow(fila); // Eliminar fila procesada
            }
        }

        // Actualizar balance del cliente
        double nuevoBalance = cliente.getBalance() - total;
        if (nuevoBalance < 0) {
            JOptionPane.showMessageDialog(this,
                    "El cliente no tiene saldo suficiente",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ClienteController.actualizarBalanceCliente(IDCliente.getText(), nuevoBalance)) {
            throw new IOException("Error al actualizar balance del cliente");
        }

        JOptionPane.showMessageDialog(this, "Cuota registrada exitosamente");
        
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private boolean existeEncabezadoCuota(String idCuota) {
    try (Scanner scanner = new Scanner(new File("data/encabezado_cuota.txt"))) {
        while (scanner.hasNextLine()) {
            String[] campos = scanner.nextLine().split(";");
            if (campos.length > 0 && campos[0].equals(idCuota)) {
                return true;
            }
        }
    } catch (FileNotFoundException ex) {
        return false;
    }
    return false;

         
       
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
