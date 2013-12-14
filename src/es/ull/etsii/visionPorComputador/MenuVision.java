/**
 * @author Daniel Afonso González
 * @author Rodrigo Valladares Santana
 * 
 * @version 1.1, 13/11/13
 *
 * Proyecto de Visión Por Computador 2013/14
 * 
 * Menú de la aplicación
 * 
 * Versión 1.1 Submenú de Ver/Histograma
 * 
 * Versión 1.0 Archivo, Edición, Ver, Herramientas e Imagen
 * Submenú de Archivo/Abrir
 * Submenú de Imagen/Propiedades
 */
package es.ull.etsii.visionPorComputador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuVision extends JMenuBar {

  // TODO Desactivar submenús hasta que no haya ninguna imagen cargada

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
    construirMenuEdicion();
    construirMenuVer();
    construirMenuImagen();
  }

  /*
   * En cada método de construir, se crean los items de cada menú. A cada uno se
   * le añade un ActionListener que llama a la clase AccionesMenu, donde se
   * encuentran las posibles acciones a realizar (para mayor comodidad).
   */
  private void construirMenuArchivo() {
    JMenuItem itemAbrir = new JMenuItem("Abrir");
    itemAbrir.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        // TODO Abre la imagen de muestra, hacer un cuadro de diálogo que
        // TODO permita elegir la nueva imagen a abrir
        //getAccionesMenu().ArchivoAbrir(linkImagen);
    	  JFileChooser chooser = new JFileChooser();
    	    //FileNameExtensionFilter filter = new FileNameExtensionFilter(
    	        //"PNG", "png");
    	    //chooser.setFileFilter(filter);  
    	  chooser.setCurrentDirectory(new File("C:/Users/Rodrigo/Documents/TestVision"));
    	  int returnVal = chooser.showOpenDialog(null);
    	  if(returnVal == JFileChooser.APPROVE_OPTION) {
    		  getAccionesMenu().ArchivoAbrir( chooser.getSelectedFile().getPath());
    	  }
      }
    });
    // Añadir ítems al menú
    getMenuArchivo().add(itemAbrir);
    
    JMenuItem itemGuardar = new JMenuItem("Guardar");
    itemGuardar.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().ArchivoGuardar(getInterfazRef().getImagenActiva());
      }
    });
    getMenuArchivo().add(itemGuardar);
  }

  private void construirMenuEdicion() {
    JMenuItem itemRecortarSeleccion = new JMenuItem("Recortar selección");
    itemRecortarSeleccion.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().EdicionRecortarSeleccion();
      }
    });
    getMenuEdicion().add(itemRecortarSeleccion);
    
    JMenu menuEspejo = new JMenu("Espejo");
    getMenuEdicion().add(menuEspejo);
    
    JMenuItem itemEspejoVertical = new JMenuItem("Vertical");
    itemEspejoVertical.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().EdicionEspejoVertical();
      }
    });
    menuEspejo.add(itemEspejoVertical);
    
    JMenuItem itemEspejoHorizontal = new JMenuItem("Horizontal");
    itemEspejoHorizontal.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().EdicionEspejoHorizontal();
      }
    });
    menuEspejo.add(itemEspejoHorizontal);
    
    JMenuItem itemTraspuesta = new JMenuItem("Traspuesta");
    itemTraspuesta.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().EdicionTraspuesta();
      }
    });
    getMenuEdicion().add(itemTraspuesta);
    
    JMenu menuRotacion = new JMenu("Rotación");
    getMenuEdicion().add(menuRotacion);
    
    JMenuItem itemRotacion90 = new JMenuItem("90º");
    itemRotacion90.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().EdicionRotacion(1);
      }
    });
    menuRotacion.add(itemRotacion90);
    
    JMenuItem itemRotacion180 = new JMenuItem("180º");
    itemRotacion180.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().EdicionRotacion(2);
      }
    });
    menuRotacion.add(itemRotacion180);
    
    JMenuItem itemRotacion270 = new JMenuItem("270º");
    itemRotacion270.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().EdicionRotacion(3);
      }
    });
    menuRotacion.add(itemRotacion270);
    
    JMenuItem itemRotacionLibre = new JMenuItem("Libre Directo");
    itemRotacionLibre.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        getInterfazRef().CrearVentanaRotacionLibreDirecto(getInterfazRef().getImagenActiva());
      }
    });
    menuRotacion.add(itemRotacionLibre);
    
    JMenuItem itemRotacionInverso = new JMenuItem("Libre inverso");
    itemRotacionInverso.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        getInterfazRef().CrearVentanaRotacionLibreInverso(getInterfazRef().getImagenActiva());
      }
    });
    menuRotacion.add(itemRotacionInverso);
    
    JMenuItem itemEscalado = new JMenuItem("Escalado");
    itemEscalado.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().EdicionEscalado();
      }
    });
    getMenuEdicion().add(itemEscalado);
    
    JMenuItem itemEscaladoPorcentual = new JMenuItem("Escalado porcentual");
    itemEscaladoPorcentual.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().EdicionEscaladoPorcentaje();
      }
    });
    getMenuEdicion().add(itemEscaladoPorcentual);
  }

  private void construirMenuVer() {
    JMenuItem itemHistograma = new JMenuItem("Histograma");
    itemHistograma.addActionListener(new ActionListener() {


      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().VerHistograma(
            getInterfazRef().getImagenActiva().getHistograma(),
            getInterfazRef().getImagenActiva().getNombre());
      }
    });
    getMenuVer().add(itemHistograma);

    JMenuItem itemHistogramaAcc = new JMenuItem("Histograma acumulativo");
    itemHistogramaAcc.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu()
            .VerHistogramaAcc(
                new Histograma(getInterfazRef().getImagenActiva()
                    .histograma_acu()),
                getInterfazRef().getImagenActiva().getNombre());
      }
    });
    getMenuVer().add(itemHistogramaAcc);
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

    JMenuItem itemCorreccionGamma = new JMenuItem("Corrección gamma");
    itemCorreccionGamma.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().ImagenCorrecionGamma(
            getInterfazRef().getImagenActiva());
      }
    });
    getMenuImagen().add(itemCorreccionGamma);

    JMenuItem itemTransLinealPorTramos = new JMenuItem("Transformación lineal"
        + " por tramos");
    itemTransLinealPorTramos.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().ImagenTransLinealPorTramos(
            getInterfazRef().getImagenActiva());
      }
    });
    getMenuImagen().add(itemTransLinealPorTramos);

    JMenuItem itemEcualizar = new JMenuItem("Ecualizar");
    itemEcualizar.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().ImagenEcualizar(getInterfazRef().getImagenActiva());
      }
    });
    getMenuImagen().add(itemEcualizar);

    JMenuItem itemDiferencia = new JMenuItem("Diferencia");
    itemDiferencia.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        getAccionesMenu().ImagenDiferencia(getInterfazRef().getImagenActiva());
      }
    });
    getMenuImagen().add(itemDiferencia);
    
    JMenuItem itemMapaCambios = new JMenuItem("Mapa cambios");
    itemMapaCambios.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().ImagenMapaCambios(getInterfazRef().getImagenActiva());
      }
    });
    getMenuImagen().add(itemMapaCambios);

    JMenuItem itemBrilloContraste = new JMenuItem("Brillo y contraste");
    itemBrilloContraste.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        getAccionesMenu().ImagenBrilloContraste(
            getInterfazRef().getImagenActiva());
      }
    });
    getMenuImagen().add(itemBrilloContraste);

    JMenuItem itemEspecificarHistograma = new JMenuItem(
        "Especificar histograma");
    itemEspecificarHistograma.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        getAccionesMenu().ImagenEspecificarHistograma(
            getInterfazRef().getImagenActiva());
      }
    });
    getMenuImagen().add(itemEspecificarHistograma);
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