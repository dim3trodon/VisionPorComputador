package es.ull.etsii.visionPorComputador;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class VentanaRotacionLibreIndirecto extends JFrame {

  private static final long serialVersionUID = 1L;
  JTextField field;
  JButton botonOk;
  Interfaz interfazRef;
  Imagen imagen;
  int tipoInterpolacion;
  
  private static final int VECINO = 0;
  private static final int BILINEAL = 1;
  
  public static final int ANCHO = 250;
  public static final int ALTO = 120;
  
  public VentanaRotacionLibreIndirecto(Imagen imagen, Interfaz interfazRef)  {
    setLayout(new FlowLayout());
    setBotonOk(new JButton("Aceptar"));
    setInterfazRef(interfazRef);
    setImagen(imagen);
    setField(new JTextField(4));
    add(getField());
    
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
    
    getBotonOk().addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        String textoField = getField().getText();
        // TODO tipo interpolacion
        Imagen imagenGrados = new Imagen(getImagen().turn(
            Integer.parseInt(textoField), getTipoInterpolacion()), getImagen().getNombre() + "_grados");
        getInterfazRef().crearNuevaVentana(
            imagenGrados, imagenGrados.getNombre());
        dispose();
      }
    });
    
    setSize(ANCHO, ALTO);
    setVisible(true);
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

  private JTextField getField() {
    return field;
  }

  private void setField(JTextField field) {
    this.field = field;
  }
  
  private int getTipoInterpolacion() {
    return tipoInterpolacion;
  }

  private void setTipoInterpolacion(int tipoInterpolacion) {
    this.tipoInterpolacion = tipoInterpolacion;
  }
  
}
