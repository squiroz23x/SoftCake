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
        updateArticulo();
        Boolean ValidacionesNulas = true;
        if (ValidacionesNulas){
            if (validadArticulo(articulo.getCodigo())){
                Conexion conex = new Conexion();
                String Query = "INSERT INTO `articulo` (`ID`, `Activo`, `Codigo`, `Nombre`, `Descripcion`, `Precio`, `IDUnidad`, `SMaximo`, `SMinimo`, `Existencia`, `FechaCreacion`, `FechaMod`) VALUES ("
                        + "NULL, "
                        + "'" + articulo.getActivo() + "', "
                        + "'" + articulo.getCodigo() + "', "
                        + "'" + articulo.getNombre() + "', "
                        + "'" + articulo.getDescripcion() + "', "
                        + "'" + articulo.getPrecio() + "', "
                        + "'" + articulo.getIDUnidad().getId() + "', "
                        + "'" + articulo.getSMaximo() + "', "
                        + "'" + articulo.getSMinimo() + "', "
                        + "'" + articulo.getExistencia() + "', "        
                        + "CURRENT_TIMESTAMP, "
                        + "CURRENT_TIMESTAMP)";
                MysqlDataSource dataSource = conex.getConnection();        
                try(Connection conn = dataSource.getConnection()){
                        Statement stmt = conn.createStatement();            
                        stmt.executeUpdate(Query);
                        JOptionPane.showMessageDialog(null,"Agregado Correctamente.");
                        ExistenciaProductos ExtProductos = new ExistenciaProductos();
                        ExtProductos.setVisible(true);
                        this.dispose();                        
                }catch(SQLException e){
                        JOptionPane.showMessageDialog(null,e);
                }
            }else{
                JOptionPane.showMessageDialog(null,"El codigo del articulo esta en uso. Favor de proporcionar otro codigo.");
            }
        }else{
            JOptionPane.showMessageDialog(null,"No se puede dejar campos vacios");
        }
    }    
    private void modArticulo(){
        updateArticulo();
        Boolean ValidacionesNulas = true;
        if (ValidacionesNulas){
            if (validadArticulo(articulo.getCodigo())){
                Conexion conex = new Conexion();
                String Query = "UPDATE `articulo` SET "
                        + "`Activo`='" + articulo.getActivo() +"',"
                        + "`Codigo`='" + articulo.getCodigo() +"',"
                        + "`Nombre`='" + articulo.getNombre() +"',"
                        + "`Descripcion`='" + articulo.getDescripcion() +"',"
                        + "`Precio`='" + articulo.getPrecio() +"',"
                        + "`IDUnidad`='" + articulo.getIDUnidad().getId() +"',"
                        + "`SMaximo`='" + articulo.getSMaximo() +"',"
                        + "`SMinimo`='" + articulo.getSMinimo() +"',"
                        + "`Existencia`='" + articulo.getExistencia() +"' "
                        + "WHERE `ID` = '" + articulo.getId() +" '";
                MysqlDataSource dataSource = conex.getConnection();        
                try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                stmt.executeUpdate(Query);
                JOptionPane.showMessageDialog(null,"Actualizacion Correcta.");
                Menu menu = new Menu();
                menu.setVisible(true);
                this.dispose();
                
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
            }else{
                JOptionPane.showMessageDialog(null,"El codigo del articulo esta en uso. Favor de proporcionar otro codigo.");
            }
        }else{
            JOptionPane.showMessageDialog(null,"No se puede dejar campos vacios");
        }        
    }
    private void eliArticulo(){
         Conexion conex = new Conexion();
                String Query = "UPDATE `articulo` SET "
                        + "`Activo`='0',"
                        + "WHERE `ID` = '" + articulo.getId() +" '";
                MysqlDataSource dataSource = conex.getConnection();        
                try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                stmt.executeUpdate(Query);
                JOptionPane.showMessageDialog(null,"Eliminacion Correcta.");
                Menu menu = new Menu();
                menu.setVisible(true);
                this.dispose();
                
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
        
    }
    private void updateArticulo(){
        Integer id = articulo.getId();
        Short activo = 1;
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        BigDecimal precio = new BigDecimal(txtPrecio.getText());
        Integer sMaximo = Integer.parseInt(txtMaximo.getText());
        Integer sMinimo = Integer.parseInt(txtMinimo.getText());
        Integer existencia = getExistenciasTabla();
        Date fechaCreacion = articulo.getFechaCreacion();
        Date fechaMod = articulo.getFechaMod();
        fillArticulo(id,activo,codigo,nombre,descripcion,precio,sMaximo,sMinimo,existencia,fechaCreacion,fechaMod);
        Integer IDUnidad = cmbUnidad.getSelectedIndex();
        articulounidad.setId(IDUnidad);
        articulo.setIDUnidad(articulounidad);
    }
   
    public Integer prepararArticulo(String Index){
        Conexion conex = new Conexion();
        Integer IDUnidad = 0;
        String Query = "SELECT * FROM `articulo` WHERE `Activo`='1' AND `Codigo`='"+Index+"'";
        MysqlDataSource dataSourceArticulo = conex.getConnection();
        try(Connection conn = dataSourceArticulo.getConnection()){
                Statement stmtArticulo = conn.createStatement();            
                ResultSet ResulQueryArticulo = stmtArticulo.executeQuery(Query);
                while(ResulQueryArticulo.next()){
                    Integer id = ResulQueryArticulo.getInt("ID");
                    short activo = ResulQueryArticulo.getShort("Activo");
                    String codigo = ResulQueryArticulo.getString("Codigo");
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
        return IDUnidad;
    }
    public void prepararArticuloUnidad(Integer Index){
        Conexion conex = new Conexion();
        String Query = "SELECT * FROM `articulo_unidad` WHERE `Activo` = '1' AND `ID` ='" + Index + "'";
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
    }
    public void prepararArticuloLote(String Index){
        articulolote.clear();
        Conexion conex = new Conexion();
        String Query = "SELECT * FROM `articulo_lote` WHERE `Activo` = '1' AND `IDArticulo` = '" + Index + "'";
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
    }
    
    public void prepararModArticulo(String Index){
        Integer IDUnidad = prepararArticulo(Index);
        prepararArticuloUnidad(IDUnidad);
        prepararArticuloLote(articulo.getId().toString());
        
        articulo.setIDUnidad(articulounidad);
        articulo.setArticuloLoteCollection(articulolote);
        btnAgregar.setVisible(false);
        fillCmbUnidad();
        fillFormulario();
  
    }
    public void prepararInsArticulo(){
        fillCmbUnidad();
        lblExistencia.setVisible(false);
        txtExistencia.setVisible(false);
        TablaExistencias.setVisible(false);
        btnAgregarLote.setVisible(false);
        btnModificarLote.setVisible(false);
        btnModificar.setVisible(false);
        btnEliminar.setVisible(false);
        
    }
    private boolean validadArticulo(String Codigo){
        Conexion conex = new Conexion();
        String Query = "SELECT * FROM `articulo` WHERE `Codigo` = '" + Codigo + " '";
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                ResultSet ResulQuery = stmt.executeQuery(Query);
                if (ResulQuery.next()){
                    Integer ID1 = ResulQuery.getInt("ID");
                    Integer ID2 = articulo.getId();
                    if (ID1 == ID2){
                        return true;                       
                    }else{
                        return false;
                    }
                }else{
                    return true;
                }
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
        return false;       
    }
    private void fillArticulo(Integer id, short activo, String codigo, String nombre, String descripcion, BigDecimal precio, int sMaximo, int sMinimo, int existencia, Date fechaCreacion, Date fechaMod){
        articulo = new Articulo(id,activo,codigo,nombre,descripcion,precio,sMaximo,sMinimo,existencia,fechaCreacion,fechaMod);
    }
    private void fillArticuloUnidad(Integer id, short activo, String descripcion, String nombreCorto, Date fechaCreacion, Date fechaMod){
        articulounidad = new ArticuloUnidad(id,activo,descripcion,nombreCorto,fechaCreacion,fechaMod);
    }
    private void fillArticuloLote(Integer id, short activo, String codigo, long cantidad, Date fechaElaboracion, Date fecbaCaducidad, Date fechaCreacion, Date fechaMod){
        ArticuloLote lote = new ArticuloLote(id,activo,codigo,cantidad,fechaElaboracion,fecbaCaducidad,fechaCreacion,fechaMod);
        articulolote.add(lote);
    }
    public void fillTablaLote(){
        limpiarTablaExistencias();
        Iterator<ArticuloLote> itArticuloLote = articulo.getArticuloLoteCollection().iterator();
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
        txtExistencia.setText(getExistenciasTabla().toString());
    }
    private void fillFormulario(){
        String codigo = articulo.getCodigo();
        String nombre = articulo.getNombre();
        String descripcion = articulo.getDescripcion();
        BigDecimal precio = articulo.getPrecio();
        Integer sMaximo = articulo.getSMaximo();
        Integer sMinimo = articulo.getSMinimo();
        Integer existencia = articulo.getExistencia();        
        txtCodigo.setText(codigo);
        txtNombre.setText(nombre);
        txtDescripcion.setText(descripcion);
        txtPrecio.setText(precio.toString());
        txtMaximo.setText(sMaximo.toString());
        txtMinimo.setText(sMinimo.toString());        
        fillTablaLote();
        cmbUnidad.setSelectedItem(articulo.getIDUnidad().getDescripcion());
    }
    public void fillCmbUnidad(){
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
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        } 
    }
    private void fillTablaExistencias(String Codigo, String Cantidad, String FechaElaboracion, String FechaCaducidad, String ID, String ID2){
        DefaultTableModel mode1TablaExistencias = (DefaultTableModel) TablaExistencias.getModel();
        mode1TablaExistencias.addRow(new Object[]{Codigo,Cantidad,FechaElaboracion,FechaCaducidad,ID,ID2});        
    }
    public void updateTablaExistencia(){
        this.setEnabled(true);
    }
    
    private Integer getExistenciasTabla(){
        Integer Existencias = 0;
         DefaultTableModel model = (DefaultTableModel) TablaExistencias.getModel();
        int CountRows = model.getRowCount();        
        for (int i = 0; i<CountRows; i++){
            
            Existencias += Integer.parseInt(TablaExistencias.getValueAt(i, 1).toString());
        }
        return Existencias;
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
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
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
        btnUnidadNuevo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtMaximo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtMinimo = new javax.swing.JTextField();
        lblExistencia = new javax.swing.JLabel();
        txtExistencia = new javax.swing.JTextField();
        btnModificarLote = new javax.swing.JButton();
        btnAgregarLote = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaExistencias = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
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

        btnAgregar.setBackground(new java.awt.Color(153, 51, 0));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 490, -1, -1));

        btnModificar.setBackground(new java.awt.Color(153, 51, 0));
        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar ");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 490, -1, -1));

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

        btnUnidadNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnidadNuevoActionPerformed(evt);
            }
        });
        jPanel2.add(btnUnidadNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 112, -1, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 800, 150));

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

        lblExistencia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblExistencia.setForeground(new java.awt.Color(255, 255, 255));
        lblExistencia.setText("En Existencia:");
        jPanel3.add(lblExistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtExistencia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtExistencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtExistenciaKeyTyped(evt);
            }
        });
        jPanel3.add(txtExistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 80, -1));

        btnModificarLote.setText("Modificar Lote");
        btnModificarLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarLoteActionPerformed(evt);
            }
        });
        jPanel3.add(btnModificarLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 120, -1));

        btnAgregarLote.setText("Agregar Lote");
        btnAgregarLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarLoteActionPerformed(evt);
            }
        });
        jPanel3.add(btnAgregarLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 120, -1));

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

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 420, 120));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 800, 180));

        btnEliminar.setBackground(new java.awt.Color(153, 51, 0));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 530, -1, -1));

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

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        insArticulo();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char c = evt.getKeyChar();
        if((c<'0'||c>'9')&&(c<'A'||c>'Z'))evt.consume();
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

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modArticulo();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAgregarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarLoteActionPerformed
        Lote lote = new Lote();
        lote.setIDArticulo(articulo.getId());
        lote.setInventario(this);
        lote.prepararInsLote();
        lote.setVisible(true);        
        this.dispose();
    }//GEN-LAST:event_btnAgregarLoteActionPerformed

    private void btnModificarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarLoteActionPerformed
        try{          
        
        int RowSeleccionado = TablaExistencias.getSelectedRow();       
        String Identificador = "";
        if (RowSeleccionado > -1){
            Identificador = TablaExistencias.getValueAt(RowSeleccionado, 4).toString();
                Lote lote = new Lote();                
                lote.setInventario(this);
                lote.setIDArticulo(articulo.getId());
                lote.prepararModLote(Identificador);
                lote.setVisible(true);
                this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Favor de seleccionar un dato.");
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            
        }
    }//GEN-LAST:event_btnModificarLoteActionPerformed

    private void btnUnidadNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnidadNuevoActionPerformed
        String Texto = cmbUnidad.getSelectedItem().toString();
        Integer Index = cmbUnidad.getSelectedIndex();
        if(Texto == "Nuevo..."){
            Unidad unidad = new Unidad();
            unidad.setInventario(this);
            unidad.prepararInsUnidad();
            unidad.setVisible(true);   
            this.dispose();
        }else if (Index > 0) {
            Unidad unidad = new Unidad();
            unidad.setInventario(this);
            unidad.prepararModUnidad(Index);
            unidad.setVisible(true);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Favor de seleccionar un dato.");
        }       
    }//GEN-LAST:event_btnUnidadNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliArticulo();
    }//GEN-LAST:event_btnEliminarActionPerformed
        
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
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarLote;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnModificarLote;
    private javax.swing.JButton btnUnidadNuevo;
    private javax.swing.JComboBox<String> cmbUnidad;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel lblExistencia;
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
