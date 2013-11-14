/**
 * @author Daniel Afonso González
 * @author Rodrigo Valladares Santana
 * 
 * @version 1.0, 11/11/13
 *
 * Proyecto de Visión Por Computador 2013/14
 * 
 * Ventana donde se muestran los datos de la imagen
 * 
 * Versión 1.0 Muestra dimensiones, brillo, contraste, entropía y ruta.
 */
package es.ull.etsii.visionPorComputador;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaDatosImagen extends JFrame {
  
  public static final int FILAS = 5;
  public static final int COLUMNAS = 1;
  public static final boolean VISIBLE = true;
  public static final boolean RESIZABLE = false;
  
  public static final int ANCHO = 200;
  public static final int ALTO = 120;

  private static final long serialVersionUID = -3505650183050371550L;
  
  public VentanaDatosImagen(Imagen imagen) {
    JLabel dimensiones;
    JLabel brillo;
    JLabel contraste;
    JLabel entropia;
    JLabel ruta;
    setLayout(new GridLayout(FILAS, COLUMNAS));
    dimensiones = new JLabel("Dimensiones: " + imagen.getAncho() + "x" + 
    imagen.getAlto() + " píxeles");
    brillo = new JLabel("Brillo: " + imagen.getBrillo());
    contraste = new JLabel("Contraste: " + imagen.getContraste());
    entropia = new JLabel("Entropía: " + imagen.getEntropia());
    ruta = new JLabel("Ruta: " + imagen.getRuta());
    add(dimensiones);
    add(brillo);
    add(contraste);
    add(entropia);
    add(ruta);
    setTitle("Propiedades de " + imagen.getNombre());
    setResizable(RESIZABLE);
    setVisible(VISIBLE);
    setSize(ANCHO, ALTO);
  }

}
