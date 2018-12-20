/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sockets;

import Modelo.Producto;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ale
 */
public class Cliente {
    private String DIRECCION="localhost";
    private int PUERTO=3478;
    private Socket cliente;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public Cliente(String direccion, int puerto) throws IOException{
            this.DIRECCION=direccion;
            this.PUERTO=puerto;
            //Establecer Conexion con el servidor
            this.cliente=new Socket(DIRECCION,PUERTO);
            out=new ObjectOutputStream(this.cliente.getOutputStream());
            in=new ObjectInputStream(this.cliente.getInputStream());
    }
    
    public Mensaje EnviarArchivos(Mensaje msj) throws IOException, ClassNotFoundException
    {
        //inicializar flujos
        
        out.writeObject(msj);
        Mensaje res=(Mensaje)in.readObject();
        return res;
    }
    /*public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        Cliente cl=new Cliente("localhost",3478);
        Producto pd=new Producto("Computadora",3,(float)769.12,34,12);
        Mensaje msj=new Mensaje("ObtenerProducto");
        //enviar dato
        
        Mensaje pdtos=cl.EnviarArchivos(msj);
        for(int i=0;i<pdtos.getPdtos().size();i++)
            pdtos.getPdtos().get(i).toString();
        
        Mensaje pdtos2=cl.EnviarArchivos(msj);
        cl.cliente.close();
    }*/
}
