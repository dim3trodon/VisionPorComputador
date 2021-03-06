package es.ull.etsii.visionPorComputador;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class VentanaEscalado extends JFrame {

  private static final long serialVersionUID = -4885075526082777757L;

  public static final int ANCHO = 240;
  public static final int ALTO = 120;
  public static final int NUM_COLS_FIELD = 5;
  public static final boolean RESIZABLE = false;

  private static final int VECINO = 0;
  private static final int BILINEAL = 1;

  JTextField textFieldAncho;
  JTextField textFieldAlto;
  JButton botonOk;
  Interfaz interfazRef;
  Imagen imagen;
  int tipoInterpolacion;

  public VentanaEscalado(Interfaz interfazRef, VentanaImagen ventanaImagen) {
    if(ventanaImagen.hayAreaSeleccionada())
      setImagen(ventanaImagen.getRegionOfInterest().getRecorte());
    else {
      setImagen(ventanaImagen.getImagen());
    }
    setLayout(new FlowLayout());
    setInterfazRef(interfazRef);
    
    setBotonOk(new JButton("Aceptar"));
    JLabel anchoOriginal = new JLabel("        Ancho = " + getImagen().getAncho());
    JLabel altoOriginal = new JLabel("Alto = " + getImagen().getAlto()
        + "          ");
    add(anchoOriginal);
    add(altoOriginal);
    add(new JLabel("Ancho: "));
    setTextFieldAncho(new JTextField(NUM_COLS_FIELD));
    add(getTextFieldAncho());
    add(new JLabel("Alto: "));
    setTextFieldAlto(new JTextField(NUM_COLS_FIELD));
    add(getTextFieldAlto());
    getBotonOk().addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        String textoAncho = getTextFieldAncho().getText();
        String textoAlto = getTextFieldAlto().getText();
        try {
          int ancho = Integer.parseInt(textoAncho);
          int alto = Integer.parseInt(textoAlto);
          Imagen imagenEsc = new Imagen(getImagen()
              .Escalado(getImagen(), ancho, alto, getTipoInterpolacion()),
              getImagen().getNombre() + "_espejo");
          getInterfazRef().crearNuevaVentana(imagenEsc, imagenEsc.getNombre());
          dispose();
        } catch (NumberFormatException noNumero) {
          System.err
              .println(getTextFieldAncho().getText() + " no es un número");
          System.err.println(getTextFieldAlto().getText() + " no es un número");
          getTextFieldAncho().setText("");
          getTextFieldAlto().setText("");
        } catch (NullPointerException noHayImagen) {
          System.err.println("No hay imágenes abiertas");
          dispose();
        }
      }
    });
    
    setTipoInterpolacion(VECINO);
    ButtonGroup agrupBoton = new ButtonGroup();
    JRadioButton radioVecino = new JRadioButton("Vecino");
    radioVecino.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        setTipoInterpolacion(VECINO);
      }
    });
    agrupBoton.add(radioVecino);
    JRadioButton radioBilineal = new JRadioButton("Bilineal");
    radioBilineal.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        setTipoInterpolacion(BILINEAL);
      }
    });
    agrupBoton.add(radioBilineal);
    add(radioVecino);
    add(radioBilineal);

    add(getBotonOk());
    setResizable(RESIZABLE);
    setSize(ANCHO, ALTO);
    setVisible(true);
  }

  private JTextField getTextFieldAlto() {
    return textFieldAlto;
  }

  private void setTextFieldAlto(JTextField textFieldAlto) {
    this.textFieldAlto = textFieldAlto;
  }

  private JTextField getTextFieldAncho() {
    return textFieldAncho;
  }

  private void setTextFieldAncho(JTextField textFieldAncho) {
    this.textFieldAncho = textFieldAncho;
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

  private int getTipoInterpolacion() {
    return tipoInterpolacion;
  }

  private void setTipoInterpolacion(int tipoInterpolacion) {
    this.tipoInterpolacion = tipoInterpolacion;
  }

  private Imagen getImagen() {
    return imagen;
  }

  private void setImagen(Imagen imagen) {
    this.imagen = imagen;
  }
}
