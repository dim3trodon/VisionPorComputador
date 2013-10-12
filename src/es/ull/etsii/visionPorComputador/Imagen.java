package es.ull.etsii.visionPorComputador;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagen {
  
  BufferedImage imagen;
  
  public Imagen(String linkImagen) {
    try {
      setImagen(ImageIO.read(new File(linkImagen)));
    } catch (IOException e) {
      System.err.println("Error al abrir " + linkImagen);
      e.printStackTrace();
    }
  }

  

  private BufferedImage getImagen() {
    return imagen;
  }

  private void setImagen(BufferedImage imagen) {
    this.imagen = imagen;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
