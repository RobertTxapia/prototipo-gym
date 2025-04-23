package prototipogym.view.consultas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import prototipogym.controller.consultas.ConCuotass;
import prototipogym.model.EncabezadoCuota;

public class ConCuotas extends javax.swing.JFrame {
    
    private static ConCuotas instanciass;
   
    public ConCuotas() {
        
        initComponents();
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            instanciass = null;
            dispose(); 
        }
        });
    }
    
    public static ConCuotas getInstancia(){
        if (instanciass == null){
            instanciass = new ConCuotas();
            getInstancia().setVisible(true);
        }
        return instanciass;
    }
    
    /*private void buscarCuotas() {
    try {
        String filtro = jComboBox1.getSelectedItem().toString();

        if (filtro.equals("Todos")) {
            List<EncabezadoCuota> cuotas = ConCuotass.getTodasCuotas();
            actualizarTablaCuotas(cuotas);

        } else if (filtro.equals("Por Rango de fecha")) {
            Date fechaInicio = dateInicial1.getDate();
            Date fechaFin = dateFinal.getDate();
            String idTexto = IDCliente.getText().trim();

            if (fechaInicio == null || fechaFin == null) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar ambas fechas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            List<EncabezadoCuota> cuotasFiltradas;

            if (!idTexto.isEmpty()) {
                cuotasFiltradas = ConCuotass.filtrarCuotasPorFechaYCliente(fechaInicio, fechaFin, idTexto);
            } else {
                cuotasFiltradas = ConCuotass.filtrarCuotas(fechaInicio, fechaFin);
            }

            actualizarTablaCuotas(cuotasFiltradas);

        } else if (filtro.equals("Cobro por Cliente")) {
            String idTexto = IDCliente.getText().trim();

            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debes ingresar un ID de cliente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            List<EncabezadoCuota> cuotas = ConCuotass.filtrarCuotasPorCliente(idTexto);
            actualizarTablaCuotas(cuotas);
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar cuotas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}*/

/*private void actualizarTablaCuotas(List<EncabezadoCuota> cuotas) {
    String[] columnas = {"ID", "Fecha", "ID Cliente", "Valor"};
    DefaultTableModel model = new DefaultTableModel(columnas, 0);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    for (EncabezadoCuota c : cuotas) {
        model.addRow(new Object[]{
            c.getIdCuota(),
            sdf.format(c.getFechaCuota()),
            c.getIdCliente(),
            c.getValorTotal(),
            c.isStatus() ? "Pagado" : "Pendiente"
        });
    }

    jTable1.setModel(model);
}*/
    
    private void buscarCuotas() {
    try {
        String filtro = jComboBox1.getSelectedItem().toString();

        if (filtro.equals("Todos")) {
            List<EncabezadoCuota> cuotas = ConCuotass.getTodasCuotas();
            actualizarTablaCuotas(cuotas);

        } else if (filtro.equals("Cuota por Fecha")) {
            Date fechaInicio = dateInicial1.getDate();
            Date fechaFin = dateFinal.getDate();
            String idTexto = IDCliente.getText().trim();

            if (fechaInicio == null || fechaFin == null) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar ambas fechas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            List<EncabezadoCuota> cuotasFiltradas;

            if (!idTexto.isEmpty()) {
                cuotasFiltradas = ConCuotass.filtrarCuotasPorFechaYCliente(fechaInicio, fechaFin, idTexto); // se pasa como String
            } else {
                cuotasFiltradas = ConCuotass.filtrarCuotas(fechaInicio, fechaFin);
            }

            actualizarTablaCuotas(cuotasFiltradas);

        } else if (filtro.equals("Cuota por Clientes")) {
            String idTexto = IDCliente.getText().trim();

            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debes ingresar un ID de cliente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            List<EncabezadoCuota> cuotas = ConCuotass.filtrarCuotasPorCliente(idTexto); // se pasa como String
            actualizarTablaCuotas(cuotas);
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar cuotas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void actualizarTablaCuotas(List<EncabezadoCuota> cuotas) {
    String[] columnas = {"ID", "Fecha", "ID Cliente", "Valor"}; // Corregido: se añadió "Estado"
    DefaultTableModel model = new DefaultTableModel(columnas, 0);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    for (EncabezadoCuota c : cuotas) {
        model.addRow(new Object[]{
            c.getIdCuota(),
            sdf.format(c.getFechaCuota()),
            c.getIdCliente(),
            c.getValorTotal(),
            c.isStatus() ? "Pagado" : "Pendiente"
        });
    }

    jTable1.setModel(model);
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        IDCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dateFinal = new com.toedter.calendar.JDateChooser();
        dateInicial1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7), 3));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Consultas Cuotas");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID Cliente");

        IDCliente.setBackground(new java.awt.Color(200, 200, 200));
        IDCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Filtrar");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Cuota por Fecha", "Cuota por Clientes" }));

        jButton1.setBackground(new java.awt.Color(255, 193, 7));
        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Fecha", "ID Cliente", "Valor "
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha Inicial");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha Final");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addComponent(jSeparator1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateInicial1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(350, 350, 350))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(dateInicial1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(dateFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(IDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(78, 78, 78)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscarCuotas();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDCliente;
    private com.toedter.calendar.JDateChooser dateFinal;
    private com.toedter.calendar.JDateChooser dateInicial1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
