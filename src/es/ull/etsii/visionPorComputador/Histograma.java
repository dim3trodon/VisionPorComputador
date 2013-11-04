/** @author Daniel Afonso González
 *  @author Rodrigo Valladares Santana
 *  @version 1.0, 30/10/13
 *  
 *  Proyecto de Visión Por Computador 2013/14
 *  
 *  Clase en la que se guarda un array con los valores del histograma
 */
package es.ull.etsii.visionPorComputador;

import java.util.Vector;

public class Histograma {
  
  private Vector<Integer> histograma;
  
  /**
   * Constructor que construye el histograma a partir de una imagen
   * @param imagen
   */
  public Histograma(Imagen imagen) {
    // TODO Construir histograma de imagen
    setHistograma(new Vector<Integer>());
  }

  /**
   * Devuelve el array con los datos del histograma
   * @return
   */
  public Vector<Integer> getHistograma() {
    return histograma;
  }

  /**
   * 
   * @param histograma
   */
  private void setHistograma(Vector<Integer> histograma) {
    this.histograma = histograma;
  }
  
}
