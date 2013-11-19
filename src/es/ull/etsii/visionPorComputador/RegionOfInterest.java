/**
 * @author Daniel Afonso González
 * @author Rodrigo Valladares Santana
 * 
 * @version 1.0, 13/11/13
 *
 * Proyecto de Visión Por Computador 2013/14
 * 
 * Región seleccionada dentro de una imagen
 */
package es.ull.etsii.visionPorComputador;

import java.awt.Graphics;

import javax.swing.JComponent;

public class RegionOfInterest extends JComponent {

  private static final long serialVersionUID = 7823139005866456422L;

  private Coordenadas puntoInicio;
  private Coordenadas puntoFinal;
  private Imagen refImagen;

  public RegionOfInterest(Imagen refImagen) {
    setRefImagen(refImagen);
    setPuntoInicio(new Coordenadas());
    setPuntoFinal(new Coordenadas());
  }

  public RegionOfInterest(Imagen refImagen, Coordenadas puntoInicio,
      Coordenadas puntoFinal) {
    setRefImagen(refImagen);
    setPuntoInicio(puntoInicio);
    setPuntoFinal(puntoFinal);
  }

  public void setPuntos(Coordenadas puntoInicio, Coordenadas puntoFinal) {
    setPuntoInicio(puntoInicio);
    setPuntoFinal(puntoFinal);
  }

  public Imagen getRecorte() {

    System.out.println("getRecorte" + getPuntoInicio().toString() + getPuntoFinal().toString());

    return RecortarImagen.getRecorte(getRefImagen(), getPuntoInicio(),
        getPuntoFinal());
  }

  @Override
  public void paintComponent(Graphics g) {
    if (estaInicializado()) {
      // TODO Bug, solo recorta bien cuando se hace la selección hacia la 
      // izquierda y abajo
      /*
       * (x, y) es el punto en el que primero se hace clic con el ratón (x_, y_)
       * es el punto por el que se arrastra el ratón
       */
      int x = getPuntoInicio().getX();
      int y = getPuntoInicio().getY();
      int x_ = getPuntoFinal().getX();
      int y_ = getPuntoFinal().getY();
      int XIni;
      int YIni;
      int XFin;
      int YFin;
      int ancho;
      int alto;
      // El ratón se arrastra hacia la derecha y abajo
      if ((x_ >= x) && (y_ >= y)) {
        XIni = x;
        YIni = y;
        XFin = x_;
        YFin = y_;

      }
      // El ratón se arrastra hacia la derecha y arriba
      else if ((x_ >= x) && (y_ < y)) {
        XIni = x;
        YIni = y_;
        XFin = x_;
        YFin = y;
      }
      // El ratón se arrastra hacia la izquierda y arriba
      else if ((x_ < x) && (y_ < y)) {
        XIni = x_;
        YIni = y_;
        XFin = x;
        YFin = y;
      }
      // El ratón se arrastra hacia la izquierda y abajo
      else {
        XIni = x_;
        YIni = y;
        XFin = x;
        YFin = y_;
      }
      ancho = YFin - YIni;
      alto = XFin - XIni;
      g.drawRect(XIni, YIni, alto, ancho);
    }
  }

  /**
   * Pone los dos puntos a 0
   */
  public void reiniciar() {
    setPuntoInicio(new Coordenadas());
    setPuntoFinal(new Coordenadas());
  }

  public boolean estaInicializado() {
    if ((getPuntoInicio().getX() != 0)
        && (getPuntoInicio().getY() != 0 && (getPuntoFinal().getX() != 0) && (getPuntoFinal()
            .getY() != 0))) {
      return true;
    } else
      return false;
  }

  public Coordenadas getPuntoInicio() {
    return puntoInicio;
  }

  private void setPuntoInicio(Coordenadas puntoInicio) {
    this.puntoInicio = puntoInicio;
  }

  public Coordenadas getPuntoFinal() {
    return puntoFinal;
  }

  private void setPuntoFinal(Coordenadas puntoFinal) {
    this.puntoFinal = puntoFinal;
  }

  private Imagen getRefImagen() {
    return refImagen;
  }

  private void setRefImagen(Imagen refImagen) {
    this.refImagen = refImagen;
  }

}
