/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

/**
 *
 * @author JIGA_
 */
public class ControlEmpleados extends javax.swing.JFrame {

    /**
     * Creates new form ControlEmpleados
     */
    public ControlEmpleados() {
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

        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdEmpleado = new javax.swing.JTextField();
        txtNombreEmpleado = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaEmpleados = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtPaterno = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtMaterno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SoftCake Lista de empleados");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Brush Script MT", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("SoftCake");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 550, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/back.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 520, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Lista de empleados");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        txtIdEmpleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtIdEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 130, -1));

        txtNombreEmpleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtNombreEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 130, -1));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 110, -1, -1));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 470, -1, -1));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 470, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Numero de Identificacion:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        TablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identificador ", "Nombre", "Apellido Paterno", "Apellido Materno"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TablaEmpleados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TablaEmpleados.setColumnSelectionAllowed(false);
        jScrollPane2.setViewportView(TablaEmpleados);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 790, 310));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Apellido Paterno:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));
        getContentPane().add(txtPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 130, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Apellido Materno:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));
        getContentPane().add(txtMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 130, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cafe.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 580));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Apellido Paterno:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        jMenu1.setText("Control de empleados");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/empleado_menubar.png"))); // NOI18N
        jMenuItem1.setText("Registro de personal");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Menu menu = new Menu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        RegistroUsuario registro = new RegistroUsuario();
        registro.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void limpiarTablaEmpleados() {
        DefaultTableModel model = (DefaultTableModel) TablaEmpleados.getModel();
        int CountRows = model.getRowCount();
        for (int i = 0; i < CountRows; i++) {
            model.removeRow(0);
        }
    }

    private void agregarRowTablaEmpleados(String IdentEmpleadoBusc, String NombreBusc, String ApellidoPBusc, String ApellidoMBusc) {
        DefaultTableModel mode1TablaEmpleados = (DefaultTableModel) TablaEmpleados.getModel();
        mode1TablaEmpleados.addRow(new Object[]{IdentEmpleadoBusc, NombreBusc, ApellidoPBusc, ApellidoMBusc});
    }

    private String getQueryBuscar() {
        String IdentEmpleadoBusc = txtIdEmpleado.getText();
        String NombreBusc = txtNombreEmpleado.getText();
        String ApellidoPBusc = txtPaterno.getText();
        String ApellidoMBusc = txtMaterno.getText();

        String Query = "SELECT * FROM `empleados` WHERE `Activo` = 1 ";
        int pOR = 0;
        if (!"".equals(IdentEmpleadoBusc)) {
            Query += ("AND `Identificador` = '" + IdentEmpleadoBusc + "'");
        } else {
            if (!"".equals(NombreBusc)) {
                Query += ("AND ( `Nombre` LIKE '%" + NombreBusc + "%'");
                pOR = 1;
            }
            if (!"".equals(ApellidoPBusc)) {
                if (pOR == 1) {
                    Query += " OR ";
                } else {
                    Query += " AND ( ";
                }
                Query += ("`ApellidoP` LIKE '%" + ApellidoPBusc + "%'");
                pOR = 2;
            }
            if (!"".equals(ApellidoMBusc)) {
                if (pOR > 0) {
                    Query += " OR ";
                } else {
                    Query += " AND ( ";
                }
                Query += ("`ApellidoM` LIKE '%" + ApellidoMBusc + "%'");
                pOR = 3;
            }
            if (pOR > 0) {
                Query += ")";
            }
        }
        return Query;
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:        
        Conexion conex = new Conexion();
        String Query = getQueryBuscar();
        MysqlDataSource dataSource = conex.getConnection();
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet ResulQuery = stmt.executeQuery(Query);
            limpiarTablaEmpleados();
            while (ResulQuery.next()) {
                String Identificador = ResulQuery.getString("Identificador");
                String Nombre = ResulQuery.getString("Nombre");
                String ApellidoP = ResulQuery.getString("ApellidoP");
                String ApellidoM = ResulQuery.getString("ApellidoM");
                agregarRowTablaEmpleados(Identificador, Nombre, ApellidoP, ApellidoM);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int RowSeleccionado = TablaEmpleados.getSelectedRow();
        String Identificador = "";
        if (RowSeleccionado > -1) {
            Identificador = TablaEmpleados.getValueAt(RowSeleccionado, 0).toString();
            RegistroUsuario registro = new RegistroUsuario();
            registro.prepararModEmpleado(Identificador);
            registro.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Favor de seleccionar un dato.");
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        RegistroUsuario registro = new RegistroUsuario();
        registro.prepararInsEmpleado();
        registro.setVisible(true);

    }//GEN-LAST:event_btnNuevoActionPerformed

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
            java.util.logging.Logger.getLogger(ControlEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ControlEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ControlEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ControlEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ControlEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaEmpleados;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtIdEmpleado;
    private javax.swing.JTextField txtMaterno;
    private javax.swing.JTextField txtNombreEmpleado;
    private javax.swing.JTextField txtPaterno;
    // End of variables declaration//GEN-END:variables
}
