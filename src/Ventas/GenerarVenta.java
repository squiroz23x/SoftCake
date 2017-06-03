/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import Ventanas.Menu;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.*;
import javax.swing.table.TableModel;
/**
 *
 * @author JIGA_
 */
public class GenerarVenta extends javax.swing.JFrame implements TableModelListener {

    /**
     * Creates new form Venta
     */
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
            for (int i = 0; i<CountRows; i++){
                if ("".equals(model.getValueAt(i, 2).toString())){ 
                    model.setValueAt("1", i, 2);
                }
                BigDecimal Cantidad = new BigDecimal(model.getValueAt(i, 2).toString()).setScale(2,BigDecimal.ROUND_HALF_DOWN);
                BigDecimal Stock = new BigDecimal(model.getValueAt(i, 4).toString()).setScale(2,BigDecimal.ROUND_HALF_DOWN);
                if(Cantidad.compareTo(Stock) == 1){
                    model.setValueAt(Stock.setScale(2).toPlainString(), i, 2);
                    Cantidad = Stock;
                }
                if (Cantidad.compareTo(BigDecimal.ZERO) == -1 ){
                    model.setValueAt("1.00", i, 2);
                    Cantidad = new BigDecimal("1.00");
                }
                BigDecimal PU = new BigDecimal(model.getValueAt(i, 5).toString()).setScale(2,BigDecimal.ROUND_HALF_DOWN);
                BigDecimal total = PU.multiply(Cantidad).setScale(2,BigDecimal.ROUND_HALF_DOWN);            
                model.setValueAt(total.setScale(2).toPlainString(), i, 6);
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
    public void agregarRowTablaConceptos(String IDArticulo_Lote,String Codigo,String Cantidad,String Descripcion, String Stock, String PrecioUnitario){
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
            mode1TablaConceptos.addRow(new Object[]{ IDArticulo_Lote, Codigo, Cantidad, Descripcion,  Stock,  PrecioUnitario, Total.toString()});

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
        getContentPane().add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 290, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("RFC:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        txtRfc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtRfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 290, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Domicilio:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        txtDomicilio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtDomicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 490, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Número Int:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        txtNumero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 40, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("C.P:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, -1, -1));

        txtCp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtCp, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 60, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Colonia:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        txtColonia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtColonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 150, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Teléfono:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, -1, -1));

        txtTelefono.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 150, -1));

        TablaConceptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDArticulo_Lote", "Codigo", "Cantidad", "Descripción", "Stock", "Precio Unitario", "Total"
            }
        ));
        TablaConceptos.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                TablaConceptosHierarchyChanged(evt);
            }
        });
        jScrollPane1.setViewportView(TablaConceptos);

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
       CompletarVenta completar = new CompletarVenta();
       completar.setVisible(true);
       this.dispose();
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
