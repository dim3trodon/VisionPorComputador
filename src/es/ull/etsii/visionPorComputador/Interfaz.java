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
  
  private Vector<VentanaImagen> listaVentanas;
  
  /**
   * Constructor
   */
  public Interfaz() {
    // TODO
    Imagen imagen = new Imagen("C:/Users/Rodrigo/Pictures/[animepaper.net]picture-standard-anime-kino-no-tabi-kinos-journey-67796-wingss-preview-76657b84.jpg");
    setTitle(TITULO_VENTANA);
    setSize(ANCHO_VENTANA, ALTO_VENTANA);
    //setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    
    VentanaImagen ventanaImagen = new VentanaImagen(imagen, "asd");
    
    // En JDektopPane es donde se ponen los JInternalFrame
    JDesktopPane jdesk = new JDesktopPane();
    //setContentPane(jdesk);
    jdesk.add(ventanaImagen);
    
    add(jdesk);
  }
  
  public void crearNuevaVentana(String nombreVentana, Imagen imagen) {
    
  }

  private Vector<VentanaImagen> getListaVentanas() {
    return listaVentanas;
  }

  private void setListaVentanas(Vector<VentanaImagen> listaVentanas) {
    this.listaVentanas = listaVentanas;
  }

  public static void main(String [] args) {
    JFrame frame = new Interfaz();
  }

}
