
package prototipogym.view;

import prototipogym.controller.*;
import prototipogym.model.ReservaActividad;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ManReservaActividades extends javax.swing.JFrame {

    private static ManReservaActividades instanciass;
    public ManReservaActividades() {
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

    public static ManReservaActividades getInstancia(){
        if (instanciass == null){
            instanciass = new ManReservaActividades();
            getInstancia().setVisible(true);
        }
        return instanciass;
    }
    
    public void Limpiar(){
        Text_ID.setText("");
        TextFechaReserva.setText("");
        TextFechaBaja.setText("");
        Text_IDEstadoReserva.setText("");
        Text_IDCliente.setText("");
        Text_IDActividad.setText("");
        Text_IDHorarioActividad.setText("");
    }

    private void guardarReservaActividad() {
        try {
            // Validar campos obligatorios
            if (Text_ID.getText().isEmpty() ||
                    Text_IDCliente.getText().isEmpty() ||
                    Text_IDActividad.getText().isEmpty() ||
                    Text_IDHorarioActividad.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Campos obligatorios faltantes!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar relaciones con otras tablas
            if (!validarRelaciones()) {
                JOptionPane.showMessageDialog(this, "Error en relaciones con otras tablas!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear objeto ReservaActividad
            ReservaActividad reserva = new ReservaActividad(
                    Integer.parseInt(Text_ID.getText()),
                    TextFechaReserva.getText(),
                    TextFechaBaja.getText(),
                    Text_IDEstadoReserva.getText(),
                    Text_IDCliente.getText(),
                    Text_IDActividad.getText(),
                    Text_IDHorarioActividad.getText()
            );

            // Guardar en archivo
            if (ReservaActividadController.guardarReserva(reserva)) {
                JOptionPane.showMessageDialog(this, "Reserva guardada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar la reserva", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID debe ser numérico!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error de acceso a archivos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarRelaciones() throws IOException {
        return ClienteController.existeCliente(Text_IDCliente.getText()) &&
                ActividadController.existeActividad(Text_IDActividad.getText()) &&
                HorarioActividadController.existeHorario(Text_IDHorarioActividad.getText()) &&
                EstadoReservaController.existeEstado(Text_IDEstadoReserva.getText());
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        Text_ID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TextFechaReserva = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TextFechaBaja = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Text_IDEstadoReserva = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Text_IDCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Text_IDActividad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Text_IDHorarioActividad = new javax.swing.JTextField();
        ButtonGuardar = new javax.swing.JButton();
        ButtonLimpiar = new javax.swing.JButton();
        ButtonSalir = new javax.swing.JButton();
        etiqueta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7), 3));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mantenimiento Reserva Actividades");

        jSeparator1.setForeground(new java.awt.Color(250, 250, 250));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID");

        Text_ID.setBackground(new java.awt.Color(200, 200, 200));
        Text_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Text_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Text_IDKeyTyped(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha Reserva");

        TextFechaReserva.setBackground(new java.awt.Color(200, 200, 200));
        TextFechaReserva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha Baja");

        TextFechaBaja.setBackground(new java.awt.Color(200, 200, 200));
        TextFechaBaja.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ID Estado Reserva");

        Text_IDEstadoReserva.setBackground(new java.awt.Color(200, 200, 200));
        Text_IDEstadoReserva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Text_IDEstadoReserva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Text_IDEstadoReservaKeyTyped(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID Cliente");

        Text_IDCliente.setBackground(new java.awt.Color(200, 200, 200));
        Text_IDCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Text_IDCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Text_IDClienteKeyTyped(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ID Actividad");

        Text_IDActividad.setBackground(new java.awt.Color(200, 200, 200));
        Text_IDActividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Text_IDActividad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Text_IDActividadKeyTyped(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID Horario Actividad");

        Text_IDHorarioActividad.setBackground(new java.awt.Color(200, 200, 200));
        Text_IDHorarioActividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Text_IDHorarioActividad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Text_IDHorarioActividadKeyTyped(evt);
            }
        });

        ButtonGuardar.setBackground(new java.awt.Color(255, 193, 7));
        ButtonGuardar.setText("Guardar");
        ButtonGuardar.setBorderPainted(false);
        ButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonGuardarActionPerformed(evt);
            }
        });

        ButtonLimpiar.setBackground(new java.awt.Color(255, 193, 7));
        ButtonLimpiar.setText("Limpiar");
        ButtonLimpiar.setBorderPainted(false);
        ButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLimpiarActionPerformed(evt);
            }
        });

        ButtonSalir.setBackground(new java.awt.Color(255, 193, 7));
        ButtonSalir.setText("Volver");
        ButtonSalir.setBorderPainted(false);
        ButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(ButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(ButtonSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Text_ID)
                                    .addComponent(TextFechaReserva)
                                    .addComponent(TextFechaBaja)
                                    .addComponent(Text_IDEstadoReserva)
                                    .addComponent(Text_IDCliente)
                                    .addComponent(Text_IDActividad)
                                    .addComponent(Text_IDHorarioActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(44, 44, 44))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Text_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TextFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Text_IDEstadoReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(Text_IDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(TextFechaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Text_IDActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Text_IDHorarioActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonGuardar)
                    .addComponent(ButtonLimpiar)
                    .addComponent(ButtonSalir))
                .addGap(18, 18, 18)
                .addComponent(etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
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

    private void ButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSalirActionPerformed
       instanciass = null;
       dispose();
    }//GEN-LAST:event_ButtonSalirActionPerformed

    private void ButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_ButtonLimpiarActionPerformed

    private void Text_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Text_IDKeyTyped
       try {
           char letra = evt.getKeyChar();
            // Permite solo números
           if (!Character.isDigit(letra)) {
               throw new Exception("Solo se permiten numeros");
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_Text_IDKeyTyped

    private void Text_IDEstadoReservaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Text_IDEstadoReservaKeyTyped
       try {
           char letra = evt.getKeyChar();
            // Permite solo números
           if (!Character.isDigit(letra)) {
               throw new Exception("Solo se permiten numeros");
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_Text_IDEstadoReservaKeyTyped

    private void Text_IDClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Text_IDClienteKeyTyped
        try {
           char letra = evt.getKeyChar();
            // Permite solo números
           if (!Character.isDigit(letra)) {
               throw new Exception("Solo se permiten numeros");
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_Text_IDClienteKeyTyped

    private void Text_IDActividadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Text_IDActividadKeyTyped
       try {
           char letra = evt.getKeyChar();
            // Permite solo números
           if (!Character.isDigit(letra)) {
               throw new Exception("Solo se permiten numeros");
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_Text_IDActividadKeyTyped

    private void Text_IDHorarioActividadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Text_IDHorarioActividadKeyTyped
        try {
           char letra = evt.getKeyChar();
            // Permite solo números
           if (!Character.isDigit(letra)) {
               throw new Exception("Solo se permiten numeros");
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_Text_IDHorarioActividadKeyTyped

    private void ButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonGuardarActionPerformed
        guardarReservaActividad();
    }//GEN-LAST:event_ButtonGuardarActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonGuardar;
    private javax.swing.JButton ButtonLimpiar;
    private javax.swing.JButton ButtonSalir;
    private javax.swing.JTextField TextFechaBaja;
    private javax.swing.JTextField TextFechaReserva;
    private javax.swing.JTextField Text_ID;
    private javax.swing.JTextField Text_IDActividad;
    private javax.swing.JTextField Text_IDCliente;
    private javax.swing.JTextField Text_IDEstadoReserva;
    private javax.swing.JTextField Text_IDHorarioActividad;
    private javax.swing.JLabel etiqueta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
