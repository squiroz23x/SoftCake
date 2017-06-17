/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Empleados.ControlEmpleados;
import Empleados.RegistroUsuario;
import Ventanas.Articulos.ConversionProductos;
import Ventas.GenerarVenta;
import Ventanas.Articulos.ExistenciaProductos;
import Ventas.BuscarVenta;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import Ventanas.Conexion;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author JIGA_
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
         this.setLocationRelativeTo(null);
         setIconImage(new ImageIcon(getClass().getResource("/Imagenes/iconcake.png")).getImage());
    }
    
    private void modQuery(String Query){
        Conexion conex = new Conexion();
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                stmt.executeUpdate(Query);
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void actualizarCaducados(){
        String FECHA = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        String Query = "SELECT `ID`,`IDArticulo` FROM `articulo_lote` WHERE `Activo` = 1 AND `FecbaCaducidad` < '"+FECHA+"'";
        Conexion conex = new Conexion();
        MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                Statement stmt = conn.createStatement();            
                ResultSet ResulQuery = stmt.executeQuery(Query);
                while(ResulQuery.next()){
                    Integer ID = ResulQuery.getInt("ID");
                    Integer IDArticulo = ResulQuery.getInt("IDArticulo");
                    modQuery("UPDATE `articulo_lote` SET `Activo` = 0 WHERE `ID` = "+ID.toString());
                    modQuery("UPDATE `articulo` SET `Existencia` = ( SELECT IFNULL(SUM(Cantidad),0) AS Total FROM articulo_lote WHERE Activo = 1 AND IDArticulo = '" + IDArticulo.toString() + "' ) WHERE `ID` = '" + IDArticulo.toString() + "' ");
                                        
                }                
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void fillTablaCaducidad(){
        Calendar FI = Calendar.getInstance();
        Integer Day = FI.get(Calendar.DAY_OF_MONTH);
        Day += 3;
        FI.set(Calendar.DAY_OF_MONTH, Day);
        String FECHA = new SimpleDateFormat("yyyy-MM-dd").format(FI.getTime());
        String Query = "SELECT `articulo`.`Codigo` AS CodigoArticulo,`articulo`.`Nombre`,`articulo`.`Precio`,`articulo`.`Existencia`, `articulo_lote`.`Codigo` AS CodigoLote,`articulo_lote`.`Cantidad`,`articulo_lote`.`FechaElaboracion`,`articulo_lote`.`FecbaCaducidad` "
                + "FROM `articulo` "
                + "LEFT JOIN `articulo_lote` ON `articulo_lote`.`IDArticulo` = `articulo`.`ID` "
                + "WHERE (`articulo_lote`.`Activo` = 1 AND `articulo_lote`.`FecbaCaducidad` < '"+FECHA+"') "
                + "ORDER by `articulo_lote`.`FecbaCaducidad` ";
        Conexion conex = new Conexion();
	MysqlDataSource dataSource = conex.getConnection();        
        try(Connection conn = dataSource.getConnection()){
                limpiarTablaCaducidad();
                Statement stmt = conn.createStatement();            
                ResultSet ResulQuery = stmt.executeQuery(Query);
                while(ResulQuery.next()){
                    String CodigoArticulo = ResulQuery.getString("CodigoArticulo");
                    String Nombre = ResulQuery.getString("Nombre");
                    BigDecimal Precio = ResulQuery.getBigDecimal("Precio");
                    Integer Existencia = ResulQuery.getInt("Existencia");
                    String CodigoLote = ResulQuery.getString("CodigoLote");
                    BigDecimal Cantidad = ResulQuery.getBigDecimal("Cantidad");
                    Date FechaElaboracion = ResulQuery.getDate("FechaElaboracion");
                    Date FechaCaducidad = ResulQuery.getDate("FecbaCaducidad");
                    String FECHA1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaElaboracion);
                    String FECHA2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaCaducidad);
                    agregarRowTablaCaducidad(CodigoArticulo,Nombre,Precio.toString(),Existencia.toString(),CodigoLote,Cantidad.toString(),FECHA1,FECHA2);
                }
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    
    private void limpiarTablaCaducidad(){
        DefaultTableModel model = (DefaultTableModel) TablaCaducidad.getModel();
        int CountRows = model.getRowCount();        
        for (int i = 0; i<CountRows; i++){
            model.removeRow(0);
        } 
    }
    private void agregarRowTablaCaducidad(String CodigoArticulo,String Nombre,String Precio,String Existencia,String CodigoLote,String Cantidad,String FechaElaboracion,String FechaCaducidad){
        DefaultTableModel model = (DefaultTableModel) TablaCaducidad.getModel();
        model.addRow(new Object[]{CodigoArticulo,Nombre,Precio,Existencia,CodigoLote,Cantidad,FechaElaboracion,FechaCaducidad});
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVenta = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnControl = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCaducidad = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SoftCake Menú principal");
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/venta.png"))); // NOI18N
        btnVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentaActionPerformed(evt);
            }
        });
        getContentPane().add(btnVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        btnInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/inventario.png"))); // NOI18N
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, -1, -1));

        btnControl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/empleado.png"))); // NOI18N
        btnControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnControlActionPerformed(evt);
            }
        });
        getContentPane().add(btnControl, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Softcake Main Menu");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Generar venta");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Control de inventario");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Gestión de personal");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 130, -1));

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logout_1.png"))); // NOI18N
        btnCerrar.setMaximumSize(new java.awt.Dimension(750, 430));
        btnCerrar.setMinimumSize(new java.awt.Dimension(750, 430));
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cerrar sesión");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 20, -1));

        jLabel6.setFont(new java.awt.Font("Brush Script MT", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("SoftCake");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        TablaCaducidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Lote", "Nombre", "Precio", "Existencia", "Codigo Lote", "Cantidad", "Elaboracion", "Caducidad"
            }
        ));
        jScrollPane1.setViewportView(TablaCaducidad);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 790, 120));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/about.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Acerca de SoftCake");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, -1, -1));

        btnSalir.setBackground(new java.awt.Color(153, 51, 0));
        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, -1, -1));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cafe.jpg"))); // NOI18N
        lblFondo.setMaximumSize(new java.awt.Dimension(750, 430));
        lblFondo.setMinimumSize(new java.awt.Dimension(750, 430));
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 440));

        jMenu1.setText("Venta");

        jMenuItem1.setText("Generar venta");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem12.setText("Buscar Venta");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Inventario");

        jMenuItem3.setText("Existencias");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem13.setText("Convertir Productos");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Usuario");

        jMenuItem10.setText("Control de empleados");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem11.setText("Agregar usuario");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Ayuda");

        jMenuItem8.setText("Acerca de SoftCake");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem8);

        jMenuItem9.setText("Salir de SoftCake");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentaActionPerformed
       GenerarVenta venta = new GenerarVenta();
       venta.Cajas();
       venta.Boton();
       venta.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btnVentaActionPerformed

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
       ExistenciaProductos existencia = new ExistenciaProductos();
       existencia.VentanaNormal();
       existencia.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btnInventarioActionPerformed

    private void btnControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnControlActionPerformed
     ControlEmpleados control = new ControlEmpleados();
        control.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnControlActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        About about = new About();
        about.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        About acerca = new About();
        acerca.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        RegistroUsuario registro = new RegistroUsuario();
        registro.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        GenerarVenta venta = new GenerarVenta();
        venta.setVisible(true);
        venta.Boton();
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        ExistenciaProductos existencia = new ExistenciaProductos();
        existencia.setVisible(true);
        this.disable();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        ControlEmpleados control = new ControlEmpleados();
        control.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        BuscarVenta buscar = new BuscarVenta();
        buscar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        actualizarCaducados();
        fillTablaCaducidad();
    }//GEN-LAST:event_formComponentShown

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        ConversionProductos convertir = new ConversionProductos();
        convertir.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaCaducidad;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnControl;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVenta;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondo;
    // End of variables declaration//GEN-END:variables
}
