/**
 * @author Daniel Afonso González
 * @author Rodrigo Valladares Santana
 * 
 * @version 1.1, 13/11/13
 *
 * Proyecto de Visión Por Computador 2013/14
 * 
 * Acciones del menú de aplicaciones
 * 
 * Versión 1.1: Añadido Ver/Histograma
 * 
 * Versión 1.0: Archivo/Abrir e Imagen/Propiedades
 * 
 */
package es.ull.etsii.visionPorComputador;

public class AccionesMenu {
  
  private Interfaz interfazRef;

  public AccionesMenu(Interfaz interfazRef) {
    setInterfazRef(interfazRef);
  }
  
  /**
   * Acción del menú Archivo/Abrir
   * @param linkImagen
   */
  public void ArchivoAbrir(String linkImagen) {
    String[] partesRutaImagen = linkImagen.split("/");
    String titulo = partesRutaImagen[partesRutaImagen.length - 1];
    getInterfazRef().crearNuevaVentana(linkImagen, titulo);
  }
  
  /**
   * Acción de Ver/Histograma 
   * @param histograma
   * @param nombreImagen
   */
  public void VerHistograma(Histograma histograma, String nombreImagen) {
    getInterfazRef().crearVentanaHistograma(histograma, nombreImagen);
  }
  
  /**
   * Acción de Ver/Histograma acumulativo
   * @param histograma
   * @param nombreImagen
   */
  public void VerHistogramaAcc(Histograma histograma, String nombreImagen) {
    getInterfazRef().crearVentanaHistograma(histograma, nombreImagen);
  }
  
  /**
   * Acción del menú Imagen/Propiedades
   * @param imagen
   */
  public void ImagenPropiedades(Imagen imagen) {
    getInterfazRef().crearVentanaDatosImagen(imagen);
  }
  
  public void ImagenCorrecionGamma(Imagen imagen) {
    // TODO
    //getInterfazRef().crearNuevaVentana(new Imagen(imagen.Gammacorrection(gamma)), titulo)
  }

  private Interfaz getInterfazRef() {
    return interfazRef;
  }

  private void setInterfazRef(Interfaz interfazRef) {
    this.interfazRef = interfazRef;
  }
  
}
