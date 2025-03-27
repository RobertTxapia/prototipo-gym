package prototipogym.view;
import java.awt.HeadlessException;
import java.io.IOException;
import javax.swing.JOptionPane;
import prototipogym.util.auth.AuthManager;
//import prototipogym.util.FileManager;

public class LoginFrame extends javax.swing.JFrame {
    public LoginFrame() {
        initComponents();
        setLocationRelativeTo(null);
        rootPane.setDefaultButton(btnIngresar);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLogin = new javax.swing.JPanel();
        labelUserBig = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        lableIconPassword = new javax.swing.JLabel();
        labelIconUser = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setSize(new java.awt.Dimension(500, 500));

        panelLogin.setBackground(new java.awt.Color(0, 204, 255));

        labelUserBig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/avatardefault_92824.png"))); // NOI18N

        txtUsuario.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtUsuario.setToolTipText("Ingresa el Usuario");
        txtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });

        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/login_icon_137429.png"))); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.setToolTipText("Ingresar");
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresar.setMaximumSize(new java.awt.Dimension(130, 130));
        btnIngresar.setMinimumSize(new java.awt.Dimension(130, 130));
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        btnIngresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnIngresarKeyPressed(evt);
            }
        });

        lableIconPassword.setBackground(new java.awt.Color(0, 255, 204));
        lableIconPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/password.png"))); // NOI18N

        labelIconUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/user_icon_150670.png"))); // NOI18N

        txtPassword.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtPassword.setToolTipText("Ingresa la Contraseña");
        txtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/out4.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.setToolTipText("Salir");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setText("Login");

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lableIconPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLoginLayout.createSequentialGroup()
                                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addComponent(labelIconUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelUserBig)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addGap(175, 175, 175))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(labelUserBig)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelIconUser))
                        .addGap(28, 28, 28)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lableIconPassword))
                .addGap(57, 57, 57)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        
        if(usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos oblogatorios");
            return;
        }
        
        //agregue este try catch
        try{
            
            boolean credencialesValidas = AuthManager.validarUsuario(usuario, password);
            int nivelAcceso = AuthManager.getNivelAcceso(usuario, password);
            
            if (credencialesValidas) {
                
                JOptionPane.showMessageDialog(this, "Bienvenido, " + usuario, "Ingreso exitoso", JOptionPane.INFORMATION_MESSAGE);
                MenuPrincipalFrame menu = new MenuPrincipalFrame(nivelAcceso);
                   
                if (nivelAcceso == 0) {
                    menu.setVisible(true);
                } else {
                    menu.setVisible(true); // Si el usuario no es admin, igual se muestra
                }

                this.dispose(); // Cierra la ventana actual
            }else {
                JOptionPane.showMessageDialog(this, "Nombre o contraseña incorrectas");
            }   
            
        }catch (IOException e) { //si no usaba el throws IOExpection en el authManager me daba error
            JOptionPane.showMessageDialog(this, "Error al validar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnIngresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIngresarKeyPressed
<<<<<<< HEAD
//        
//        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
//            
//            String usuario = txtUsuario.getText().trim();
//            String password = new String(txtPassword.getPassword()).trim();
//        
//            if (!usuario.isEmpty() && !password.isEmpty()) {
//                 btnIngresar.doClick(); // Solo se ejecuta si ambos campos tienen contenido
//            }
//        }
=======
        
        /*if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            
            String usuario = txtUsuario.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();
        
            if (!usuario.isEmpty() && !password.isEmpty()) {
                 btnIngresar.doClick(); // Solo se ejecuta si ambos campos tienen contenido
            }
        }*/
>>>>>>> 629074fbab4f3e3a0a97bcc54a2c08d063b3752a
    }//GEN-LAST:event_btnIngresarKeyPressed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
//       
//        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
//            
//           txtPassword.requestFocus();
//           evt.consume(); //esto es para que el enter no siga de lalgo
//           
//        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelIconUser;
    private javax.swing.JLabel labelUserBig;
    private javax.swing.JLabel lableIconPassword;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}