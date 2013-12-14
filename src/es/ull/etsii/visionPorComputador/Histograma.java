/** @author Daniel Afonso González
 *  @author Rodrigo Valladares Santana
 *  @version 1.0, 30/10/13
 *  
 *  Proyecto de Visión Por Computador 2013/14
 *  
 *  Clase en la que se guarda un array con los valores del histograma
 */
package es.ull.etsii.visionPorComputador;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Histograma {
  
  public static final int NUMERO_PIXELES = 256;

  private int[] histograma;

  /**
   * Constructor que construye el histograma a partir de una imagen
   * 
   * @param imagen
   */
  public Histograma(BufferedImage imagen) {
    Color red = Color.red;
    Color color;
    int[] histogra = new int[NUMERO_PIXELES];

    for (int i = 0; i < imagen.getWidth(); i++) {
      for (int j = 0; j < imagen.getHeight(); j++) {

        color = new Color(imagen.getRGB(i, j));
        if(color != red)
          histogra[color.getGreen()] += 1;

      }

    }

    setHistograma(histogra);
  }
  
  public Histograma(ArrayList<Integer> arrayHisto) {
    int[] histogra = new int[NUMERO_PIXELES];
    for(int i = 0; i < NUMERO_PIXELES; i++)
      histogra[i] = (int) arrayHisto.get(i).intValue();
    setHistograma(histogra);
  }
  
  public int getValor(int i) {
    return getHistograma()[i];
  }

  /**
   * Devuelve el array con los datos del histograma
   * 
   * @return
   */
  public int[] getHistograma() {
    return histograma;
  }

  /**
   * 
   * @param histograma
   */
  private void setHistograma(int[] histograma) {
    this.histograma = histograma;
  }

}
