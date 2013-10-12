package es.ull.etsii.visionPorComputador;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagen {
  
  BufferedImage imagen;
  
  public Imagen(String linkImagen) {
    try {
      // TODO Falta pasar la imagen a escala de grises
      setImagen(ImageIO.read(new File(linkImagen)));
    } catch (IOException e) {
      System.err.println("Error al abrir " + linkImagen);
      e.printStackTrace();
    }
  }

  public int getPixel(int i, int j) {
    Color color = new Color(getImagen().getRGB(i, j));
    return color.getRed();
  }
  
  public void setPixel(int i, int j, int valPixel) {
    
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
