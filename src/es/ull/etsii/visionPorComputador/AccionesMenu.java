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
   * 
   * @param linkImagen
   */
  public void ArchivoAbrir(String linkImagen) {
    // TODO
    String[] partesRutaImagen = linkImagen.split("/");
    String titulo = partesRutaImagen[partesRutaImagen.length - 1];
    getInterfazRef().crearNuevaVentana(linkImagen, titulo);
  }

  /**
   * Acción del menú Edición/Recortar selección
   */
  public void EdicionRecortarSeleccion() {
    try {
      Imagen imagenRecortada = getInterfazRef().getVentanaImagenActiva()
          .getRegionOfInterest().getRecorte();
      getInterfazRef().crearNuevaVentana(imagenRecortada,
          imagenRecortada.getNombre());
    } catch (NullPointerException noHayImagen) {
      System.err.println("No hay imágenes");
    }
  }

  /**
   * Acción de Ver/Histograma
   * 
   * @param histograma
   * @param nombreImagen
   */
  public void VerHistograma(Histograma histograma, String nombreImagen) {
    getInterfazRef().crearVentanaHistograma(histograma, nombreImagen);
  }

  /**
   * Acción de Ver/Histograma acumulativo
   * 
   * @param histograma
   * @param nombreImagen
   */
  public void VerHistogramaAcc(Histograma histograma, String nombreImagen) {
    getInterfazRef().crearVentanaHistograma(histograma, nombreImagen);
  }

  /**
   * Acción del menú Imagen/Propiedades
   * 
   * @param imagen
   */
  public void ImagenPropiedades(Imagen imagen) {
    getInterfazRef().crearVentanaDatosImagen(imagen);
  }

  /**
   * Acción del menú Imagen/Transformación lineal por tramos
   * 
   * @param imagen
   */
  public void ImagenTransLinealPorTramos(Imagen imagen) {
    getInterfazRef().crearVentanaTransLinealTramos(imagen);
  }

  /**
   * Acción del menú Imagen/Ecualizar
   * @param imagen
   */
  public void ImagenEcualizar(Imagen imagen) {
    Imagen imagenEq = new Imagen(imagen.Equalize(), imagen.getNombre()
        + "equalized");
    getInterfazRef().crearNuevaVentana(imagenEq, imagenEq.getNombre());
  }
  
  /**
   * Acción del menú Imagen/Diferencia
   * @param imagen
   */
  public void ImagenDiferencia(Imagen imagen) {
    getInterfazRef().crearVentanaDiferencia(imagen);
  }
  
  public void ImagenBrilloContraste(Imagen imagen) {
    getInterfazRef().crearVentanaBrilloContraste(imagen);
  }

  public void ImagenCorrecionGamma(Imagen imagen) {
    getInterfazRef().crearVentanaCorreccionGAmma(imagen);
  }

  public void ImagenEspecificarHistograma(Imagen imagen) {
    getInterfazRef().crearVentanaEspecificarHistograma(imagen);
  }
  
  private Interfaz getInterfazRef() {
    return interfazRef;
  }

  private void setInterfazRef(Interfaz interfazRef) {
    this.interfazRef = interfazRef;
  }

}
