package prototipogym.view.mantenimientos;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;
import prototipogym.controller.UsuarioController;
import prototipogym.model.Usuario;

public class ManUsuario extends javax.swing.JFrame {
    
    private static ManUsuario instancia;
    private static String antiguaLinea=""; 
   
    public ManUsuario() {
        initComponents();
        setLocationRelativeTo(null);
        //al presionar enter llama a buscarUsuario en la caja de user y password 
        KeyAdapter enterListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buscarUsuario();
                }
            }
        };
        TextUsuario.addKeyListener(enterListener);
        txtPassword.addKeyListener(enterListener);

        addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            instancia = null;
            dispose(); 
        }
    });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TextUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ButtonGuardar = new javax.swing.JButton();
        ButtonLimpiar = new javax.swing.JButton();
        ButtonSalir = new javax.swing.JButton();
        TextNombre = new javax.swing.JTextField();
        TextApellido = new javax.swing.JTextField();
        TextCorreo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ButtonAdmin = new javax.swing.JRadioButton();
        ButtonUsuario = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        txtPassword = new javax.swing.JPasswordField();
        etiqueta = new javax.swing.JLabel();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 193, 7), 3, true));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mantenimiento de Usuario");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/businessapplication_edit_male_user_thepencil_theclient_negocio_2321.png"))); // NOI18N

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usuario");

        TextUsuario.setBackground(new java.awt.Color(200, 200, 200));
        TextUsuario.setToolTipText("Usuario");
        TextUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextUsuarioActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contraseña");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Apellidos");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Correo");

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

        TextNombre.setBackground(new java.awt.Color(200, 200, 200));
        TextNombre.setToolTipText("Nombre");
        TextNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextNombreActionPerformed(evt);
            }
        });
        TextNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextNombreKeyTyped(evt);
            }
        });

        TextApellido.setBackground(new java.awt.Color(200, 200, 200));
        TextApellido.setToolTipText("Apellidos");
        TextApellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextApellidoActionPerformed(evt);
            }
        });
        TextApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextApellidoKeyTyped(evt);
            }
        });

        TextCorreo.setBackground(new java.awt.Color(200, 200, 200));
        TextCorreo.setToolTipText("Correo");
        TextCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextCorreoActionPerformed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tipo");

        ButtonAdmin.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup1.add(ButtonAdmin);
        ButtonAdmin.setForeground(new java.awt.Color(255, 255, 255));
        ButtonAdmin.setText("Administrador");

        ButtonUsuario.setBackground(new java.awt.Color(51, 51, 51));
        buttonGroup1.add(ButtonUsuario);
        ButtonUsuario.setForeground(new java.awt.Color(255, 255, 255));
        ButtonUsuario.setText("Usuario");

        jSeparator1.setBackground(new java.awt.Color(250, 250, 250));

        txtPassword.setBackground(new java.awt.Color(200, 200, 200));
        txtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        etiqueta.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        etiqueta.setForeground(new java.awt.Color(255, 255, 255));

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
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(TextUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TextApellido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TextCorreo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(TextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(ButtonAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(ButtonUsuario)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(ButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 36, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(ButtonAdmin)
                    .addComponent(ButtonUsuario))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonGuardar)
                    .addComponent(ButtonLimpiar)
                    .addComponent(ButtonSalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    
    
    // Para que no se cierren automaticamente las ventanas
    public static ManUsuario getInstancia(){
        if (instancia == null){
            instancia = new ManUsuario();
            getInstancia().setVisible(true);
        }
        return instancia;
    }
    
    
    //Funcion para Limpiar las cajas de texto
    public void Limpiar (){
        TextUsuario.setText("");
        txtPassword.setText("");
        TextNombre.setText("");
        TextApellido.setText("");
        TextCorreo.setText("");
        buttonGroup1.clearSelection();
        etiqueta.setText("");
    }
    
    private void buscarUsuario() {
        boolean encontrado = false;
        Scanner s = null;
        String user = TextUsuario.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();

        try {
            File f = new File("data/usuarios.txt"); 
            s = new Scanner(f);
            while (s.hasNextLine() && !encontrado) {
                String linea = s.nextLine().trim();

                Scanner sl = new Scanner(linea);
                sl.useDelimiter("\\s*;\\s*");

                try {
                    if (sl.hasNext()) { 
                        String usuarioArchivo = sl.next().trim();
                        if (sl.hasNext()) {
                            String clave = sl.next().trim(); 

                            if (user.equalsIgnoreCase(usuarioArchivo) && pass.equals(clave)) { 
                                encontrado = true;
                                etiqueta.setText("Modificando");
                                String tipo = sl.hasNext() ? sl.next().trim() : "";
                                String nombre = sl.hasNext() ? sl.next().trim() : "";
                                String apellido = sl.hasNext() ? sl.next().trim() : "";
                                String correo = sl.hasNext() ? sl.next().trim() : "";

                                TextNombre.setText(nombre);
                                TextApellido.setText(apellido);
                                TextCorreo.setText(correo);
                                txtPassword.setText(clave);  

                                if (tipo.equals("0")) {
                                    ButtonAdmin.setSelected(true);
                                } else {
                                    ButtonUsuario.setSelected(true);
                                }

                                antiguaLinea = usuarioArchivo + ";" + clave + ";" + tipo + ";" + nombre + ";" + apellido + ";" + correo;
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error al leer linea: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (s != null) {
                s.close();
            }
        }
            
        if (!encontrado) {
            etiqueta.setText("Creando");
        }
}

    
    private void TextUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextUsuarioActionPerformed

    private void ButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSalirActionPerformed
       instancia = null;
       dispose(); 
    }//GEN-LAST:event_ButtonSalirActionPerformed

    private void TextNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextNombreActionPerformed

    private void TextApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextApellidoActionPerformed

    private void TextCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextCorreoActionPerformed

    private void ButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonGuardarActionPerformed
        String user = TextUsuario.getText();
        String password = new String(txtPassword.getPassword()).trim();
        String nombre = TextNombre.getText();
        String apellidos = TextApellido.getText();
        String correo = TextCorreo.getText();
        int nivelAcceso = (ButtonAdmin.isSelected()) ? 0 : 1;
        
        //campos obligatorios
        if (user.isEmpty() || password.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() ){
            JOptionPane.showMessageDialog(this, "Campos oblogatorios");
            return;
        }
        
        UsuarioController uc = new UsuarioController();
        Usuario usuario = new Usuario(user, password,nivelAcceso,nombre,apellidos,correo);
        
        
        if(UsuarioController.guardarUsuario(usuario)) {
            JOptionPane.showMessageDialog(this, "Usuario guardado");
        }else {
            String Snuevalinea = (user + ";" + password + ";" + nivelAcceso + ";" + nombre + ";" + apellidos + ";" + correo);
            uc.ModificaDatos(antiguaLinea, Snuevalinea);
        }
        
       Limpiar();
    }//GEN-LAST:event_ButtonGuardarActionPerformed

    private void ButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_ButtonLimpiarActionPerformed
    
    //no permite escribir numeros en la caja de nombres
    private void TextNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextNombreKeyTyped
        
        
    }//GEN-LAST:event_TextNombreKeyTyped
    
    //no permite escribir numeros en la caja de apellidos
    private void TextApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextApellidoKeyTyped
       
    }//GEN-LAST:event_TextApellidoKeyTyped

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ButtonAdmin;
    private javax.swing.JButton ButtonGuardar;
    private javax.swing.JButton ButtonLimpiar;
    private javax.swing.JButton ButtonSalir;
    private javax.swing.JRadioButton ButtonUsuario;
    private javax.swing.JTextField TextApellido;
    private javax.swing.JTextField TextCorreo;
    private javax.swing.JTextField TextNombre;
    private javax.swing.JTextField TextUsuario;
    private javax.swing.ButtonGroup buttonGroup1;
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
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables

}