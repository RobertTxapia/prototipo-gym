package prototipogym.view;

public class MenuPrincipalFrame extends javax.swing.JFrame {
    public MenuPrincipalFrame(int nivelAcceso) {
        initComponents();
        setLocationRelativeTo(null);
        configurarMenu(nivelAcceso);
        addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
            new LoginFrame().setVisible(true);
        }
        });
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelMenuPrincipal.setBackground(new java.awt.Color(51, 51, 51));
        panelMenuPrincipal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7), 3));
        panelMenuPrincipal.setForeground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout panelMenuPrincipalLayout = new javax.swing.GroupLayout(panelMenuPrincipal);
        panelMenuPrincipal.setLayout(panelMenuPrincipalLayout);
        panelMenuPrincipalLayout.setHorizontalGroup(
            panelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1102, Short.MAX_VALUE)
        );
        panelMenuPrincipalLayout.setVerticalGroup(
            panelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );

        menuBar.setBackground(new java.awt.Color(255, 193, 7));
        menuBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7), 2));

        mMantenimientos.setBackground(new java.awt.Color(255, 193, 7));
        mMantenimientos.setForeground(new java.awt.Color(255, 255, 255));
        mMantenimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/maintenance256_24835.png"))); // NOI18N
        mMantenimientos.setText("Mantenimientos");

        mItemUsuario.setBackground(new java.awt.Color(255, 193, 7));
        mItemUsuario.setForeground(new java.awt.Color(0, 0, 0));
        mItemUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/businessapplication_edit_male_user_thepencil_theclient_negocio_2321.png"))); // NOI18N
        mItemUsuario.setText("Usuarios");
        mItemUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7)));
        mItemUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemUsuarioActionPerformed(evt);
            }
        });
        mMantenimientos.add(mItemUsuario);

        mItemEntrenador.setBackground(new java.awt.Color(255, 193, 7));
        mItemEntrenador.setForeground(new java.awt.Color(0, 0, 0));
        mItemEntrenador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/trainer_man_people_avatar_person_icon_224850.png"))); // NOI18N
        mItemEntrenador.setText("Entrenador");
        mItemEntrenador.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7)));
        mMantenimientos.add(mItemEntrenador);

        menuBar.add(mMantenimientos);

        mMovimientos.setBackground(new java.awt.Color(255, 193, 7));
        mMovimientos.setForeground(new java.awt.Color(255, 255, 255));
        mMovimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/analytics_chart_bars_graphic_finance_business_money_coin_icon_150732 (1).png"))); // NOI18N
        mMovimientos.setText("Movimientos");

        jMenuItem1.setBackground(new java.awt.Color(255, 193, 7));
        jMenuItem1.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/business-color_handout_icon-icons.com_53455.png"))); // NOI18N
        jMenuItem1.setText("Cobros");
        mMovimientos.add(jMenuItem1);

        menuBar.add(mMovimientos);

        mProcesos.setBackground(new java.awt.Color(255, 193, 7));
        mProcesos.setForeground(new java.awt.Color(255, 255, 255));
        mProcesos.setText("Procesos");
        menuBar.add(mProcesos);

        mConsulta.setBackground(new java.awt.Color(255, 193, 7));
        mConsulta.setForeground(new java.awt.Color(255, 255, 255));
        mConsulta.setText("Consultas");
        menuBar.add(mConsulta);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenuPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //agregue la ventana de usuario
    private void mItemUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemUsuarioActionPerformed
        ManUsuario.getInstancia();
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
