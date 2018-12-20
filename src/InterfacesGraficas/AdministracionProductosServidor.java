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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ale
 */
public class AdministracionProductosServidor extends javax.swing.JFrame {
    //Rutas de los archivos seleccionados para subir
    private String[] archivosSeleccionados=null;
    //modelos para las listas
    public DefaultListModel<String> modeloCargarImagenes;
    public DefaultListModel<String> modeloImagenes;
    
    public ArrayList<Producto> productosCatalogo;
    //Modelo para los campos de las tablas
    DefaultTableModel modeloTabla = new DefaultTableModel();
    Conexion con=new Conexion("root","xxxx");
    
    int columnaModificar=0, columnaVisualizar=0, columnaEliminar=0;
    
    public AdministracionProductosServidor() throws IOException, ClassNotFoundException, SQLException {
        initComponents();
        this.TablaModificar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                columnaModificar = TablaModificar.rowAtPoint(e.getPoint());
                //Cambiar los textbox
                descripcionTXT2.setText(productosCatalogo.get(columnaModificar).getDescripcion());
                tamanoTXT2.setText(productosCatalogo.get(columnaModificar).getTamano()+"");
                precioTXT2.setText(productosCatalogo.get(columnaModificar).getPrecio()+"");
                tiempoEntregaTXT2.setText(productosCatalogo.get(columnaModificar).getTiempoentrega()+"");
                existenciasTXT2.setText(productosCatalogo.get(columnaModificar).getExistencias()+"");
                idProductoTXT2.setText(productosCatalogo.get(columnaModificar).getIdProducto()+"");
            }
        });
        this.TablaVisualizar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                columnaVisualizar = TablaVisualizar.rowAtPoint(e.getPoint());
                CargarImagenesProductos();
            }
        });
        this.TablaEliminar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                columnaEliminar=TablaEliminar.rowAtPoint(e.getPoint());
                EliminarTXT.setText(productosCatalogo.get(columnaEliminar).getIdProducto()+"");
            }
        });
        this.ListaVisualizar.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int imagen=ListaVisualizar.getSelectedIndex();
                ImageIcon img=productosCatalogo.get(columnaVisualizar).getImagenes().get(imagen).getImage();
                MostrarImagenTXT.setIcon(img);
            }
        });
        
        
        this.visualizarTablaArticulos();
    }
    
    public void visualizarTablaArticulos() throws SQLException{
        this.modeloTabla= new DefaultTableModel();
        this.TablaModificar.setModel(modeloTabla);
        this.TablaEliminar.setModel(modeloTabla);
        this.TablaVisualizar.setModel(modeloTabla);
        
        modeloTabla.addColumn("idProducto");
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("Tamaño");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Tiempo entrega");
        modeloTabla.addColumn("Existencia");
        //Traemos los objetos de la base de datos
        this.productosCatalogo=con.ObtenerProductos();
        //Imprimimos en la tabla los productos obtenidos
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
        //Muestra la imagen en la lista
        CargarImagenesProductos();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        descripcionTXT = new javax.swing.JTextField();
        tamanoTXT = new javax.swing.JTextField();
        precioTXT = new javax.swing.JTextField();
        tiempoEntregaTXT = new javax.swing.JTextField();
        existenciasTXT = new javax.swing.JTextField();
        AgregarProducto = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaModificar = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        descripcionTXT2 = new javax.swing.JTextField();
        tamanoTXT2 = new javax.swing.JTextField();
        precioTXT2 = new javax.swing.JTextField();
        tiempoEntregaTXT2 = new javax.swing.JTextField();
        existenciasTXT2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        ExaminarModificar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        ListaModificar = new javax.swing.JList<>();
        Modificar = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        idProductoTXT2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaEliminar = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        EliminarTXT = new javax.swing.JTextField();
        EliminarBTN = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TablaVisualizar = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        ListaVisualizar = new javax.swing.JList<>();
        jLabel21 = new javax.swing.JLabel();
        MostrarImagenTXT = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Descripcion");

        jLabel2.setText("Tamaño");

        jLabel3.setText("Precio");

        jLabel4.setText("Tiempo Entrega");

        jLabel5.setText("Existencias");

        AgregarProducto.setText("Agregar Producto");
        AgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(descripcionTXT, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(tiempoEntregaTXT)
                    .addComponent(precioTXT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tamanoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(existenciasTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(AgregarProducto))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(descripcionTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(tamanoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tiempoEntregaTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(existenciasTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(precioTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AgregarProducto)))
                .addContainerGap(398, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Agregar", jPanel1);

        TablaModificar.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(TablaModificar);

        jLabel14.setText("Descripcion");

        jLabel15.setText("Tamaño");

        jLabel16.setText("Precio");

        jLabel17.setText("Tiempo Entrega");

        jLabel18.setText("Existencias");

        jLabel19.setText("Cargar Imágenes");

        ExaminarModificar.setText("Examinar");
        ExaminarModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExaminarModificarActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(ListaModificar);

        Modificar.setText("Modificar");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });

        jLabel20.setText("ID Producto");

        idProductoTXT2.setEditable(false);

        jLabel9.setText("Lista de imágenes");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(23, 23, 23)
                                        .addComponent(existenciasTXT2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel15))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(idProductoTXT2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                            .addComponent(tamanoTXT2))))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addGap(23, 23, 23))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addGap(18, 18, 18)))
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(descripcionTXT2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ExaminarModificar)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(precioTXT2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tiempoEntregaTXT2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Modificar)))))
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel20)
                    .addComponent(idProductoTXT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionTXT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tamanoTXT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(tiempoEntregaTXT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(precioTXT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel18)
                        .addComponent(existenciasTXT2)
                        .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19))
                    .addComponent(ExaminarModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Modificar", jPanel3);

        TablaEliminar.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(TablaEliminar);

        jLabel7.setText("Ingrese el id que quiera eliminar");

        EliminarTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarTXTActionPerformed(evt);
            }
        });

        EliminarBTN.setText("Eliminar");
        EliminarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(EliminarTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EliminarBTN)
                        .addGap(0, 146, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(EliminarTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EliminarBTN))
                .addContainerGap(283, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Eliminar", jPanel2);

        TablaVisualizar.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(TablaVisualizar);

        jScrollPane7.setViewportView(ListaVisualizar);

        jLabel21.setText("Lista de imagenes");

        MostrarImagenTXT.setBackground(new java.awt.Color(254, 254, 254));
        MostrarImagenTXT.setForeground(new java.awt.Color(254, 254, 254));
        MostrarImagenTXT.setMaximumSize(new java.awt.Dimension(100, 100));
        MostrarImagenTXT.setMinimumSize(new java.awt.Dimension(100, 100));

        jLabel8.setText("Imagen del producto");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(MostrarImagenTXT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MostrarImagenTXT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Visualizar", jPanel7);

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

    private void EliminarTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarTXTActionPerformed

    }//GEN-LAST:event_EliminarTXTActionPerformed

    private void EliminarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarBTNActionPerformed
        try {
            con.EliminarProducto(Integer.parseInt(this.EliminarTXT.getText()));
            this.modeloTabla= new DefaultTableModel();
            visualizarTablaArticulos();
            
        } catch (SQLException ex) {
            Logger.getLogger(AdministracionProductosServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EliminarBTNActionPerformed

    private void ExaminarModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExaminarModificarActionPerformed
        Examinar();
    }//GEN-LAST:event_ExaminarModificarActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        Producto pdto=new Producto(
                Integer.parseInt(idProductoTXT2.getText()),
                                 descripcionTXT2.getText(),
                Integer.parseInt(tamanoTXT2.getText()),
                Float.parseFloat(precioTXT2.getText()),
                Integer.parseInt(tiempoEntregaTXT2.getText()),
                Integer.parseInt(existenciasTXT2.getText()));
        try {
            con.ModificarProducto(pdto);
            if(archivosSeleccionados!=null)
            {
                ArrayList<Archivos> arch=new ArrayList<>();
                //Se crean los objetos que contienen la imagenes  
                for(int i=0;i<archivosSeleccionados.length;i++)
                    arch.add(new Archivos(archivosSeleccionados[i]));
                //Se insertan las imagenes de los productos en la base de datos
                for(int i=0;i<arch.size();i++)
                    con.InsertarImagenProducto(Integer.parseInt(idProductoTXT2.getText()), arch.get(i));
                
                //Quitar los archivos cargados de la lista
                archivosSeleccionados=null;
            }
            visualizarTablaArticulos();

        } catch (SQLException ex) {
            Logger.getLogger(AdministracionProductosServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ModificarActionPerformed

    private void AgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarProductoActionPerformed
        // TODO add your handling code here:
        Producto pdto=new Producto(this.descripcionTXT.getText(),
            Integer.parseInt(this.tamanoTXT.getText()),
            Float.parseFloat(this.precioTXT.getText()),
            Integer.parseInt(this.tiempoEntregaTXT.getText()),
            Integer.parseInt(this.existenciasTXT.getText()));
        try {
            con.AgregarProducto(pdto);
            visualizarTablaArticulos();
        } catch (SQLException ex) {
            Logger.getLogger(AdministracionProductosServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AgregarProductoActionPerformed
    
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
            java.util.logging.Logger.getLogger(AdministracionProductosServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministracionProductosServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministracionProductosServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministracionProductosServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AdministracionProductosServidor().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(AdministracionProductosServidor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdministracionProductosServidor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AdministracionProductosServidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarProducto;
    private javax.swing.JButton EliminarBTN;
    private javax.swing.JTextField EliminarTXT;
    private javax.swing.JButton ExaminarModificar;
    private javax.swing.JList<String> ListaModificar;
    private javax.swing.JList<String> ListaVisualizar;
    private javax.swing.JButton Modificar;
    private javax.swing.JLabel MostrarImagenTXT;
    private javax.swing.JTable TablaEliminar;
    private javax.swing.JTable TablaModificar;
    private javax.swing.JTable TablaVisualizar;
    private javax.swing.JTextField descripcionTXT;
    private javax.swing.JTextField descripcionTXT2;
    private javax.swing.JTextField existenciasTXT;
    private javax.swing.JTextField existenciasTXT2;
    private javax.swing.JTextField idProductoTXT2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField precioTXT;
    private javax.swing.JTextField precioTXT2;
    private javax.swing.JTextField tamanoTXT;
    private javax.swing.JTextField tamanoTXT2;
    private javax.swing.JTextField tiempoEntregaTXT;
    private javax.swing.JTextField tiempoEntregaTXT2;
    // End of variables declaration//GEN-END:variables

    private void Examinar() {
        //Se reinicia el modelo de texto
        this.modeloCargarImagenes= new DefaultListModel<>();
        JFileChooser fc=new JFileChooser();
        /*Se abre la ventana de seleccion y puede dar como resultadoo lo siguientes casos
        1.-JFileChooser.CANCEL_OPTION: el usuario dio cancelar
        2.-JFileChoser.APPROVE_OPTION: el usuario dio click en aceptar
        3.-JFileChooser:ERROR_OPTION: Si ocurre un error
        */
        fc.setMultiSelectionEnabled(true);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion=fc.showOpenDialog(jPanel1);

        //Si el usuario da en aceptar
        if(seleccion==JFileChooser.APPROVE_OPTION)
        {
            File[] seleccionados=fc.getSelectedFiles();
            this.archivosSeleccionados=new String[seleccionados.length];
            for(int i=0; i<seleccionados.length;i++)
            {
                //en modeloCargarImagenes contiene los nombres de las imagenes
                this.modeloCargarImagenes.addElement(seleccionados[i].getName());
                this.archivosSeleccionados[i]=seleccionados[i].getAbsolutePath();
            }
            this.ListaModificar.setModel(this.modeloCargarImagenes);
        }    
    }
    private void CargarImagenesProductos() {
        //Se crea el modelo para la lista de texto
        this.modeloImagenes= new DefaultListModel<>();
        //Obtenemos los nombres de las imagenes para el producto seleccionado
        for (int i=0;i<this.productosCatalogo.get(columnaVisualizar).getImagenes().size();i++)
            modeloImagenes.addElement(this.productosCatalogo.get(columnaVisualizar).getImagenes().get(i).getIdImagen()+"");              
        //Se carga el modelo de imagenes en la lista
        this.ListaVisualizar.setModel(modeloImagenes);
    }
}
