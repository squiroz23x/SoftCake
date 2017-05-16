/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import javax.swing.ImageIcon;
import java.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.swing.JOptionPane;
import DataBase.Empleados;
import java.text.SimpleDateFormat;

/**
 *
 * @author JIGA_
 */
public class RegistroUsuario extends javax.swing.JFrame {

    /**
     * Creates new form RegistroUsu
     */
    Empleados empleado = new Empleados();

    public RegistroUsuario() {
        initComponents();

        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/iconcake.png")).getImage());
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellidoP = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtApellidoM = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        CalendarFecha = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        txtNumeroE = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtNumeroI = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtColonia = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtCp = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        txtMunicipio = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtExtension = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtFacebook = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtIdentificador = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Jigay = new javax.swing.JTextArea();
        lblfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SoftCake Registro de empleados");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registro de empleados");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/back.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 460, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuarioregistro.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 210, 230));

        btnGuardar.setBackground(new java.awt.Color(153, 51, 0));
        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 410, 80, 30));

        btnBorrar.setBackground(new java.awt.Color(153, 51, 0));
        btnBorrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBorrar.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 410, 70, 30));

        btnModificar.setBackground(new java.awt.Color(153, 51, 0));
        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 410, 90, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Apellido paterno:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Celular:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Dirección:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, -1, -1));

        txtNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 160, -1));

        txtApellidoP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtApellidoP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoPKeyTyped(evt);
            }
        });
        getContentPane().add(txtApellidoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 160, -1));

        txtDireccion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 130, 410, -1));

        txtCelular.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 60, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Teléfono:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, -1, -1));

        txtTelefono.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 210, 130, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Usuario:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, -1, -1));

        txtUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 200, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Password:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 300, -1, -1));

        txtPassword.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 300, 200, -1));

        jLabel11.setFont(new java.awt.Font("Brush Script MT", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("SoftCake");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Apellido materno:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, -1, -1));

        txtApellidoM.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtApellidoM, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 90, 170, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Fecha de nacimiento:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, -1, -1));
        getContentPane().add(CalendarFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 140, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Número exterior:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, -1, -1));

        txtNumeroE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtNumeroE, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 50, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Número interior:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, -1, -1));

        txtNumeroI.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNumeroI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroIActionPerformed(evt);
            }
        });
        getContentPane().add(txtNumeroI, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 50, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Colonia:");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, -1, -1));

        txtColonia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtColonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 270, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("CP:");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 170, -1, -1));

        txtCp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtCp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 170, 60, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Estado:");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Municipio:");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, -1, -1));

        cmbEstado.setBackground(new java.awt.Color(153, 51, 0));
        cmbEstado.setMaximumRowCount(33);
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Chiapas", "Chihuahua", "Ciudad de México", "Coahuila", "Colima", "Durango", "Estado de México", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "Michoacán", "Morelos", "Nayarit", "Nuevo León", "Oaxaca", "Puebla", "Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas" }));
        cmbEstado.setAutoscrolls(true);
        cmbEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoActionPerformed(evt);
            }
        });
        getContentPane().add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, -1, -1));

        txtMunicipio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, 160, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Extensión:");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 210, -1, -1));

        txtExtension.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtExtension, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 210, 50, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Email:");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, -1, -1));

        txtEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, 280, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Sitio web:");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 250, -1, -1));

        txtFacebook.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtFacebook, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 250, 240, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Identificador:");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, -1, -1));

        txtIdentificador.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtIdentificador, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 100, -1));

        jButton1.setBackground(new java.awt.Color(153, 51, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Buscar");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, -1, -1));

        Jigay.setColumns(20);
        Jigay.setRows(5);
        jScrollPane1.setViewportView(Jigay);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        lblfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cafe.jpg"))); // NOI18N
        getContentPane().add(lblfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void prepararModEmpleado(String Identificador) {
        btnGuardar.setVisible(false);
        String Ident = Identificador;
        Conexion conex = new Conexion();
        String Query = ("SELECT * FROM `empleados` WHERE `Activo` = 1 AND `Identificador` = '" + Ident + "'");
        MysqlDataSource dataSource = conex.getConnection();
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet ResulQuery = stmt.executeQuery(Query);
            if (ResulQuery.next()) {
                fillEmpleado(ResulQuery.getInt("id"), ResulQuery.getShort("activo"), ResulQuery.getString("nombre"), ResulQuery.getString("apellidoP"), ResulQuery.getString("apellidoM"), ResulQuery.getDate("fechaNacimiento"), ResulQuery.getString("direccion"), ResulQuery.getString("numExt"), ResulQuery.getString("numInt"), ResulQuery.getString("cp"), ResulQuery.getString("colonia"), ResulQuery.getString("estado"), ResulQuery.getString("municipio"), ResulQuery.getString("telefono"), ResulQuery.getString("ext"), ResulQuery.getString("celular"), ResulQuery.getString("email"), ResulQuery.getString("facebook"), ResulQuery.getString("identificador"), ResulQuery.getString("usuario"), ResulQuery.getString("password"), ResulQuery.getDate("fechaCreacion"), ResulQuery.getDate("fechaMod"));
                fillFormulario();
            } else {
                JOptionPane.showMessageDialog(null, "Problema al encontrar los registros del Empleado.");
                System.exit(0);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error con la conexion de la base de datos: " + e);
        }

    }

    public void prepararInsEmpleado() {
        btnModificar.setVisible(false);
        btnBorrar.setVisible(false);
    }

    private void fillFormulario() {
        txtNombre.setText(empleado.getNombre());
        txtApellidoP.setText(empleado.getApellidoP());
        txtApellidoM.setText(empleado.getApellidoM());
        CalendarFecha.setDate(empleado.getFechaNacimiento());
        txtDireccion.setText(empleado.getDireccion());
        txtNumeroE.setText(empleado.getNumExt());
        txtNumeroI.setText(empleado.getNumInt());
        txtColonia.setText(empleado.getColonia());
        txtCp.setText(empleado.getCp());
        cmbEstado.setSelectedItem(empleado.getEstado());
        txtMunicipio.setText(empleado.getMunicipio());
        txtTelefono.setText(empleado.getTelefono());
        txtExtension.setText(empleado.getExt());
        txtCelular.setText(empleado.getCelular());
        txtEmail.setText(empleado.getEmail());
        txtFacebook.setText(empleado.getFacebook());
        txtIdentificador.setText(empleado.getIdentificador());
        txtUsuario.setText(empleado.getUsuario());
        txtPassword.setText(empleado.getPassword());

    }

    private void fillEmpleado(Integer id, short activo, String nombre, String apellidoP, String apellidoM, java.util.Date fechaNacimiento, String direccion, String numExt, String numInt, String cp, String colonia, String estado, String municipio, String telefono, String ext, String celular, String email, String facebook, String identificador, String usuario, String password, java.util.Date fechaCreacion, java.util.Date fechaMod) {
        empleado = new Empleados(id, activo, nombre, apellidoP, apellidoM, fechaNacimiento, direccion, numExt, numInt, cp, colonia, estado, municipio, telefono, ext, celular, email, facebook, identificador, usuario, password, fechaCreacion, fechaMod);
    }

    private void updateEmpleado() {
        short Activo = 1;
        empleado.setActivo(Activo);
        empleado.setNombre(txtNombre.getText());
        empleado.setApellidoP(txtApellidoP.getText());
        empleado.setApellidoM(txtApellidoM.getText());
        empleado.setFechaNacimiento(CalendarFecha.getDate());
        empleado.setDireccion(txtDireccion.getText());
        empleado.setNumExt(txtNumeroE.getText());
        empleado.setNumInt(txtNumeroI.getText());
        empleado.setCp(txtCp.getText());
        empleado.setColonia(txtColonia.getText());
        empleado.setEstado(cmbEstado.getSelectedItem().toString());
        empleado.setMunicipio(txtMunicipio.getText());
        empleado.setTelefono(txtTelefono.getText());
        empleado.setExt(txtExtension.getText());
        empleado.setCelular(txtCelular.getText());
        empleado.setEmail(txtEmail.getText());
        empleado.setFacebook(txtFacebook.getText());
        empleado.setIdentificador(txtIdentificador.getText());
        empleado.setUsuario(txtUsuario.getText());
        empleado.setPassword(txtPassword.getText());
    }

    private void modEmpleado() {
        updateEmpleado();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String fechanacimiento = date.format(empleado.getFechaNacimiento());
        Conexion conex = new Conexion();
        String Query = ("UPDATE empleados SET Activo='" + empleado.getActivo() + "',Nombre='" + empleado.getNombre() + "',ApellidoP='" + empleado.getApellidoP() + "',ApellidoM='" + empleado.getApellidoM() + "',FechaNacimiento='" + fechanacimiento + "',Direccion='" + empleado.getDireccion() + "',NumExt='" + empleado.getNumExt() + "',NumInt='" + empleado.getNumInt() + "',CP='" + empleado.getCp() + "',Colonia='" + empleado.getColonia() + "',Estado='" + empleado.getEstado() + "',Municipio='" + empleado.getMunicipio() + "',Telefono='" + empleado.getTelefono() + "',Ext='" + empleado.getExt() + "',Celular='" + empleado.getCelular() + "',Email='" + empleado.getEmail() + "',Facebook='" + empleado.getFacebook() + "',Identificador='" + empleado.getIdentificador() + "',Usuario='" + empleado.getUsuario() + "',Password='" + empleado.getPassword() + "',FechaMod=CURRENT_TIMESTAMP WHERE ID=" + empleado.getId());
        MysqlDataSource dataSource = conex.getConnection();
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Los datos fueron modificados.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error con la conexion de la base de datos: " + e);
        }

    }

    private boolean validarUsuario(String Usuario, String Identificador) {
        Conexion conex = new Conexion();
        String Query = ("SELECT * FROM `empleados` WHERE `Usuario`= '" + Usuario + "' OR `Identificador`= '" + Identificador + "'");
        MysqlDataSource dataSource = conex.getConnection();
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet ResulQuery = stmt.executeQuery(Query);
            if (ResulQuery.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error con la conexion de la base de datos: " + e);
        }
        return false;
    }

    private void insEmpleado() {
        updateEmpleado();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String fechanacimiento = date.format(empleado.getFechaNacimiento());
        Conexion conex = new Conexion();
        String Query = ("INSERT INTO `empleados` (`Activo`, `Nombre`, `ApellidoP`, `ApellidoM`, `FechaNacimiento`, `Direccion`, `NumExt`, `NumInt`, `CP`, `Colonia`, `Estado`, `Municipio`, `Telefono`, `Ext`, `Celular`, `Email`, `Facebook`, `Identificador`, `Usuario`, `Password`, `FechaCreacion`, `FechaMod`) VALUES ('1','" + empleado.getNombre() + "','" + empleado.getApellidoP() + "','" + empleado.getApellidoM() + "','" + fechanacimiento + "','" + empleado.getDireccion() + "','" + empleado.getNumExt() + "','" + empleado.getNumInt() + "','" + empleado.getCp() + "','" + empleado.getColonia() + "','" + empleado.getEstado() + "','" + empleado.getMunicipio() + "','" + empleado.getTelefono() + "','" + empleado.getExt() + "','" + empleado.getCelular() + "','" + empleado.getEmail() + "','" + empleado.getFacebook() + "','" + empleado.getIdentificador() + "','" + empleado.getUsuario() + "','" + empleado.getPassword() + "',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)");
        if (validarUsuario(empleado.getUsuario(), empleado.getIdentificador())) {
            MysqlDataSource dataSource = conex.getConnection();
            try (Connection conn = dataSource.getConnection()) {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(Query);
                JOptionPane.showMessageDialog(null, "Los datos fueron modificados.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error con la conexion de la base de datos: " + e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El Usuario o Identificador ya existe, favor de proporcionar uno diferente.");
        }
    }

    private void eliEmpleado() {
        updateEmpleado();
        Conexion conex = new Conexion();
        String Query = ("UPDATE empleados SET Activo='0' WHERE ID=" + empleado.getId());
        MysqlDataSource dataSource = conex.getConnection();
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "El Usuario '" + empleado.getUsuario() + "' Con codigo de empleado " + empleado.getIdentificador() + " fue eliminado satisfactoriamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error con la conexion de la base de datos: " + e);
        }
    }

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        Menu menu = new Menu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        eliEmpleado();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtNumeroIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroIActionPerformed

    private void cmbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        insEmpleado();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char c = evt.getKeyChar();
        if (c < 'A' || c > 'Z') {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPKeyTyped
        char c = evt.getKeyChar();
        if (c < 'A' || c > 'Z') {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellidoPKeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modEmpleado();
    }//GEN-LAST:event_btnModificarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser CalendarFecha;
    private javax.swing.JTextArea Jigay;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblfondo;
    private javax.swing.JTextField txtApellidoM;
    private javax.swing.JTextField txtApellidoP;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtCp;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtExtension;
    private javax.swing.JTextField txtFacebook;
    private javax.swing.JTextField txtIdentificador;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumeroE;
    private javax.swing.JTextField txtNumeroI;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
