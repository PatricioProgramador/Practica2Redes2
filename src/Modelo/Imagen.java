package Modelo;

import java.io.Serializable;
import javax.swing.ImageIcon;

public class Imagen implements Serializable{
    ImageIcon image; 
    int idImagen;

    public Imagen(ImageIcon image, int nombre) {
        this.image = image;
        this.idImagen = nombre;
    }

    
    
    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    
    
    
}
