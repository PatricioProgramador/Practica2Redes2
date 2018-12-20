/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sockets;

import Modelo.Producto;
import Modelo.UsuarioCarrito;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author ale
 */
public class Mensaje implements Serializable{
    private String accion;
    private ArrayList<Producto> pdtos;
    private Producto pdto;
    private int existencia;
    private int idCarrito;
    private UsuarioCarrito usuario;
    private long fecha;
    private byte[] pdf;
    public Mensaje(String accion) {
        this.accion = accion;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    
    
    public Producto getPdto() {
        return pdto;
    }

    public void setPdto(Producto pdto) {
        this.pdto = pdto;
    }

    
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public ArrayList<Producto> getPdtos() {
        return pdtos;
    }

    public void setPdtos(ArrayList<Producto> pdtos) {
        this.pdtos = pdtos;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public UsuarioCarrito getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioCarrito usuario) {
        this.usuario = usuario;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }
    
    
}
