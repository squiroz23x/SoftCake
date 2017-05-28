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
public class Unidad extends javax.swing.JFrame {

    /**
     * Creates new form Unidad
     */
    public Unidad() {
        initComponents();
    }
    
    Inventario inventario = new Inventario();
    ArticuloUnidad articulounidad = new ArticuloUnidad();
    
    public void setInventario(Inventario parametro){
        inventario = parametro;
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
        jLabel3 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNombreCorto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("UNIDAD");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Descripción:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));
        getContentPane().add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 220, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre corto:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, -1));
        getContentPane().add(txtNombreCorto, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 220, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/back.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 380, -1, -1));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, -1, -1));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, -1, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cafe.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        inventario.fillCmbUnidad();
        inventario.setVisible(true);                
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked
    
    private void insUnidad(){
        if (txtDescripcion.getText() != null && txtNombreCorto.getText() != null){
            updateUnidad();
            Conexion conex = new Conexion();
            String Query = "INSERT INTO `articulo_unidad` (`ID`, `Activo`, `Descripcion`, `NombreCorto`, `FechaCreacion`, `FechaMod`) VALUES ("
                    + "NULL, "
                    + "'" + articulounidad.getActivo() + "', "
                    + "'" + articulounidad.getDescripcion() + "', "
                    + "'" + articulounidad.getNombreCorto() + "', "
                    + "CURRENT_TIMESTAMP, "
                    + "CURRENT_TIMESTAMP);";
            MysqlDataSource dataSource = conex.getConnection();        
            try(Connection conn = dataSource.getConnection()){
                    Statement stmt = conn.createStatement();            
                    stmt.executeUpdate(Query);
                    JOptionPane.showMessageDialog(null,"Agregado correctamente.");
                    inventario.fillCmbUnidad();
                    inventario.setVisible(true);
                    this.dispose();
            }catch(SQLException e){
                    JOptionPane.showMessageDialog(null,e);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Favor de proppraosjdnas");
        }
        
    }    
    private void modUnidad(){
        if (!"".equals(txtDescripcion.getText()) && !"".equals(txtNombreCorto.getText())){
            updateUnidad();
            Conexion conex = new Conexion();
            String Query = "UPDATE `articulo_unidad` SET "
                    + "`ID`='" + articulounidad.getId() + "',"
                    + "`Activo`='" + articulounidad.getActivo() + "',"
                    + "`Descripcion`='" + articulounidad.getDescripcion() + "',"
                    + "`NombreCorto`='" + articulounidad.getNombreCorto() + "'"
                    + "WHERE `ID`='" + articulounidad.getId() + "'";
            MysqlDataSource dataSource = conex.getConnection();        
            try(Connection conn = dataSource.getConnection()){
                    Statement stmt = conn.createStatement();            
                    stmt.executeUpdate(Query);
                    JOptionPane.showMessageDialog(null,"Modificado correctamente.");
                    inventario.fillCmbUnidad();
                    inventario.setVisible(true);

                    this.dispose();
            }catch(SQLException e){
                    JOptionPane.showMessageDialog(null,e);
            } 
        }else{
            JOptionPane.showMessageDialog(null,"Favor de proppraosjdnas");
        }
        
        
        
    }
    private void eliUnidad(){
        Conexion conex = new Conexion();
        String Query = "UPDATE `articulo_unidad` SET "
                + "`Activo`='0',"
                + "WHERE `ID` = '" + articulounidad.getId() + "'";
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                stmt.executeUpdate(Query);
                JOptionPane.showMessageDialog(null,"Eliminado correctamente.");
                inventario.fillCmbUnidad();
                inventario.setVisible(true);                
                this.dispose();
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        } 
    }
    private void updateUnidad(){
        Integer id = articulounidad.getId();
        short activo = 1;
        String descripcion = txtDescripcion.getText();
        String nombreCorto = txtNombreCorto.getText();
        Date fechaCreacion = articulounidad.getFechaCreacion();
        Date fechaMod = articulounidad.getFechaMod();
        fillArticuloUnidad( id,  activo,  descripcion,  nombreCorto,  fechaCreacion,  fechaMod);        
    }
    public void prepararModUnidad(Integer ID){
        this.btnAgregar.setVisible(false);
        Conexion conex = new Conexion();
        String Query = "SELECT * FROM `articulo_unidad` WHERE `Activo` = '1' AND `ID` = '"+ ID +"'";
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                ResultSet ResulQuery = stmt.executeQuery(Query);
                while(ResulQuery.next()){
                    Integer id = ResulQuery.getInt("ID");
                    short activo = ResulQuery.getShort("Activo");
                    String descripcion = ResulQuery.getString("Descripcion");
                    String nombreCorto = ResulQuery.getString("NombreCorto");
                    Date fechaCreacion = ResulQuery.getDate("FechaCreacion");
                    Date fechaMod = ResulQuery.getDate("FechaMod");
                    fillArticuloUnidad( id,  activo,  descripcion,  nombreCorto,  fechaCreacion,  fechaMod);
                }
                fillFormulario();
                this.btnAgregar.setVisible(false);
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
     }
    public void prepararInsUnidad(){
        this.btnModificar.setVisible(false);
        this.btnEliminar.setVisible(false);
    }
    private void validadUnidad(){
        
    }
    private void fillUnidad(){
        
    }
    private void fillFormulario(){
        txtDescripcion.setText(articulounidad.getDescripcion());
        txtNombreCorto.setText(articulounidad.getNombreCorto());        
    }
    private void fillArticuloUnidad(Integer id, short activo, String descripcion, String nombreCorto, Date fechaCreacion, Date fechaMod){
        articulounidad = new ArticuloUnidad(id,activo,descripcion,nombreCorto,fechaCreacion,fechaMod);
    }
    
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        insUnidad();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modUnidad();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliUnidad();
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
            java.util.logging.Logger.getLogger(Unidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Unidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Unidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Unidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Unidad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombreCorto;
    // End of variables declaration//GEN-END:variables
}
