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

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

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
    getInterfazRef().crearNuevaVentana(linkImagen, TratamientoCadenas.getNombreImagen(linkImagen));
  }
  
  public void ArchivoGuardar(Imagen imagen) {
    // new File(arg0)
    // ;
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("C:/Users/Rodrigo/Pictures/"));
    int returnVal = chooser.showSaveDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = new File(chooser.getSelectedFile().getPath() + ".png");
      try {
        ImageIO.write(imagen.getImagen(), "png", file);
      } catch (IOException e) {
        System.err.println("Error al guardar " + file.getPath());
      }
    }
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
  
  public void ImagenMapaCambios(Imagen imagen) {
    getInterfazRef().crearVentanaMapaCambios(imagen);
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
