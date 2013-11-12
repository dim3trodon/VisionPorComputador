/**
 * @author Daniel Afonso González
 * @author Rodrigo Valladares Santana
 * 
 * @version 1.0, 11/11/13
 *
 * Proyecto de Visión Por Computador 2013/14
 * 
 * Menú de la aplicación
 * 
 * Versión 1.0 Archivo, Edición, Ver, Herramientas e Imagen
 * Submenú de Archivo/Abrir
 * Submenú de Imagen/Propiedades
 */
package es.ull.etsii.visionPorComputador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuVision extends JMenuBar {

  private static final long serialVersionUID = 6669480296209581882L;

  public static final String ST_ARCHIVO = "Archivo";
  public static final String ST_EDICION = "Edición";
  public static final String ST_VER = "Ver";
  public static final String ST_HERRAMIENTAS = "Herramientas";
  public static final String ST_IMAGEN = "Imagen";
  
  private Interfaz interfazRef;
  private AccionesMenu accionesMenu;
  
  private JMenu menuArchivo;
  private JMenu menuEdicion;
  private JMenu menuVer;
  private JMenu menuHerramientas;
  private JMenu menuImagen;

  public MenuVision(Interfaz interfazRef) {
    super();
    setInterfazRef(interfazRef);
    setAccionesMenu(new AccionesMenu(getInterfazRef()));
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
    // Construir ítems de cada menú
    construirMenuArchivo();
    construirMenuImagen();
  }
  
  /*
   * En cada método de construir, se crean los items de cada menú. A cada uno
   * se le añade un ActionListener que llama a la clase AccionesMenu, donde 
   * se encuentran las posibles acciones a realizar (para mayor comodidad).
   */
  private void construirMenuArchivo() {
    JMenuItem itemAbrir = new JMenuItem("Abrir");
    itemAbrir.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        // TODO Abre la imagen de muestra, hacer un cuadro de diálogo que
        // TODO permita elegir la nueva imagen a abrir
        getAccionesMenu().ArchivoAbrir(Interfaz.imagenMuestra3);
      }
    });
    // Añadir ítems al menú
    getMenuArchivo().add(itemAbrir);
  }
  
  private void construirMenuImagen() {
    JMenuItem itemPropiedades = new JMenuItem("Propiedades");
    itemPropiedades.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().ImagenPropiedades(getInterfazRef().getImagenActiva());
      }
    });
    getMenuImagen().add(itemPropiedades);
  }
  
  private Interfaz getInterfazRef() {
    return interfazRef;
  }

  private void setInterfazRef(Interfaz interfazRef) {
    this.interfazRef = interfazRef;
  }

  private AccionesMenu getAccionesMenu() {
    return accionesMenu;
  }

  private void setAccionesMenu(AccionesMenu accionesMenu) {
    this.accionesMenu = accionesMenu;
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