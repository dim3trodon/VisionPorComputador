/**
 * @author Daniel Afonso González
 * @author Rodrigo Valladares Santana
 * 
 * @version 1.0, 13/11/13
 *
 * Proyecto de Visión Por Computador 2013/14
 * 
 * Ventana donde se elige el valor de la corrección gamma
 */
package es.ull.etsii.visionPorComputador;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaCorrecionGamma extends JFrame {

  private static final long serialVersionUID = -5479950151819481988L;

  public static final int ANCHO = 200;
  public static final int ALTO = 120;
  public static final int FILAS = 1;
  public static final int COLUMNAS = 1;
  public static final int NUM_COLS_FIELD = 3;
  public static final boolean RESIZABLE = false;

  JLabel labelGamma;
  JTextField fieldGamma;
  JButton botonOk;
  Interfaz interfazRef;
  Imagen imagen;

  public VentanaCorrecionGamma(Imagen imagen, Interfaz interfazRef) {
    setLayout(new FlowLayout());
    setLabelGamma(new JLabel("Valor de correción gamma: "));
    setFieldGamma(new JTextField(NUM_COLS_FIELD));
    setBotonOk(new JButton("Aceptar"));
    setInterfazRef(interfazRef);
    setImagen(imagen);
    getBotonOk().addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        try {
          String textoField = getFieldGamma().getText();
          getInterfazRef().crearNuevaVentana(
              new Imagen(getImagen().Gammacorrection(
                  Float.parseFloat(textoField)), getImagen().getNombre()),
              getImagen().getNombre() + textoField + "gammaCorrec");
          dispose(); // Cerrar ventana
        } catch (NumberFormatException exception) {
          System.err.println(getFieldGamma().getText() + " no es un número en" +
          		" punto flotante");
          getFieldGamma().setText("");
          
        } catch (NullPointerException noHayImagen) {
          System.err.println("No hay imágenes abiertas");
          dispose();
        }
      }
    });
    add(getLabelGamma());
    add(getFieldGamma());
    add(getBotonOk());
    setResizable(RESIZABLE);
    setSize(ANCHO, ALTO);
    setVisible(true);
  }

  private JTextField getFieldGamma() {
    return fieldGamma;
  }

  private void setFieldGamma(JTextField fieldGamma) {
    this.fieldGamma = fieldGamma;
  }

  private JLabel getLabelGamma() {
    return labelGamma;
  }

  private void setLabelGamma(JLabel labelGamma) {
    this.labelGamma = labelGamma;
  }

  private JButton getBotonOk() {
    return botonOk;
  }

  private void setBotonOk(JButton botonOk) {
    this.botonOk = botonOk;
  }

  private Interfaz getInterfazRef() {
    return interfazRef;
  }

  private void setInterfazRef(Interfaz interfazRef) {
    this.interfazRef = interfazRef;
  }

  private Imagen getImagen() {
    return imagen;
  }

  private void setImagen(Imagen imagen) {
    this.imagen = imagen;
  }

}
