/** 
 * @author Daniel Afonso González
 * @author Rodrigo Valladares Santana
 * @version 1.2, 05/11/13
 *
 * Proyecto de Visión Por Computador 2013/14
 *
 * Interfaz del programa
 * 
 * Versión 1.2 getImagenActiva() devuelve la imagen del frame activo
 * 
 * Versión 1.1b Añadido método getImagenActiva() para devolver la imagen de la
 * ventana que está activa actualmente (no implementado)
 *
 * Versión 1.1 Añadidos métodos para poder trabajar con varias VentanaImagen
 * Creado ListaVentana, Vector donde se almacenan las VentanaImagen que se
 * vayan abriendo
 */
package es.ull.etsii.visionPorComputador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Interfaz extends JFrame {

  private static final long serialVersionUID = -5863315165069961036L;

  public static final Dimension tamPantalla = Toolkit.getDefaultToolkit()
      .getScreenSize();
  public static String TITULO_VENTANA = "Interfaz";
  public static int ANCHO_VENTANA = 2 * tamPantalla.width / 3;
  public static int ALTO_VENTANA = 2 * tamPantalla.height / 3;

  public static String imagenMuestra = "/home/dani/Imágenes/index.jpg";
  public static String imagenMuestra2 = "C:/Users/Rodrigo/Pictures/6TeaeZilZiHT"
      + "E595ZgFi38.jpg";
  public static String imagenMuestra3 = "C:/Users/Rodrigo/Pictures/1323_tc.jpg";
  public static String imagenMuestra4 = "C:/Users/Rodrigo/Pictures/1323_tc - copia.jpg";

  // En listaVentanas se encuentran todas las ventanas que se abren
  private Vector<VentanaImagen> listaVentanas;
  // En escritorio se muestran las ventanas que van abriéndose en la aplicación
  private JDesktopPane escritorio;
  // Menú de la aplicación
  private MenuVision menu;
  // Posición del ratón en la imagen y valor del pixel activo
  private JLabel datosPixelActivo;

  /**
   * Constructor
   */
  public Interfaz() {
    setListaVentanas(new Vector<VentanaImagen>());
    // TODO Botones de la interfaz
    setTitle(TITULO_VENTANA);
    setSize(ANCHO_VENTANA, ALTO_VENTANA);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    // Creación del menú
    setMenu(new MenuVision(this));
    add(getMenu(), BorderLayout.NORTH);
    // Creación del escritorio
    setEscritorio(new JDesktopPane());
    add(getEscritorio(), BorderLayout.CENTER);
    // Creación del JLabel para mostrar datos de la posición del ratón
    setDatosPixelActivo(new JLabel("(,) "));
    add(getDatosPixelActivo(), BorderLayout.SOUTH);
    setVisible(true);

    // crearVentanaTransLinealTramos(null);

    // crearNuevaVentana(imagenMuestra3, "asd"); // Ejemplo
    // crearNuevaVentana(imagenMuestra2, "aa");

    // crearVentanaHistograma(getImagenActiva().getHistograma(), "pirirpio");
    // crearNuevaVentana(new Imagen(getImagenActiva().Diferencia(getImagen(0)),
    // "aaaaa"), "afasf");

    // crearNuevaVentana(new Imagen(
    // getImagenActiva().Histo_especify(getImagen(0)), "afasdf"), "aasfs");

  }

  /**
   * Método llamado desde VentanaImagen para actualizar los datos del píxel
   * activo
   */
  public void actualizarDatosPixelActivo(int x, int y, int valorPixel) {
    setValorDatosPixelActivo(x, y, valorPixel);
  }
  
  public void CrearVentanaRotacionLibreInverso(Imagen imagen) {
    VentanaRotacionLibreIndirecto ventana = new VentanaRotacionLibreIndirecto(imagen, this);
  }
  
  public void CrearVentanaRotacionLibreDirecto(Imagen imagen) {
    VentanaRotacionLibreDirecto ventana = new VentanaRotacionLibreDirecto(imagen, this);
  }
  
  public void crearVentanaEscaladoPorcentual(VentanaImagen ventanaImagen) {
    @SuppressWarnings("unused")
    VentanaEscaladoPorcentual ventanaEscalado = new VentanaEscaladoPorcentual(this, ventanaImagen);
  }
  
  public void crearVentanaEscalado(VentanaImagen ventanaImagen) {
    @SuppressWarnings("unused")
    VentanaEscalado ventanaEscalado = new VentanaEscalado(this, ventanaImagen);
  }
  
  public void crearVentanaMapaCambios(Imagen imagen) {
    @SuppressWarnings("unused")
    VentanaMapaCambios ventanaMapaCambios = new VentanaMapaCambios(imagen, this);
  }

  public void crearVentanaEspecificarHistograma(Imagen imagen) {
    @SuppressWarnings("unused")
    VentanaEspecificarHistograma ventanaHistoEspec = new VentanaEspecificarHistograma(
        imagen, this);
  }

  public void crearVentanaBrilloContraste(Imagen imagen) {
    @SuppressWarnings("unused")
    VentanaBrilloContraste ventanabrillCont = new VentanaBrilloContraste(
        imagen, this);
  }

  public void crearVentanaDiferencia(Imagen imagen) {
    @SuppressWarnings("unused")
    VentanaDiferencia ventanaDif = new VentanaDiferencia(imagen, this);
  }

  public void crearVentanaTransLinealTramos(Imagen imagen) {
    @SuppressWarnings("unused")
    VentanaTransLinealTramos ventanaTrans = new VentanaTransLinealTramos(
        imagen, this);
  }

  public void crearVentanaCorreccionGAmma(Imagen imagen) {
    @SuppressWarnings("unused")
    VentanaCorrecionGamma ventanaGamma = new VentanaCorrecionGamma(imagen, this);
  }

  public void crearVentanaHistogramaAcc(Histograma histograma,
      String nombreImagen) {

  }

  public void crearVentanaHistograma(Histograma histograma, String nombreImagen) {
    @SuppressWarnings("unused")
    VentanaHistograma ventanaHistograma = new VentanaHistograma(histograma,
        nombreImagen);

  }

  /**
   * Crea una nueva VentaImagen en ListaVentanas
   * 
   * @param nombreVentana
   * @param imagen
   */
  public void crearNuevaVentana(String linkImagen, String titulo) {
    addVentanaImagen(new VentanaImagen(new Imagen(linkImagen), titulo, this,
        getNumeroVentanas()));
  }

  public void crearNuevaVentana(Imagen imagen, String titulo) {
    addVentanaImagen(new VentanaImagen(imagen, titulo, this,
        getNumeroVentanas()));
  }

  /**
   * Crea una ventana donde se muestran los datos de la imagen
   * 
   * @param imagen
   */
  public void crearVentanaDatosImagen(Imagen imagen) {
    @SuppressWarnings("unused")
    VentanaDatosImagen ventanaDatosImagen = new VentanaDatosImagen(imagen);
  }

  /**
   * Devuelve la VentanaImagen en la posición i de ListaVentanas
   * 
   * @param i
   * @return
   */
  public VentanaImagen getVentanaImagen(int i) {
    return getListaVentanas().get(i);
  }

  /**
   * Añade una nueva VentanaImagen a ListaVentanas
   * 
   * @param ventanaImagen
   */
  private void addVentanaImagen(VentanaImagen ventanaImagen) {
    getListaVentanas().add(ventanaImagen);
    getEscritorio().add(ventanaImagen);
  }

  /**
   * Elimina la VentanaImagen en la posición i de ListaVentanas
   * 
   * @param i
   */
  public void eliminarVentanaImagen(int i) {
    // TODO ¿Errores?
    getListaVentanas().remove(i);
  }

  /**
   * Devuelve el número de ventanas que hay actualmente abiertas
   * 
   * @return
   */
  public int getNumeroVentanas() {
    return getListaVentanas().size();
  }

  /**
   * Devuelve la imagen en la VentanaImagen i
   * 
   * @param i
   * @return
   */
  public Imagen getImagen(int i) {
    return getVentanaImagen(i).getImagen();
  }

  /**
   * Devuelve la imagen de la VentanaImagen en primer plano
   * 
   * @return
   */
  public Imagen getImagenActiva() {
    /*if (getNumeroVentanas() > 0) {
      int i = 0;
      VentanaImagen auxImagen = null;// = getVentanaImagen(i);
      while ((i < getNumeroVentanas())
          && ((auxImagen = getVentanaImagen(i)).isSelected())) {
        i++;
      }
      if (auxImagen != null)
        return auxImagen.getImagen();
      else
        return null;
    } else
      return null;*/
    return getVentanaImagenActiva().getImagen();
  }

  /**
   * Devuelve la VentanaImagen en primer plano
   * 
   * @return
   */
  public VentanaImagen getVentanaImagenActiva() {
    if (getNumeroVentanas() > 0) {
      int i = 0;
      VentanaImagen auxImagen = null;// = getVentanaImagen(i);
      while ((i < getNumeroVentanas())
          && (!(auxImagen = getVentanaImagen(i)).isSelected())) {
        i++;
      }
      if (auxImagen != null) 
        return auxImagen;
      else
        return null;
    } else
      return null;
  }

  /**
   * Devuelve el vector en el que están almacenadas las ventanas de la interfaz
   * 
   * @return
   */
  private Vector<VentanaImagen> getListaVentanas() {
    return listaVentanas;
  }

  /**
   * 
   * @param listaVentanas
   */
  private void setListaVentanas(Vector<VentanaImagen> listaVentanas) {
    this.listaVentanas = listaVentanas;
  }

  /**
   * 
   * @return
   */
  private JDesktopPane getEscritorio() {
    return escritorio;
  }

  /**
   * 
   * @param escritorio
   */
  private void setEscritorio(JDesktopPane escritorio) {
    this.escritorio = escritorio;
  }

  private MenuVision getMenu() {
    return menu;
  }

  private void setMenu(MenuVision menu) {
    this.menu = menu;
  }

  private void setValorDatosPixelActivo(int x, int y, int valorPixel) {
    getDatosPixelActivo().setText(
        "(" + x + ", " + y + ") " + valorPixel + "   Nº ventanas: "
            + getNumeroVentanas());
  }

  private JLabel getDatosPixelActivo() {
    return datosPixelActivo;
  }

  private void setDatosPixelActivo(JLabel datosPixelActivo) {
    this.datosPixelActivo = datosPixelActivo;
  }

  public static void main(String[] args) {
    @SuppressWarnings("unused")
    JFrame frame = new Interfaz();

  }

}