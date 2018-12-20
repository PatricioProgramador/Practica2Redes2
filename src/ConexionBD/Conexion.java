/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionBD;

import Modelo.Archivos;
import Modelo.Imagen;
import Modelo.Producto;
import Modelo.UsuarioCarrito;
import com.mysql.jdbc.Connection;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.ImageIcon;

/**
 *
 * @author ale
 */
public class Conexion {
    private Connection con;
    private Properties conProperties=new Properties();
    private final String URL_DB="jdbc:mysql://localhost:3306/";
    public final String CADENA_CONEXION="com.mysql.jdbc.Driver";
    private String BDNOMBRE="SistemaCompra";
    
    public Conexion(String user, String password) throws SQLException{
        this.conProperties.put("user", user);
        this.conProperties.put("password", password);
        con=(Connection) DriverManager.getConnection(URL_DB+BDNOMBRE,this.conProperties);
       
    }
    public ArrayList<Producto> ObtenerProductos() throws SQLException{
        ArrayList<Producto> productos=new ArrayList<>();
        String consulta="SELECT * FROM Producto";
        Statement stmn=con.createStatement();
        ResultSet rs=stmn.executeQuery(consulta);
        while(rs.next())
        {
            Producto pd=new Producto(rs.getInt("idProducto"),
                                    rs.getString("descripcion"),
                                    rs.getInt("tamaño"),
                                    rs.getFloat("precio"),
                                    rs.getInt("tiempo_entrega"),
                                    rs.getInt("existencia"));
            
            
            //Lista para guardar los imagenes
            ArrayList<Imagen> imagenesList=ObtenerImagenesProducto(pd.getIdProducto());;
            pd.setImagenes(imagenesList);
            productos.add(pd);
        }
        stmn.close();
        rs.close();
        return productos;
    }
    public ArrayList<Producto> ObtenerCarrito(int idUsuario) throws SQLException{
        ArrayList<Producto> listaProductos=new ArrayList<>();
        CallableStatement statement=this.con.prepareCall("{call ObtenerCarritoUsuario(?)}");
        statement.setInt(1, idUsuario);
        ResultSet rs=statement.executeQuery();
        while(rs.next()){
            Producto pdto= new Producto(
            rs.getInt("idProducto"),
            rs.getString("descripcion"),
            rs.getInt("tamaño"),
            rs.getFloat("precio"),
            rs.getInt("tiempo_entrega"),
            rs.getInt("Cantidad"));
            ArrayList<Imagen> imagenes=this.ObtenerImagenesProducto(pdto.getIdProducto());
            pdto.setImagenes(imagenes);
            listaProductos.add(pdto);
        }
        //liberar recursos
        statement.close();
        rs.close();
        return listaProductos;
    }
    //ABC de los producto
    public void AgregarProducto(Producto pdto) throws SQLException{
        //Crear instancia de CallableStatement
        CallableStatement statement= con.prepareCall("{call InsertarProducto(?,?,?,?,?)}");
        //mandar valores a la consulta
        statement.setString(1, pdto.getDescripcion());
        statement.setInt(2, pdto.getTamano());
        statement.setFloat(3, pdto.getPrecio());
        statement.setInt(4, pdto.getTiempoentrega());
        statement.setInt(5, pdto.getExistencias());
        //Ejecutar la consulta
        statement.executeUpdate();
        //liberar recursos
        statement.close();
    }
    public void EliminarProducto(int idProducto) throws SQLException{
        //Crear instancia de CallableStatement
        CallableStatement statement=this.con.prepareCall("{call EliminarProducto(?)}");
        statement.setInt(1, idProducto);
        statement.executeUpdate();
        //liberar recursos
        statement.close();
    }
    public void ModificarProducto(Producto pdto) throws SQLException{
        CallableStatement statement=this.con.prepareCall("{call ActualizarProducto(?,?,?,?,?,?)}");
        statement.setInt(1, pdto.getIdProducto());
        statement.setString(2, pdto.getDescripcion());
        statement.setInt(3, pdto.getTamano());
        statement.setFloat(4, pdto.getPrecio());
        statement.setInt(5, pdto.getTiempoentrega());
        statement.setInt(6, pdto.getExistencias());
        //ejecutar procedimiento
        statement.executeUpdate();
        //liberar recursos
        statement.close();
    }
    public void InsertarImagenProducto(int idProducto,Archivos archivo) throws SQLException{
        Blob x=con.createBlob();
        x.setBytes(1, archivo.getArrayByte());
        PreparedStatement statement=this.con.prepareStatement("insert into ImagenProducto(idProducto,imagen) values(?,?)");
        statement.setInt(1, idProducto);
        statement.setBlob(2, x);
        statement.executeUpdate();
    }
    public ArrayList<Imagen> ObtenerImagenesProducto(int idProducto) throws SQLException
    {
       ArrayList<Imagen> imagenes = new ArrayList<>();
       PreparedStatement statement=this.con.prepareStatement("select * from ImagenProducto where idProducto=?");
       statement.setInt(1, idProducto);
       ResultSet rs=statement.executeQuery();
       while(rs.next())
       {
           Blob x=rs.getBlob("imagen");
           int id=rs.getInt("idImagen");
           Imagen img=new Imagen(new ImageIcon(x.getBytes(1, (int)x.length())),id);
           imagenes.add(img);
       }
       rs.close();
       statement.close();
       return imagenes;
    }
    public int ObtenerExistencia(int idProducto) throws SQLException{
        CallableStatement st=this.con.prepareCall("{call ValidarExistencia(?)}");
        st.setInt(1, idProducto);
        ResultSet rs=st.executeQuery();
        int existencia=0;
        while(rs.next())
        {
            existencia=rs.getInt("existencia");
        }
        st.close();
        return existencia;
    }
    public void InsertarProductoCarrito(int idCarrito, int idProducto, int cantidad) throws SQLException
    {
        CallableStatement statement=con.prepareCall("{call InsertarProductoCarrito(?,?,?)}");
        statement.setInt(1, idCarrito);
        statement.setInt(2, idProducto);
        statement.setInt(3, cantidad);
        statement.executeUpdate();
        statement.close();
    }
    public void ActualizarExistencias(int idProducto, int cantidad) throws SQLException
    {
        CallableStatement statement=con.prepareCall("{call ActualizarExistencias(?,?)}");
        statement.setInt(1, idProducto);
        statement.setInt(2, cantidad);
        statement.executeUpdate();
        statement.close();
    }
    public void EliminarProductoCarrito(int idCarrito,int idProducto) throws SQLException
    {
        CallableStatement statement=con.prepareCall("{call EliminarProductoCarrito(?,?)}");
        statement.setInt(1, idCarrito);
        statement.setInt(2, idProducto);
        statement.executeUpdate();
        statement.close();
    }
    public boolean ObtenerCarritoUsuarioID(int idUsuario) throws SQLException
    {
        CallableStatement statement=con.prepareCall("{call ObtenerCarritoUsuarioID(?)}");
        statement.setInt(1, idUsuario);
        ResultSet rs=statement.executeQuery();
        while(rs.next())
        {
            System.out.println("Carrito no creado en el server");
            return true;
        }
        statement.close();
        rs.close();
        System.out.println("Carrito creado en el server");
        CrearCarritoUsuario(idUsuario);
        return false;
    }
    public void CrearCarritoUsuario(int idUsuario) throws SQLException{
        System.out.println("entra a crearCarritoUsuario");
        CallableStatement statement=con.prepareCall("{call CrearCarrito(?)}");
        statement.setInt(1, idUsuario);
        statement.executeUpdate();
        statement.close();
    }
    
    public UsuarioCarrito ObtenerCarritoUsuarioIdentificadores(int idUsuario) throws SQLException{
        CallableStatement statement=con.prepareCall("{call ObtenerCarritoUsuarioID(?)}");
        statement.setInt(1, idUsuario);
        ResultSet rs=statement.executeQuery();
        UsuarioCarrito usuario=null;
        while(rs.next())
        {
            usuario=new UsuarioCarrito(rs.getInt("idUsuario"),rs.getInt("idCarrito"));
        }
        statement.close();
        rs.close();
        return usuario;
    }
    public void RegistrarVenta(int idUsuario, long fechaLong, ArrayList<Producto> productos) throws SQLException
    {
        CallableStatement statement= con.prepareCall("{call CrearVenta(?,?)}");
        statement.setInt(1, idUsuario);
        Date fecha=new Date(fechaLong);
        statement.setDate(2, fecha);
        statement.executeUpdate();
        statement.close();
        //Despues de crear la venta insertar productos
        PreparedStatement statement2=con.prepareCall("SELECT MAX(idVenta) as idVenta FROM Ventas");
        ResultSet rs=statement2.executeQuery();
        int idVenta=0;
        while(rs.next())
        {
            idVenta=rs.getInt("idVenta");
            System.out.println("Veenta numero: "+idVenta);
        }
        rs.close();
        statement2.close();
        CallableStatement statement3= con.prepareCall("{call AgregarProducto(?,?)}");
        statement3.setInt(1, idVenta);
        for(int i=0;i<productos.size();i++)
        {
            statement3.setInt(2, productos.get(i).getIdProducto());
            statement3.executeUpdate();
        }
        statement3.close();
        rs.close();            
    }
    public void EliminarCarritoCompras(int idCarrito) throws SQLException{
        CallableStatement statement=con.prepareCall("{call EliminarCarritoCompras(?)}");
        statement.setInt(1, idCarrito);
        statement.executeUpdate();
    }
    
    
    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    public void CerrarConexion() throws SQLException{
        this.con.close();
    }

}
