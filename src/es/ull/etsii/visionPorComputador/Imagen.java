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
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Imagen {
  
  // En imagen se guarda la información de la imagen. Se puede acceder a los 
  // datos de los píxeles en un BufferedImage y ser modificados
  BufferedImage imagen;
  // Histograma de la imagen. Se crea automáticamente al crear una imagen.
  Histograma histograma;
  float brillo;
  float contraste;
  float entropia;
  ArrayList<Integer> Histograma_acu;
  
  /**
   * Constructor al que se le pasa la ruta de la imagen
   * @param linkImagen
   */
  public Imagen(String linkImagen) {
    try {
      // TODO Falta pasar Especifiacion de histograma y correccion Gamma
      setImagen(ImageIO.read(new File(linkImagen)));
      // Se crea el histograma pasando como parámetro la imagen actual
      this.imagen=this.set_gris(this.getImagen());
      setHistograma(new Histograma(this.getImagen()));
      this.setBrillo();
      this.setContraste();
      this.Histograma_acu=this.histograma_acu();
      /* codigo para llamar a linear trans
      ArrayList<Coordenadas> points = new ArrayList<Coordenadas>();
      Coordenadas p1 = new Coordenadas(0,0); 
      Coordenadas p2 = new Coordenadas(25,100); 
      points.add(p1);
      points.add(p2);
      this.imagen=this.Linear_trans(points);
      
      */
     //this.imagen= this.BrilloYContraste(82, 10);
      this.imagen = this.Equalize();
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
  
  /*
   * Devuelve el máximo entre dos números
   * 
   */
  private int maximo(int a, int b){
	  return (a>b) ? a : b;
  }
  
  

  
  public ArrayList<Integer> histograma_acu(){
	  ArrayList<Integer> hist = new ArrayList<Integer>();
	  int temp =0;
	 int [] h = this.histograma.getHistograma();
	  for (int i=0; i<256; i++) {
		  temp+= h[i];
		  hist.add(temp);
	  }
	  return hist;
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
	  float temp = 0;
	  int size = imagen.getHeight() * imagen.getWidth()  ;

	  int [] histo=this.histograma.getHistograma();
	  for( int i = 0; i < 255; i++ ){
		  temp+=histo[i]*i;
	  }
	  this.brillo=(float) (temp/size);
	  
  }
  
  public float getBrillo(){
	  return this.brillo;
  }
  
  public void setContraste(){
	  float temp = 0;
	  int size = imagen.getHeight() * imagen.getWidth()  ;
	  float u = getBrillo();
	  int [] histo=this.histograma.getHistograma();
	  for( int i = 0; i < 255; i++ ){
		  temp+= Math.pow((i-u),2)*histo[i];
	  }
	  this.contraste=(float) Math.sqrt(temp/size);
	  
  }
  
  public double getContraste(){
	  return this.contraste;
  }
  
  public void setEntropia(){
	  float temp = 0;
	  int [] histo=this.histograma.getHistograma();
	  for( int i = 0; i < 255; i++ ){
		  float p=(float)(histo[i]/255);
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
  
  public BufferedImage Equalize(){
	  ArrayList<Integer> lut = new ArrayList<Integer>();
	  int size = imagen.getWidth() * imagen.getHeight();
	  int m = 256;
	  float cons= (float)m/(float)size;
	  int maxNumber = 0;
	  for (int i=0; i<256; i++) {
		  maxNumber = maximo(0, Math.round( (cons * this.Histograma_acu.get(i)) ) - 1);
          lut.add(maxNumber);  
	  }
	  BufferedImage newImg =  GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(imagen.getWidth(), imagen.getHeight(), Transparency.OPAQUE);

	     for( int i = 0; i < imagen.getWidth(); i++ ){

	         for( int j = 0; j < imagen.getHeight(); j++ ){
	        	 Color c1=new Color(imagen.getRGB(i, j));
	        	 int c_value= c1.getBlue();
	        	 int ncol=lut.get(c_value);
	        	 newImg.setRGB(i, j, new Color(ncol,ncol,ncol).getRGB());
	        	 
	         }        	 
	     }
	     
	     return newImg;
  }
  
  public BufferedImage BrilloYContraste(int nbrillo, int ncontr){
	 float A=(float) ((float)(ncontr)/(this.contraste));
	 float B = nbrillo-(float)(brillo*A);
     ArrayList<Integer> lut = new ArrayList<Integer>();
     
     for (int i=0; i<256; i++) {
             lut.add(compruebarango(0, 255, (int)(Math.round(A * i + B))));
     }
     BufferedImage newImg =  GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(imagen.getWidth(), imagen.getHeight(), Transparency.OPAQUE);

     for( int i = 0; i < imagen.getWidth(); i++ ){

         for( int j = 0; j < imagen.getHeight(); j++ ){
        	 Color c1=new Color(imagen.getRGB(i, j));
        	 int c_value= c1.getBlue();
        	 int ncol=lut.get(c_value);
        	 newImg.setRGB(i, j, new Color(ncol,ncol,ncol).getRGB());
        	 
         }        	 
     }
     
     return newImg;
	  
  }
  
  
  public BufferedImage Linear_trans(ArrayList<Coordenadas> points) {
	 	  
	 ArrayList<Integer> lut = new ArrayList<Integer>();
     Coordenadas initial = points.get(0);
     
     if (points.get(points.size()-1).getX() < 255) {
             points.add(new Coordenadas(255, 255));
     }
     
     for (int i=0; i<256; i++) {
             lut.add(i);
     }
     
     float denominator = 0;
     float m = 0;
     float n = 0;     
     for (int i=1; i<points.size(); i++) {
             denominator = (float)(points.get(i).getX() - initial.getX());
             if (denominator < 1E-5) {
                     denominator = 1;
             }
             m = (float)(points.get(i).getY() - initial.getY()) / denominator;
             n = points.get(i).getY() - n * points.get(i).getX();
             
             
             for (int j=initial.getX(); j<points.get(i).getX(); j++) {
                     lut.set(j, compruebarango(0, 255, Math.round((m*j)+n)));
             }
             
             initial = points.get(i);
     }
     BufferedImage newImg =  GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(imagen.getWidth(), imagen.getHeight(), Transparency.OPAQUE);

     for( int i = 0; i < imagen.getWidth(); i++ ){

         for( int j = 0; j < imagen.getHeight(); j++ ){
        	 Color c1=new Color(imagen.getRGB(i, j));
        	 int c_value= c1.getBlue();
        	 int ncol=lut.get(c_value);
        	 newImg.setRGB(i, j, new Color(ncol,ncol,ncol).getRGB());
        	 
         }        	 
     }
     
     return newImg;
  }
  private Integer compruebarango(int min, int max, int value) {
	  int value_check = Math.abs(value);
      if (value < min) {
              return min;
      } else if (value_check > max) {
              return max;
      }
      return value_check;
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
