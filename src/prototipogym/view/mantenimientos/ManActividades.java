
package prototipogym.view.mantenimientos;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import prototipogym.controller.ActividadController;
import prototipogym.model.Actividad;
import javax.swing.*;

import java.io.IOException;
import java.util.Scanner;

public class ManActividades extends javax.swing.JFrame {
    private static String antiguaLinea="";
    private static ManActividades instanciass;
    public ManActividades() {
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
    
    public static ManActividades getInstancia(){
        if (instanciass == null){
            instanciass = new ManActividades();
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
            File f = new File("data/actividades.txt"); 
            s = new Scanner(f);
             while (s.hasNextLine() && !encontrado){
                String linea = s.nextLine().trim();
                Scanner sl = new Scanner(linea);
                sl.useDelimiter("\\s*;\\s*");
                try{
                    
                    if (cod==Integer.parseInt(sl.next())){
                         encontrado = true;
                        etiqueta.setText("Modificando");
                        
                        String nombre = sl.hasNext() ? sl.next().trim() : "";
                        String descripcion= sl.hasNext() ? sl.next().trim() : "";
                        String id_localizacion = sl.hasNext() ? sl.next().trim() : "";
                        String id_entrenador = sl.hasNext() ? sl.next().trim() : "";
                        
                        TextNombre.setText(nombre);
                        TextDescripcion.setText(descripcion);
                        TextLocalizacion.setText(id_localizacion);
                        TextEntrenador.setText(id_entrenador);
                        
                        
                        antiguaLinea = cod + ";" + nombre + ";" + descripcion + ";" + id_localizacion + ";" + id_entrenador;
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
    
    public void Limpiar(){
        Text_ID.setText("");
        TextNombre.setText("");
        TextDescripcion.setText("");
        TextLocalizacion.setText("");
        TextEntrenador.setText("");
        etiqueta.setText("");
    }



    private void guardarActividad() {
        try {
            int id = Integer.parseInt(Text_ID.getText());
            String nombre = TextNombre.getText().trim();
            String descripcion = TextDescripcion.getText().trim();
            int idLocalizacion = Integer.parseInt(TextLocalizacion.getText());
            int idEntrenador = Integer.parseInt(TextEntrenador.getText());

            if (nombre.isEmpty() || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre y Descripción son obligatorios");
                return;
            }

            if (!ActividadController.validarRelaciones(idLocalizacion, idEntrenador)) {
                JOptionPane.showMessageDialog(this, "Localización o Entrenador no existen");
                return;
            }

            Actividad actividad = new Actividad(id, nombre, descripcion, idLocalizacion, idEntrenador);
            ActividadController.guardarActividad(actividad);
            JOptionPane.showMessageDialog(this, "Actividad guardada!");
            Limpiar();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "IDs deben ser numéricos");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
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
        TextNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TextDescripcion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TextLocalizacion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TextEntrenador = new javax.swing.JTextField();
        ButtonGuardar = new javax.swing.JButton();
        ButtonSalir = new javax.swing.JButton();
        ButtonLimpiar = new javax.swing.JButton();
        etiqueta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7), 3));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mantenimiento Actividades ");

        jSeparator1.setForeground(new java.awt.Color(250, 250, 250));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID");

        Text_ID.setBackground(new java.awt.Color(200, 200, 200));
        Text_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Text_ID.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Text_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_IDActionPerformed(evt);
            }
        });
        Text_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Text_IDKeyTyped(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre");

        TextNombre.setBackground(new java.awt.Color(200, 200, 200));
        TextNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextNombreKeyTyped(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Descripcion");

        TextDescripcion.setBackground(new java.awt.Color(200, 200, 200));
        TextDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ID Localizacion");

        TextLocalizacion.setBackground(new java.awt.Color(200, 200, 200));
        TextLocalizacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextLocalizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextLocalizacionKeyTyped(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID Entrenador");

        TextEntrenador.setBackground(new java.awt.Color(200, 200, 200));
        TextEntrenador.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextEntrenador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextEntrenadorKeyTyped(evt);
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

        ButtonSalir.setBackground(new java.awt.Color(255, 193, 7));
        ButtonSalir.setText("Volver");
        ButtonSalir.setBorderPainted(false);
        ButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSalirActionPerformed(evt);
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

        etiqueta.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etiqueta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(ButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(TextDescripcion)
                                .addComponent(Text_ID)
                                .addComponent(TextNombre)
                                .addComponent(TextLocalizacion)
                                .addComponent(TextEntrenador, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(ButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(ButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Text_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextLocalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonGuardar)
                    .addComponent(ButtonSalir)
                    .addComponent(ButtonLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etiqueta, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
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

    private void Text_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_IDActionPerformed

    private void ButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSalirActionPerformed
       instanciass = null;
       dispose();
    }//GEN-LAST:event_ButtonSalirActionPerformed

    private void ButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_ButtonLimpiarActionPerformed

    private void ButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonGuardarActionPerformed
        guardarActividad();
    }//GEN-LAST:event_ButtonGuardarActionPerformed

    private void Text_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Text_IDKeyTyped
        
    }//GEN-LAST:event_Text_IDKeyTyped

    private void TextNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextNombreKeyTyped
       
    }//GEN-LAST:event_TextNombreKeyTyped

    private void TextLocalizacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextLocalizacionKeyTyped
        
    }//GEN-LAST:event_TextLocalizacionKeyTyped

    private void TextEntrenadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextEntrenadorKeyTyped
      
    }//GEN-LAST:event_TextEntrenadorKeyTyped

    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonGuardar;
    private javax.swing.JButton ButtonLimpiar;
    private javax.swing.JButton ButtonSalir;
    private javax.swing.JTextField TextDescripcion;
    private javax.swing.JTextField TextEntrenador;
    private javax.swing.JTextField TextLocalizacion;
    private javax.swing.JTextField TextNombre;
    private javax.swing.JTextField Text_ID;
    private javax.swing.JLabel etiqueta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
