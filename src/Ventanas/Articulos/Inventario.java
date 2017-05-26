/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas.Articulos;

import DataBase.Articulo;
import DataBase.ArticuloLote;
import DataBase.ArticuloUnidad;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;
import Ventanas.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JIGA_
 */
public class Inventario extends javax.swing.JFrame {    
    /**
     * Creates new form Inventario
     */
    
    Articulo articulo = new Articulo();
    ArticuloUnidad articulounidad = new ArticuloUnidad();    
    ArrayList<ArticuloLote> articulolote = new ArrayList<ArticuloLote>();
    
    public Inventario() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/iconcake.png")).getImage());
        this.setLocationRelativeTo(null);
    }
    
    private void insArticulo(){
        
    }    
    private void modArticulo(){
        
    }
    private void eliArticulo(){
        
    }
    private void updateArticulo(){
        Integer codigo = Integer.parseInt(txtCodigo.getText());
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        BigDecimal precio = new BigDecimal(txtPrecio.getText());
        Integer sMaximo = Integer.parseInt(txtMaximo.getText());
        Integer sMinimo = Integer.parseInt(txtMinimo.getText());
        Integer existencia = Integer.parseInt(txtExistencia.getText());

        
    }
    public void prepararModArticulo(String Index){
        Conexion conex = new Conexion();
        Integer IDUnidad = 0;
        String Query = "SELECT * FROM `articulo` WHERE `Activo`='1' AND `ID`='"+Index+"'";
        MysqlDataSource dataSourceArticulo = conex.getConnection();        
        try(Connection conn = dataSourceArticulo.getConnection()){
                Statement stmtArticulo = conn.createStatement();            
                ResultSet ResulQueryArticulo = stmtArticulo.executeQuery(Query);
                while(ResulQueryArticulo.next()){
                    Integer id = ResulQueryArticulo.getInt("ID");
                    short activo = ResulQueryArticulo.getShort("Activo");
                    int codigo = ResulQueryArticulo.getInt("Codigo");
                    String nombre = ResulQueryArticulo.getString("Nombre");
                    String descripcion = ResulQueryArticulo.getString("Descripcion");
                    BigDecimal precio = ResulQueryArticulo.getBigDecimal("Precio");
                    int sMaximo = ResulQueryArticulo.getInt("SMaximo");
                    int sMinimo = ResulQueryArticulo.getInt("SMinimo");
                    int existencia = ResulQueryArticulo.getInt("Existencia");
                    Date fechaCreacion = ResulQueryArticulo.getDate("FechaCreacion");
                    Date fechaMod = ResulQueryArticulo.getDate("FechaMod");
                    IDUnidad = ResulQueryArticulo.getInt("IDUnidad");                    
                    fillArticulo(id,activo,codigo,nombre,descripcion,precio,sMaximo,sMinimo,existencia,fechaCreacion,fechaMod);
                }
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
        Query = "SELECT * FROM `articulo_unidad` WHERE `Activo` = '1' AND `ID` ='" + IDUnidad + "'";
        MysqlDataSource dataSourceArticuloUnidad = conex.getConnection();        
        try(Connection conn = dataSourceArticuloUnidad.getConnection()){
            Statement stmtArticuloUnidad = conn.createStatement();            
            ResultSet ResulQueryArticuloUnidad = stmtArticuloUnidad.executeQuery(Query);	
            while(ResulQueryArticuloUnidad.next()){
                Integer id = ResulQueryArticuloUnidad.getInt("ID");
                short activo = ResulQueryArticuloUnidad.getShort("Activo");
                String descripcion = ResulQueryArticuloUnidad.getString("Descripcion");
                String nombreCorto = ResulQueryArticuloUnidad.getString("NombreCorto");
                Date fechaCreacion = ResulQueryArticuloUnidad.getDate("FechaCreacion");
                Date fechaMod = ResulQueryArticuloUnidad.getDate("FechaMod");
                fillArticuloUnidad( id,  activo,  descripcion,  nombreCorto,  fechaCreacion,  fechaMod);
		}
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
        Query = "SELECT * FROM `articulo_lote` WHERE `Activo` = '1' AND `IDArticulo` = '" + articulo.getId() + "'";
        MysqlDataSource dataSourceArticuloLote = conex.getConnection();        
        try(Connection conn = dataSourceArticuloLote.getConnection()){
                Statement stmtArticuloLote = conn.createStatement();            
                ResultSet ResulQueryArticuloLote = stmtArticuloLote.executeQuery(Query);

                while(ResulQueryArticuloLote.next()){
                    Integer id = ResulQueryArticuloLote.getInt("ID");
                    short activo = ResulQueryArticuloLote.getShort("Activo");
                    String codigo = ResulQueryArticuloLote.getString("Codigo");
                    long cantidad = ResulQueryArticuloLote.getLong("Cantidad");
                    Date fechaElaboracion = ResulQueryArticuloLote.getDate("FechaElaboracion");
                    Date fecbaCaducidad = ResulQueryArticuloLote.getDate("FecbaCaducidad");
                    Date fechaCreacion = ResulQueryArticuloLote.getDate("FechaCreacion");
                    Date fechaMod = ResulQueryArticuloLote.getDate("FechaMod");
                    fillArticuloLote( id,  activo,  codigo,  cantidad,  fechaElaboracion,  fecbaCaducidad,  fechaCreacion,  fechaMod);
             
                
                }
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
        articulo.setIDUnidad(articulounidad);
        articulo.setArticuloLoteCollection(articulolote);  
        fillCmbUnidad();
        fillFormulario();
  
    }
    public void prepararInsArticulo(){
        
    }
    private boolean validadArticulo(){
        
        return false;
    }
    private void fillArticulo(Integer id, short activo, int codigo, String nombre, String descripcion, BigDecimal precio, int sMaximo, int sMinimo, int existencia, Date fechaCreacion, Date fechaMod){
        articulo = new Articulo(id,activo,codigo,nombre,descripcion,precio,sMaximo,sMinimo,existencia,fechaCreacion,fechaMod);
    }
    private void fillArticuloUnidad(Integer id, short activo, String descripcion, String nombreCorto, Date fechaCreacion, Date fechaMod){
        articulounidad = new ArticuloUnidad(id,activo,descripcion,nombreCorto,fechaCreacion,fechaMod);
    }
    private void fillArticuloLote(Integer id, short activo, String codigo, long cantidad, Date fechaElaboracion, Date fecbaCaducidad, Date fechaCreacion, Date fechaMod){
        ArticuloLote lote = new ArticuloLote(id,activo,codigo,cantidad,fechaElaboracion,fecbaCaducidad,fechaCreacion,fechaMod);
        articulolote.add(lote);
    }
    private void fillFormulario(){
        Integer codigo = articulo.getCodigo();
        String nombre = articulo.getNombre();
        String descripcion = articulo.getDescripcion();
        BigDecimal precio = articulo.getPrecio();
        Integer sMaximo = articulo.getSMaximo();
        Integer sMinimo = articulo.getSMinimo();
        Integer existencia = articulo.getExistencia();
        Iterator<ArticuloLote> itArticuloLote = articulo.getArticuloLoteCollection().iterator();
        txtCodigo.setText(codigo.toString());
        txtNombre.setText(nombre);
        txtDescripcion.setText(descripcion);
        txtPrecio.setText(precio.toString());
        txtMaximo.setText(sMaximo.toString());
        txtMinimo.setText(sMinimo.toString());
        txtExistencia.setText(existencia.toString());
        limpiarTablaExistencias();
        while (itArticuloLote.hasNext()){
            ArticuloLote Lote = itArticuloLote.next();
            Integer Loteid = Lote.getId();
            Short Loteactivo = Lote.getActivo();
            String Lotecodigo = Lote.getCodigo();
            Long Lotecantidad = Lote.getCantidad();
            Date LotefechaElaboracion = Lote.getFechaElaboracion();
            Date LotefecbaCaducidad = Lote.getFecbaCaducidad();
            Date LotefechaCreacion = Lote.getFechaCreacion();
            Date LotefechaMod = Lote.getFechaMod();            
            fillTablaExistencias(Lotecodigo,  Lotecantidad.toString(),  LotefechaElaboracion.toString(),  LotefecbaCaducidad.toString(),  Loteid.toString(), "0");          
        }
    }
    private void fillCmbUnidad(){
        Conexion conex = new Conexion();
        String Query = "SELECT * FROM `articulo_unidad` WHERE `Activo` = '1' ORDER BY `ID`";
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                ResultSet ResulQuery = stmt.executeQuery(Query);
                cmbUnidad.removeAllItems();
                cmbUnidad.addItem("Seleccionar");
                while(ResulQuery.next()){
                    cmbUnidad.addItem(ResulQuery.getString("Descripcion"));
                }
                 cmbUnidad.addItem("Nuevo...");
                 cmbUnidad.setSelectedItem(articulo.getIDUnidad().getDescripcion());
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        } 
    }
    private void fillTablaExistencias(String Codigo, String Cantidad, String FechaElaboracion, String FechaCaducidad, String ID, String ID2){
        DefaultTableModel mode1TablaExistencias = (DefaultTableModel) TablaExistencias.getModel();
        mode1TablaExistencias.addRow(new Object[]{Codigo,Cantidad,FechaElaboracion,FechaCaducidad,ID,ID2});        
    }
     private void limpiarTablaExistencias(){
        DefaultTableModel model = (DefaultTableModel) TablaExistencias.getModel();
        int CountRows = model.getRowCount();        
        for (int i = 0; i<CountRows; i++){
            model.removeRow(0);
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

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        lblFondo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cmbUnidad = new javax.swing.JComboBox<>();
        btnLote = new javax.swing.JButton();
        btnUnidad = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtMaximo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtMinimo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtExistencia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaExistencias = new javax.swing.JTable();
        lblFondo1 = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("File");
        jMenuBar3.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar3.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar4.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar4.add(jMenu6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Softcake Inventario");
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFondo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblFondo.setForeground(new java.awt.Color(255, 255, 255));
        lblFondo.setText("Control de Inventario");
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/back.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 550, -1, -1));

        jLabel3.setFont(new java.awt.Font("Brush Script MT", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SoftCake");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 580, -1, -1));

        jButton1.setBackground(new java.awt.Color(153, 51, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 490, -1, -1));

        jButton2.setBackground(new java.awt.Color(153, 51, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Modificar ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 490, -1, -1));

        jButton4.setBackground(new java.awt.Color(153, 51, 0));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Buscar");
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 490, -1, -1));

        jPanel2.setBackground(new java.awt.Color(113, 22, 2));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUCTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Código:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        txtCodigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });
        jPanel2.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 100, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nombre:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        txtNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 240, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Precio:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        txtPrecio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });
        jPanel2.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 100, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Descripción:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, -1, -1));

        txtDescripcion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });
        jPanel2.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 240, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Unidad:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        cmbUnidad.setBackground(new java.awt.Color(153, 51, 0));
        cmbUnidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Pieza", "Rebanada" }));
        cmbUnidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbUnidadItemStateChanged(evt);
            }
        });
        cmbUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUnidadActionPerformed(evt);
            }
        });
        jPanel2.add(cmbUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 100, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 800, 150));

        btnLote.setText("Lote");
        btnLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoteActionPerformed(evt);
            }
        });
        getContentPane().add(btnLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 490, -1, -1));

        btnUnidad.setText("Unidad");
        getContentPane().add(btnUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 540, -1, -1));

        jPanel3.setBackground(new java.awt.Color(113, 22, 2));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INVENTARIO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Stock Máximo:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        txtMaximo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMaximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaximoActionPerformed(evt);
            }
        });
        txtMaximo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaximoKeyTyped(evt);
            }
        });
        jPanel3.add(txtMaximo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 80, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Stock Mínimo:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        txtMinimo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinimoActionPerformed(evt);
            }
        });
        txtMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMinimoKeyTyped(evt);
            }
        });
        jPanel3.add(txtMinimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 80, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("En Existencia:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtExistencia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtExistencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtExistenciaKeyTyped(evt);
            }
        });
        jPanel3.add(txtExistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 80, -1));

        TablaExistencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Cantidad", "FechaElaboracion", "FechaCaducidad", "ID", "ID2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaExistencias);
        if (TablaExistencias.getColumnModel().getColumnCount() > 0) {
            TablaExistencias.getColumnModel().getColumn(2).setPreferredWidth(100);
            TablaExistencias.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 420, 120));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 800, 180));

        lblFondo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cafe.jpg"))); // NOI18N
        lblFondo1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                lblFondo1ComponentShown(evt);
            }
        });
        getContentPane().add(lblFondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        Menu menu = new Menu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txtMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinimoActionPerformed
        
    }//GEN-LAST:event_txtMinimoActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void cmbUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUnidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUnidadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'||c>'9')evt.consume();
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char c = evt.getKeyChar();
        if((c<'0'||c>'9')&&(c<'A'||c>'Z')&&(c !=(char)KeyEvent.VK_SPACE))evt.consume();
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        char c = evt.getKeyChar();
        if((c<'0'||c>'9')&&(c<'A'||c>'Z')&&(c !=(char)KeyEvent.VK_SPACE))evt.consume();
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        if(!Character.isDigit(evt.getKeyChar())&& evt.getKeyChar()!='.')
            evt.consume();
        
        if(evt.getKeyChar()=='.' && txtPrecio.getText().contains("."))
        {
            evt.consume();
        }
    
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtMaximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaximoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaximoActionPerformed

    private void txtMaximoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaximoKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'||c>'9')evt.consume();
    }//GEN-LAST:event_txtMaximoKeyTyped

    private void txtMinimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinimoKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'||c>'9')evt.consume();
    }//GEN-LAST:event_txtMinimoKeyTyped

    private void txtExistenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExistenciaKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'||c>'9')evt.consume();
    }//GEN-LAST:event_txtExistenciaKeyTyped

    private void lblFondo1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_lblFondo1ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_lblFondo1ComponentShown

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentShown

    private void cmbUnidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbUnidadItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cmbUnidadItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoteActionPerformed
        Lote lote = new Lote();
        lote.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLoteActionPerformed
        
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
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaExistencias;
    private javax.swing.JButton btnLote;
    private javax.swing.JButton btnUnidad;
    private javax.swing.JComboBox<String> cmbUnidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblFondo1;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtExistencia;
    private javax.swing.JTextField txtMaximo;
    private javax.swing.JTextField txtMinimo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
