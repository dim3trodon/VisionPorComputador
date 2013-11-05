/** @author Daniel Afonso González
 *  @author Rodrigo Valladares Santana
 *  @version 1.0, 30/10/13
 *  
 *  Proyecto de Visión Por Computador 2013/14
 *  
 *  Interfaz del programa
 */
package es.ull.etsii.visionPorComputador;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

public class Interfaz extends JFrame {
  
  private static final long serialVersionUID = -5863315165069961036L;
  
  public static final Dimension tamPantalla = Toolkit.getDefaultToolkit().
      getScreenSize();
  public static String TITULO_VENTANA = "Interfaz";
  public static int ANCHO_VENTANA = 2 * tamPantalla.width / 3;
  public static int ALTO_VENTANA = 2 * tamPantalla.height / 3;

  public static String imagenMuestra = "C:/Users/Rodrigo/Pictures/[animepaper."
      + "net]picture-standard-anime-kino-no-tabi-kinos-journey-67796-wingss-pre"
      + "view-76657b84.jpg";
  
  // En listaVentanas se encuentran todas las ventanas que se abren
  private Vector<VentanaImagen> listaVentanas;
  // En escritorio se muestran las ventanas que van abriéndose en la aplicación
  private JDesktopPane escritorio;
  
  /**
   * Constructor
   */
  public Interfaz() {
    setListaVentanas(new Vector<VentanaImagen>());
    // TODO Botones de la interfaz
    setTitle(TITULO_VENTANA);
    setSize(ANCHO_VENTANA, ALTO_VENTANA);
    //setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setEscritorio(new JDesktopPane());
    add(getEscritorio());
    setVisible(true);

    crearNuevaVentana(imagenMuestra, "asd"); // Ejemplo
  }
  
  /**
   * Crea una nueva VentaImagen en ListaVentanas
   * @param nombreVentana
   * @param imagen
   */
  public void crearNuevaVentana(String linkImagen, String titulo) {
    addVentanaImagen(new VentanaImagen(new Imagen(linkImagen), titulo));
  }
  
  /**
   * Devuelve la VentanaImagen en la posición i de ListaVentanas
   * @param i
   * @return
   */
  public VentanaImagen getVentanaImagen(int i) {
    return getListaVentanas().get(i);
  }
  
  /**
   * Añade una nueva VentanaImagen a ListaVentanas
   * @param ventanaImagen
   */
  private void addVentanaImagen(VentanaImagen ventanaImagen) {
    getListaVentanas().add(ventanaImagen);
    getEscritorio().add(ventanaImagen);
  }
  
  /**
   * Elimina la VentanaImagen en la posición i de ListaVentanas
   * @param i
   */
  public void eliminarVentanaImagen(int i) {
    // TODO
    getListaVentanas().remove(i);
    // getListaVentanas().set(i, null);
  }
  
  /**
   * Devuelve el número de ventanas que hay actualmente abiertas
   * @return
   */
  public int getNumeroVentanas() {
    return getListaVentanas().size();
  }

  /**
   * Devuelve el vector en el que están almacenadas las ventanas de la interfaz
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

  public static void main(String [] args) {
    @SuppressWarnings("unused")
    JFrame frame = new Interfaz();
  }

}
