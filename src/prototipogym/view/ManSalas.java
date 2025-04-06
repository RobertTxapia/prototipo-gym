
package prototipogym.view;

import prototipogym.controller.SalaController;
import prototipogym.model.Sala;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class ManSalas extends javax.swing.JFrame {
    private static String antiguaLinea=""; 
    private static ManSalas instanciass;
    public ManSalas() {
        initComponents();
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
        addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            instanciass = null;
            dispose(); 
        }
    });
    }
    
    public static ManSalas getInstancia(){
        if (instanciass == null){
            instanciass = new ManSalas();
            getInstancia().setVisible(true);
        }
        return instanciass;
    }
    
    public void Limpiar(){
        Text_ID.setText("");
        TextNombreSala.setText("");
        TextDescripcion.setText("");
        TextLocalizacion.setText("");
    }

    
     private void buscarUsuario(){
        boolean encontrado = false;
        Scanner s = null;
        int cod;
        
        cod = Integer.parseInt(Text_ID.getText());
        
        try{
            File f = new File("data/salas.txt"); 
            s = new Scanner(f);
             while (s.hasNextLine() && !encontrado){
                String linea = s.nextLine().trim();
                Scanner sl = new Scanner(linea);
                sl.useDelimiter("\\s*;\\s*");
                try{
                    
                    if (cod==Integer.parseInt(sl.next())){
                         encontrado = true;
                        etiqueta.setText("Modificando");
                        
                        String nombreSala = sl.hasNext() ? sl.next().trim() : "";
                        String descripcion = sl.hasNext() ? sl.next().trim() : "";
                        String idLocalizacion = sl.hasNext() ? sl.next().trim() : "";
                        
                        TextNombreSala.setText(nombreSala);
                        TextDescripcion.setText(descripcion);
                        TextLocalizacion.setText(idLocalizacion);
                        
                        
                        antiguaLinea = cod + ";" + nombreSala + ";" + descripcion + ";" + idLocalizacion;
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

    private void guardarSala() {
        try {
            int id = Integer.parseInt(Text_ID.getText());
            String nombre = TextNombreSala.getText().trim();
            String descripcion = TextDescripcion.getText().trim();
            int idLocalizacion = Integer.parseInt(TextLocalizacion.getText());
            SalaController salas = new SalaController();
            Sala sala = new Sala(id, nombre, descripcion, idLocalizacion);
            if (nombre.isEmpty() || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre y Descripción son obligatorios");
                return;
            }

            if (!SalaController.existeLocalizacion(idLocalizacion)) {
                JOptionPane.showMessageDialog(this, "Localización no existe");
                return;
            }

            
           if(SalaController.guardarSala(sala)){
               JOptionPane.showMessageDialog(this, "Sala guardada!");
           }
           else{
                String Snuevalinea = ( id + ";" + nombre + ";" + descripcion + ";" +idLocalizacion );
                 salas.ModificaDatos(antiguaLinea, Snuevalinea);
           }
            

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID debe ser numérico");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
        Limpiar();
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
        TextNombreSala = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TextDescripcion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TextLocalizacion = new javax.swing.JTextField();
        ButtonGuardar = new javax.swing.JButton();
        ButtonLimpiar = new javax.swing.JButton();
        ButtonSalir = new javax.swing.JButton();
        etiqueta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7), 3));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mantenimiento Salas");

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
        jLabel3.setText("Nombre de la sala");

        TextNombreSala.setBackground(new java.awt.Color(200, 200, 200));
        TextNombreSala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Descripcion de la sala");

        TextDescripcion.setBackground(new java.awt.Color(200, 200, 200));
        TextDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ID Localizacion");

        TextLocalizacion.setBackground(new java.awt.Color(200, 200, 200));
        TextLocalizacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextLocalizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextLocalizacionActionPerformed(evt);
            }
        });
        TextLocalizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextLocalizacionKeyTyped(evt);
            }
        });

        ButtonGuardar.setBackground(new java.awt.Color(255, 193, 7));
        ButtonGuardar.setText("Guardar");
        ButtonGuardar.setToolTipText("Guardar");
        ButtonGuardar.setBorderPainted(false);
        ButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonGuardarActionPerformed(evt);
            }
        });

        ButtonLimpiar.setBackground(new java.awt.Color(255, 193, 7));
        ButtonLimpiar.setText("Limpiar");
        ButtonLimpiar.setToolTipText("Limpiar");
        ButtonLimpiar.setBorderPainted(false);
        ButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLimpiarActionPerformed(evt);
            }
        });

        ButtonSalir.setBackground(new java.awt.Color(255, 193, 7));
        ButtonSalir.setText("Volver");
        ButtonSalir.setToolTipText("Volver");
        ButtonSalir.setBorderPainted(false);
        ButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSalirActionPerformed(evt);
            }
        });

        etiqueta.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(21, 21, 21))
                                .addComponent(jLabel4))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextLocalizacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextDescripcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextNombreSala, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Text_ID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(etiqueta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ButtonGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addComponent(ButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(ButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Text_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextNombreSala, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextLocalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonGuardar)
                    .addComponent(ButtonLimpiar)
                    .addComponent(ButtonSalir))
                .addGap(18, 18, 18)
                .addComponent(etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
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

    private void TextLocalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextLocalizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextLocalizacionActionPerformed

    private void ButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonGuardarActionPerformed
        guardarSala();
    }//GEN-LAST:event_ButtonGuardarActionPerformed

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

    private void TextLocalizacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextLocalizacionKeyTyped
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
    }//GEN-LAST:event_TextLocalizacionKeyTyped

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonGuardar;
    private javax.swing.JButton ButtonLimpiar;
    private javax.swing.JButton ButtonSalir;
    private javax.swing.JTextField TextDescripcion;
    private javax.swing.JTextField TextLocalizacion;
    private javax.swing.JTextField TextNombreSala;
    private javax.swing.JTextField Text_ID;
    private javax.swing.JLabel etiqueta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
