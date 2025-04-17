package prototipogym.view;
import prototipogym.view.consultas.ConActividades;
import prototipogym.view.consultas.ConCliente;
import prototipogym.view.consultas.ConCobroRangoFecha;
import prototipogym.view.consultas.ConCuotas;
import prototipogym.view.consultas.ConEntrenador;
import prototipogym.view.mantenimientos.ManActividades;
import prototipogym.view.consultas.ConHorariosActividades;
import prototipogym.view.consultas.ConLocalizacion;
import prototipogym.view.consultas.ConSalas;
import prototipogym.view.consultas.ConUsuario;
import prototipogym.view.mantenimientos.ActualizarCobro;
import prototipogym.view.mantenimientos.Cobros;
import prototipogym.view.mantenimientos.Cuotas;
import prototipogym.view.mantenimientos.ManClientes;
import prototipogym.view.mantenimientos.ManEntrenador;
import prototipogym.view.mantenimientos.ManEstadoReserva;
import prototipogym.view.mantenimientos.ManHoraActi;
import prototipogym.view.mantenimientos.ManLocalizacion;
import prototipogym.view.mantenimientos.ManReserva;
import prototipogym.view.mantenimientos.ManReservaActividades;
import prototipogym.view.mantenimientos.ManSalas;
import prototipogym.view.mantenimientos.ManUsuario;

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
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
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
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        mConsulta = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();

        jMenuItem3.setText("jMenuItem3");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenuItem5.setText("jMenuItem5");

        jMenuItem10.setText("jMenuItem10");

        jMenuItem15.setText("jMenuItem15");

        jMenuItem16.setText("jMenuItem16");

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
            .addGap(0, 536, Short.MAX_VALUE)
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
        mItemLocalizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/find_search_locate_6201.png"))); // NOI18N
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
        mItemSalas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/living_room_sofa_home_house_icon_231050.png"))); // NOI18N
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
        mItemActividades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/foldergreenactivities_92929.png"))); // NOI18N
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
        mItemHorarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/calendar_clock_schedule_icon-icons.com_51085.png"))); // NOI18N
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
        mItemClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/customer_person_people_man_user_client_1629.png"))); // NOI18N
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
        mItemEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/backup_29169.png"))); // NOI18N
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
        mItemReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/calendar_clock_schedule_icon-icons.com_51085.png"))); // NOI18N
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
        mItemReservaActividades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/calendar_clock_schedule_icon-icons.com_51085.png"))); // NOI18N
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
        jMenuItem1.setText("Cuotas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mMovimientos.add(jMenuItem1);

        menuBar.add(mMovimientos);

        mProcesos.setBackground(new java.awt.Color(255, 193, 7));
        mProcesos.setForeground(new java.awt.Color(255, 255, 255));
        mProcesos.setText("Procesos");

        jMenuItem12.setBackground(new java.awt.Color(255, 193, 7));
        jMenuItem12.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem12.setText("Cobros");
        jMenuItem12.setPreferredSize(new java.awt.Dimension(115, 35));
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        mProcesos.add(jMenuItem12);

        jMenuItem13.setBackground(new java.awt.Color(255, 193, 7));
        jMenuItem13.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem13.setText("Actualizar");
        jMenuItem13.setPreferredSize(new java.awt.Dimension(115, 35));
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        mProcesos.add(jMenuItem13);

        menuBar.add(mProcesos);

        mConsulta.setBackground(new java.awt.Color(255, 193, 7));
        mConsulta.setForeground(new java.awt.Color(255, 255, 255));
        mConsulta.setText("Consultas");

        jMenuItem2.setBackground(new java.awt.Color(255, 193, 7));
        jMenuItem2.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem2.setText("Usuario");
        jMenuItem2.setPreferredSize(new java.awt.Dimension(125, 35));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mConsulta.add(jMenuItem2);

        jMenuItem4.setBackground(new java.awt.Color(255, 193, 7));
        jMenuItem4.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem4.setText("Entrenador");
        jMenuItem4.setPreferredSize(new java.awt.Dimension(125, 35));
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        mConsulta.add(jMenuItem4);

        jMenuItem11.setBackground(new java.awt.Color(255, 193, 7));
        jMenuItem11.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem11.setText("Localizacion");
        jMenuItem11.setPreferredSize(new java.awt.Dimension(125, 35));
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        mConsulta.add(jMenuItem11);

        jMenuItem6.setBackground(new java.awt.Color(255, 193, 7));
        jMenuItem6.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem6.setText("Salas");
        jMenuItem6.setPreferredSize(new java.awt.Dimension(125, 35));
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        mConsulta.add(jMenuItem6);

        jMenuItem7.setBackground(new java.awt.Color(255, 193, 7));
        jMenuItem7.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem7.setText("Actividades");
        jMenuItem7.setPreferredSize(new java.awt.Dimension(125, 35));
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        mConsulta.add(jMenuItem7);

        jMenuItem8.setBackground(new java.awt.Color(255, 193, 7));
        jMenuItem8.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem8.setText("Horarios Actividades");
        jMenuItem8.setPreferredSize(new java.awt.Dimension(125, 35));
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        mConsulta.add(jMenuItem8);

        jMenuItem9.setBackground(new java.awt.Color(255, 193, 7));
        jMenuItem9.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem9.setText("Cliente");
        jMenuItem9.setPreferredSize(new java.awt.Dimension(125, 35));
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        mConsulta.add(jMenuItem9);

        jMenuItem14.setBackground(new java.awt.Color(255, 193, 7));
        jMenuItem14.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem14.setText("Cuotas");
        jMenuItem14.setPreferredSize(new java.awt.Dimension(125, 35));
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        mConsulta.add(jMenuItem14);

        jMenuItem17.setBackground(new java.awt.Color(255, 193, 7));
        jMenuItem17.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem17.setText("Cobros");
        jMenuItem17.setPreferredSize(new java.awt.Dimension(125, 35));
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        mConsulta.add(jMenuItem17);

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

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        ConUsuario.getInstancia();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
       ConEntrenador.getInstancia();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        ConLocalizacion.getInstancia();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
      ConSalas.getInstancia();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        ConActividades.getInstancia();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        ConHorariosActividades.getInstancia();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        ConCliente.getInstancia();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Cuotas.getInstancia();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
       ActualizarCobro.getInstancia();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        ConCuotas.getInstancia();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
       Cobros.getInstancia();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        ConCobroRangoFecha.getInstancia();
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void configurarMenu(int nivelAcceso) {
        boolean esAdmin = (nivelAcceso == 0);
        mMantenimientos.setVisible(true);
        mItemUsuario.setEnabled(esAdmin);
        mMovimientos.setVisible(esAdmin);
    }
    
   
    

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
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
