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

        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        panelMenuPrincipal = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        mMantenimientos = new javax.swing.JMenu();
        mItemUsuario = new javax.swing.JMenuItem();
        mItemEntrenador = new javax.swing.JMenuItem();
        mItemLocalizacion = new javax.swing.JMenuItem();
        mItemSalas = new javax.swing.JMenuItem();
        mItemActividades = new javax.swing.JMenuItem();
        mItemHorarios = new javax.swing.JMenuItem();
        mItemClientes = new javax.swing.JMenuItem();
        mItemEstado = new javax.swing.JMenuItem();
        mItemReserva = new javax.swing.JMenuItem();
        mItemReservaActividades = new javax.swing.JMenuItem();
        mMovimientos = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mProcesos = new javax.swing.JMenu();
        mConsulta = new javax.swing.JMenu();

        jMenuItem3.setText("jMenuItem3");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenuItem5.setText("jMenuItem5");

        jMenuItem10.setText("jMenuItem10");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelMenuPrincipal.setBackground(new java.awt.Color(51, 51, 51));
        panelMenuPrincipal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7), 3));
        panelMenuPrincipal.setForeground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout panelMenuPrincipalLayout = new javax.swing.GroupLayout(panelMenuPrincipal);
        panelMenuPrincipal.setLayout(panelMenuPrincipalLayout);
        panelMenuPrincipalLayout.setHorizontalGroup(
            panelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1168, Short.MAX_VALUE)
        );
        panelMenuPrincipalLayout.setVerticalGroup(
            panelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );

        menuBar.setBackground(new java.awt.Color(255, 193, 7));
        menuBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7), 2));

        mMantenimientos.setBackground(new java.awt.Color(255, 193, 7));
        mMantenimientos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7)));
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
        mItemEntrenador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemEntrenadorActionPerformed(evt);
            }
        });
        mMantenimientos.add(mItemEntrenador);

        mItemLocalizacion.setBackground(new java.awt.Color(255, 193, 7));
        mItemLocalizacion.setForeground(new java.awt.Color(0, 0, 0));
        mItemLocalizacion.setText("Localizacion");
        mItemLocalizacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7)));
        mItemLocalizacion.setPreferredSize(new java.awt.Dimension(123, 35));
        mItemLocalizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemLocalizacionActionPerformed(evt);
            }
        });
        mMantenimientos.add(mItemLocalizacion);

        mItemSalas.setBackground(new java.awt.Color(255, 193, 7));
        mItemSalas.setForeground(new java.awt.Color(0, 0, 0));
        mItemSalas.setText("Salas");
        mItemSalas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7)));
        mItemSalas.setPreferredSize(new java.awt.Dimension(123, 35));
        mItemSalas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemSalasActionPerformed(evt);
            }
        });
        mMantenimientos.add(mItemSalas);

        mItemActividades.setBackground(new java.awt.Color(255, 193, 7));
        mItemActividades.setForeground(new java.awt.Color(0, 0, 0));
        mItemActividades.setText("Actividades");
        mItemActividades.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7)));
        mItemActividades.setPreferredSize(new java.awt.Dimension(123, 35));
        mItemActividades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemActividadesActionPerformed(evt);
            }
        });
        mMantenimientos.add(mItemActividades);

        mItemHorarios.setBackground(new java.awt.Color(255, 193, 7));
        mItemHorarios.setForeground(new java.awt.Color(0, 0, 0));
        mItemHorarios.setText("Horarios Actividades");
        mItemHorarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7)));
        mItemHorarios.setPreferredSize(new java.awt.Dimension(169, 35));
        mItemHorarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemHorariosActionPerformed(evt);
            }
        });
        mMantenimientos.add(mItemHorarios);

        mItemClientes.setBackground(new java.awt.Color(255, 193, 7));
        mItemClientes.setForeground(new java.awt.Color(0, 0, 0));
        mItemClientes.setText("Clientes");
        mItemClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7)));
        mItemClientes.setPreferredSize(new java.awt.Dimension(167, 35));
        mItemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemClientesActionPerformed(evt);
            }
        });
        mMantenimientos.add(mItemClientes);

        mItemEstado.setBackground(new java.awt.Color(255, 193, 7));
        mItemEstado.setForeground(new java.awt.Color(0, 0, 0));
        mItemEstado.setText("Estado Reserva");
        mItemEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7)));
        mItemEstado.setPreferredSize(new java.awt.Dimension(167, 35));
        mItemEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemEstadoActionPerformed(evt);
            }
        });
        mMantenimientos.add(mItemEstado);

        mItemReserva.setBackground(new java.awt.Color(255, 193, 7));
        mItemReserva.setForeground(new java.awt.Color(0, 0, 0));
        mItemReserva.setText("Reservas");
        mItemReserva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7)));
        mItemReserva.setPreferredSize(new java.awt.Dimension(167, 35));
        mItemReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemReservaActionPerformed(evt);
            }
        });
        mMantenimientos.add(mItemReserva);

        mItemReservaActividades.setBackground(new java.awt.Color(255, 193, 7));
        mItemReservaActividades.setForeground(new java.awt.Color(0, 0, 0));
        mItemReservaActividades.setText("Reserva Actividades");
        mItemReservaActividades.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 193, 7), 1, true));
        mItemReservaActividades.setPreferredSize(new java.awt.Dimension(167, 35));
        mItemReservaActividades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemReservaActividadesActionPerformed(evt);
            }
        });
        mMantenimientos.add(mItemReservaActividades);

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
            .addComponent(panelMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //agregue la ventana de usuario
    private void mItemUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemUsuarioActionPerformed
        ManUsuario.getInstancia();
    }//GEN-LAST:event_mItemUsuarioActionPerformed

    private void mItemEntrenadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemEntrenadorActionPerformed
       ManEntrenador.getInstancia();
    }//GEN-LAST:event_mItemEntrenadorActionPerformed

    private void mItemReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemReservaActionPerformed
        ManReserva.getInstancia();
    }//GEN-LAST:event_mItemReservaActionPerformed

    private void mItemLocalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemLocalizacionActionPerformed
        ManLocalizacion.getInstancia();
    }//GEN-LAST:event_mItemLocalizacionActionPerformed

    private void mItemSalasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemSalasActionPerformed
        ManSalas.getInstancia();
    }//GEN-LAST:event_mItemSalasActionPerformed

    private void mItemActividadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemActividadesActionPerformed
        ManActividades.getInstancia();
    }//GEN-LAST:event_mItemActividadesActionPerformed

    private void mItemHorariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemHorariosActionPerformed
        ManHoraActi.getInstancia();
    }//GEN-LAST:event_mItemHorariosActionPerformed

    private void mItemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemClientesActionPerformed
        ManClientes.getInstancia();
    }//GEN-LAST:event_mItemClientesActionPerformed

    private void mItemEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemEstadoActionPerformed
        ManEstadoReserva.getInstancia();
    }//GEN-LAST:event_mItemEstadoActionPerformed

    private void mItemReservaActividadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemReservaActividadesActionPerformed
        ManReservaActividades.getInstancia();
    }//GEN-LAST:event_mItemReservaActividadesActionPerformed

    private void configurarMenu(int nivelAcceso) {
        mMantenimientos.setVisible(nivelAcceso == 0);
        mMovimientos.setVisible(nivelAcceso == 0);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenu mConsulta;
    private javax.swing.JMenuItem mItemActividades;
    private javax.swing.JMenuItem mItemClientes;
    private javax.swing.JMenuItem mItemEntrenador;
    private javax.swing.JMenuItem mItemEstado;
    private javax.swing.JMenuItem mItemHorarios;
    private javax.swing.JMenuItem mItemLocalizacion;
    private javax.swing.JMenuItem mItemReserva;
    private javax.swing.JMenuItem mItemReservaActividades;
    private javax.swing.JMenuItem mItemSalas;
    private javax.swing.JMenuItem mItemUsuario;
    private javax.swing.JMenu mMantenimientos;
    private javax.swing.JMenu mMovimientos;
    private javax.swing.JMenu mProcesos;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel panelMenuPrincipal;
    // End of variables declaration//GEN-END:variables
}
