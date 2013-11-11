/** 
 * @author Daniel Afonso González
 * @author Rodrigo Valladares Santana
 * @version 1.1, 05/11/13
 *
 * Proyecto de Visión Por Computador 2013/14
 *
 * Interfaz del programa
 *
 * Versión 1.1 Añadidos métodos para poder trabajar con varias VentanaImagen
 * Creado ListaVentana, Vector donde se almacenan las VentanaImagen que se
 * vayan abriendo
 */
package es.ull.etsii.visionPorComputador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Interfaz extends JFrame {

  private static final long serialVersionUID = -5863315165069961036L;

  public static final Dimension tamPantalla = Toolkit.getDefaultToolkit()
      .getScreenSize();
  public static String TITULO_VENTANA = "Interfaz";
  public static int ANCHO_VENTANA = 2 * tamPantalla.width / 3;
  public static int ALTO_VENTANA = 2 * tamPantalla.height / 3;

  public static String imagenMuestra = "/home/dani/Imágenes/index.jpeg";
  public static String imagenMuestra2 = "C:/Users/Rodrigo/Pictures/6TeaeZilZiHT"
      + "E595ZgFi38.jpg";

  // En listaVentanas se encuentran todas las ventanas que se abren
  private Vector<VentanaImagen> listaVentanas;
  // En escritorio se muestran las ventanas que van abriéndose en la aplicación
  private JDesktopPane escritorio;
  // Menú de la aplicación
  private MenuVision menu;

  /**
   * Constructor
   */
  public Interfaz() {
    setListaVentanas(new Vector<VentanaImagen>());
    // TODO Botones de la interfaz
    setTitle(TITULO_VENTANA);
    setSize(ANCHO_VENTANA, ALTO_VENTANA);
    // setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    // Creación del menú
    setMenu(new MenuVision());
    add(getMenu(), BorderLayout.NORTH);
    // Creación del escritorio
    setEscritorio(new JDesktopPane());
    add(getEscritorio(), BorderLayout.CENTER);
    setVisible(true);

    crearNuevaVentana(imagenMuestra2, "asd"); // Ejemplo
  }

  /**
   * @author Daniel Afonso González
   * @author Rodrigo Valladares Santana
   * 
   * Menú de la aplicación
   * 
   */
  public class MenuVision extends JMenuBar {

    private static final long serialVersionUID = 6669480296209581882L;

    public static final String ST_ARCHIVO = "Archivo";
    public static final String ST_EDICION = "Edición";
    public static final String ST_VER = "Ver";
    public static final String ST_HERRAMIENTAS = "Herramientas";
    public static final String ST_IMAGEN = "Imagen";
    
    private JMenu menuArchivo;
    private JMenu menuEdicion;
    private JMenu menuVer;
    private JMenu menuHerramientas;
    private JMenu menuImagen;

    public MenuVision() {
      super();
      // Se añaden los menús al MenuBar
      setMenuArchivo(new JMenu(ST_ARCHIVO));
      add(getMenuArchivo());
      setMenuEdicion(new JMenu(ST_EDICION));
      add(getMenuEdicion());
      setMenuVer(new JMenu(ST_VER));
      add(getMenuVer());
      setMenuHerramientas(new JMenu(ST_HERRAMIENTAS));
      add(getMenuHerramientas());
      setMenuImagen(new JMenu(ST_IMAGEN));
      add(getMenuImagen());
    }
    
    private void construirMenuArchivo() {
      
    }

    private JMenu getMenuArchivo() {
      return menuArchivo;
    }

    private void setMenuArchivo(JMenu menuArchivo) {
      this.menuArchivo = menuArchivo;
    }

    private JMenu getMenuEdicion() {
      return menuEdicion;
    }

    private void setMenuEdicion(JMenu menuEdicion) {
      this.menuEdicion = menuEdicion;
    }

    private JMenu getMenuVer() {
      return menuVer;
    }

    private void setMenuVer(JMenu menuVer) {
      this.menuVer = menuVer;
    }

    private JMenu getMenuHerramientas() {
      return menuHerramientas;
    }

    private void setMenuHerramientas(JMenu menuHerramientas) {
      this.menuHerramientas = menuHerramientas;
    }

    private JMenu getMenuImagen() {
      return menuImagen;
    }

    private void setMenuImagen(JMenu menuImagen) {
      this.menuImagen = menuImagen;
    }
  }

  /**
   * Crea una nueva VentaImagen en ListaVentanas
   * 
   * @param nombreVentana
   * @param imagen
   */
  public void crearNuevaVentana(String linkImagen, String titulo) {
    addVentanaImagen(new VentanaImagen(new Imagen(linkImagen), titulo));
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
    // TODO
    getListaVentanas().remove(i);
    // getListaVentanas().set(i, null);
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

  public static void main(String[] args) {
    @SuppressWarnings("unused")
    JFrame frame = new Interfaz();

  }

}