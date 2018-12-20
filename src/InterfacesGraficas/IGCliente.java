/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesGraficas;

import ConexionBD.Conexion;
import Modelo.Archivos;
import Modelo.Producto;
import Sockets.Cliente;
import java.io.File;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import Sockets.Mensaje;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ale
 */
public class IGCliente extends javax.swing.JFrame {

    private int idUsuario=3;
    private int idCarrito=2;
    public DefaultListModel<String> modeloImagenes;
    public DefaultListModel<String> modeloNombresCarrito;

    public ArrayList<Producto> productosCatalogo=new ArrayList<>();
    public ArrayList<Producto> productosCarrito=new ArrayList<>();
    
    DefaultTableModel modeloTabla = new DefaultTableModel();
    DefaultTableModel modeloCarrito = new DefaultTableModel();

    int columnaVisualizar=0, columnaCarrito=0;
    Cliente cl=null;
    
    public IGCliente() throws IOException, ClassNotFoundException, SQLException {
        initComponents();
        this.JTableVisualizaPdtos.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                //Obtener el indice clickeado
                columnaVisualizar = JTableVisualizaPdtos.rowAtPoint(e.getPoint());
                CargarImagenesProductos();
            }
        });
        this.jTableCarrito.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                columnaCarrito = jTableCarrito.rowAtPoint(e.getPoint());
                CargarImagenesProductosCarrito();
            }
        });
        this.ListaVisualizar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int imagen=ListaVisualizar.getSelectedIndex();
                ImageIcon img=productosCatalogo.get(columnaVisualizar).getImagenes().get(imagen).getImage();
                MostrarImagenTXT.setIcon(img);
                
            }
        });
        this.ListaCarrito.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int imagen=ListaCarrito.getSelectedIndex();
                ImageIcon img=productosCarrito.get(columnaCarrito).getImagenes().get(imagen).getImage();
                imagenCarritoTXT.setIcon(img);
            }
        });
        this.visualizarTablaArticulos();
    }
    
    public void visualizarTablaArticulos() throws SQLException, IOException, ClassNotFoundException{
        float total=(float) 0.0;
        this.modeloTabla= new DefaultTableModel();
        this.modeloCarrito=new DefaultTableModel();
        this.JTableVisualizaPdtos.setModel(modeloTabla);
        this.jTableCarrito.setModel(modeloCarrito);
        modeloTabla.addColumn("idProducto");
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("Tamaño");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Tiempo entrega");
        modeloTabla.addColumn("Existencia");
        
        modeloCarrito.addColumn("idProducto");
        modeloCarrito.addColumn("Descripcion");
        modeloCarrito.addColumn("Tamaño");
        modeloCarrito.addColumn("Precio");
        modeloCarrito.addColumn("Tiempo entrega");
        modeloCarrito.addColumn("#Articulos");
        modeloCarrito.addColumn("Subtotal");
        if(cl!=null)
        {
            //Obtiene los productos al momento de hacer la conexion
            Mensaje msj=new Mensaje("ObtenerProducto");
            this.productosCatalogo=cl.EnviarArchivos(msj).getPdtos();
            
            //Obtiene los productos del carrito
            Mensaje msj2=new Mensaje("ObtenerCarritoUsuario");
            msj2.setExistencia(this.idUsuario);
            this.productosCarrito=cl.EnviarArchivos(msj2).getPdtos();
        }
        for(int i=0;i<productosCatalogo.size();i++)
        {
            Object [] fila=new Object[6];
            fila[0]=this.productosCatalogo.get(i).getIdProducto();
            fila[1]=this.productosCatalogo.get(i).getDescripcion();
            fila[2]=this.productosCatalogo.get(i).getTamano();
            fila[3]=this.productosCatalogo.get(i).getPrecio();
            fila[4]=this.productosCatalogo.get(i).getTiempoentrega();
            fila[5]=this.productosCatalogo.get(i).getExistencias();
            modeloTabla.addRow(fila);
        }
        for(int i=0;i<productosCarrito.size();i++)
        {
            Object [] fila=new Object[7];
            fila[0]=this.productosCarrito.get(i).getIdProducto();
            fila[1]=this.productosCarrito.get(i).getDescripcion();
            fila[2]=this.productosCarrito.get(i).getTamano();
            fila[3]=this.productosCarrito.get(i).getPrecio();
            fila[4]=this.productosCarrito.get(i).getTiempoentrega();
            fila[5]=this.productosCarrito.get(i).getExistencias();
            fila[6]=this.productosCarrito.get(i).getPrecio()*this.productosCarrito.get(i).getExistencias();
            total=total+this.productosCarrito.get(i).getPrecio()*this.productosCarrito.get(i).getExistencias();
            modeloCarrito.addRow(fila);
        }
        //El total se muestra en el textbox
        this.TotalTXT.setText(total+"");
        //Carga la lista de texto de la imagen
        CargarImagenesProductos();
        //Se carga la lista de texto del carrito
        CargarImagenesProductosCarrito();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        Conectar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        IPTXT = new javax.swing.JTextField();
        PUERTOTXT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        IdUsuarioTXT = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        JTableVisualizaPdtos = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        ListaVisualizar = new javax.swing.JList<>();
        jLabel21 = new javax.swing.JLabel();
        ImagenMostrar = new javax.swing.JPanel();
        MostrarImagenTXT = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Elementos = new javax.swing.JTextField();
        ValidarExistencia = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCarrito = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListaCarrito = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        EliminarElemento = new javax.swing.JButton();
        RealizarCompra = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        TotalTXT = new javax.swing.JTextField();
        imagenCarritoTXT = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Conectar.setText("Conectar");
        Conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConectarActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingrese la ip del servidor");

        jLabel2.setText("Ingrese el puerto");

        IPTXT.setText("localhost");

        PUERTOTXT.setText("3478");

        jLabel6.setText("Ingrese id de Usuario");

        IdUsuarioTXT.setText("2");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Conectar)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IPTXT)
                            .addComponent(PUERTOTXT, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(IdUsuarioTXT))))
                .addGap(0, 274, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(IPTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(PUERTOTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(IdUsuarioTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(Conectar)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(251, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Conectar A Servidor", jPanel1);

        JTableVisualizaPdtos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(JTableVisualizaPdtos);

        jScrollPane7.setViewportView(ListaVisualizar);

        jLabel21.setText("Lista de imagenes");

        javax.swing.GroupLayout ImagenMostrarLayout = new javax.swing.GroupLayout(ImagenMostrar);
        ImagenMostrar.setLayout(ImagenMostrarLayout);
        ImagenMostrarLayout.setHorizontalGroup(
            ImagenMostrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImagenMostrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MostrarImagenTXT, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addGap(76, 76, 76))
        );
        ImagenMostrarLayout.setVerticalGroup(
            ImagenMostrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MostrarImagenTXT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel3.setText("Ingrese la cantidad a agregar al carrito");

        Elementos.setText("1");

        ValidarExistencia.setText("Agregar al carrito");
        ValidarExistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValidarExistenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(Elementos, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ValidarExistencia)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Elementos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ValidarExistencia))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ImagenMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 130, Short.MAX_VALUE))
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ImagenMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Visualizar Productos", jPanel7);

        jTableCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableCarrito);

        jScrollPane2.setViewportView(ListaCarrito);

        jLabel4.setText("Lista de imagenes");

        EliminarElemento.setText("Eliminar Elemento");
        EliminarElemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarElementoActionPerformed(evt);
            }
        });

        RealizarCompra.setText("Realizar Compra");
        RealizarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizarCompraActionPerformed(evt);
            }
        });

        jLabel5.setText("Total");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TotalTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RealizarCompra)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(EliminarElemento)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addComponent(imagenCarritoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(TotalTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(EliminarElemento))
                    .addComponent(imagenCarritoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(RealizarCompra))
        );

        jTabbedPane1.addTab("Ver Carrito", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ValidarExistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValidarExistenciaActionPerformed
        Mensaje msj=new Mensaje("ValidarExistencia");
        msj.setExistencia(Integer.parseInt(this.Elementos.getText()));
        msj.setPdto(this.productosCatalogo.get(columnaVisualizar));
        try {
            String existencia=cl.EnviarArchivos(msj).getAccion();
            if(existencia.equals("disponible"))
            {
                Mensaje msj2=new Mensaje("InsertarProductoCarrito");
                msj2.setExistencia(msj.getExistencia());
                msj2.setIdCarrito(idCarrito);
                msj2.setPdto(this.productosCatalogo.get(columnaVisualizar));
                //this.productosCarrito.add(this.productosCatalogo.get(columnaVisualizar));
                msj2=cl.EnviarArchivos(msj2);
                
                
                Mensaje msj3=new Mensaje("ActualizarExistencias");
                msj3.setExistencia(this.productosCatalogo.get(columnaVisualizar).getExistencias() - Integer.parseInt(this.Elementos.getText()));
                //this.productosCatalogo.get(columnaVisualizar).setExistencias(this.productosCatalogo.get(columnaVisualizar).getExistencias() - Integer.parseInt(this.Elementos.getText()));
                msj3.setPdto(this.productosCatalogo.get(columnaVisualizar));
                System.out.println("existencia nueva: "+msj3.getExistencia());
                System.out.println("id Producto: "+msj3.getPdto().getIdProducto());
                msj3=cl.EnviarArchivos(msj3);

                //this.productosCarrito.get(this.productosCarrito.size()-1).setExistencias(Integer.parseInt(Elementos.getText()));                
                this.visualizarTablaArticulos();

            }
            else{
                JOptionPane.showMessageDialog(null, "No hay suficientes productos disponibles","no se puede agregar al carrito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ValidarExistenciaActionPerformed

    private void ConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConectarActionPerformed
        try {
            this.cl=new Cliente(this.IPTXT.getText(),Integer.parseInt(this.PUERTOTXT.getText()));
            JOptionPane.showMessageDialog(null, "Conexion exitosa","Servidor encontrado", JOptionPane.INFORMATION_MESSAGE);
            
            CrearCarrito();
            
            Mensaje msj=new Mensaje("ObtenerProducto");
            this.productosCatalogo=cl.EnviarArchivos(msj).getPdtos();

            Mensaje msj2=new Mensaje("ObtenerCarritoUsuario");
            msj2.setExistencia(this.idUsuario);
            this.productosCarrito=cl.EnviarArchivos(msj2).getPdtos();
            System.out.println("carrito: "+this.productosCarrito.size());
            visualizarTablaArticulos();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar encontrar el servidor","Error de conexion", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ConectarActionPerformed

    private void EliminarElementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarElementoActionPerformed
        try {
            Mensaje msj2=new Mensaje("EliminarProductoCarrito");
            msj2.setIdCarrito(idCarrito);
            msj2.setPdto(this.productosCarrito.get(this.columnaCarrito));
            msj2=cl.EnviarArchivos(msj2);

            Mensaje msj4= new Mensaje("ObtenerExistencia");
            msj4.setPdto(this.productosCarrito.get(this.columnaCarrito));
            msj4=cl.EnviarArchivos(msj4);
            int existenciaBD=msj4.getExistencia();

            Mensaje msj3=new Mensaje("ActualizarExistencias");
            msj3.setExistencia(existenciaBD + this.productosCarrito.get(this.columnaCarrito).getExistencias());

            //this.productosCatalogo.get(columnaVisualizar).setExistencias(existenciaBD + this.productosCarrito.get(this.columnaCarrito).getExistencias());
            msj3.setPdto(this.productosCarrito.get(columnaCarrito));
            msj3=cl.EnviarArchivos(msj3);

            System.out.println("Acutualizacion completa");
            this.visualizarTablaArticulos();

        } catch (IOException ex) {
            Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EliminarElementoActionPerformed

    private void RealizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RealizarCompraActionPerformed
        try {
        Mensaje msj=new Mensaje("RegistrarVenta");
        System.out.println("Id usuario a mandar"+this.idUsuario);
        msj.setIdCarrito(this.idUsuario);
        Date fecha=new Date();
        msj.setFecha(fecha.getTime());
        msj.setPdtos(productosCarrito);
        //Generar el pdf
        Mensaje pdf=cl.EnviarArchivos(msj);
        BufferedOutputStream exportar=new BufferedOutputStream(new FileOutputStream("Reporte.pdf"));
        exportar.write(pdf.getPdf());
        exportar.close();
        //Eliminar el carrito de la bd
        Mensaje msj2=new Mensaje("EliminarCarritoCompras");
        msj2.setIdCarrito(idCarrito);
        cl.EnviarArchivos(msj2);
        
        productosCarrito=new ArrayList<>();
        
        visualizarTablaArticulos();
        } catch (IOException ex) {
            Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_RealizarCompraActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IGCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IGCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IGCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IGCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new IGCliente().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Conectar;
    private javax.swing.JTextField Elementos;
    private javax.swing.JButton EliminarElemento;
    private javax.swing.JTextField IPTXT;
    private javax.swing.JTextField IdUsuarioTXT;
    private javax.swing.JPanel ImagenMostrar;
    private javax.swing.JTable JTableVisualizaPdtos;
    private javax.swing.JList<String> ListaCarrito;
    private javax.swing.JList<String> ListaVisualizar;
    private javax.swing.JLabel MostrarImagenTXT;
    private javax.swing.JTextField PUERTOTXT;
    private javax.swing.JButton RealizarCompra;
    private javax.swing.JTextField TotalTXT;
    private javax.swing.JButton ValidarExistencia;
    private javax.swing.JLabel imagenCarritoTXT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCarrito;
    // End of variables declaration//GEN-END:variables

    private void CargarImagenesProductos() {
    //Se crea un Objeto JFileChoser
        this.modeloImagenes= new DefaultListModel<>();
        if(this.productosCatalogo.isEmpty())
            return;
        else{
            //de la colmna seleccionada obtenemos todos los nombres de imagenes y las agregamos almodelo
            for (int i=0;i<this.productosCatalogo.get(columnaVisualizar).getImagenes().size();i++)
                modeloImagenes.addElement(this.productosCatalogo.get(columnaVisualizar).getImagenes().get(i).getIdImagen()+"");          
        }
        this.ListaVisualizar.setModel(modeloImagenes);
    }
    private void CargarImagenesProductosCarrito() {
    //Se crea un Objeto JFileChoser
        this.modeloNombresCarrito= new DefaultListModel<>();
        if(this.productosCarrito.isEmpty())
            return;
        else{
            //de la colmna seleccionada obtenemos todos los nombres de imagenes y las agregamos almodelo
            for (int i=0;i<this.productosCarrito.get(columnaCarrito).getImagenes().size();i++)
                modeloNombresCarrito.addElement(this.productosCarrito.get(columnaCarrito).getImagenes().get(i).getIdImagen()+"");          
        }
        this.ListaCarrito.setModel(modeloNombresCarrito);
    }
    private void CrearCarrito() 
    {
        Mensaje msj= new Mensaje("ObtenerCarritoUsuarioID");
        msj.setIdCarrito(Integer.parseInt(IdUsuarioTXT.getText()));
        try {
            Mensaje respuesta=cl.EnviarArchivos(msj);
            if(respuesta.getAccion().equals("RegistroExiste"))
                JOptionPane.showMessageDialog(null, "El usuario ya tiene carrito","no se puede crear", JOptionPane.INFORMATION_MESSAGE);
            else{
                JOptionPane.showMessageDialog(null, "Carrito Creado","CarritoCreado", JOptionPane.INFORMATION_MESSAGE);
            }
        Mensaje msj2=new Mensaje("ObtenerCarritoUsuarioIdentificadores");
        msj2.setIdCarrito(Integer.parseInt(IdUsuarioTXT.getText()));
        msj2=cl.EnviarArchivos(msj2);
        this.idUsuario=msj2.getUsuario().getIdUsuario();
        this.idCarrito=msj2.getUsuario().getIdCarrito();
        
        System.out.println("id Usuario:"+idUsuario);
        System.out.println("id Carrtio:"+idCarrito);

        } catch (IOException ex) {
            Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IGCliente.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
