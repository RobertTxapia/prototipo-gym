package prototipogym.view.mantenimientos;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import prototipogym.controller.ActividadController;
import prototipogym.controller.ReservaController;
import prototipogym.model.Reserva;
import java.io.IOException;
import java.util.Scanner;

public class ManReserva extends javax.swing.JFrame {
    private static String antiguaLinea=""; 
    private static ManReserva instanciass;
    public ManReserva() {
        initComponents();
        
        Chooser.getDateEditor().addPropertyChangeListener("date", evt -> {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (Chooser.getDate() != null) {
            String fecha = sdf.format(Chooser.getDate());
            TextFecha.setText(fecha);
        }
        });
        
        setLocationRelativeTo(null);
        KeyAdapter enterListener = new KeyAdapter() {
            
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                buscarUsuario();
            }
        }
        };
        
        Text_ID.addKeyListener(enterListener);

        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            instanciass = null;
            dispose(); 
        }
    });
    }
    
    public static ManReserva getInstancia(){
        if (instanciass == null){
            instanciass = new ManReserva();
            getInstancia().setVisible(true);
        }
        return instanciass;
    }
     private void buscarUsuario(){
        boolean encontrado = false;
        Scanner s = null;
        int cod;
        
        cod = Integer.parseInt(Text_ID.getText());
        
        try{
            File f = new File("data/reservas.txt"); 
            s = new Scanner(f);
             while (s.hasNextLine() && !encontrado){
                String linea = s.nextLine().trim();
                Scanner sl = new Scanner(linea);
                sl.useDelimiter("\\s*;\\s*");
                try{
                    
                    if (cod==Integer.parseInt(sl.next())){
                        encontrado = true;
                        etiqueta.setText("Modificando");
                        String Sala = sl.hasNext() ? sl.next().trim() : "";
                        String Cliente = sl.hasNext() ? sl.next().trim() : "";
                        String fecha = sl.hasNext() ? sl.next().trim() : "";
                        String Horario = sl.hasNext() ? sl.next().trim() : "";
                        String Estado = sl.hasNext() ? sl.next().trim() : "";
                        TextSala.setText(Sala);
                        TextCliente.setText(Cliente);
                        TextFecha.setText(fecha);
                        TextHorario.setText(Horario);
                        TextEstado.setText(Estado);
                        antiguaLinea = cod + ";" + Sala + ";" + Cliente + ";" +fecha + ";" + Horario + ";" +Estado;
                    }
                    
                }catch (Exception e) {
                    System.out.println("Error al leer linea: " + e.getMessage());
                }
                
             }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            if (s != null) {
                s.close();
            }
        }
        
        if (!encontrado) {
            etiqueta.setText("Creando");
        }
    }

    private void guardarReserva() {
        int idECliente = Integer.parseInt(TextCliente.getText());
        int idSala = Integer.parseInt(TextSala.getText());
        int idHorario = Integer.parseInt(TextHorario.getText());
        int idEstado = Integer.parseInt(TextEstado.getText());

        try {
            Reserva reserva = new Reserva(
                    Text_ID.getText(),
                    TextSala.getText(), // Campo añadido
                    TextCliente.getText(),
                    TextFecha.getText(),
                    TextHorario.getText(),
                    TextEstado.getText()
            );

            // Validaciones actualizadas
            if (reserva.getIdReserva().isEmpty() ||
                    reserva.getIdSala().isEmpty() ||
                    reserva.getIdCliente().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Campos obligatorios faltantes!");
                return;
            }

            if (!ReservaController.validarRelaciones(idECliente, idSala, idHorario, idEstado)) {
                JOptionPane.showMessageDialog(this, "Error en relaciones con otras tablas!");
                return;
            }

            boolean exito = ReservaController.guardarReserva(reserva);
            JOptionPane.showMessageDialog(this, exito ? "Éxito" : "Error");
            
            Limpiar();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public void Limpiar (){
        Text_ID.setText("");
        TextSala.setText("");
        TextCliente.setText("");
        TextFecha.setText("");
        TextHorario.setText("");
        TextEstado.setText("");
        Chooser.setDate(null);
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
        TextSala = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TextCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TextHorario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TextEstado = new javax.swing.JTextField();
        ButtonGuardar = new javax.swing.JButton();
        ButtonLimpiar = new javax.swing.JButton();
        ButtonVolver = new javax.swing.JButton();
        etiqueta = new javax.swing.JLabel();
        TextFecha = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Chooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7), 3));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mantenimiento Reserva");

        jSeparator1.setForeground(new java.awt.Color(250, 250, 250));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID");

        Text_ID.setBackground(new java.awt.Color(200, 200, 200));
        Text_ID.setToolTipText("Ingrese el ID");
        Text_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Text_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Text_IDKeyTyped(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID Sala");

        TextSala.setBackground(new java.awt.Color(200, 200, 200));
        TextSala.setToolTipText("Ingrese el ID Sala");
        TextSala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextSala.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TextSala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextSalaKeyTyped(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID Cliente");

        TextCliente.setBackground(new java.awt.Color(200, 200, 200));
        TextCliente.setToolTipText("Ingrese el ID Cliente");
        TextCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextClienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextClienteKeyTyped(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID Horario ");

        TextHorario.setBackground(new java.awt.Color(200, 200, 200));
        TextHorario.setToolTipText("Ingrese el ID Horario");
        TextHorario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextHorario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextHorarioKeyTyped(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ID Estado");

        TextEstado.setBackground(new java.awt.Color(200, 200, 200));
        TextEstado.setToolTipText("Ingrese el ID Estado");
        TextEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextEstadoActionPerformed(evt);
            }
        });
        TextEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextEstadoKeyTyped(evt);
            }
        });

        ButtonGuardar.setBackground(new java.awt.Color(255, 193, 7));
        ButtonGuardar.setText("Guarda");
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

        ButtonVolver.setBackground(new java.awt.Color(255, 193, 7));
        ButtonVolver.setText("Volver");
        ButtonVolver.setBorderPainted(false);
        ButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonVolverActionPerformed(evt);
            }
        });

        etiqueta.setForeground(new java.awt.Color(255, 255, 255));

        TextFecha.setEditable(false);
        TextFecha.setBackground(new java.awt.Color(200, 200, 200));
        TextFecha.setToolTipText("Fecha");
        TextFecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/calendar_clock_schedule_icon-icons.com_51085.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(Text_ID)
                                        .addComponent(TextSala, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TextCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TextHorario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TextEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(TextFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(etiqueta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ButtonGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(ButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ButtonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(68, 68, 68))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(29, 29, 29)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextSala, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Text_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5)
                        .addComponent(TextFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Chooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonGuardar)
                    .addComponent(ButtonLimpiar)
                    .addComponent(ButtonVolver))
                .addGap(18, 18, 18)
                .addComponent(etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void TextEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextEstadoActionPerformed

    private void ButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLimpiarActionPerformed
        Limpiar(); //Esto es un comentario
    }//GEN-LAST:event_ButtonLimpiarActionPerformed

    private void ButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonVolverActionPerformed
        instanciass = null;
        dispose();
    }//GEN-LAST:event_ButtonVolverActionPerformed

    private void Text_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Text_IDKeyTyped
       
    }//GEN-LAST:event_Text_IDKeyTyped

    private void TextSalaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextSalaKeyTyped
      
    }//GEN-LAST:event_TextSalaKeyTyped

    private void TextClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextClienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TextClienteKeyReleased

    private void TextClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextClienteKeyTyped
        
    }//GEN-LAST:event_TextClienteKeyTyped

    private void TextHorarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextHorarioKeyTyped
       
    }//GEN-LAST:event_TextHorarioKeyTyped

    private void TextEstadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextEstadoKeyTyped
        
    }//GEN-LAST:event_TextEstadoKeyTyped

    private void ButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonGuardarActionPerformed
        guardarReserva();
    }//GEN-LAST:event_ButtonGuardarActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonGuardar;
    private javax.swing.JButton ButtonLimpiar;
    private javax.swing.JButton ButtonVolver;
    private com.toedter.calendar.JDateChooser Chooser;
    private javax.swing.JTextField TextCliente;
    private javax.swing.JTextField TextEstado;
    private javax.swing.JTextField TextFecha;
    private javax.swing.JTextField TextHorario;
    private javax.swing.JTextField TextSala;
    private javax.swing.JTextField Text_ID;
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
