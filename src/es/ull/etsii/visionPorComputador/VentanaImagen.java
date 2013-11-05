/** @author Daniel Afonso González
 *  @author Rodrigo Valladares Santana
 *  @version 1.1, 05/11/13
 *  
 *  Proyecto de Visión Por Computador 2013/14
 *  
 *  Ventana en la que se mostrará la imagen que será posteriormente modificada
 *  en la interfaz del programa.
 *  
 *  Tiene una clase interna llamada PanelImagen que es donde se muestra la 
 *  imagen gracias a Graphics2D.
 *  
 *  Versión 1.1 La ventana muestra los botones de minimizar, maximizar y 
 *  cerra. Se puede cambiar su tamaño.
 */
package es.ull.etsii.visionPorComputador;

import java.awt.Graphics;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class VentanaImagen extends JInternalFrame {

  public static final boolean RESIZABLE = true;
  public static final boolean CLOSABLE = true;
  public static final boolean MAXIMIZABLE = true;
  public static final boolean ICONIFIABLE = true;

  private static final long serialVersionUID = 8689935453546765653L;
  Imagen imagen;
  PanelImagen panelImagen;
  
  /**
   * Constructor que recibe la Imagen que va a mostrar y el titulo de la ventana
   * @param imagen
   * @param nombre
   */
  public VentanaImagen(Imagen imagen, String titulo) {
    // TODO cambiar la interfaz del frame interno para que esté acorde al resto
    // TODO hacer que cuando se cierre una ventana se elimine de ListaVentana
    // de Interfaz
    super(titulo, RESIZABLE, CLOSABLE, MAXIMIZABLE, ICONIFIABLE);
    setImagen(imagen);
    setPanelImagen(new PanelImagen());
    setSize(getImagen().getAncho(), getImagen().getAlto());
    add(getPanelImagen());
    setVisible(true);
  }
  
  public class PanelImagen extends JPanel {
    private static final long serialVersionUID = 8039435365744075304L;
    
    public PanelImagen() {}
    
    // TODO hacer scroll de la imagen
    
    /**
     * Muestra la imagen en el panel
     */
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(getImagen().getImagen(), 0, 0, this);
    }
  }

  /**
   * Devuelve la Imagen
   * @return
   */
  private Imagen getImagen() {
    return imagen;
  }

  /**
   * 
   * @param imagen
   */
  private void setImagen(Imagen imagen) {
    this.imagen = imagen;
  }

  /**
   * Wrap de getTitle()
   * @return
   */
  public String getTitulo() {
    return getTitle();
  }

  /**
   * Devuelve el JPanel donde se muestra la imagen
   * @return
   */
  private PanelImagen getPanelImagen() {
    return panelImagen;
  }

  /**
   * 
   * @param panelImagen
   */
  private void setPanelImagen(PanelImagen panelImagen) {
    this.panelImagen = panelImagen;
  }

}