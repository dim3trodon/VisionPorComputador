/** @author Daniel Afonso González
 *  @author Rodrigo Valladares Santana
 *  @version 1.2, 11/11/13
 *  
 *  Proyecto de Visión Por Computador 2013/14
 *  
 *  Clase en la que se guarda la imagen que va a ser modificada. Esta se alma-
 *  cena como un BufferedImage y en escala de grises. A pesar de ello, se man-
 *  tienen los tres arrays para los colores rojo, verde y azul, aunque en este
 *  caso contienen la misma información. 
 *  
 *  Versión 1.2 Añadido brillo, contraste, entropía y ruta de la imagen.
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
  int max;
  int min;
  ArrayList<Integer> Histograma_acu;
  private String ruta;
  private String nombre;

  public Imagen(BufferedImage bufferedImagen, String nombre) {
    // Nombre de la imagen
    String titulo = nombre;
    setImagen(bufferedImagen);
    setRuta("");
    setNombre(titulo);
    // Se crea el histograma pasando como parámetro la imagen actual
    //this.imagen = this.set_gris(this.getImagen());
    setHistograma(new Histograma(this.getImagen()));
    this.setBrillo();
    this.setContraste();
    this.Histograma_acu = this.histograma_acu();
    this.setEntropia();
    this.set_maxmin();
  }

  /**
   * Constructor al que se le pasa la ruta de la imagen
   * 
   * @param linkImagen
   */
  public Imagen(String linkImagen) {
    try {
      // Nombre de la imagen
      setImagen(ImageIO.read(new File(linkImagen)));
      setRuta(linkImagen);
      setNombre(TratamientoCadenas.getNombreImagen(linkImagen));
      // Se crea el histograma pasando como parámetro la imagen actual
      this.imagen = this.set_gris(this.getImagen());
      setHistograma(new Histograma(this.getImagen()));
      this.setBrillo();
      this.setContraste();
      this.Histograma_acu = this.histograma_acu();
      this.setEntropia();
      this.set_maxmin();
      /*
       * codigo para llamar a linear trans 
       ArrayList<Coordenadas> points = new
       ArrayList<Coordenadas>(); Coordenadas p1 = new Coordenadas(0,255);
       Coordenadas p2 = new Coordenadas(255,0); points.add(p1);
       points.add(p2); this.imagen=this.Linear_trans(points);
       */
      // this.imagen= this.BrilloYContraste(82, 10);
      // this.imagen = this.Equalize();
      // this.imagen=this.Gammacorrection(12);
      // this.imagen = this.Diferencia(this);
    } catch (IOException e) {
      System.err.println("Error al abrir " + linkImagen);
      e.printStackTrace();
      setImagen(null);
      setHistograma(null);
    }
  }

  /**
   * Devuelve el ancho de la imagen en píxeles
   * 
   * @return
   */
  public int getAncho() {
    return getImagen().getWidth();
  }

  /*
   * Devuelve el máximo entre dos números
   */
  private int maximo(int a, int b) {
    return (a > b) ? a : b;
  }

  public BufferedImage Histo_especify(Imagen imag) {
    int vout = 0;
    ArrayList<Long> accHistogramRef = imag.histograma_acu_norm();
    ArrayList<Long> accHistogramImg = this.histograma_acu_norm();
    ArrayList<Integer> lut = new ArrayList<Integer>();

    for (int i = 0; i < 256; i++) {
      lut.add(0);
    }

    for (int i = 0; i < accHistogramImg.size(); i++) {
      vout = BuscaValornormalizado(accHistogramImg.get(i), accHistogramRef);
      lut.set(i, vout);
    }

    BufferedImage newImg = GraphicsEnvironment
        .getLocalGraphicsEnvironment()
        .getDefaultScreenDevice()
        .getDefaultConfiguration()
        .createCompatibleImage(imagen.getWidth(), imagen.getHeight(),
            Transparency.OPAQUE);

    for (int i = 0; i < imagen.getWidth(); i++) {

      for (int j = 0; j < imagen.getHeight(); j++) {
        Color c1 = new Color(imagen.getRGB(i, j));
        int c_value = c1.getBlue();
        int ncol = lut.get(c_value);
        newImg.setRGB(i, j, new Color(ncol, ncol, ncol).getRGB());

      }
    }

    return newImg;
  }
  private void set_maxmin(){
		int i =0;
		boolean encontrado = false;
	  int[] histo = this.histograma.getHistograma();
		while ((i < 256)&& encontrado==false) {
			if (histo[i]!=0){
				encontrado=true;
				this.min=i;
			}
			i++;
		}
		i=255;
		encontrado=false;
		while ((i > 0)&& encontrado==false) {
			if (histo[i]!=0){
				encontrado=true;
				this.max=i;
			}
			i--;
		}
	}
  private ArrayList<Long> histograma_acu_norm() {
    long acc = 0;
    ArrayList<Long> accHistogram = new ArrayList<Long>();
    int[] histo = this.histograma.getHistograma();
    double size = imagen.getWidth() * imagen.getHeight();
    for (int i = 0; i < 256; i++) {
      accHistogram.add(Math.round((double) (histo[i] + acc) * 100 / size));
      acc = histo[i] + acc;
    }

    return accHistogram;
  }

  // Calcula la diferencia entre dos imágenes
  public BufferedImage Diferencia(Imagen image) {
    Color c1, c2 = new Color(0, 0, 0);
    int i1, i2, i3 = 0;
    BufferedImage newImg = GraphicsEnvironment
        .getLocalGraphicsEnvironment()
        .getDefaultScreenDevice()
        .getDefaultConfiguration()
        .createCompatibleImage(imagen.getWidth(), imagen.getHeight(),
            Transparency.OPAQUE);
    // Comprobamos que las imagenes tienen las mismas dimensiones
    if (image.imagen.getWidth() == imagen.getWidth()
        && image.imagen.getHeight() == imagen.getHeight())
      for (int i = 0; i < imagen.getWidth(); i++) {
        for (int j = 0; j < imagen.getHeight(); j++) {
          c1 = new Color(imagen.getRGB(i, j));
          c2 = new Color(image.imagen.getRGB(i, j));
          i1 = c1.getBlue();
          i2 = c2.getBlue();
          i3 = Math.abs(i1 - i2);
          i3 = compruebarango(0, 255, i3);
          newImg.setRGB(i, j, new Color(i3, i3, i3).getRGB());
        }
      }

    return newImg;
  }

  // metodo para la busca del valor normalizado
  private Integer BuscaValornormalizado(long nivel,
      ArrayList<Long> histo_acc_nor) {
    int index = 0;
    int selectedIndex = 0;
    boolean cont = true;
    while ((index < histo_acc_nor.size()) && cont) {
      if (histo_acc_nor.get(index).equals(nivel)) {
        selectedIndex = index;
        cont = false;
      } else if (histo_acc_nor.get(index) > nivel) {
        selectedIndex =maximo(0, index - 1);
        cont = false;
      }
      index++;
    }
    return selectedIndex;
  }

  public ArrayList<Integer> histograma_acu() {
    ArrayList<Integer> hist = new ArrayList<Integer>();
    int temp = 0;
    int[] h = this.histograma.getHistograma();
    for (int i = 0; i < 256; i++) {
      temp += h[i];
      hist.add(temp);
    }
    return hist;
  }

  /**
   * Devuelve el alto de la imagen en píxeles
   * 
   * @return
   */
  public int getAlto() {
    return getImagen().getHeight();
  }

  /* Devuelve el brillo de la imagen */
  public void setBrillo() {
    float temp = 0;
    float size = imagen.getHeight() * imagen.getWidth();

    int[] histo = this.histograma.getHistograma();
    for (int i = 0; i < 256; i++) {
      temp += (float)(histo[i] * i);
    }
    this.brillo = (float) (temp / size);

  }

  public float getBrillo() {
    return this.brillo;
  }

  public void setContraste() {
	 
    float temp = 0;
    float size = imagen.getHeight() * imagen.getWidth();
    float u = getBrillo();
    int[] histo = this.histograma.getHistograma();
    for (int i = 0; i < 256; i++) {
      temp += Math.pow((i - u), 2) * histo[i];
    }
    this.contraste = (float) Math.sqrt(temp / size);

  }

  public double getContraste() {
    return this.contraste;
  }

  public void setEntropia() {
    float temp = 0;
    float size = this.imagen.getHeight()*this.imagen.getWidth();
    int[] histo = this.histograma.getHistograma();
    for (int i = 0; i < 256; i++) {
      float p = (float) (histo[i]) / (size);
      if (p != 0) {
      temp += p * (Math.log(p)/Math.log(2));
      }
      }
    this.entropia = -temp;

  }

  public float getEntropia() {
    return this.entropia;
  }

  /**
   * Devuelve el valor numérico del píxel que se encuentra en la posición i j
   * 
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
   * 
   * @param i
   * @param j
   * @param valPixel
   */
  public void setPixel(int i, int j, int valPixel) {
    // TODO Modificar el píxel en los tres arrays de colores RGB
  }

  /**
   * Devuelve el BufferedImage de Imagen
   * 
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

  public BufferedImage Equalize() {
    ArrayList<Integer> lut = new ArrayList<Integer>();
    int size = imagen.getWidth() * imagen.getHeight();
    int m = 256;
    float cons = (float) m / (float) size;
    int maxNumber = 0;
    for (int i = 0; i < 256; i++) {
      maxNumber = maximo(0, Math.round((cons * this.Histograma_acu.get(i))) - 1);
      lut.add(maxNumber);
    }
    BufferedImage newImg = GraphicsEnvironment
        .getLocalGraphicsEnvironment()
        .getDefaultScreenDevice()
        .getDefaultConfiguration()
        .createCompatibleImage(imagen.getWidth(), imagen.getHeight(),
            Transparency.OPAQUE);

    for (int i = 0; i < imagen.getWidth(); i++) {

      for (int j = 0; j < imagen.getHeight(); j++) {
        Color c1 = new Color(imagen.getRGB(i, j));
        int c_value = c1.getBlue();
        int ncol = lut.get(c_value);
        newImg.setRGB(i, j, new Color(ncol, ncol, ncol).getRGB());

      }
    }

    return newImg;
  }

  public BufferedImage BrilloYContraste(int nbrillo, int ncontr) {
    float A = (float) ((float) (ncontr) / (this.contraste));
    float B = nbrillo - (float) (brillo * A);
    ArrayList<Integer> lut = new ArrayList<Integer>();

    for (int i = 0; i < 256; i++) {
      lut.add(compruebarango(0, 255, (int) (Math.round(A * i + B))));
    }
    BufferedImage newImg = GraphicsEnvironment
        .getLocalGraphicsEnvironment()
        .getDefaultScreenDevice()
        .getDefaultConfiguration()
        .createCompatibleImage(imagen.getWidth(), imagen.getHeight(),
            Transparency.OPAQUE);

    for (int i = 0; i < imagen.getWidth(); i++) {

      for (int j = 0; j < imagen.getHeight(); j++) {
        Color c1 = new Color(imagen.getRGB(i, j));
        int c_value = c1.getBlue();
        int ncol = lut.get(c_value);
        newImg.setRGB(i, j, new Color(ncol, ncol, ncol).getRGB());

      }
    }

    return newImg;

  }

  public BufferedImage Linear_trans(ArrayList<Coordenadas> points) {

    ArrayList<Integer> lut = new ArrayList<Integer>();
    Coordenadas initial = points.get(0);

    if (points.get(points.size() - 1).getX() < 255) {
      points.add(new Coordenadas(255, 255));
    }

    for (int i = 0; i < 256; i++) {
      lut.add(i);
    }

    float denominator = 0;
    float m = 0;
    float n = 0;
    for (int i = 1; i < points.size(); i++) {
      denominator = (float) (points.get(i).getX() - initial.getX());
      if (denominator < 1E-5) {
        denominator = 1;
      }
      m = (float) (points.get(i).getY() - initial.getY()) / denominator;
      n = points.get(i).getY() - m * points.get(i).getX();

      for (int j = initial.getX(); j < points.get(i).getX(); j++) {
        lut.set(j, compruebarango(0, 255, Math.round((m * j) + n)));
      }

      initial = points.get(i);
    }
    BufferedImage newImg = GraphicsEnvironment
        .getLocalGraphicsEnvironment()
        .getDefaultScreenDevice()
        .getDefaultConfiguration()
        .createCompatibleImage(imagen.getWidth(), imagen.getHeight(),
            Transparency.OPAQUE);

    for (int i = 0; i < imagen.getWidth(); i++) {

      for (int j = 0; j < imagen.getHeight(); j++) {
        Color c1 = new Color(imagen.getRGB(i, j));
        int c_value = c1.getBlue();
        int ncol = lut.get(c_value);
        newImg.setRGB(i, j, new Color(ncol, ncol, ncol).getRGB());

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

    BufferedImage bi = GraphicsEnvironment
        .getLocalGraphicsEnvironment()
        .getDefaultScreenDevice()
        .getDefaultConfiguration()
        .createCompatibleImage(imagen.getWidth(), imagen.getHeight(),
            Transparency.OPAQUE);
    double newR, newG, newB;
    for (int i = 0; i < imagen.getWidth(); i++) {

      for (int j = 0; j < imagen.getHeight(); j++) {

        // Obtiene el color

        Color c1 = new Color(imagen.getRGB(i, j));
        newR = 0.299 * (float) (c1.getRed());
        newG = 0.587 * (float) (c1.getGreen());
        newB = 0.114 * (float) (c1.getBlue());
        // Calcula la media de tonalidades
        int med = (int) Math.round(newR + newG + newB);
        // int med = (c1.getRed() + c1.getGreen() + c1.getBlue()) / 3;

        // Almacena el color en la imagen destino
        med = compruebarango(0, 255, med);
        bi.setRGB(i, j, new Color(med, med, med).getRGB());

      }

    }

    return bi;

  }

  public BufferedImage Gammacorrection(float gamma) {
    double a, b;
    ArrayList<Integer> lut = new ArrayList<Integer>();
    for (int i = 0; i < 256; i++) {
      // b = a^gamma, a and b normalized
      a = (double) i / (double) 255; // In interval [0,1]
      b = Math.pow(a, gamma);
      lut.add(compruebarango(0, 255, Long.valueOf(Math.round(b * 255))
          .intValue()));
    }

    BufferedImage newImg = GraphicsEnvironment
        .getLocalGraphicsEnvironment()
        .getDefaultScreenDevice()
        .getDefaultConfiguration()
        .createCompatibleImage(imagen.getWidth(), imagen.getHeight(),
            Transparency.OPAQUE);

    for (int i = 0; i < imagen.getWidth(); i++) {

      for (int j = 0; j < imagen.getHeight(); j++) {
        Color c1 = new Color(imagen.getRGB(i, j));
        int c_value = c1.getBlue();
        int ncol = lut.get(c_value);
        newImg.setRGB(i, j, new Color(ncol, ncol, ncol).getRGB());

      }
    }

    return newImg;
  }
public BufferedImage Mapa_cambios(Imagen imag, int umbral){
	BufferedImage Diferencia;
	Color red = Color.red;
	Diferencia = this.Diferencia(imag);
	 BufferedImage newImg = GraphicsEnvironment
		        .getLocalGraphicsEnvironment()
		        .getDefaultScreenDevice()
		        .getDefaultConfiguration()
		        .createCompatibleImage(imagen.getWidth(), imagen.getHeight(),
		            Transparency.OPAQUE);
    for (int i = 0; i < imagen.getWidth(); i++) {
      for (int j = 0; j < imagen.getHeight(); j++) {
    	  Color c1 = new Color(Diferencia.getRGB(i, j));
          int c_value = c1.getBlue();
          if (c_value > umbral){
        	  newImg.setRGB(i, j, red.getRGB());
          }
          else
        	  newImg.setRGB(i, j, imagen.getRGB(i,j));
      }
    }
	return newImg;
}
  public String getRuta() {
    return ruta;
  }

  private void setRuta(String ruta) {
    this.ruta = ruta;
  }

  public String getNombre() {
    return nombre;
  }

  private void setNombre(String nombre) {
    this.nombre = nombre;
  }

}
