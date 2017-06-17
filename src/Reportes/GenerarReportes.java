/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import Ventanas.Conexion;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *

 */
public class GenerarReportes {
    Conexion conexion = new Conexion();
    Connection con = conexion.reportes();
    JasperReport jrReporte = null;
    JasperPrint jpReporte = null;
    JasperViewer jvRepoerte = null;
    
    String rtReporteEmpleado = "Reportes/Empleado.jasper";
    String rtReporteEmpleados = "Reportes/Empleados.jasper";
    String rtReporteInventario = "Reportes/Inventario.jasper";
    String rtReporteProducto = "Reportes/Producto.jasper";
    String rtReporteVenta = "Reportes/Venta.jasper";
    
    public void ReporteEmpleado(Integer pID){
        try{
            jrReporte = (JasperReport) JRLoader.loadObject(rtReporteEmpleado);
            Map parametro = new HashMap();
            parametro.put("ID",pID);            
            jpReporte = JasperFillManager.fillReport(jrReporte, parametro, con);
            jvRepoerte = new JasperViewer(jpReporte,false);
            jvRepoerte.setTitle("Reporte de Empleado");
            jvRepoerte.setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void ReporteEmpleados(){
        try{            
            jrReporte = (JasperReport) JRLoader.loadObject(rtReporteEmpleados);            
            jpReporte = JasperFillManager.fillReport(jrReporte, null, con);
            jvRepoerte = new JasperViewer(jpReporte,false);
            jvRepoerte.setTitle("Reporte general de empleados");
            jvRepoerte.setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void ReporteInventario()
    {
        try
        {
           jrReporte = (JasperReport) JRLoader.loadObject(rtReporteInventario); 
           jpReporte = JasperFillManager.fillReport(jrReporte, null, con);
           jvRepoerte = new JasperViewer(jpReporte,false);
           jvRepoerte.setTitle("Reporte general de inventario");
           jvRepoerte.setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
    public void ReporteProducto(Integer pID)
    {
        try
        {
           jrReporte = (JasperReport) JRLoader.loadObject(rtReporteProducto); 
           Map parametro = new HashMap();
           parametro.put("ID",pID);    
           jpReporte = JasperFillManager.fillReport(jrReporte, parametro, con);
           jvRepoerte = new JasperViewer(jpReporte,false);
           jvRepoerte.setTitle("Reporte general de inventario");
           jvRepoerte.setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
    public void ReporteVenta(Integer pID)
    {
        try
        {
           jrReporte = (JasperReport) JRLoader.loadObject(rtReporteVenta); 
           Map parametro = new HashMap();
           parametro.put("ID",pID);    
           jpReporte = JasperFillManager.fillReport(jrReporte, parametro, con);
           jvRepoerte = new JasperViewer(jpReporte,false);
           jvRepoerte.setTitle("Ticket de compra");
           jvRepoerte.setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
}

