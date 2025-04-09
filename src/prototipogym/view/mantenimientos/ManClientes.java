package prototipogym.view.mantenimientos;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import prototipogym.controller.*;
import prototipogym.model.Cliente;
import java.text.SimpleDateFormat;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;


public class ManClientes extends javax.swing.JFrame {
   public static final SimpleDateFormat FORMATO_FECHA= new SimpleDateFormat("dd/MM/yyyy");//sirve para la feccha
    public static SimpleDateFormat clockFormat=new SimpleDateFormat("h:mm a");
    private static String antiguaLinea=""; 
    private static ManClientes instanciass;
    public ManClientes() {
        initComponents();
        Chooser.getDateEditor().addPropertyChangeListener("date", evt -> {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (Chooser.getDate() != null) {
            String fecha = sdf.format(Chooser.getDate());
            TextFechaNac.setText(fecha);
            
        }
        });
        
        TipoCliente.setSelectedIndex(-1);
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
    
    public static ManClientes getInstancia(){
        if (instanciass == null){
            instanciass = new ManClientes();
            getInstancia().setVisible(true);
        }
        return instanciass;
    }
    
    private void guardarCliente() {
    try {
        String id = Text_ID.getText().trim();
        String nombre = TextNombre.getText().trim();
        String papellido = TextPapellido.getText().trim();
        String fechaNac = TextFechaNac.getText().trim();

        // Validar campos obligatorios
        if (id.isEmpty() || nombre.isEmpty() || papellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos obligatorios faltantes!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean status = CheckBox.isSelected();
        // Crear objeto Cliente
        Cliente cliente = new Cliente(
                id,
                nombre,
                papellido,
                TextSapellido.getText().trim(),
                TextDireccion.getText().trim(),
                fechaNac,
                TextTelefono.getText().trim(),
                TextCelular.getText().trim(),
                TextFechaIngreso.getText().trim(),
                status, // Usando JCheckBox
                TipoCliente.getSelectedIndex(),
                TextCorreo.getText().trim(),
                Double.parseDouble(TextBalance.getText().trim()),
                Double.parseDouble(TextCuota.getText().trim())
        );

        ClienteController cc = new ClienteController();
        String nuevaLinea = id + ";" + nombre + ";" + papellido + ";" + TextSapellido.getText().trim() + ";" +
                TextDireccion.getText().trim() + ";" + fechaNac + ";" + TextTelefono.getText().trim() + ";" +
                TextCelular.getText().trim() + ";" + TextFechaIngreso.getText().trim() + ";" +
                (CheckBox.isSelected() ? "true" : "false") + ";" + TipoCliente.getSelectedIndex() + ";" +
                TextCorreo.getText().trim() + ";" + TextBalance.getText().trim() + ";" + TextCuota.getText().trim();

        if (ClienteController.existeCliente(id)) {
            // Modificar si ya existe
            if (antiguaLinea == null || antiguaLinea.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se puede modificar: línea antigua no definida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            cc.ModificaDatos(antiguaLinea, nuevaLinea);
            JOptionPane.showMessageDialog(this, "Cliente modificado correctamente.", "Modificacion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Guardar si no existe
            if (ClienteController.guardarCliente(cliente)) {
                JOptionPane.showMessageDialog(this, "Cliente guardado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        Limpiar();

    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Balance y Valor Cuota deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al guardar o modificar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void buscarUsuario(){
        boolean encontrado = false;
        Scanner s = null;
        int cod;
        
        cod = Integer.parseInt(Text_ID.getText());
        
        try{
            File f = new File("data/Clientes.txt"); 
            s = new Scanner(f);
             while (s.hasNextLine() && !encontrado){
                String linea = s.nextLine().trim();
                Scanner sl = new Scanner(linea);
                sl.useDelimiter("\\s*;\\s*");
                try{
                    
                    if (cod == Integer.parseInt(sl.next())) {
                        encontrado = true;
                    etiqueta.setText("Modificando");

                    String nombre = sl.hasNext() ? sl.next().trim() : "";
                    String Papellido = sl.hasNext() ? sl.next().trim() : "";
                    String Sapellido = sl.hasNext() ? sl.next().trim() : "";
                    String Direccion = sl.hasNext() ? sl.next().trim() : "";
                    String FechaNa = sl.hasNext() ? sl.next().trim() : "";
                    String Telefono = sl.hasNext() ? sl.next().trim() : "";
                    String Celular = sl.hasNext() ? sl.next().trim() : "";
                    String FechaIn = sl.hasNext() ? sl.next().trim() : "";
                    boolean estado = sl.hasNext() ? Boolean.parseBoolean(sl.next().trim()) : false;
                    int tipoClienteIndex = sl.hasNext() ? Integer.parseInt(sl.next().trim()) : 0;
                    String Correo = sl.hasNext() ? sl.next().trim() : "";
                    String Balance = sl.hasNext() ? sl.next().trim() : "";
                    String Valor = sl.hasNext() ? sl.next().trim() : "";
                    String FechaNac = sl.hasNext() ? sl.next().trim() : "";
                    Date fecha = FORMATO_FECHA.parse(FechaNa);
                    
                    Chooser.setDate(fecha);
                    TextNombre.setText(nombre);
                    TextPapellido.setText(Papellido);
                    TextSapellido.setText(Sapellido);
                    TextDireccion.setText(Direccion);
                    TextFechaNac.setText(FechaNa);
                    TextTelefono.setText(Telefono);
                    TextCelular.setText(Celular);
                    TextFechaIngreso.setText(FechaIn);
                    CheckBox.setSelected(estado);
                    TipoCliente.setSelectedIndex(tipoClienteIndex);
                    TextCorreo.setText(Correo);
                    TextBalance.setText(Balance);
                    TextCuota.setText(Valor);

                    // Reconstruir antiguaLinea para poder modificar
                    antiguaLinea = cod + ";" + nombre + ";" + Papellido + ";" + Sapellido + ";" + Direccion
                            + ";" + FechaNa + ";" + Telefono + ";" + Celular + ";" + FechaIn + ";"
                            + (estado ? "true" : "false") + ";" + tipoClienteIndex + ";"
                            + Correo + ";" + Balance + ";" + Valor;
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
            TextFechaIngreso.setText(FORMATO_FECHA.format(new Date())); //Arreglar fecha si exite el cliente
        }
    }

    
    public void Limpiar() {
        Text_ID.setText("");
        TextNombre.setText("");
        TextPapellido.setText("");
        TextSapellido.setText("");
        TextDireccion.setText("");
        TextFechaNac.setText("");
        TextTelefono.setText("");
        TextCelular.setText("");
        TextFechaIngreso.setText("");
        CheckBox.setSelected(false);
        Chooser.setDate(null);
        TipoCliente.setSelectedIndex(-1);
        TextCorreo.setText("");
        TextBalance.setText("");
        TextCuota.setText("");
        etiqueta.setText("");
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Text_ID = new javax.swing.JTextField();
        TextNombre = new javax.swing.JTextField();
        TextPapellido = new javax.swing.JTextField();
        TextSapellido = new javax.swing.JTextField();
        TextDireccion = new javax.swing.JTextField();
        TextFechaNac = new javax.swing.JTextField();
        TextTelefono = new javax.swing.JTextField();
        TextCelular = new javax.swing.JTextField();
        TextFechaIngreso = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        TipoCliente = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TextCorreo = new javax.swing.JTextField();
        TextBalance = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        TextCuota = new javax.swing.JTextField();
        ButtonGuardar = new javax.swing.JButton();
        ButtonLimpiar = new javax.swing.JButton();
        ButtonVolver = new javax.swing.JButton();
        etiqueta = new javax.swing.JLabel();
        CheckBox = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();
        Chooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 193, 7), 3));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mantenimiento Clientes");

        jSeparator1.setBackground(new java.awt.Color(250, 250, 250));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Primer Apellido");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Segundo Apellido");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Direccion");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Fecha de nacimiento");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Telefono");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Celular");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Fecha de Ingreso");

        Text_ID.setBackground(new java.awt.Color(200, 200, 200));
        Text_ID.setToolTipText("Ingrese el ID");
        Text_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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

        TextNombre.setBackground(new java.awt.Color(200, 200, 200));
        TextNombre.setToolTipText("Ingrese el Nombre");
        TextNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextNombreKeyTyped(evt);
            }
        });

        TextPapellido.setBackground(new java.awt.Color(200, 200, 200));
        TextPapellido.setToolTipText("Ingrese el primer Apellido");
        TextPapellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextPapellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextPapellidoKeyTyped(evt);
            }
        });

        TextSapellido.setBackground(new java.awt.Color(200, 200, 200));
        TextSapellido.setToolTipText("Ingrese el segundo Apellido");
        TextSapellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextSapellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextSapellidoKeyTyped(evt);
            }
        });

        TextDireccion.setBackground(new java.awt.Color(200, 200, 200));
        TextDireccion.setToolTipText("Ingrese la Direccion");
        TextDireccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TextFechaNac.setEditable(false);
        TextFechaNac.setBackground(new java.awt.Color(200, 200, 200));
        TextFechaNac.setToolTipText("Ingrese la Fecha de nacimiento");
        TextFechaNac.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextFechaNac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFechaNacActionPerformed(evt);
            }
        });

        TextTelefono.setBackground(new java.awt.Color(200, 200, 200));
        TextTelefono.setToolTipText("Ingrese el numero de telefono");
        TextTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextTelefonoKeyTyped(evt);
            }
        });

        TextCelular.setBackground(new java.awt.Color(200, 200, 200));
        TextCelular.setToolTipText("Ingrese el numero de celular");
        TextCelular.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextCelularKeyTyped(evt);
            }
        });

        TextFechaIngreso.setEditable(false);
        TextFechaIngreso.setBackground(new java.awt.Color(200, 200, 200));
        TextFechaIngreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextFechaIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFechaIngresoActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Status");

        TipoCliente.setBackground(new java.awt.Color(200, 200, 200));
        TipoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Socio Activo", "Invitado" }));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tipo Cliente");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Correo");

        TextCorreo.setBackground(new java.awt.Color(200, 200, 200));
        TextCorreo.setToolTipText("Ingrese el Correo");
        TextCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TextBalance.setEditable(false);
        TextBalance.setBackground(new java.awt.Color(200, 200, 200));
        TextBalance.setToolTipText("Balence");
        TextBalance.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Balance");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Valor Cuota");

        TextCuota.setBackground(new java.awt.Color(200, 200, 200));
        TextCuota.setToolTipText("Ingrese el Valor de la cuota");
        TextCuota.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        ButtonVolver.setBackground(new java.awt.Color(255, 193, 7));
        ButtonVolver.setText("Volver");
        ButtonVolver.setBorderPainted(false);
        ButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonVolverActionPerformed(evt);
            }
        });

        etiqueta.setForeground(new java.awt.Color(255, 255, 255));

        CheckBox.setText("Activo");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/customer_person_people_man_user_client_1629.png"))); // NOI18N

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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel7)
                            .addComponent(etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Text_ID)
                            .addComponent(TextNombre)
                            .addComponent(TextPapellido)
                            .addComponent(TextSapellido)
                            .addComponent(TextDireccion)
                            .addComponent(TextTelefono)
                            .addComponent(ButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(ButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110)
                                .addComponent(ButtonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel12)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(43, 43, 43))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(68, 68, 68)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(TextBalance)
                                        .addComponent(TipoCliente, 0, 178, Short.MAX_VALUE)
                                        .addComponent(TextCelular)
                                        .addComponent(TextCorreo)
                                        .addComponent(TextCuota)
                                        .addComponent(TextFechaIngreso))
                                    .addComponent(CheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(100, 100, 100))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(282, 282, 282))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Text_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(TextCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(TextFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(TextPapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(CheckBox))))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TextSapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TextDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(TextCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(TextFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(TextCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonVolver)
                    .addComponent(ButtonLimpiar)
                    .addComponent(ButtonGuardar)
                    .addComponent(etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
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
        Text_ID.requestFocus();
        
    }//GEN-LAST:event_Text_IDActionPerformed

    private void ButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLimpiarActionPerformed
       Limpiar();
    }//GEN-LAST:event_ButtonLimpiarActionPerformed

    private void ButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonVolverActionPerformed
        instanciass = null;
        dispose();
    }//GEN-LAST:event_ButtonVolverActionPerformed

    private void TextFechaIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFechaIngresoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFechaIngresoActionPerformed

    private void Text_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Text_IDKeyTyped
       
    }//GEN-LAST:event_Text_IDKeyTyped

    private void TextNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextNombreKeyTyped
        
    }//GEN-LAST:event_TextNombreKeyTyped

    private void TextPapellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextPapellidoKeyTyped
       
    }//GEN-LAST:event_TextPapellidoKeyTyped

    private void TextSapellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextSapellidoKeyTyped
        
    }//GEN-LAST:event_TextSapellidoKeyTyped

    private void TextTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextTelefonoKeyTyped
       
    }//GEN-LAST:event_TextTelefonoKeyTyped

    private void TextCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextCelularKeyTyped
        
    }//GEN-LAST:event_TextCelularKeyTyped

    private void TextFechaNacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFechaNacActionPerformed
        
    }//GEN-LAST:event_TextFechaNacActionPerformed

    private void ButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonGuardarActionPerformed
        guardarCliente();
    }//GEN-LAST:event_ButtonGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonGuardar;
    private javax.swing.JButton ButtonLimpiar;
    private javax.swing.JButton ButtonVolver;
    private javax.swing.JCheckBox CheckBox;
    private com.toedter.calendar.JDateChooser Chooser;
    private javax.swing.JTextField TextBalance;
    private javax.swing.JTextField TextCelular;
    private javax.swing.JTextField TextCorreo;
    private javax.swing.JTextField TextCuota;
    private javax.swing.JTextField TextDireccion;
    private javax.swing.JTextField TextFechaIngreso;
    private javax.swing.JTextField TextFechaNac;
    private javax.swing.JTextField TextNombre;
    private javax.swing.JTextField TextPapellido;
    private javax.swing.JTextField TextSapellido;
    private javax.swing.JTextField TextTelefono;
    private javax.swing.JTextField Text_ID;
    private javax.swing.JComboBox<String> TipoCliente;
    private javax.swing.JLabel etiqueta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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