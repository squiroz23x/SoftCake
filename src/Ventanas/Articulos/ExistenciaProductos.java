/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas.Articulos;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Ventanas.*;
import Ventas.GenerarVenta;

/**
 *
 * @author JIGA_
 */
public class ExistenciaProductos extends javax.swing.JFrame {

    /**
     * Creates new form ExistenciaProductos
     */
    public ExistenciaProductos() {
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

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        lblExistencia = new javax.swing.JLabel();
        lblBack = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblCatalogo = new javax.swing.JLabel();
        lblVenta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SoftCake Existencia de productos");
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Brush Script MT", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("SoftCake");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, -1, -1));

        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Descripción", "Precio", "Existencia", "Unidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaProductos);
        if (TablaProductos.getColumnModel().getColumnCount() > 0) {
            TablaProductos.getColumnModel().getColumn(1).setPreferredWidth(150);
            TablaProductos.getColumnModel().getColumn(2).setPreferredWidth(200);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 710, 210));

        lblExistencia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblExistencia.setForeground(new java.awt.Color(255, 255, 255));
        lblExistencia.setText("Existencia de productos");
        getContentPane().add(lblExistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/back.png"))); // NOI18N
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
        });
        getContentPane().add(lblBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 410, -1, -1));

        btnModificar.setBackground(new java.awt.Color(153, 51, 0));
        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, -1, -1));

        btnNuevo.setBackground(new java.awt.Color(153, 51, 0));
        btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 390, 90, -1));

        btnBuscar.setBackground(new java.awt.Color(153, 51, 0));
        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Código de producto:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

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
        getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 120, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, -1, -1));

        txtNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 130, -1));

        lblCatalogo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCatalogo.setForeground(new java.awt.Color(255, 255, 255));
        lblCatalogo.setText("Catalogo de productos");
        getContentPane().add(lblCatalogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 230, -1));

        lblVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/back.png"))); // NOI18N
        lblVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVentaMouseClicked(evt);
            }
        });
        getContentPane().add(lblVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 410, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cafe.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void VentanaNormal()
    {
        lblVenta.setVisible(false);
        lblCatalogo.setVisible(false);
    }
    public void MostrarCatalogo()
    {
        btnNuevo.setVisible(false);
        btnModificar.setVisible(false);
        lblExistencia.setVisible(false);
        lblBack.setVisible(false);
    }
    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        Menu menu = new Menu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblBackMouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:        
    }//GEN-LAST:event_formComponentShown
    
    private String getQueryBuscar(){
        String Query = "SELECT * FROM `articulo` WHERE `Activo` = '1' ";
        String codigoBuscar;
        String nombreBuscar;
        
        codigoBuscar = txtCodigo.getText();
        nombreBuscar = txtNombre.getText();
        
        if (!"".equals(codigoBuscar)){
            Query +="AND `Codigo` = '" + codigoBuscar + "'";
        }else if(!"".equals(nombreBuscar)){
            Query += "AND `Nombre` LIKE '%" + nombreBuscar + "%'";         
        }           
        return Query;
    }
    private void limpiarTablaArticulo(){
        DefaultTableModel model = (DefaultTableModel) TablaProductos.getModel();
        int CountRows = model.getRowCount();        
        for (int i = 0; i<CountRows; i++){
            model.removeRow(0);
        } 
    }
    private void agregarRowTablaArticulo(String Codigo, String Nombre,String Descripcion, String Precio,String Existencia,String Unidad){
        DefaultTableModel mode1TablaProductos = (DefaultTableModel) TablaProductos.getModel();
        mode1TablaProductos.addRow(new Object[]{Codigo,Nombre,Descripcion,Precio,Existencia,Unidad});
    }
    private String getUnidad(Integer Index){
        String Parametro = "";
        String Query = "SELECT * FROM `articulo_unidad` WHERE `ID` = '" + Index + "'";
        Conexion  conex = new Conexion();
        MysqlDataSource dataSource = conex.getConnection();     
        try(Connection conn = dataSource.getConnection()){
            Statement stmt = conn.createStatement();            
            ResultSet ResulQuery = stmt.executeQuery(Query);
            while(ResulQuery.next()){
               Parametro = ResulQuery.getString("NombreCorto");                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        } 
        return Parametro;
    }
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        Conexion conex = new Conexion();
        String Query = getQueryBuscar();
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
            Statement stmt = conn.createStatement();            
            ResultSet ResulQuery = stmt.executeQuery(Query);
            limpiarTablaArticulo();
            while(ResulQuery.next()){
                String Codigo = ResulQuery.getString("Codigo");
                String Nombre = ResulQuery.getString("Nombre");
                String Descripcion = ResulQuery.getString("Descripcion");
                String Precio = ResulQuery.getString("Precio");
                String Existencia = ResulQuery.getString("Existencia");
                String Unidad = getUnidad(ResulQuery.getInt("IDUnidad"));
                agregarRowTablaArticulo(Codigo,Nombre,Descripcion,Precio,Existencia,Unidad);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        } 
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        int RowSeleccionado = TablaProductos.getSelectedRow();       
        String Identificador;
        if (RowSeleccionado > -1){
            Identificador = TablaProductos.getValueAt(RowSeleccionado, 0).toString();
            Inventario articulo = new Inventario();
            articulo.prepararModArticulo(Identificador);
            articulo.Cajas();
            articulo.setVisible(true);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Favor de seleccionar un dato.");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        Inventario registro = new Inventario();
        registro.prepararInsArticulo();
        registro.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char c = evt.getKeyChar();
        if((c<'0'||c>'9')&&(c<'A'||c>'Z'))evt.consume();
        
        int longitud = 25;
        if(txtCodigo.getText().length()>=longitud)
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Este campo no puede ser mayor a 25 caracteres, intente de nuevo", "Error", 0);
            txtCodigo.setText("");
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char c = evt.getKeyChar();
        if((c<'0'||c>'9')&&(c<'A'||c>'Z'))evt.consume();
        
        int longitud = 50;
        if(txtNombre.getText().length()>=longitud)
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Este campo no puede ser mayor a 50 caracteres, intente de nuevo", "Error", 0);
            txtNombre.setText("");
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void lblVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVentaMouseClicked
        Menu menu = new Menu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblVentaMouseClicked

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
            java.util.logging.Logger.getLogger(ExistenciaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExistenciaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExistenciaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExistenciaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //TablaProductosr-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExistenciaProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaProductos;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblCatalogo;
    private javax.swing.JLabel lblExistencia;
    private javax.swing.JLabel lblVenta;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
