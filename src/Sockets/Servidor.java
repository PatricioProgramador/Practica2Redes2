package Sockets;

import ConexionBD.Conexion;
import Modelo.Producto;
import Modelo.UsuarioCarrito;
import com.mysql.jdbc.Connection;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class Servidor {
    private Socket cliente;
    private ServerSocket servidor;
    private int PUERTO=3478;
    private Conexion con;
    public Servidor() {
    }
    
    public void escucha() throws ClassNotFoundException, SQLException, FileNotFoundException, JRException
    {
        con=new Conexion("root","xxxx");
        ObjectOutputStream out=null;
        ObjectInputStream in=null;
        boolean bandera=true;
        try {this.servidor=new ServerSocket(PUERTO);} 
        catch (IOException ex) {System.out.println("excepcion de servidor: "+ex);}
        while(true)
        {
            try
            {
                bandera=true;
                System.out.println("esperando una conexion");
                /*se mantiene a la espera de una conexión, cuando haya una conexion 
                el servidor la aceptará y empezará el desmadre*/
                this.cliente=servidor.accept();
                System.out.println("cliente acptado");
                //se obtiene el flujo de datos que llega al servidor
                in= new ObjectInputStream(cliente.getInputStream());
                out=new ObjectOutputStream(cliente.getOutputStream());
                //Se cumple mientras haya datos que leer
                while(bandera)
                {
                    //leemmos lo que envia el cliente
                    Mensaje entrada=(Mensaje)in.readObject();
                    Mensaje msj = null;
                    if(entrada.getAccion().equals("AgregarProducto"))
                    {
                        con.AgregarProducto(entrada.getPdto());
                        //System.out.println("Producto agregado");
                        msj=new Mensaje("Agregado");
                        out.writeObject(msj);
                    }
                    if(entrada.getAccion().equals("ObtenerProducto"))
                    {
                        ArrayList<Producto> pdtos=con.ObtenerProductos();
                        //System.out.println("Productos obtenidos");
                        msj=new Mensaje("Productos Obtenidos");
                        msj.setPdtos(pdtos);
                        out.writeObject(msj);
                    }
                    if(entrada.getAccion().equals("ValidarExistencia"))
                    {
                        int existencia=con.ObtenerExistencia(entrada.getPdto().getIdProducto());
                        if(existencia>=entrada.getExistencia())
                        {
                            msj=new Mensaje("disponible");
                            //msj.setExistencia(existencia-entrada.getExistencia());
                            out.writeObject(msj);
                        }
                        else{
                            msj=new Mensaje("no disponible");
                            //msj.setExistencia(existencia-entrada.getExistencia());
                            out.writeObject(msj);
                        }
                       
                    }
                    if(entrada.getAccion().equals("ObtenerCarritoUsuario"))
                    {
                        int idUsuario=entrada.getExistencia();
                        ArrayList<Producto> pdtos=con.ObtenerCarrito(idUsuario);
                        msj=new Mensaje("Carrito");
                        msj.setPdtos(pdtos);
                        out.writeObject(msj);
                    }
                    if(entrada.getAccion().equals("InsertarProductoCarrito"))
                    {
                        con.InsertarProductoCarrito(entrada.getIdCarrito(), entrada.getPdto().getIdProducto(), entrada.getExistencia());
                        out.writeObject(msj);
                    }
                    if(entrada.getAccion().equals("ActualizarExistencias"))
                    {
                        con.ActualizarExistencias(entrada.getPdto().getIdProducto(), entrada.getExistencia());
                        out.writeObject(msj);
                    }
                    if(entrada.getAccion().equals("ObtenerExistencia"))
                    {
                        int existencia=con.ObtenerExistencia(entrada.getPdto().getIdProducto());
                        msj=new Mensaje("Exitencia");
                        msj.setExistencia(existencia);
                        out.writeObject(msj);
                    }
                    if(entrada.getAccion().equals("EliminarProductoCarrito"))
                    {
                        con.EliminarProductoCarrito(entrada.getIdCarrito(),entrada.getPdto().getIdProducto());
                        msj=new Mensaje("Eliminado");
                        out.writeObject(msj);
                    }
                    if(entrada.getAccion().equals("ObtenerCarritoUsuarioID"))
                    {
                        boolean resp=con.ObtenerCarritoUsuarioID(entrada.getIdCarrito());
                        if(resp)
                            msj=new Mensaje("RegistroExiste");
                        else{
                            msj=new Mensaje("Registro Creado");
                        }    
                        out.writeObject(msj);
                    }
                    if(entrada.getAccion().equals("ObtenerCarritoUsuarioIdentificadores"))
                    {
                        UsuarioCarrito usu=con.ObtenerCarritoUsuarioIdentificadores(entrada.getIdCarrito());
                        //System.out.println("Usuarios: "+usu.getIdCarrito()+" "+usu.getIdUsuario());
                        msj=new Mensaje("Usuario");
                        msj.setUsuario(usu);
                        out.writeObject(msj);
                    }
                    if(entrada.getAccion().equals("RegistrarVenta"))
                    {
                        con.RegistrarVenta(entrada.getIdCarrito(), entrada.getFecha(), entrada.getPdtos());
                        msj=new Mensaje("Venta Realizada");
                        msj.setPdf(GenerarReporte(entrada.getIdCarrito()));
                        out.writeObject(msj);
                    }
                    if(entrada.getAccion().equals("EliminarCarritoCompras"))
                    {
                        con.EliminarCarritoCompras(entrada.getIdCarrito());
                        msj=new Mensaje("Carrito eliminado");
                        out.writeObject(msj);
                    }
                }
            //Entra a esta excepcion cuando el cliente acabe su conexion, aca se escribe en el fichero    
            }catch(IOException ex){
                try
                {
                    //escribimos el arreglo de bytes en el fichero que se creo anteriormente
                    System.out.println("conexión con cliente terminada "+ex);
                    //Se cierran flujos
                    if(in!=null)
                        in.close();
                    if(out!=null)
                        out.close();
                    //Se abre a la verga el cliente para que entre otro
                    cliente.close();
                }catch(IOException ex1){
                    System.out.println("error al cerrar los flujos");
                }
            }
        }
    }
    public byte[] GenerarReporte(int idUsuario) throws FileNotFoundException, IOException, JRException{
        File dir=new File(".");
        //Crear instancia de jasper report
        JasperReport reporte=null;
        Map parametros=new HashMap();
        parametros.put("idUsuarioParam", idUsuario);
        String path=dir.getCanonicalPath()+"/build/classes/Sockets/"+"ReporteCarrito.jasper";
        //Cargar reporte
        reporte=(JasperReport) JRLoader.loadObjectFromFile(path);
        
        //Visualizador de JasperReport
        JasperPrint jprint=JasperFillManager.fillReport(reporte, parametros, con.getCon());
        return JasperExportManager.exportReportToPdf(jprint);
        /*byte[] x=JasperExportManager.exportReportToPdf(jprint);
        exportar.write(x);
        
        exportar.close();*/
    } 
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, JRException
    {
        Servidor serv=new Servidor();
        serv.escucha();
    }
}
