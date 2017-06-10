/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import DataBase.*;
import javax.swing.ImageIcon;
import Ventanas.Conexion;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author JIGA_
 */
public class CompletarVenta extends javax.swing.JFrame {

    /**
     * Creates new form CompletarVenta
     */
     Venta venta = new Venta();
     ArrayList<VentaConcepto> ventaconcepto = new ArrayList<VentaConcepto>();
     ArrayList<VentaPago> ventapago = new ArrayList<VentaPago>();
     VentaMp ventamp = new VentaMp();
     ArticuloLote articulolote = new ArticuloLote();
     
    public CompletarVenta() {
        initComponents();
        this.setLocationRelativeTo(null);
         setIconImage(new ImageIcon(getClass().getResource("/Imagenes/iconcake.png")).getImage());
    }
	private void insPago(){
        Boolean ValidadNulo = true;        
        if (ValidadNulo){
            updatePago();
            if (validadPago()){
                Conexion conex = new Conexion();
                String Query = "";
                MysqlDataSource dataSource = conex.getConnection();        
                try(Connection conn = dataSource.getConnection()){
                        Statement stmt = conn.createStatement();            
                        stmt.executeUpdate(Query);
                }catch(SQLException e){
                        JOptionPane.showMessageDialog(null,e);
                }
            }else{
                JOptionPane.showMessageDialog(null,"El codigo del Pago esta en uso. Favor de proporcionar otro codigo.");
            }
        }else{
            JOptionPane.showMessageDialog(null,"No se puede dejar campos vacios");
        }
    }    
    private void modPago(){
        Boolean ValidadNulo = true;        
        if (ValidadNulo){
            updatePago();
            if (validadPago()){
                Conexion conex = new Conexion();
                String Query = "";
                MysqlDataSource dataSource = conex.getConnection();        
                try(Connection conn = dataSource.getConnection()){
                        Statement stmt = conn.createStatement();            
                        stmt.executeUpdate(Query);
                }catch(SQLException e){
                        JOptionPane.showMessageDialog(null,e);
                }
            }else{
                JOptionPane.showMessageDialog(null,"El codigo del Pago esta en uso. Favor de proporcionar otro codigo.");
            }
        }else{
            JOptionPane.showMessageDialog(null,"No se puede dejar campos vacios");
        }
        
    }
    private void eliPago(){
        Conexion conex = new Conexion();
        String Query = "";
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                stmt.executeUpdate(Query);
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
        
    }
    private void updatePago(){
        
	}
    public void prepararModPago(){
        Conexion conex = new Conexion();
        String Query = "";
		MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                ResultSet ResulQuery = stmt.executeQuery(Query);
                while(ResulQuery.next()){

                }
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        } 
    }
    public void prepararInsPago(String IDVenta){
        prepararVenta(IDVenta);
        prepararVentaConcepto(venta.getId().toString());
        venta.setVentaConceptoCollection(ventaconcepto);
        prepararVentaPago(venta.getId().toString());
        venta.setVentaPagoCollection(ventapago);
        fillFormulario();
        
    }
    
    public void prepararVenta(String IDVenta){
        Conexion conex = new Conexion();
        String Query = "SELECT * FROM `venta` WHERE `ID` = " + IDVenta;
	MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                ResultSet ResulQuery = stmt.executeQuery(Query);
                while(ResulQuery.next()){
                    Integer id = ResulQuery.getInt("ID");
                    int activo = ResulQuery.getInt("Activo");
                    String estadoDoc = ResulQuery.getString("EstadoDoc");
                    String cliente = ResulQuery.getString("Cliente");
                    String rfc = ResulQuery.getString("RFC");
                    String domicilio = ResulQuery.getString("Domicilio");
                    String numExt = ResulQuery.getString("NumExt");
                    String numInt = ResulQuery.getString("NumInt");
                    String cp = ResulQuery.getString("CP");
                    String colonia = ResulQuery.getString("Colonia");
                    String telefono = ResulQuery.getString("Telefono");
                    BigDecimal subTotal = ResulQuery.getBigDecimal("SubTotal");
                    BigDecimal iva = ResulQuery.getBigDecimal("IVA");
                    BigDecimal total = ResulQuery.getBigDecimal("Total");
                    Date fechaElaboracion = ResulQuery.getDate("FechaElaboracion");
                    Date fechaCreacion = ResulQuery.getDate("FechaCreacion");
                    Date fechaMod = ResulQuery.getDate("FechaMod");
                    fillVenta(id, activo,  estadoDoc,  cliente,  rfc,  domicilio,  numExt,  numInt,  cp,  colonia,  telefono,  subTotal,  iva,  total,  fechaElaboracion,  fechaCreacion,  fechaMod);
                }
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
        
    }
    public void prepararVentaConcepto(String IDVenta){
        Conexion conex = new Conexion();
        String Query = "SELECT * FROM `venta_concepto` WHERE `IDVenta` = " + IDVenta;
	MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                ResultSet ResulQuery = stmt.executeQuery(Query);
                ventaconcepto.clear();
                while(ResulQuery.next()){
                    Integer id = ResulQuery.getInt("ID");
                    int activo = ResulQuery.getInt("Activo");
                    String prodCodigo = ResulQuery.getString("ProdCodigo");
                    int cantidad = ResulQuery.getInt("Cantidad");
                    String drescripcion = ResulQuery.getString("Drescripcion");
                    BigDecimal precioUnitario = ResulQuery.getBigDecimal("PrecioUnitario");
                    Date fechaCreacion = ResulQuery.getDate("FechaCreacion");
                    Date fechaMod = ResulQuery.getDate("FechaMod");
                    Integer IDArticuloLote = ResulQuery.getInt("IDArticulo_Lote");
                    prepararArticuloLote(IDArticuloLote.toString());
                    fillVentaConcepto( id,  activo,  prodCodigo,  cantidad,  drescripcion,  precioUnitario,  fechaCreacion,  fechaMod);
                }
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    public void prepararVentaMP(String IDVentaMP){
        Conexion conex = new Conexion();
        String Query = "SELECT * FROM `venta_mp` WHERE `ID` = " + IDVentaMP;
	MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                ResultSet ResulQuery = stmt.executeQuery(Query);
                while(ResulQuery.next()){
                    Integer id = ResulQuery.getInt("ID");
                    Integer activo = ResulQuery.getInt("Activo");
                    String codigo = ResulQuery.getString("Codigo");
                    String descripcion = ResulQuery.getString("Descripcion");
                    Date fechaCreacion = ResulQuery.getDate("FechaCreacion");
                    Date fechaMod = ResulQuery.getDate("FechaMod");
                    fillVentaMP( id, activo,  codigo,  descripcion,  fechaCreacion,  fechaMod);          
                }
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    public void prepararVentaPago(String IDVenta){
        Conexion conex = new Conexion();
        String Query = "SELECT * FROM `venta_pago` WHERE `IDVenta` = " + IDVenta;
	MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                ResultSet ResulQuery = stmt.executeQuery(Query);
                ventapago.clear();
                while(ResulQuery.next()){
                    Integer id = ResulQuery.getInt("ID");
                    int activo = ResulQuery.getInt("Activo");
                    BigDecimal monto = ResulQuery.getBigDecimal("Monto");
                    String autorizo = ResulQuery.getString("Autorizo");
                    Date fechaPago = ResulQuery.getDate("FechaPago");
                    Date fechaCreacion = ResulQuery.getDate("FechaCreacion");
                    Date fechaMod = ResulQuery.getDate("FechaMod");
                    Integer IDVentaMP = ResulQuery.getInt("IDVenta_MP");
                    prepararVentaMP(IDVentaMP.toString());
                    fillVentaPago( id,  activo,  monto, autorizo,  fechaPago,  fechaCreacion,  fechaMod);
                  }
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
        public void prepararArticuloLote(String Index){
        Conexion conex = new Conexion();
        String Query = "SELECT * FROM `articulo_lote` WHERE `ID` = '" + Index + "'";
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
    private void fillVenta(Integer id, int activo, String estadoDoc, String cliente, String rfc, String domicilio, String numExt, String numInt, String cp, String colonia, String telefono, BigDecimal subTotal, BigDecimal iva, BigDecimal total, Date fechaElaboracion, Date fechaCreacion, Date fechaMod){
        venta = new Venta( id,  activo,  estadoDoc,  cliente,  rfc,  domicilio,  numExt,  numInt,  cp,  colonia,  telefono,  subTotal,  iva,  total,  fechaElaboracion,  fechaCreacion,  fechaMod);
    } 
    private void fillVentaConcepto(Integer id, int activo, String prodCodigo, int cantidad, String drescripcion, BigDecimal precioUnitario, Date fechaCreacion, Date fechaMod){
        VentaConcepto vc = new VentaConcepto( id, activo,  prodCodigo,  cantidad,  drescripcion,  precioUnitario,  fechaCreacion,  fechaMod);
        vc.setIDArticuloLote(articulolote);
        ventaconcepto.add(vc);
    } 
    private void fillVentaMP(Integer id, Integer activo, String codigo, String descripcion, Date fechaCreacion, Date fechaMod){
        ventamp = new VentaMp( id, activo, codigo,  descripcion,  fechaCreacion,  fechaMod);
    } 
    private void fillVentaPago(Integer id, int activo, BigDecimal monto, String autorizo, Date fechaPago, Date fechaCreacion, Date fechaMod){
        VentaPago vp = new VentaPago( id,  activo,  monto, autorizo,  fechaPago,  fechaCreacion,  fechaMod);
        vp.setIDVentaMP(ventamp);
        ventapago.add(vp);
    }
    private void fillArticuloLote(Integer id, short activo, String codigo, long cantidad, Date fechaElaboracion, Date fecbaCaducidad, Date fechaCreacion, Date fechaMod){
        ArticuloLote articulolote = new ArticuloLote(id,activo,codigo,cantidad,fechaElaboracion,fecbaCaducidad,fechaCreacion,fechaMod);
    }
    
    
    private void fillTablaVenta(String Monto, String Metodo, String Autorizo, String FechaPago){
        DefaultTableModel mode1TablaVenta = (DefaultTableModel) TablaVenta.getModel();
        mode1TablaVenta.addRow(new Object[]{Monto,Metodo,Autorizo,FechaPago});        
    }
    public void fillTablaVentas(){
        limpiarTablaVenta();
        Iterator<VentaPago> itventapago = venta.getVentaPagoCollection().iterator();
        while (itventapago.hasNext()){
            VentaPago Pago = itventapago.next();
            String Monto = Pago.getMonto().setScale(2,BigDecimal.ROUND_HALF_DOWN).toString();
            String Metodo = Pago.getIDVentaMP().getDescripcion();
            String Autorizo = Pago.getAutorizo();
            String FechaPago = Pago.getFechaPago().toString();
            fillTablaVenta(Monto, Metodo, Autorizo, FechaPago);
        }
    }
     private void limpiarTablaVenta(){
        DefaultTableModel model = (DefaultTableModel) TablaVenta.getModel();
        int CountRows = model.getRowCount();        
        for (int i = 0; i<CountRows; i++){
            model.removeRow(0);
        } 
    }
    
    private boolean validadPago(){
        
        return true;        
    }

    private BigDecimal obtenerTotalPagado(){
        BigDecimal TotalPagado = BigDecimal.ZERO;
        DefaultTableModel model = (DefaultTableModel) TablaVenta.getModel();
        int CountRows = model.getRowCount();        
        for (int i = 0; i<CountRows; i++){
            BigDecimal Pago = new BigDecimal(model.getValueAt(i, 0).toString()).setScale(2,BigDecimal.ROUND_HALF_DOWN);
            TotalPagado = TotalPagado.add(Pago).setScale(2,BigDecimal.ROUND_HALF_DOWN);
        } 
        return TotalPagado.setScale(2,BigDecimal.ROUND_HALF_DOWN);
    }
    private void fillFormulario(){
        fillCmbMetodoPago();
        String IDVenta = venta.getId().toString();
        String Cliente = venta.getCliente();
        String RFC = venta.getRfc();
        String TotalFacturado = venta.getTotal().toString();
        fillTablaVentas();
        String TotalPagado = obtenerTotalPagado().toString();
        BigDecimal Totalpagar = new BigDecimal(TotalFacturado).subtract(new BigDecimal(TotalPagado));
        String TotalAPagar = Totalpagar.setScale(2,BigDecimal.ROUND_HALF_DOWN).toString();     
        
        txtVentaNumero.setText(IDVenta);
        txtCliente.setText(Cliente);
        txtRFC.setText(RFC);
        txtTotalFacturado.setText(TotalFacturado);
        txtTotalPAgado.setText(TotalPagado);
        txtTotalPagar.setText(TotalAPagar);
        
    }
     public void fillCmbMetodoPago(){
        Conexion conex = new Conexion();
        String Query = "SELECT * FROM `venta_mp` WHERE `Activo` = '1' ORDER BY `ID`";
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                ResultSet ResulQuery = stmt.executeQuery(Query);
                cmbMetodoPago.removeAllItems();
                cmbMetodoPago.addItem("Seleccionar");
                while(ResulQuery.next()){
                    cmbMetodoPago.addItem(ResulQuery.getString("Descripcion"));
                }
                cmbMetodoPago.addItem("Nuevo...");
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
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

        jLabel2 = new javax.swing.JLabel();
        txtVentaNumero = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtRFC = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaVenta = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTotalFacturado = new javax.swing.JTextField();
        txtTotalPAgado = new javax.swing.JTextField();
        txtTotalPagar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbMetodoPago = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtVendedor = new javax.swing.JTextField();
        btnPagar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnMetodoPago = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SoftCake Completar venta");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Venta no.:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        txtVentaNumero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtVentaNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 150, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cliente:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("RFC:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        txtCliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 150, -1));

        txtRFC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtRFC, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 150, -1));

        TablaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MONTO PAGADO", "METODO DE PAGO", "PERSONA QUE AUTORIZO", "FECHA DE PAGO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaVenta);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, -1, 60));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total facturado:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total pagado:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total a pagar:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        txtTotalFacturado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalFacturado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalFacturadoActionPerformed(evt);
            }
        });
        getContentPane().add(txtTotalFacturado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 150, -1));

        txtTotalPAgado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalPAgado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPAgadoActionPerformed(evt);
            }
        });
        getContentPane().add(txtTotalPAgado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 150, -1));

        txtTotalPagar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 150, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Monto a pagar:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Método de pago:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        cmbMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Efectivo", "Tarjeta" }));
        getContentPane().add(cmbMetodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 150, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Autorizado por:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        txtVendedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 400, 250, -1));

        btnPagar.setBackground(new java.awt.Color(153, 51, 0));
        btnPagar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnPagar.setForeground(new java.awt.Color(255, 255, 255));
        btnPagar.setText("PAGAR");
        getContentPane().add(btnPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, -1, -1));

        btnCerrar.setBackground(new java.awt.Color(153, 51, 0));
        btnCerrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 450, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/back.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 550, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Completar venta");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        txtMonto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });
        getContentPane().add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 150, -1));

        jLabel13.setFont(new java.awt.Font("Brush Script MT", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("SoftCake");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 580, -1, 20));

        btnMetodoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMetodoPagoActionPerformed(evt);
            }
        });
        getContentPane().add(btnMetodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 20, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cafe.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, 780, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalFacturadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalFacturadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalFacturadoActionPerformed

    private void txtTotalPAgadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalPAgadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPAgadoActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        GenerarVenta venta = new GenerarVenta();
        venta.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

    private void btnMetodoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMetodoPagoActionPerformed
        String Texto = cmbMetodoPago.getSelectedItem().toString();
        Integer Index = cmbMetodoPago.getSelectedIndex();
        if(Texto == "Nuevo..."){

        }else if (Index > 0) {

        }else{
            JOptionPane.showMessageDialog(null, "Favor de seleccionar un dato.");
        } 
    }//GEN-LAST:event_btnMetodoPagoActionPerformed

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
            java.util.logging.Logger.getLogger(CompletarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompletarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompletarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompletarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompletarVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaVenta;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnMetodoPago;
    private javax.swing.JButton btnPagar;
    private javax.swing.JComboBox<String> cmbMetodoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtRFC;
    private javax.swing.JTextField txtTotalFacturado;
    private javax.swing.JTextField txtTotalPAgado;
    private javax.swing.JTextField txtTotalPagar;
    private javax.swing.JTextField txtVendedor;
    private javax.swing.JTextField txtVentaNumero;
    // End of variables declaration//GEN-END:variables
}
