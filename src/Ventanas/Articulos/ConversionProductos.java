/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas.Articulos;

import DataBase.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Ventanas.*;
import java.awt.event.KeyEvent;
import java.util.Date;

/**
 *
 * @author JIGA_
 */
public class ConversionProductos extends javax.swing.JFrame {

    /**
     * Creates new form Rebanadas
     */
    
    ArticuloLote articuloloteOrigen = new ArticuloLote();
    Articulo articuloOrigen = new Articulo();
    ArticuloLote articuloloteDestino = new ArticuloLote();
    Articulo articuloDestino = new Articulo();
    
    public ConversionProductos() {
        initComponents();
        this.setLocationRelativeTo(null);
         setIconImage(new ImageIcon(getClass().getResource("/Imagenes/iconcake.png")).getImage());
    }
    
     private String getQueryBuscar(String Codigo, String Nombre){
        String Query = "SELECT * FROM `articulo` WHERE `Activo` = '1' ";
        String codigoBuscar;
        String nombreBuscar;
        
        codigoBuscar = Codigo;
        nombreBuscar = Nombre;
        
        if (!"".equals(codigoBuscar)){
            Query +="AND `Codigo` = '" + codigoBuscar + "'";
        }else if(!"".equals(nombreBuscar)){
            Query += "AND `Nombre` LIKE '%" + nombreBuscar + "%'";         
        }           
        return Query;
    }
    private void limpiarTablaProductoOrigen(){
        DefaultTableModel model = (DefaultTableModel) TablaProductoOrigen.getModel();
        int CountRows = model.getRowCount();        
        for (int i = 0; i<CountRows; i++){
            model.removeRow(0);
        } 
    }
    private void agregarRowTablaProductoOrigen(String ID, String Codigo, String Nombre, String Existencia, String Precio){
        DefaultTableModel model = (DefaultTableModel) TablaProductoOrigen.getModel();
        model.addRow(new Object[]{ID,Codigo,Nombre,Existencia,Precio});
    }
	
	
    private void limpiarTablaLoteOrigen(){
        DefaultTableModel model = (DefaultTableModel) TablaLoteOrigen.getModel();
        int CountRows = model.getRowCount();        
        for (int i = 0; i<CountRows; i++){
            model.removeRow(0);
        } 
    }
    private void agregarRowTablaLoteOrigen(String ID, String Codigo, String Cantidad, String FechaElaboracion, String FechaCaducidad){
        DefaultTableModel model = (DefaultTableModel) TablaLoteOrigen.getModel();
        model.addRow(new Object[]{ID,Codigo,Cantidad,FechaElaboracion,FechaCaducidad});
    }
	
	
    private void limpiarTablaProductoDestino(){
        DefaultTableModel model = (DefaultTableModel) TablaProductoDestino.getModel();
        int CountRows = model.getRowCount();        
        for (int i = 0; i<CountRows; i++){
            model.removeRow(0);
        } 
    }
    private void agregarRowTablaProductoDestino(String ID, String Codigo, String Nombre, String Existencia, String Precio){
        DefaultTableModel model = (DefaultTableModel) TablaProductoDestino.getModel();
        model.addRow(new Object[]{ID,Codigo,Nombre,Existencia,Precio});
    }
	
	
    private void limpiarTablaLoteDestino(){
        DefaultTableModel model = (DefaultTableModel) TablaLoteDestino.getModel();
        int CountRows = model.getRowCount();        
        for (int i = 0; i<CountRows; i++){
            model.removeRow(0);
        } 
    }
    private void agregarRowTablaLoteDestino(String ID, String Codigo, String Cantidad, String FechaElaboracion, String FechaCaducidad){
        DefaultTableModel model = (DefaultTableModel) TablaLoteDestino.getModel();
        model.addRow(new Object[]{ID,Codigo,Cantidad,FechaElaboracion,FechaCaducidad});
    }
    
    private void fillArticuloLoteOrigen(Integer id, Integer idArticulo , short activo, String codigo, long cantidad, Date fechaElaboracion, Date fecbaCaducidad, Date fechaCreacion, Date fechaMod){
        articuloloteOrigen = new ArticuloLote(id,activo,codigo,cantidad,fechaElaboracion,fecbaCaducidad,fechaCreacion,fechaMod);
        articuloOrigen = new Articulo();
        articuloOrigen.setId(idArticulo);

    }
    private void fillArticuloLoteDestino(Integer id, Integer idArticulo, short activo, String codigo, long cantidad, Date fechaElaboracion, Date fecbaCaducidad, Date fechaCreacion, Date fechaMod){
        articuloloteDestino = new ArticuloLote(id,activo,codigo,cantidad,fechaElaboracion,fecbaCaducidad,fechaCreacion,fechaMod);
        articuloDestino = new Articulo();
        articuloDestino.setId(idArticulo);
    }
    private boolean convertirProductio(){
        Integer crTablaLoteOrigen = TablaLoteOrigen.getSelectedRow();
        Integer crTablaProductoDestino = TablaProductoDestino.getSelectedRow();
        Integer crTablaProductoOrigen = TablaProductoOrigen.getSelectedRow();
        if (crTablaLoteOrigen > -1 && crTablaProductoDestino > -1 && crTablaProductoOrigen > -1){
            String idALO = TablaLoteOrigen.getValueAt(crTablaLoteOrigen, 0).toString();
            String idArticuloALO = TablaProductoOrigen.getValueAt(crTablaProductoOrigen, 0).toString();
            String activoALO = "1";
            String codigoALO = TablaLoteOrigen.getValueAt(crTablaLoteOrigen, 1).toString();
            String cantidadALO = TablaLoteOrigen.getValueAt(crTablaLoteOrigen, 2).toString();
            String fechaElaboracionALO = TablaLoteOrigen.getValueAt(crTablaLoteOrigen, 3).toString();
            String fecbaCaducidadALO = TablaLoteOrigen.getValueAt(crTablaLoteOrigen, 4).toString();
            String idArticuloALD = TablaProductoDestino.getValueAt(crTablaProductoDestino, 0).toString();
            String activoALD = "1";
            String codigoALD = obtenerCodigo(idALO);
            String cantidadALD = txtCantidad.getText();
            String fechaElaboracionALD = fechaElaboracionALO;
            String fecbaCaducidadALD = fecbaCaducidadALO;
            String Query;
            if (idArticuloALO.equals(idArticuloALD)){
                JOptionPane.showMessageDialog(null,"No se puede convertir producto al mismo producto");
                return false;
            }
            Query = "INSERT INTO `articulo_lote`(`IDArticulo`, `Activo`, `Codigo`, `Cantidad`, `FechaElaboracion`, `FecbaCaducidad`, `FechaCreacion`, `FechaMod`) VALUES ("
                    + "'" + idArticuloALD + "',"
                    + "'" + activoALD + "',"
                    + "'" + codigoALD + "',"
                    + "'" + cantidadALD + "',"
                    + "'" + fechaElaboracionALD + "',"
                    + "'" + fecbaCaducidadALD + "',"
                    + "CURRENT_TIMESTAMP,"
                    + "CURRENT_TIMESTAMP)";
            if (updateLote(Query)){
                Query = "UPDATE `articulo` SET `Existencia` = ( SELECT IFNULL(SUM(Cantidad),0) AS Total FROM articulo_lote WHERE Activo = 1 AND IDArticulo = '" + idArticuloALD + "' ) WHERE `ID` = '" + idArticuloALD + "'";
                if (updateLote(Query)){
                    Integer Cantidad = Integer.parseInt(cantidadALO)-1;
                    if ( Cantidad == 0){
                        activoALO = "0";
                    }
                    Query = "UPDATE `articulo_lote` SET "
                            + "`Activo` = '" + activoALO + "',"
                            + "`Cantidad` = '" + Cantidad.toString() + "' "
                            + "WHERE "
                            + "`ID` = '" + idALO + "'";
                    if (updateLote(Query)){
                        Query = "UPDATE `articulo` SET `Existencia` = ( SELECT IFNULL(SUM(Cantidad),0) AS Total FROM articulo_lote WHERE Activo = 1 AND IDArticulo = '" + idArticuloALO + "' ) WHERE `ID` = '" + idArticuloALO + "'";
                        if (updateLote(Query)){
                            JOptionPane.showMessageDialog(null,"Conversion Realizada Exitosamente");
                            return true;
                        }
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"Favor de seleccionar los datos requeridos.");
            return false;
        }
        JOptionPane.showMessageDialog(null,"Sucedio un error.");
        return false;
    }
    private String obtenerCodigo(String IDOrigen){
        String CodigoCompleto;
        String Comodin = "AC";
        String Parcial = Comodin + IDOrigen;
        Conexion conex = new Conexion();
        Integer Cantidad = 0;
        String Query = "SELECT * FROM `articulo_lote` WHERE `Codigo` LIKE '" + Parcial + "%'";
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                ResultSet ResulQuery = stmt.executeQuery(Query);
                ResulQuery.last();
                Cantidad = ResulQuery.getRow() + 1;
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
        CodigoCompleto = Parcial + "-" + Cantidad.toString();
        return CodigoCompleto;
    }
    
    private boolean updateLote(String Parametro){
        Conexion conex = new Conexion();
        String Query = Parametro;
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                stmt.executeUpdate(Query);
                return true;
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
                return false;
        } 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPartir = new javax.swing.JButton();
        txtNombreOrigen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigoOrigen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductoDestino = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCodigoDestino = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNombreDestino = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaProductoOrigen = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaLoteDestino = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaLoteOrigen = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnBuscarDestino = new javax.swing.JButton();
        btnBuscarOrigen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPartir.setBackground(new java.awt.Color(153, 51, 0));
        btnPartir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnPartir.setForeground(new java.awt.Color(255, 255, 255));
        btnPartir.setText(">>>");
        btnPartir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPartirActionPerformed(evt);
            }
        });
        getContentPane().add(btnPartir, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, -1, -1));

        txtNombreOrigen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNombreOrigen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreOrigenKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombreOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 150, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Código:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        txtCodigoOrigen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCodigoOrigen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoOrigenKeyTyped(evt);
            }
        });
        getContentPane().add(txtCodigoOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 150, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Conversión de productos");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 230, 20));

        TablaProductoDestino.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Código", "Nombre", "Existencia", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaProductoDestino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProductoDestinoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaProductoDestino);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 410, 90));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Producto origen");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Producto destino");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Código:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        txtCantidad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        getContentPane().add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, 110, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Cantidad a convertir");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, -1, -1));

        txtCodigoDestino.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCodigoDestino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoDestinoKeyTyped(evt);
            }
        });
        getContentPane().add(txtCodigoDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 150, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nombre:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        txtNombreDestino.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNombreDestino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreDestinoKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombreDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 150, -1));

        TablaProductoOrigen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Código", "Nombre", "Existencia", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaProductoOrigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProductoOrigenMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaProductoOrigen);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 410, 90));

        TablaLoteDestino.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Código", "Cantidad", "Fecha elaboracion", "Fecha caducidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaLoteDestino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaLoteDestinoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TablaLoteDestino);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 300, 430, 90));

        TablaLoteOrigen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Código", "Cantidad", "Fecha elaboración", "Fecha caducidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaLoteOrigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaLoteOrigenMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TablaLoteOrigen);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 430, 90));

        jLabel10.setFont(new java.awt.Font("Brush Script MT", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("SoftCake");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 20, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/back.png"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 460, -1, -1));

        btnBuscarDestino.setText("Buscar Destino");
        btnBuscarDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDestinoActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 400, -1, -1));

        btnBuscarOrigen.setText("Buscar Origen");
        btnBuscarOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarOrigenActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscarOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cafe.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoOrigenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoOrigenKeyTyped
         char c = evt.getKeyChar();
        if((c<'0'||c>'9')&&(c<'A'||c>'Z'))evt.consume();
        
        int longitud = 25;
        if(txtCodigoOrigen.getText().length()>=longitud)
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Este campo no puede ser mayor a 25 caracteres, intente de nuevo", "Error", 0);
            txtCodigoOrigen.setText("");
        }
    }//GEN-LAST:event_txtCodigoOrigenKeyTyped

    private void txtCodigoDestinoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoDestinoKeyTyped
        char c = evt.getKeyChar();
        if((c<'0'||c>'9')&&(c<'A'||c>'Z'))evt.consume();
        
        int longitud = 25;
        if(txtCodigoDestino.getText().length()>=longitud)
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Este campo no puede ser mayor a 25 caracteres, intente de nuevo", "Error", 0);
            txtCodigoDestino.setText("");
        }
    }//GEN-LAST:event_txtCodigoDestinoKeyTyped

    private void txtNombreOrigenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreOrigenKeyTyped
        char c = evt.getKeyChar();
        if((c<'0'||c>'9')&&(c<'A'||c>'Z')&&(c !=(char)KeyEvent.VK_SPACE))evt.consume();
        
        int longitud = 50;
        if(txtNombreOrigen.getText().length()>=longitud)
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Este campo no puede ser mayor a 50 caracteres, intente de nuevo", "Error", 0);
            txtNombreOrigen.setText("");
        }
    }//GEN-LAST:event_txtNombreOrigenKeyTyped

    private void txtNombreDestinoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreDestinoKeyTyped
        char c = evt.getKeyChar();
        if((c<'0'||c>'9')&&(c<'A'||c>'Z')&&(c !=(char)KeyEvent.VK_SPACE))evt.consume();
        
        int longitud = 50;
        if(txtNombreDestino.getText().length()>=longitud)
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Este campo no puede ser mayor a 50 caracteres, intente de nuevo", "Error", 0);
            txtNombreDestino.setText(""); ////prueba commit git
        }
    }//GEN-LAST:event_txtNombreDestinoKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'||c>'9')evt.consume();
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void btnBuscarOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarOrigenActionPerformed
        Conexion conex = new Conexion();
        String CodigoBuscar = txtCodigoOrigen.getText();
        String NombreBuscar = txtNombreOrigen.getText();
        String Query = getQueryBuscar(CodigoBuscar,NombreBuscar);
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
            Statement stmt = conn.createStatement();            
            ResultSet ResulQuery = stmt.executeQuery(Query);
            limpiarTablaProductoOrigen();
            while(ResulQuery.next()){
                String ID = ResulQuery.getString("ID");
                String Codigo = ResulQuery.getString("Codigo");
                String Nombre = ResulQuery.getString("Nombre");
                String Existencia = ResulQuery.getString("Existencia");
                String Precio = ResulQuery.getString("Precio");
                agregarRowTablaProductoOrigen(ID,Codigo,Nombre,Existencia,Precio);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnBuscarOrigenActionPerformed

    private void btnBuscarDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDestinoActionPerformed
        Conexion conex = new Conexion();
        String CodigoBuscar = txtCodigoDestino.getText();
        String NombreBuscar = txtNombreDestino.getText();
        String Query = getQueryBuscar(CodigoBuscar,NombreBuscar);
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
            Statement stmt = conn.createStatement();            
            ResultSet ResulQuery = stmt.executeQuery(Query);
            limpiarTablaProductoDestino();
            while(ResulQuery.next()){
                String ID = ResulQuery.getString("ID");
                String Codigo = ResulQuery.getString("Codigo");
                String Nombre = ResulQuery.getString("Nombre");
                String Existencia = ResulQuery.getString("Existencia");
                String Precio = ResulQuery.getString("Precio");
                agregarRowTablaProductoDestino(ID,Codigo,Nombre,Existencia,Precio);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnBuscarDestinoActionPerformed

    private void TablaLoteOrigenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaLoteOrigenMouseClicked
        // TODO add your handling code here:       
    }//GEN-LAST:event_TablaLoteOrigenMouseClicked

    private void TablaLoteDestinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaLoteDestinoMouseClicked
        // TODO add your handling code here:        
    }//GEN-LAST:event_TablaLoteDestinoMouseClicked

    private void TablaProductoOrigenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProductoOrigenMouseClicked
        // TODO add your handling code here:
        int RowSeleccionado = TablaProductoOrigen.getSelectedRow();       
        if (RowSeleccionado > -1){	
		String IDArticulo = TablaProductoOrigen.getValueAt(RowSeleccionado, 0).toString();
		Conexion conex = new Conexion();
        String Query = "SELECT * FROM `articulo_lote` WHERE  `Activo` = '1' AND `IDArticulo` = '" +IDArticulo+ "'";
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
            Statement stmt = conn.createStatement();            
            ResultSet ResulQuery = stmt.executeQuery(Query);
            limpiarTablaLoteOrigen();
            while(ResulQuery.next()){
                String ID = ResulQuery.getString("ID");
                String Codigo = ResulQuery.getString("Codigo");
                String Cantidad = ResulQuery.getString("Cantidad");
                String FechaElaboracion = ResulQuery.getString("FechaElaboracion");
                String FechaCaducidad = ResulQuery.getString("FecbaCaducidad");
                agregarRowTablaLoteOrigen(ID, Codigo, Cantidad, FechaElaboracion, FechaCaducidad);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
		}else{
            JOptionPane.showMessageDialog(null, "Favor de seleccionar un dato.");
        }
    }//GEN-LAST:event_TablaProductoOrigenMouseClicked

    private void TablaProductoDestinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProductoDestinoMouseClicked
        // TODO add your handling code here:
        int RowSeleccionado = TablaProductoDestino.getSelectedRow();       
        if (RowSeleccionado > -1){	
		String IDArticulo = TablaProductoDestino.getValueAt(RowSeleccionado, 0).toString();
		Conexion conex = new Conexion();
        String Query = "SELECT * FROM `articulo_lote` WHERE  `Activo` = '1' AND `IDArticulo` = '" +IDArticulo+ "'";
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
            Statement stmt = conn.createStatement();            
            ResultSet ResulQuery = stmt.executeQuery(Query);
            limpiarTablaLoteDestino();
            while(ResulQuery.next()){
                String ID = ResulQuery.getString("ID");
                String Codigo = ResulQuery.getString("Codigo");
                String Cantidad = ResulQuery.getString("Cantidad");
                String FechaElaboracion = ResulQuery.getString("FechaElaboracion");
                String FechaCaducidad = ResulQuery.getString("FecbaCaducidad");
                agregarRowTablaLoteDestino(ID, Codigo, Cantidad, FechaElaboracion, FechaCaducidad);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
		}else{
            JOptionPane.showMessageDialog(null, "Favor de seleccionar un dato.");
        }
    }//GEN-LAST:event_TablaProductoDestinoMouseClicked

    private void btnPartirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPartirActionPerformed
        // TODO add your handling code here:
        if (convertirProductio()){
            Menu menu = new Menu();
            menu.setVisible(true);
            this.dispose();            
        }
    }//GEN-LAST:event_btnPartirActionPerformed

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
            java.util.logging.Logger.getLogger(ConversionProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConversionProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConversionProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConversionProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConversionProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaLoteDestino;
    private javax.swing.JTable TablaLoteOrigen;
    private javax.swing.JTable TablaProductoDestino;
    private javax.swing.JTable TablaProductoOrigen;
    private javax.swing.JButton btnBuscarDestino;
    private javax.swing.JButton btnBuscarOrigen;
    private javax.swing.JButton btnPartir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigoDestino;
    private javax.swing.JTextField txtCodigoOrigen;
    private javax.swing.JTextField txtNombreDestino;
    private javax.swing.JTextField txtNombreOrigen;
    // End of variables declaration//GEN-END:variables
}
