/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;
import DataBase.VentaMp;
import Ventanas.Conexion;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author JIGA_
 */
public class MetodosPago extends javax.swing.JFrame {

    /**
     * Creates new form MetodosPago
     */
    public MetodosPago() {
        initComponents();
    }
    
    
    CompletarVenta completarventa = new CompletarVenta();
    VentaMp ventamp = new VentaMp();
    
    public void setCompletarVenta(CompletarVenta parametro){
        completarventa = parametro;
    }
    
    	private void insMDPago(){
        Boolean ValidadNulo = true;        
        if (ValidadNulo){
            updateMDPago();
            if (validadMDPago()){
                Conexion conex = new Conexion();
                String Query = "INSERT INTO `venta_mp`(`Activo`, `Codigo`, `Descripcion`, `FechaCreacion`, `FechaMod`) VALUES ( "
                        + "'1',"
                        + "'" + ventamp.getCodigo() + "',"
                        + "'" + ventamp.getDescripcion() + "',"
                        + "CURRENT_TIMESTAMP,"
                        + "CURRENT_TIMESTAMP)";
                MysqlDataSource dataSource = conex.getConnection();        
                try(Connection conn = dataSource.getConnection()){
                        Statement stmt = conn.createStatement();            
                        stmt.executeUpdate(Query);
                        completarventa.fillCmbMetodoPago();
                        completarventa.setVisible(true);
                        this.dispose();
                }catch(SQLException e){
                        JOptionPane.showMessageDialog(null,e);
                }
            }else{
                JOptionPane.showMessageDialog(null,"El codigo del MDPago esta en uso. Favor de proporcionar otro codigo.");
            }
        }else{
            JOptionPane.showMessageDialog(null,"No se puede dejar campos vacios");
        }
    }    
    private void modMDPago(){
        Boolean ValidadNulo = true;        
        if (ValidadNulo){
            updateMDPago();
            if (validadMDPago()){
                Conexion conex = new Conexion();
                String Query = "UPDATE `venta_mp` SET "
                        + "`Activo`='1',"
                        + "`Codigo`='" + ventamp.getCodigo() + "',"
                        + "`Descripcion`='" + ventamp.getDescripcion() + "' "
                        + "WHERE "
                        + "`ID` = '" + ventamp.getId() + "'";
                MysqlDataSource dataSource = conex.getConnection();        
                try(Connection conn = dataSource.getConnection()){
                        Statement stmt = conn.createStatement();            
                        stmt.executeUpdate(Query);
                        completarventa.fillCmbMetodoPago();
                        completarventa.setVisible(true);
                        this.dispose();
                }catch(SQLException e){
                        JOptionPane.showMessageDialog(null,e);
                }
            }else{
                JOptionPane.showMessageDialog(null,"El codigo del MDPago esta en uso. Favor de proporcionar otro codigo.");
            }
        }else{
            JOptionPane.showMessageDialog(null,"No se puede dejar campos vacios");
        }
        
    }
    private void eliMDPago(){
        Conexion conex = new Conexion();
        String Query = "UPDATE `venta_mp` SET "
                        + "`Activo`='0'"
                        + "WHERE "
                        + "`ID` = '" + ventamp.getId() + "'";
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                stmt.executeUpdate(Query);
                completarventa.fillCmbMetodoPago();
                completarventa.setVisible(true);
                this.dispose();
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
        
    }
    private void updateMDPago(){
        String Codigo = this.txtCodigo.getText();
        String Descripcion = this.txtDescripcion.getText();
        
        ventamp.setCodigo(Codigo);
        ventamp.setDescripcion(Descripcion);        
    }

    public void prepararModMDPago(String IDVentaMP){
        this.btnAgregar.setVisible(false);
        this.btnAgregar.setEnabled(false);
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
                    fillFormulario();
                }
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        } 
    }
    public void prepararInsMDPago(){
        this.btnEliminar.setVisible(false);
        this.btnEliminar.setEnabled(false);
        this.btnModificar.setVisible(false);
        this.btnModificar.setEnabled(false);    
        
    }
    private boolean validadMDPago(){
        
        
        return true;        
    }
    private void fillVentaMP(Integer id, Integer activo, String codigo, String descripcion, Date fechaCreacion, Date fechaMod){
        ventamp = new VentaMp( id, activo, codigo,  descripcion,  fechaCreacion,  fechaMod);
    }     
    private void fillFormulario(){
        this.txtCodigo.setText(ventamp.getCodigo());
        this.txtDescripcion.setText(ventamp.getDescripcion());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Brush Script MT", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("SoftCake");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/back.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 330, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Metodos de pago");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Código:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Descripción:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));

        txtCodigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });
        getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 180, -1));

        txtDescripcion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });
        getContentPane().add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 180, -1));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, -1, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, -1, -1));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cafe.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -2, 690, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        completarventa.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        char c = evt.getKeyChar();
        if(c<'A'||c>'Z')evt.consume();
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char c = evt.getKeyChar();
        if((c<'0'||c>'9')&&(c<'A'||c>'Z'))evt.consume();
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        insMDPago();        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modMDPago();        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliMDPago();        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(MetodosPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MetodosPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MetodosPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MetodosPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MetodosPago().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
