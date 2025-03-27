package prototipogym.view;

public class MenuPrincipalFrame extends javax.swing.JFrame {
    public MenuPrincipalFrame(int nivelAcceso) {
        initComponents();
        setLocationRelativeTo(null);
        configurarMenu(nivelAcceso);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenuPrincipal = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        mMantenimientos = new javax.swing.JMenu();
        mItemUsuario = new javax.swing.JMenuItem();
        mItemEntrenador = new javax.swing.JMenuItem();
        mMovimientos = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mProcesos = new javax.swing.JMenu();
        mConsulta = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMenuPrincipal.setBackground(new java.awt.Color(0, 102, 102));
        panelMenuPrincipal.setForeground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout panelMenuPrincipalLayout = new javax.swing.GroupLayout(panelMenuPrincipal);
        panelMenuPrincipal.setLayout(panelMenuPrincipalLayout);
        panelMenuPrincipalLayout.setHorizontalGroup(
            panelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        panelMenuPrincipalLayout.setVerticalGroup(
            panelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
        );

        mMantenimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/maintenance256_24835.png"))); // NOI18N
        mMantenimientos.setText("Mantenimientos");

        mItemUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/businessapplication_edit_male_user_thepencil_theclient_negocio_2321.png"))); // NOI18N
        mItemUsuario.setText("Usuarios");
        mItemUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemUsuarioActionPerformed(evt);
            }
        });
        mMantenimientos.add(mItemUsuario);

        mItemEntrenador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/trainer_man_people_avatar_person_icon_224850.png"))); // NOI18N
        mItemEntrenador.setText("Entrenador");
        mMantenimientos.add(mItemEntrenador);

        menuBar.add(mMantenimientos);

        mMovimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/analytics_chart_bars_graphic_finance_business_money_coin_icon_150732 (1).png"))); // NOI18N
        mMovimientos.setText("Movimientos");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/business-color_handout_icon-icons.com_53455.png"))); // NOI18N
        jMenuItem1.setText("Cobros");
        mMovimientos.add(jMenuItem1);

        menuBar.add(mMovimientos);

        mProcesos.setText("Procesos");
        menuBar.add(mProcesos);

        mConsulta.setText("Consultas");
        menuBar.add(mConsulta);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenuPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //agregue la ventana de usuario
    private void mItemUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemUsuarioActionPerformed
        ManUsuario usuario = new ManUsuario();
        usuario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mItemUsuarioActionPerformed

    private void configurarMenu(int nivelAcceso) {
        mMantenimientos.setVisible(nivelAcceso == 0);
        mMovimientos.setVisible(nivelAcceso == 0);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu mConsulta;
    private javax.swing.JMenuItem mItemEntrenador;
    private javax.swing.JMenuItem mItemUsuario;
    private javax.swing.JMenu mMantenimientos;
    private javax.swing.JMenu mMovimientos;
    private javax.swing.JMenu mProcesos;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel panelMenuPrincipal;
    // End of variables declaration//GEN-END:variables
}
