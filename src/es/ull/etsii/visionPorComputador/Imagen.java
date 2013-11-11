/** @author Daniel Afonso González
 *  @author Rodrigo Valladares Santana
 *  @version 1.0, 04/11/13
 *  
 *  Proyecto de Visión Por Computador 2013/14
 *  
 *  Clase en la que se guarda la imagen que va a ser modificada. Esta se alma-
 *  cena como un BufferedImage y en escala de grises. A pesar de ello, se man-
 *  tienen los tres arrays para los colores rojo, verde y azul, aunque en este
 *  caso contienen la misma información. 
 *  
 *  Versión 1.1 04/11/2013
 *  Se crea el Histograma de la Imagen nada más ser inicializada esta
 *  
 *  Versión 1.0 30/10/2013
 *  Se tienen las funciones para acceder al valor de cada píxel y cambiarlo,
 *  pero aún no están implementadas.
 */
package es.ull.etsii.visionPorComputador;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Imagen {
  
  // En imagen se guarda la información de la imagen. Se puede acceder a los 
  // datos de los píxeles en un BufferedImage y ser modificados
  BufferedImage imagen;
  // Histograma de la imagen. Se crea automáticamente al crear una imagen.
  Histograma histograma;
  double brillo;
  double contraste;
  double entropia;
  
  /**
   * Constructor al que se le pasa la ruta de la imagen
   * @param linkImagen
   */
  public Imagen(String linkImagen) {
    try {
      // TODO Falta pasar la imagen a escala de grises
      setImagen(ImageIO.read(new File(linkImagen)));
      // Se crea el histograma pasando como parámetro la imagen actual
      this.imagen=this.set_gris(this.getImagen());
      setHistograma(new Histograma(this.getImagen()));
      this.setBrillo();
    } catch (IOException e) {
      System.err.println("Error al abrir " + linkImagen);
      e.printStackTrace();
      setImagen(null);
      setHistograma(null);
    }
  }
  
  /**
   * Devuelve el ancho de la imagen en píxeles
   * @return
   */
  public int getAncho() {
    return getImagen().getWidth();
  }
  
  /**
   * Devuelve el alto de la imagen en píxeles
   * @return
   */
  public int getAlto() {
    return getImagen().getHeight();
  }
/* Devuelve el brillo de la imagen */
  public void setBrillo(){
	  double temp = 0;
	  int [] histo=this.histograma.getHistograma();
	  for( int i = 0; i < 255; i++ ){
		  temp+=histo[i]*i;
	  }
	  this.brillo=temp/255;
	  
  }
  
  public double getBrillo(){
	  return this.brillo;
  }
  
  public void setContraste(){
	  double temp = 0;
	  double u = getBrillo();
	  int [] histo=this.histograma.getHistograma();
	  for( int i = 0; i < 255; i++ ){
		  temp+= Math.pow((i-u),2)*histo[i];
	  }
	  this.contraste=Math.sqrt(temp/255);
	  
  }
  
  public double getContraste(){
	  return this.contraste;
  }
  
  public void setEntropia(){
	  double temp = 0;
	  int [] histo=this.histograma.getHistograma();
	  for( int i = 0; i < 255; i++ ){
		  double p=histo[i]/255;
		  temp+= p*Math.log(p);
	  }
	  this.contraste=-temp;
	  
  }
  
  public double getEntropia(){
	  return this.contraste;
  }
  
  /**
   * Devuelve el valor numérico del píxel que se encuentra en la posición i j
   * @param i
   * @param j
   * @return
   */
  public int getPixel(int i, int j) {
    // TODO Comprobar que i y j está dentro del rango
    Color color = new Color(getImagen().getRGB(i, j));
    return color.getRed();
  }
  
  /**
   * Modifica el píxel en i j para que tenga el valor valPixel
   * @param i
   * @param j
   * @param valPixel
   */
  public void setPixel(int i, int j, int valPixel) {
    // TODO Modificar el píxel en los tres arrays de colores RGB
  }
  
  /**
   * Devuelve el BufferedImage de Imagen
   * @return
   */
  public BufferedImage getImagen() {
    return imagen;
  }

  /**
   * 
   * @param imagen
   */
  private void setImagen(BufferedImage imagen) {
    this.imagen = imagen;
  }
  
  /**
   * 
   * @return
   */
  public Histograma getHistograma() {
    return histograma;
  }

  /**
   * 
   * @param histograma
   */
  private void setHistograma(Histograma histograma) {
    this.histograma = histograma;
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
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }


}
