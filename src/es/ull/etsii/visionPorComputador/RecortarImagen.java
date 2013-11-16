/**
 * @author Daniel Afonso González
 * @author Rodrigo Valladares Santana
 * 
 * @version 1.0, 13/11/13
 *
 * Proyecto de Visión Por Computador 2013/14
 * 
 * Wrap de com.kushal.graphics.ImageCropper
 */
package es.ull.etsii.visionPorComputador;

import com.kushal.graphics.ImageCropper;

public class RecortarImagen {

  static public Imagen getRecorte(Imagen imagen, Coordenadas puntoInicio,
      Coordenadas puntoFinal) {
    int XIni = puntoInicio.getX();
    int YIni = puntoInicio.getY();
    int ancho = puntoFinal.getY() - YIni;
    int alto = puntoFinal.getX() - XIni;
    try {
      return new Imagen(ImageCropper.cropMyImage(imagen.getImagen(), alto,
          ancho, XIni, YIni), imagen.getNombre() + "_cropped");
    } catch (Exception e) {
      System.err.println("Error al recortar " + imagen.getNombre());
      return null;
    }
  }

}
