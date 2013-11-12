/**
 * @author Daniel Afonso González
 * @author Rodrigo Valladares Santana
 * 
 * @version 1.0, 11/11/13
 *
 * Proyecto de Visión Por Computador 2013/14
 * 
 * Acciones del menú de aplicaciones
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
   * Acción del menú Imagen/Propiedades
   * @param imagen
   */
  public void ImagenPropiedades(Imagen imagen) {
    getInterfazRef().crearVentanaDatosImagen(imagen);
  }

  private Interfaz getInterfazRef() {
    return interfazRef;
  }

  private void setInterfazRef(Interfaz interfazRef) {
    this.interfazRef = interfazRef;
  }
  
}
