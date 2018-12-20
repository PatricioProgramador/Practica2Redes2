/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import Modelo.Producto;
import java.sql.SQLException;
import java.util.ArrayList;
import ConexionBD.Conexion;
import Modelo.Archivos;
import Modelo.Imagen;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ale
 */
public class Practica2Redes2 {

    
     
    public static void main(String[] args) throws SQLException, IOException, JRException 
    {
        /*Conexion con=new Conexion("root","xxxx");
        File dir=new File(".");
        BufferedOutputStream exportar= new BufferedOutputStream(new FileOutputStream("reporte.pdf"));
        //Crear instancia de jasper report
        JasperReport reporte=null;
        Map parametros=new HashMap();
        parametros.put("idUsuarioParam", 2);
        String path=dir.getCanonicalPath()+"/build/classes/Pruebas/"+"ReporteCarrito.jasper";
        //Cargar reporte
        reporte=(JasperReport) JRLoader.loadObjectFromFile(path);
        
        //Visualizador de JasperReport
        JasperPrint jprint=JasperFillManager.fillReport(reporte, parametros, con.getCon());
        
        byte[] x=JasperExportManager.exportReportToPdf(jprint);
        exportar.write(x);
        exportar.close();
        
        /*JasperViewer view=new JasperViewer(jprint, false);
        view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        view.setVisible(true);
        con.CerrarConexion();
        System.out.println("Programa terminado");*/
    }
    
}
