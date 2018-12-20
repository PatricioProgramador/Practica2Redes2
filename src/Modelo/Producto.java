package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Producto implements Serializable{
    private int idProducto;
    private String descripcion;
    private int tamano;
    private float precio;
    private int tiempoentrega;
    private ArrayList<String> colores;
    private ArrayList<Imagen> imagenes;
    private int existencias;
    
    public Producto(int idProducto){
        this.idProducto=idProducto;
    }
    
    public Producto(int idProducto, String descripcion, int tamano, float precio, int tiempoentrega, int existencias) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.tamano = tamano;
        this.precio = precio;
        this.tiempoentrega = tiempoentrega;
        this.existencias=existencias;
        this.colores=new ArrayList<>();
        this.imagenes=new ArrayList<>();
    }
    
    public Producto(String descripcion, int tamano, float precio, int tiempoentrega, int existencias) {
        this.descripcion = descripcion;
        this.tamano = tamano;
        this.precio = precio;
        this.tiempoentrega = tiempoentrega;
        this.existencias=existencias;
        this.colores=new ArrayList<>();
        this.imagenes=new ArrayList<>();
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }
    
    
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getTiempoentrega() {
        return tiempoentrega;
    }

    public void setTiempoentrega(int tiempoentrega) {
        this.tiempoentrega = tiempoentrega;
    }

    public ArrayList<String> getColores() {
        return colores;
    }

    public void setColores(ArrayList<String> colores) {
        this.colores = colores;
    }

    public ArrayList<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    
    
    
    @Override
    public String toString() {
        for(int i=0; i<this.imagenes.size();i++)
            JOptionPane.showMessageDialog(null, "Imagenes", "Imagen", JOptionPane.INFORMATION_MESSAGE, imagenes.get(i).getImage());
        
        return "Producto{" + "idProducto=" + idProducto + ", descripcion=" + descripcion + ", tamano=" + tamano + ", precio=" + precio + ", tiempoentrega=" + tiempoentrega + ", existencia="+existencias+'}';
    }
    
}
