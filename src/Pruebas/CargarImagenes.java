package Pruebas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CargarImagenes extends Component{
    BufferedImage img;
    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
 
    public CargarImagenes(String ruta) {
       try {
           img = ImageIO.read(new File(ruta));
           
       } catch (IOException e) {
       }
 
    }
 
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(10,10);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
    
    public void redimensionar(int newW, int newH) {
    int w = this.img.getWidth();
    int h = this.img.getHeight();
    BufferedImage bufim = new BufferedImage(newW, newH, this.img.getType());
    Graphics2D g = bufim.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.drawImage(this.img, 0, 0, newW, newH, 0, 0, w, h, null);
    g.dispose();
    this.img=bufim;
    }
}
