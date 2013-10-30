package es.ull.etsii.visionPorComputador;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagen {
  
  BufferedImage imagen;
  
  public Imagen(String linkImagen) {
    try {
      // TODO Falta pasar la imagen a escala de grises
    	
      this.setImagen(ImageIO.read(new File(linkImagen)));
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

  public BufferedImage getImagen() {
    return imagen;
  }
  
 

  public void setImagen(BufferedImage imagen) {
    this.imagen = imagen;
  }
  public BufferedImage set_gris(BufferedImage imagen) {
	  BufferedImage bi =  GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(imagen.getWidth(), imagen.getHeight(), Transparency.OPAQUE);
	  for( int i = 0; i < imagen.getWidth(); i++ ){
          for( int j = 0; j < imagen.getHeight(); j++ ){
        	//Obtiene el color
			Color c1=new Color(imagen.getRGB(i, j));
			//Calcula la media de tonalidades
			int med=(c1.getRed()+c1.getGreen()+c1.getBlue())/3;
			//Almacena el color en la imagen destino
			bi.setRGB(i, j, new Color(med,med,med).getRGB());  
          }
  
	  }
	  return bi;
  }
  
  public int[] histograma(BufferedImage imagen){
	  Color color;
	  int[] histogra = new int[256];
	  for( int i = 0; i < imagen.getWidth(); i++ ){
          for( int j = 0; j < imagen.getHeight(); j++ ){
        	  color = new Color(imagen.getRGB(i, j));
        	  histogra[color.getGreen()]+=1;
          }
	  }
	  return histogra;
	  
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
