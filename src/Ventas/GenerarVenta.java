/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import DataBase.*;
import Ventanas.Articulos.ExistenciaProductos;
import Ventanas.Menu;
import java.math.BigDecimal;
import javax.swing.ImageIcon;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.*;
import Ventanas.Conexion;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JOptionPane;
/**
 *
 * @author JIGA_
 */
public class GenerarVenta extends javax.swing.JFrame implements TableModelListener {

    /**
     * Creates new form Venta
     */
    public void Cajas()
    {
        txtSubtotal.setEditable(false);
        txtIva.setEditable(false);
        txtTotal.setEditable(false);
        txtLetras.setEditable(false);
    }
    public GenerarVenta() {
        initComponents();
         this.setLocationRelativeTo(null);
         setIconImage(new ImageIcon(getClass().getResource("/Imagenes/iconcake.png")).getImage());
         TablaConceptos.getModel().addTableModelListener(this);
    }
    String[] nUnidades = {"UN", "DOS", "TRES", "CUATRO", "CINCO", "SEIS", "SIETE", "OCHO", "NUEVE", "DIEZ", "ONCE", "DOCE", "TRECE", "CATORCE", "QUINCE", "DIECISEIS", "DIECISIETE", "DIECIOCHO", "DIECINUEVE", "VEINTE", "VEINTIUN", "VENTIDOS", "VEINTITRES", "VEINTICUATRO", "VEINTICINCO", "VEINTISEIS", "VEINTISIETE", "VEINTIOCHO", "VEINTINUEVE"};
    String[] nDecenas = {"DIEZ", "VEINTE", "TREINTA", "CUARENTA", "CINCUENTA", "SESENTA", "SETENTA", "OCHENTA", "NOVENTA"};
    String[] nCentenas = {"CIENTO", "DOCIENTOS", "TRECIENTOS", "CUATROCIENTOS", "QUINIENTOS", "SEISCIENTOS","SETECIENTOS","OCHOCIENTOS", "NOVECIENTOS"};
    private char[] nValor;
    String valorLetras="",rta="";
    int bloqueTres=0;
    Venta venta = new Venta();
    ArrayList<VentaConcepto> ventaconcepto = new ArrayList<VentaConcepto>();
    ArrayList<VentaPago> ventapago = new ArrayList<VentaPago>();
    VentaMp ventamp = new VentaMp();
    ArticuloLote articulolote = new ArticuloLote();
    VentaPago indventapago = new VentaPago();
    Articulo articulo = new Articulo();

   public String convertirnumtext(int valor){ 
      
       int nBloque=0;
       String mostrar="";
    char []arregloValor=Integer.toString(valor).toCharArray();
    int[]arregloValores=new int[arregloValor.length];
       for(int i=0;i<arregloValores.length;i++)            
           arregloValores[arregloValor.length-i-1]=Character.getNumericValue(arregloValor[i]);
       int recorrer=0;
           while(arregloValor.length-recorrer!=0){
                int bloqueCero=0;
               int primerDigito=0;
               int segundoDigito=0;
               int tercerDigito=0;
               
               for(int i=0;i<3;i++ ){
                   
                   if(arregloValores[recorrer]!=0){
                   switch (i){
                       case 0:
                           valorLetras=" "+nUnidades[arregloValores[recorrer]-1];
                           primerDigito=arregloValores[recorrer];
                           break;
                       case 1:
                           if(arregloValores[recorrer]<=2){
                           valorLetras=" "+nUnidades[arregloValores[recorrer]*10+primerDigito-1];
                           }else{
                               valorLetras=" "+nDecenas[arregloValores[recorrer]-1]+(primerDigito!=0?" Y":"")+valorLetras;
                           }
                           segundoDigito=arregloValores[recorrer];
                           break;
                       case 2:
                          valorLetras=((primerDigito==0 && segundoDigito==0 && arregloValores[recorrer]==1)?" CIEN":nCentenas[arregloValores[recorrer]-1])+valorLetras; 
                           tercerDigito=arregloValores[recorrer];
                           break;
                   }}
                   else{
                      bloqueCero++; 
                   }
                   if((++recorrer)>arregloValores.length-1)
                   break; 
               }
               switch(nBloque){
                   case 0:
                       mostrar=valorLetras;
                       break;
                   case 1:
                       mostrar=valorLetras+(bloqueCero==3?"":" MIL ")+mostrar;
                       break;
                   case 2:
                       mostrar=valorLetras+((primerDigito==1 && segundoDigito==0 && tercerDigito==0)?" MILLON ":" MILLONES ")+(mostrar.trim().compareTo("")==0?" DE ":"")+mostrar;
                       
               }
               nBloque++;
               
           }
           
           return mostrar+(valor>1?" PESOS":" PESO");
   }
    public String generarNumeroLetras(BigDecimal Numero){        
        
        BigDecimal bd = Numero.setScale(2,BigDecimal.ROUND_HALF_DOWN);
        String textBD = bd.toPlainString();
   
        int radixLoc = textBD.indexOf('.');
        Integer Entereo = Integer.parseInt(textBD.substring(0, radixLoc));
        Integer Decimal = Integer.parseInt(textBD.substring(radixLoc + 1, textBD.length()));
        String Texto = convertirnumtext(Entereo);
        Texto += " CON "+Decimal.toString()+"/100";
        
        
        return Texto;
    }
    public void setTotales(){
        try{        
            BigDecimal SubTotal = new BigDecimal(0);
            BigDecimal IVA = new BigDecimal(0);
            BigDecimal Total = new BigDecimal(0);
            DefaultTableModel model = (DefaultTableModel) TablaConceptos.getModel();
            int CountRows = model.getRowCount();
            int indCantidad = 3;
            int indStock = 5;
            int indPU = 6;
            int indTotal = 7;
            for (int i = 0; i<CountRows; i++){
                if ("".equals(model.getValueAt(i, 3).toString())){ 
                    model.setValueAt("1", i, 2);
                }
                BigDecimal Cantidad = new BigDecimal(model.getValueAt(i, indCantidad).toString()).setScale(0,BigDecimal.ROUND_HALF_DOWN);
                BigDecimal Stock = new BigDecimal(model.getValueAt(i, indStock).toString()).setScale(2,BigDecimal.ROUND_HALF_DOWN);
                if(Cantidad.compareTo(Stock) == 1){
                    model.setValueAt(Stock.setScale(0).toPlainString(), i, indCantidad);
                    Cantidad = Stock.setScale(0,BigDecimal.ROUND_HALF_DOWN);
                }
                if (Cantidad.compareTo(BigDecimal.ZERO) == -1 ){
                    model.setValueAt("1", i, indCantidad);
                    Cantidad = new BigDecimal("1");
                }
                BigDecimal PU = new BigDecimal(model.getValueAt(i, indPU).toString()).setScale(2,BigDecimal.ROUND_HALF_DOWN);
                BigDecimal total = PU.multiply(Cantidad).setScale(2,BigDecimal.ROUND_HALF_DOWN);            
                model.setValueAt(total.setScale(2).toPlainString(), i, indTotal);
                SubTotal = SubTotal.add(total).setScale(2,BigDecimal.ROUND_HALF_DOWN);            
            }
            IVA = SubTotal.multiply(new BigDecimal(0.16)).setScale(2,BigDecimal.ROUND_HALF_DOWN);
            Total = IVA.add(SubTotal).setScale(2,BigDecimal.ROUND_HALF_DOWN);
            String TextoTotalLetras = "";
            if (Total.compareTo(BigDecimal.ZERO) == 1){
                TextoTotalLetras = generarNumeroLetras(Total.setScale(2,BigDecimal.ROUND_HALF_DOWN));
            }
            txtSubtotal.setText(SubTotal.setScale(2,BigDecimal.ROUND_HALF_DOWN).toPlainString());
            txtIva.setText(IVA.setScale(2,BigDecimal.ROUND_HALF_DOWN).toPlainString());
            txtTotal.setText(Total.setScale(2,BigDecimal.ROUND_HALF_DOWN).toPlainString());
            txtLetras.setText(TextoTotalLetras);
            Activo = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
    
    Boolean Activo = true;
     public void tableChanged(TableModelEvent e) {
         if (Activo) {
             Activo = false;
             setTotales();
         }
    }
    public void agregarRowTablaConceptos(String IDArticulo,String IDArticulo_Lote,String Codigo,String Cantidad,String Descripcion, String Stock, String PrecioUnitario){
        DefaultTableModel model = (DefaultTableModel) TablaConceptos.getModel();
        int CountRows = model.getRowCount();
        Boolean Repetido = false;
        String CurrentID;
        for (int i = 0; i<CountRows; i++){
            CurrentID = model.getValueAt(i, 0).toString();
            if(CurrentID.equals(IDArticulo_Lote)){
                Repetido = true;
            }
        }
        if (Repetido){
            JOptionPane.showMessageDialog(null, "Dato Duplicado.");
        }else{
            DefaultTableModel mode1TablaConceptos = (DefaultTableModel) TablaConceptos.getModel();
            BigDecimal cantidadDecimal =  new BigDecimal(Cantidad);
            BigDecimal PUDecimal = new BigDecimal(PrecioUnitario.replaceAll(",", ""));
            BigDecimal Total = PUDecimal.multiply(cantidadDecimal);
            mode1TablaConceptos.addRow(new Object[]{ IDArticulo, IDArticulo_Lote, Codigo, Cantidad, Descripcion,  Stock,  PrecioUnitario, Total.toString()});

        }
    }
	private Integer insVenta(){       
        if (!"".equals(txtCliente.getText()) && !"".equals(txtRfc.getText()) && !"".equals(txtDomicilio.getText()) && !"".equals(txtNumero.getText()) && !"".equals(txtCp.getText()) && !"".equals(txtColonia.getText()) && !"".equals(txtTelefono.getText()) &&  this.TablaConceptos.getRowCount() > 0){
            updateVenta();
            if (validadVenta()){
                Conexion conex = new Conexion();                
                String Query = "INSERT INTO `venta`(`Activo`, `EstadoDoc`, `Cliente`, `RFC`, `Domicilio`, `NumExt`, `NumInt`, `CP`, `Colonia`, `Telefono`, `SubTotal`, `IVA`, `Total`, `FechaElaboracion`, `FechaCreacion`, `FechaMod`) VALUES ("
                        + "'" + venta.getActivo() + "',"
                        + "'" + venta.getEstadoDoc() + "',"
                        + "'" + venta.getCliente()  + "',"
                        + "'" + venta.getRfc() + "',"
                        + "'" + venta.getDomicilio() + "',"
                        + "'" + venta.getNumExt() + "',"
                        + "'" + venta.getNumInt() + "',"
                        + "'" + venta.getCp() + "',"
                        + "'" + venta.getColonia() + "',"
                        + "'" + venta.getTelefono() + "',"
                        + "'" + venta.getSubTotal() + "',"
                        + "'" + venta.getIva() + "',"
                        + "'" + venta.getTotal() + "',"
                        + "CURRENT_TIMESTAMP,"
                        + "CURRENT_TIMESTAMP,"
                        + "CURRENT_TIMESTAMP)";
                MysqlDataSource dataSource = conex.getConnection();        
                try(Connection conn = dataSource.getConnection()){
                    PreparedStatement  stmt = conn.prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
                    Integer IDFuera;
                    IDFuera = stmt.executeUpdate();
                    ResultSet ResulQuery = stmt.getGeneratedKeys();
                    while(ResulQuery.next()){
                        Integer ID = ResulQuery.getInt(1);
                        insVentaConcepto(ID);
                        return ID;
                    }
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null,e);
                }
            }
        }else{
                JOptionPane.showMessageDialog(null,"No se puede dejar campos vacios");
            }
        return 0;
    }
    
    private void insVentaConcepto(Integer IDVenta){
        DefaultTableModel model = (DefaultTableModel) TablaConceptos.getModel();
        int CountRows = model.getRowCount();        
        for (int i = 0; i<CountRows; i++){
            Conexion conex = new Conexion();
            String IDArticulo = model.getValueAt(i, 0).toString();
            String IDArticulo_Lote = model.getValueAt(i, 1).toString();
            String Activo = "1";
            String ProdCodigo = model.getValueAt(i, 2).toString();
            Integer Cantidad = Integer.parseInt(model.getValueAt(i, 3).toString());
            String Descripcion = model.getValueAt(i, 4).toString();
            Integer Stock = Integer.parseInt(model.getValueAt(i, 5).toString());
            String PrecioUnitario = model.getValueAt(i, 6).toString();
            Integer CantidadRestante = Stock - Cantidad;
            String Query = "INSERT INTO `venta_concepto`(`IDVenta`, `IDArticulo`, `IDArticulo_Lote`, `Activo`, `ProdCodigo`, `Cantidad`, `Drescripcion`, `PrecioUnitario`, `FechaCreacion`, `FechaMod`) VALUES ("
                    + "'" + IDVenta + "',"
                    + "'" + IDArticulo + "',"
                    + "'" + IDArticulo_Lote + "',"
                    + "'" + Activo + "',"
                    + "'" + ProdCodigo + "',"
                    + "'" + Cantidad + "',"
                    + "'" + Descripcion + "',"
                    + "'" + PrecioUnitario + "',"
                    + "CURRENT_TIMESTAMP,"
                    + "CURRENT_TIMESTAMP)";
            MysqlDataSource dataSource = conex.getConnection();        
            try(Connection conn = dataSource.getConnection()){
                    Statement stmt = conn.createStatement();            
                    stmt.executeUpdate(Query);
            }catch(SQLException e){
                    JOptionPane.showMessageDialog(null,e);
            }

            String Query1 = "UPDATE `articulo_lote` SET ";
            if (CantidadRestante == 0){
                Query1 += "`Activo` = '0', ";
            }
            Query1 += "`Cantidad` = '"+ CantidadRestante +"' WHERE `articulo_lote`.`ID` = "+IDArticulo_Lote;
            try(Connection conn1 = dataSource.getConnection()){
                    Statement stmt1 = conn1.createStatement();            
                    stmt1.executeUpdate(Query1);
                    Query1 = "UPDATE `articulo` SET `Existencia` = ( SELECT IFNULL(SUM(Cantidad),0) AS Total FROM articulo_lote WHERE Activo = 1 AND IDArticulo = '" + IDArticulo + "' ) WHERE `ID` = '" + IDArticulo + "' ";
                    stmt1 = conn1.createStatement();            
                    stmt1.executeUpdate(Query1);
            }catch(SQLException e){
                    JOptionPane.showMessageDialog(null,e);
            }
        }
         
    }
    private void modVenta(){
       
    }
    private void eliVenta(){
        Conexion conex = new Conexion();
        String Query = "UPDATE `venta` SET `EstadoDoc` = 'CANCELADO' WHERE `ID` = " + venta.getId().toString();
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
            Statement stmt = conn.createStatement();            
            stmt.executeUpdate(Query);
            Query = "UPDATE `venta_pago` SET `Activo` = 0 WHERE `IDVenta` = "+ venta.getId().toString();
            stmt.executeUpdate(Query);
            try{               
                Iterator<VentaConcepto> itVentaConceto = venta.getVentaConceptoCollection().iterator();
                while (itVentaConceto.hasNext()){
                    VentaConcepto vc = itVentaConceto.next();
                    Query = "UPDATE `venta_concepto` SET `Activo`='0' WHERE `ID` = "+ vc.getId().toString();
                    stmt.executeUpdate(Query);
                    long Total = vc.getIDArticuloLote().getCantidad() + vc.getCantidad();
                    Query = "UPDATE `articulo_lote` SET `Cantidad`= '"+Total+" ' WHERE `ID` = "+ vc.getIDArticuloLote().getId().toString();
                    stmt.executeUpdate(Query);
                    String FechaInicio = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                    Query = "UPDATE `articulo_lote` SET `Activo`= 1 WHERE `ID` = "+vc.getIDArticuloLote().getId().toString()+" AND `Cantidad` > 0 AND `FecbaCaducidad` > '"+FechaInicio+"'";
                    stmt.executeUpdate(Query);
                    Query = "UPDATE `articulo` SET `Existencia` = ( SELECT IFNULL(SUM(Cantidad),0) AS Total FROM articulo_lote WHERE Activo = 1 AND IDArticulo = '" + vc.getIDArticulo().getId().toString() + "' ) WHERE `ID` = '" + vc.getIDArticulo().getId().toString() + "' ";
                    stmt.executeUpdate(Query);
                    
                    JOptionPane.showMessageDialog(null,"El socumento se cancelo exitosamente.");
                    
                    Menu menu = new Menu();
                    menu.setVisible(true);
                    this.dispose();                    
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,e);
            }            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }        
    }
    private void updateVenta(){
        Integer Activo = 1;
        String EstadoDoc = "NOPAGADO";
        String Cliente = txtCliente.getText();
        String RFC = txtRfc.getText();
        String Domicilio = txtDomicilio.getText();
        String NumExt = txtNumero.getText();
        String NumInt = "0";
        String CP = txtCp.getText();
        String Colonia = txtColonia.getText();
        String Telefono = txtTelefono.getText();
        BigDecimal SubTotal = new BigDecimal(txtSubtotal.getText()).setScale(2,BigDecimal.ROUND_HALF_DOWN);
        BigDecimal IVA = new BigDecimal(txtIva.getText()).setScale(2,BigDecimal.ROUND_HALF_DOWN);
        BigDecimal Total = new BigDecimal(txtTotal.getText()).setScale(2,BigDecimal.ROUND_HALF_DOWN);
        
        venta.setActivo(Activo);
        venta.setEstadoDoc(EstadoDoc);
        venta.setCliente(Cliente);
        venta.setRfc(RFC);
        venta.setDomicilio(Domicilio);
        venta.setNumExt(NumExt);
        venta.setNumInt(NumInt);
        venta.setCp(CP);
        venta.setColonia(Colonia);
        venta.setTelefono(Telefono);
        venta.setSubTotal(SubTotal);
        venta.setIva(IVA);
        venta.setTotal(Total);
        
        
	}
    public void prepararModVenta(String ID){
        prepararVenta(ID);
        prepararVentaConcepto(venta.getId().toString());
        venta.setVentaConceptoCollection(ventaconcepto);
        prepararVentaPago(venta.getId().toString());
        venta.setVentaPagoCollection(ventapago);
        fillFormulario();
        lblEstadoDoc.setText(venta.getEstadoDoc());
        if ("CANCELADO".equals(venta.getEstadoDoc())){
            btnCanelar.setEnabled(false);
            btnCanelar.setVisible(false);            
        }
        txtCliente.setEnabled(false);
        txtRfc.setEnabled(false);
        txtDomicilio.setEnabled(false);
        txtNumero.setEnabled(false);
        txtCp.setEnabled(false);
        txtColonia.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtSubtotal.setEnabled(false);
        txtIva.setEnabled(false);
        txtTotal.setEnabled(false);
        txtLetras.setEnabled(false);
        btnAgregar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGenerar.setEnabled(false);
        btnAgregar.setVisible(false);
        btnEliminar.setVisible(false);
        btnGenerar.setVisible(false);
        
      
        
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
                    Integer IDArticulo = ResulQuery.getInt("IDArticulo");
                    prepararArticuloLote(IDArticuloLote.toString());
                    prepararArticulo(IDArticulo.toString());
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
    public void prepararArticulo(String Index){
        Conexion conex = new Conexion();
        String Query = "SELECT * FROM `articulo` WHERE `ID`='"+Index+"'";
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
              
                    fillArticulo(id,activo,codigo,nombre,descripcion,precio,sMaximo,sMinimo,existencia,fechaCreacion,fechaMod);
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
        vc.setIDArticulo(articulo);
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
    private void fillArticulo(Integer id, short activo, String codigo, String nombre, String descripcion, BigDecimal precio, int sMaximo, int sMinimo, int existencia, Date fechaCreacion, Date fechaMod){
        articulo = new Articulo(id,activo,codigo,nombre,descripcion,precio,sMaximo,sMinimo,existencia,fechaCreacion,fechaMod);
    }
    private void fillArticuloLote(Integer id, short activo, String codigo, long cantidad, Date fechaElaboracion, Date fecbaCaducidad, Date fechaCreacion, Date fechaMod){
        articulolote = new ArticuloLote(id,activo,codigo,cantidad,fechaElaboracion,fecbaCaducidad,fechaCreacion,fechaMod);
    }
    public void prepararInsVenta(){
        
    }
    private boolean validadVenta(){
        
        return true;        
    }
    private void limpiarTablaTablaConceptos(){
        DefaultTableModel model = (DefaultTableModel) TablaConceptos.getModel();
        int CountRows = model.getRowCount();        
        for (int i = 0; i<CountRows; i++){
            model.removeRow(0);
        } 
    }
    private void agregarRowTTablaConceptos(String IDArticulo, String IDArticuloLote, String Codigo, String Cantidad, String Descripcion, String Stock, String PrecioU, String Total){
        DefaultTableModel model = (DefaultTableModel) TablaConceptos.getModel();
        model.addRow(new Object[]{IDArticulo,IDArticuloLote,Codigo,Cantidad,Descripcion,Stock,PrecioU,Total});
    }
    
    private void fillTablaConceptos(){
        try{
            limpiarTablaTablaConceptos();
            Iterator<VentaConcepto> itVentaConceto = venta.getVentaConceptoCollection().iterator();
            while (itVentaConceto.hasNext()){
                VentaConcepto vc = itVentaConceto.next();
                String IDArticulo = vc.getIDArticulo().getId().toString();
                String IDArticuloLote = vc.getIDArticuloLote().getId().toString();
                String Codigo = vc.getProdCodigo();
                Integer Cantidad = vc.getCantidad();
                String Descripcion = vc.getDrescripcion();
                Integer Stock = vc.getCantidad();
                String PrecioU = vc.getPrecioUnitario().toString();            
                agregarRowTTablaConceptos(IDArticulo, IDArticuloLote, Codigo, Cantidad.toString(), Descripcion, Stock.toString(), PrecioU, "0");          
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }       
        
    }
    private void fillFormulario(){
        txtCliente.setText(venta.getCliente());
        txtRfc.setText(venta.getRfc());
        txtDomicilio.setText(venta.getDomicilio());
        txtNumero.setText(venta.getNumExt());
        txtCp.setText(venta.getCp());
        txtColonia.setText(venta.getColonia());
        txtTelefono.setText(venta.getTelefono());
        txtSubtotal.setText(venta.getSubTotal().toString());
        txtIva.setText(venta.getIva().toString());
        txtTotal.setText(venta.getTotal().toString());
        txtLetras.setText(generarNumeroLetras(venta.getTotal().setScale(2,BigDecimal.ROUND_HALF_DOWN)));
        fillTablaConceptos();
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
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtRfc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDomicilio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCp = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtColonia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaConceptos = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtLetras = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        txtIva = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnGenerar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblEstadoDoc = new javax.swing.JLabel();
        btnCanelar = new javax.swing.JButton();
        txtCatalogo = new javax.swing.JButton();
        txtEfectivo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SoftCake Ventas");
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Generar venta");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/back.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 590, -1, -1));

        jLabel3.setFont(new java.awt.Font("Brush Script MT", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SoftCake");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 620, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nombre del Cliente:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtCliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClienteKeyTyped(evt);
            }
        });
        getContentPane().add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 290, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("RFC:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        txtRfc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtRfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRfcKeyTyped(evt);
            }
        });
        getContentPane().add(txtRfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 290, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Domicilio:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        txtDomicilio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDomicilio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDomicilioKeyTyped(evt);
            }
        });
        getContentPane().add(txtDomicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 490, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Número Ext:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        txtNumero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroKeyTyped(evt);
            }
        });
        getContentPane().add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 40, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("C.P:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, -1, -1));

        txtCp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCpKeyTyped(evt);
            }
        });
        getContentPane().add(txtCp, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 60, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Colonia:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        txtColonia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtColonia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtColoniaKeyTyped(evt);
            }
        });
        getContentPane().add(txtColonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 150, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Teléfono:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, -1, -1));

        txtTelefono.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 150, -1));

        TablaConceptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDArticulo", "IDArticulo_Lote", "Codigo", "Cantidad", "Descripción", "Stock", "Precio Unitario", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaConceptos.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                TablaConceptosHierarchyChanged(evt);
            }
        });
        jScrollPane1.setViewportView(TablaConceptos);
        if (TablaConceptos.getColumnModel().getColumnCount() > 0) {
            TablaConceptos.getColumnModel().getColumn(0).setResizable(false);
            TablaConceptos.getColumnModel().getColumn(1).setResizable(false);
            TablaConceptos.getColumnModel().getColumn(2).setResizable(false);
            TablaConceptos.getColumnModel().getColumn(3).setResizable(false);
            TablaConceptos.getColumnModel().getColumn(4).setResizable(false);
            TablaConceptos.getColumnModel().getColumn(5).setResizable(false);
            TablaConceptos.getColumnModel().getColumn(6).setResizable(false);
            TablaConceptos.getColumnModel().getColumn(7).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 680, 140));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Total en letras:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Subtotal:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 360, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("IVA:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 380, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Total:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 400, -1, -1));

        txtLetras.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtLetras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLetrasKeyTyped(evt);
            }
        });
        getContentPane().add(txtLetras, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 490, -1));

        txtSubtotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, 100, -1));

        txtIva.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 380, 100, -1));

        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, 100, -1));

        btnGenerar.setBackground(new java.awt.Color(153, 51, 0));
        btnGenerar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGenerar.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerar.setText("Generar venta");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 560, -1, -1));

        btnAgregar.setBackground(new java.awt.Color(153, 51, 0));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar Producto");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, -1, -1));

        btnEliminar.setBackground(new java.awt.Color(153, 51, 0));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar Producto");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 370, -1, -1));

        lblEstadoDoc.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        lblEstadoDoc.setForeground(new java.awt.Color(255, 0, 0));
        lblEstadoDoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblEstadoDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 330, 60));

        btnCanelar.setBackground(new java.awt.Color(153, 51, 0));
        btnCanelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCanelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCanelar.setText("Cancelar Venta");
        btnCanelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCanelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 560, 130, -1));

        txtCatalogo.setBackground(new java.awt.Color(153, 51, 0));
        txtCatalogo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtCatalogo.setForeground(new java.awt.Color(255, 255, 255));
        txtCatalogo.setText("Mostrar Catalogo");
        txtCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCatalogoActionPerformed(evt);
            }
        });
        getContentPane().add(txtCatalogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, 130, -1));

        txtEfectivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtEfectivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cafe.jpg"))); // NOI18N
        getContentPane().add(txtEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        Menu menu = new Menu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        Integer ID = insVenta();
        if (ID > 0){
        CompletarVenta completar = new CompletarVenta();
        completar.prepararInsPago(ID.toString());
        completar.Cajas();
        completar.setVisible(true);
        this.dispose();
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        Agregar agregar = new Agregar();
        agregar.setGenerarVenta(this);
        agregar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        DefaultTableModel model = (DefaultTableModel) TablaConceptos.getModel();
        int CountRow = TablaConceptos.getSelectedRow();
        if (CountRow > -1){
            model.removeRow(CountRow);
        }else{
            JOptionPane.showMessageDialog(null, "Favor de seleccionar un dato.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void TablaConceptosHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_TablaConceptosHierarchyChanged
    }//GEN-LAST:event_TablaConceptosHierarchyChanged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       setTotales();
    }//GEN-LAST:event_formWindowOpened

    private void btnCanelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanelarActionPerformed
        eliVenta();
    }//GEN-LAST:event_btnCanelarActionPerformed

    private void txtLetrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLetrasKeyTyped
        txtLetras.setEditable(false);
    }//GEN-LAST:event_txtLetrasKeyTyped

    private void txtClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyTyped
        char c = evt.getKeyChar();
        if((c<'A'||c>'Z')&&(c !=(char)KeyEvent.VK_SPACE))evt.consume();
        
        int longitud = 50;
        if(txtCliente.getText().length()>=longitud)
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Este campo no puede ser mayor a 50 caracteres, intente de nuevo", "Error", 0);
            txtCliente.setText("");
        }
    }//GEN-LAST:event_txtClienteKeyTyped

    private void txtRfcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRfcKeyTyped
        char c = evt.getKeyChar();
       if((c<'0'||c>'9')&&(c<'A'||c>'Z'))evt.consume();
        
        int longitud = 15;
        if(txtRfc.getText().length()>=longitud)
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Este campo no puede ser mayor a 15 caracteres, intente de nuevo", "Error", 0);
            txtRfc.setText("");
        }
    }//GEN-LAST:event_txtRfcKeyTyped

    private void txtDomicilioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDomicilioKeyTyped
        char c = evt.getKeyChar();
        if((c<'0'||c>'9')&&(c<'A'||c>'Z')&&(c !=(char)KeyEvent.VK_SPACE))evt.consume();
        
        int longitud = 80;
        if(txtDomicilio.getText().length()>=longitud)
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Este campo no puede ser mayor a 80 caracteres, intente de nuevo", "Error", 0);
            txtDomicilio.setText("");
        }
    }//GEN-LAST:event_txtDomicilioKeyTyped

    private void txtNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyTyped
        char c = evt.getKeyChar();
        if((c<'0'||c>'9')&&(c<'A'||c>'Z')&&(c !=(char)KeyEvent.VK_SPACE))evt.consume();
        
        int longitud = 10;
        if(txtNumero.getText().length()>=longitud)
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Este campo no puede ser mayor a 10 caracteres, intente de nuevo", "Error", 0);
            txtNumero.setText("");
        }
    }//GEN-LAST:event_txtNumeroKeyTyped

    private void txtCpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpKeyTyped
       char c = evt.getKeyChar();
        if(c<'0'||c>'9')evt.consume();
        
        int longitud = 10;
        if(txtCp.getText().length()>=longitud)
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Este campo no puede ser mayor a 10 caracteres, intente de nuevo", "Error", 0);
            txtCp.setText("");
        }
    }//GEN-LAST:event_txtCpKeyTyped

    private void txtColoniaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColoniaKeyTyped
         char c = evt.getKeyChar();
        if((c<'0'||c>'9')&&(c<'A'||c>'Z')&&(c !=(char)KeyEvent.VK_SPACE))evt.consume();
        
        int longitud = 25;
        if(txtColonia.getText().length()>=longitud)
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Este campo no puede ser mayor a 25 caracteres, intente de nuevo", "Error", 0);
            txtColonia.setText("");
        }
    }//GEN-LAST:event_txtColoniaKeyTyped

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'||c>'9')evt.consume();
        
        int longitud = 25;
        if(txtTelefono.getText().length()>=longitud)
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Este campo no puede ser mayor a 25 caracteres, intente de nuevo", "Error", 0);
            txtTelefono.setText("");
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCatalogoActionPerformed
        ExistenciaProductos existencia = new ExistenciaProductos();
        existencia.MostrarCatalogo();
        existencia.setComopVenta(this);
        existencia.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_txtCatalogoActionPerformed

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
            java.util.logging.Logger.getLogger(GenerarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerarVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaConceptos;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCanelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstadoDoc;
    private javax.swing.JButton txtCatalogo;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtCp;
    private javax.swing.JTextField txtDomicilio;
    private javax.swing.JLabel txtEfectivo;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtLetras;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRfc;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
