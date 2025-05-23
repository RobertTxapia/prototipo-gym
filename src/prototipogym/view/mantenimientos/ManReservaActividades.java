
package prototipogym.view.mantenimientos;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import prototipogym.controller.*;
import prototipogym.model.ReservaActividad;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ManReservaActividades extends javax.swing.JFrame {
private static String antiguaLinea=""; 
    private static ManReservaActividades instanciass;
    public ManReservaActividades() {
        initComponents();
        Chooser.getDateEditor().addPropertyChangeListener("date", evt -> {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (Chooser.getDate() != null) {
            String fecha = sdf.format(Chooser.getDate());
            TextFechaReserva.setText(fecha);
        }
        });
        
        chooser.getDateEditor().addPropertyChangeListener("date", evt -> {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (chooser.getDate() != null) {
            String fecha = sdf.format(chooser.getDate());
            TextFechaBaja.setText(fecha);
        }
        });
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
        Chooser.setDate(null);
        chooser.setDate(null);
    }
    
    private void buscarUsuario(){
        boolean encontrado = false;
        Scanner s = null;
        int cod;
        
        cod = Integer.parseInt(Text_ID.getText());
        
        try{
            File f = new File("data/reservaActividades.txt"); 
            s = new Scanner(f);
             while (s.hasNextLine() && !encontrado){
                String linea = s.nextLine().trim();
                Scanner sl = new Scanner(linea);
                sl.useDelimiter("\\s*;\\s*");
                try{
                    
                    if (cod==Integer.parseInt(sl.next())){
                         encontrado = true;
                         
                        etiqueta.setText("Modificando");
                        
                        String fechaReserva = sl.hasNext() ? sl.next().trim() : "";
                        String FechaBaja = sl.hasNext() ? sl.next().trim() : "";
                        String IDestado = sl.hasNext() ? sl.next().trim() : "";
                        String cliente = sl.hasNext() ? sl.next().trim() : "";
                        String Actividad = sl.hasNext() ? sl.next().trim() : "";
                        String Horario = sl.hasNext() ? sl.next().trim() : "";
                        Date dateBaja = new SimpleDateFormat("dd/MM/yyyy").parse(FechaBaja);
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fechaReserva);
                        
                        Chooser.setDate(date);  
                        chooser.setDate(dateBaja); 
                        TextFechaReserva.setText(fechaReserva);
                        TextFechaBaja.setText(FechaBaja);
                        Text_IDEstadoReserva.setText(IDestado);
                        Text_IDCliente.setText(cliente);
                        Text_IDActividad.setText(Actividad);
                        Text_IDHorarioActividad.setText(Horario);
                        
                        antiguaLinea = cod + ";" + fechaReserva + ";" + FechaBaja + ";" + IDestado + ";" + cliente+ ";" +Actividad+ ";" +Horario;
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
    
    private void guardarReservaActividad() {
    try {
        String id = Text_ID.getText().trim();
        String fecha = TextFechaReserva.getText().trim();
        String fechaBaja = TextFechaBaja.getText().trim();
        String estado = Text_IDEstadoReserva.getText().trim();
        String cliente = Text_IDCliente.getText().trim();
        String actividad = Text_IDActividad.getText().trim();
        String horario = Text_IDHorarioActividad.getText().trim();

        // Validar campos vacíos
        if (id.isEmpty() || fecha.isEmpty() || estado.isEmpty() || cliente.isEmpty() || actividad.isEmpty() || horario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.");
            return;
        }

        ReservaActividad reserva = new ReservaActividad(id, fecha, fechaBaja, estado, cliente, actividad, horario);
        ReservaActividadController rac = new ReservaActividadController();
        String nuevaLinea = id + ";" + fecha + ";" + fechaBaja + ";" + estado + ";" + cliente + ";" + actividad + ";" + horario;
        
         if (!ReservaActividadController.validarRelaciones(cliente, actividad, horario, estado)) {
                JOptionPane.showMessageDialog(this, "El ID Estado Reserva, Cliente, Actividad, Horario Reserva no existe");
                return;
            }

        if (ReservaActividadController.existeReserva(id)) {
            // Modificar si ya existe
            if (antiguaLinea == null || antiguaLinea.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se puede modificar: línea antigua no definida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            rac.ModificaDatos(antiguaLinea, nuevaLinea);
            JOptionPane.showMessageDialog(this, "Reserva modificada correctamente.");
        } else {
            // Guardar si no existe
            if (ReservaActividadController.guardarReserva(reserva)) {
                JOptionPane.showMessageDialog(this, "Reserva guardada.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar la reserva.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        Limpiar();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al guardar o modificar la reserva.", "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
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
        Chooser = new com.toedter.calendar.JDateChooser();
        chooser = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();

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
        Text_ID.setToolTipText("Ingrese el ID");
        Text_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Text_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Text_IDKeyTyped(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha Reserva");

        TextFechaReserva.setEditable(false);
        TextFechaReserva.setBackground(new java.awt.Color(200, 200, 200));
        TextFechaReserva.setToolTipText("Fecha Reserva");
        TextFechaReserva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextFechaReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFechaReservaActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha Baja");

        TextFechaBaja.setEditable(false);
        TextFechaBaja.setBackground(new java.awt.Color(200, 200, 200));
        TextFechaBaja.setToolTipText("Fecha Baja");
        TextFechaBaja.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ID Estado Reserva");

        Text_IDEstadoReserva.setBackground(new java.awt.Color(200, 200, 200));
        Text_IDEstadoReserva.setToolTipText("Ingrese el ID Estado Reserva");
        Text_IDEstadoReserva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Text_IDEstadoReserva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Text_IDEstadoReservaKeyTyped(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID Cliente");

        Text_IDCliente.setBackground(new java.awt.Color(200, 200, 200));
        Text_IDCliente.setToolTipText("Ingrese el ID del Cliente");
        Text_IDCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Text_IDCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Text_IDClienteKeyTyped(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ID Actividad");

        Text_IDActividad.setBackground(new java.awt.Color(200, 200, 200));
        Text_IDActividad.setToolTipText("Ingrese el ID Actividad");
        Text_IDActividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Text_IDActividad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Text_IDActividadKeyTyped(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID Horario Actividad");

        Text_IDHorarioActividad.setBackground(new java.awt.Color(200, 200, 200));
        Text_IDHorarioActividad.setToolTipText("Ingresa el ID Horario Actividad");
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

        etiqueta.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/calendar_clock_schedule_icon-icons.com_51085.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Text_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Text_IDEstadoReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Text_IDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Text_IDActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Text_IDHorarioActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 78, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Chooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(chooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TextFechaBaja, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                            .addComponent(TextFechaReserva)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(ButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(ButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(35, 35, 35))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Text_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextFechaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Chooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Text_IDEstadoReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Text_IDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
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

    private void ButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSalirActionPerformed
       instanciass = null;
       dispose();
    }//GEN-LAST:event_ButtonSalirActionPerformed

    private void ButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_ButtonLimpiarActionPerformed

    private void Text_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Text_IDKeyTyped
       
    }//GEN-LAST:event_Text_IDKeyTyped

    private void Text_IDEstadoReservaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Text_IDEstadoReservaKeyTyped
       
    }//GEN-LAST:event_Text_IDEstadoReservaKeyTyped

    private void Text_IDClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Text_IDClienteKeyTyped
        
    }//GEN-LAST:event_Text_IDClienteKeyTyped

    private void Text_IDActividadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Text_IDActividadKeyTyped
       
    }//GEN-LAST:event_Text_IDActividadKeyTyped

    private void Text_IDHorarioActividadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Text_IDHorarioActividadKeyTyped
       
    }//GEN-LAST:event_Text_IDHorarioActividadKeyTyped

    private void ButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonGuardarActionPerformed
        guardarReservaActividad();
    }//GEN-LAST:event_ButtonGuardarActionPerformed

    private void TextFechaReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFechaReservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFechaReservaActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonGuardar;
    private javax.swing.JButton ButtonLimpiar;
    private javax.swing.JButton ButtonSalir;
    private com.toedter.calendar.JDateChooser Chooser;
    private javax.swing.JTextField TextFechaBaja;
    private javax.swing.JTextField TextFechaReserva;
    private javax.swing.JTextField Text_ID;
    private javax.swing.JTextField Text_IDActividad;
    private javax.swing.JTextField Text_IDCliente;
    private javax.swing.JTextField Text_IDEstadoReserva;
    private javax.swing.JTextField Text_IDHorarioActividad;
    private com.toedter.calendar.JDateChooser chooser;
    private javax.swing.JLabel etiqueta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
