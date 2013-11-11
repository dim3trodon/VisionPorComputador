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

public class Histograma {

  private int[] histograma;

  /**
   * Constructor que construye el histograma a partir de una imagen
   * 
   * @param imagen
   */
  public Histograma(BufferedImage imagen) {

    Color color;
    int[] histogra = new int[256];

    for (int i = 0; i < imagen.getWidth(); i++) {
      for (int j = 0; j < imagen.getHeight(); j++) {

        color = new Color(imagen.getRGB(i, j));

        histogra[color.getGreen()] += 1;

      }

    }

    setHistograma(histogra);

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
