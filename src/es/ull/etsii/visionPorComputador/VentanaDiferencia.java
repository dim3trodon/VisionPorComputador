package es.ull.etsii.visionPorComputador;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class VentanaDiferencia extends JFrame {

  private static final long serialVersionUID = 1610031678950862902L;

  public static final int ANCHO = 200;
  public static final int ALTO = 120;
  public static final boolean RESIZABLE = true;

  Interfaz interfazRef;
  Imagen imagen;
  Vector<JRadioButton> vectorBotones;
  ButtonGroup selectorImagenes;
  Imagen imagenSeleccionada;

  public VentanaDiferencia(Imagen imagen, Interfaz interfazRef) {
    setInterfazRef(interfazRef);
    setImagen(imagen);
    int numImagenes = getInterfazRef().getNumeroVentanas();
    JButton botonOk = new JButton("Aceptar");
    setLayout(new FlowLayout());
    setVectorBotones(new Vector<JRadioButton>());
    setSelectorImagenes(new ButtonGroup());
    setImagenSeleccionada(imagen); // Se pone como seleccionada la misma imagen
    for (int i = 0; i < numImagenes; i++) {
      JRadioButton radioBoton = new JRadioButton(getInterfazRef().getImagen(i)
          .getNombre());
      radioBoton.addActionListener(new OyenteRadioBotones(getInterfazRef()
          .getImagen(i)));
      getVectorBotones().add(radioBoton);
      getSelectorImagenes().add(getVectorBotones().get(i));
    }
    botonOk.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String nombre = getImagen().getNombre() + "_dif_"
            + getImagenSeleccionada().getNombre();
        Imagen imagenDif = new Imagen(getImagen().Diferencia(
            getImagenSeleccionada()), nombre);
        getInterfazRef().crearNuevaVentana(imagenDif, nombre);
        dispose();
      }
    });
    for(int i = 0; i < getVectorBotones().size(); i++)
      add(getVectorBotones().get(i));
    add(botonOk);
    setSize(ANCHO, ALTO);
    setResizable(RESIZABLE);
    setVisible(true);
  }

  // Se le pasa una imagen a la clase, que es la imagen que representa
  // seleccionar un RadioButton en la interfaz
  private class OyenteRadioBotones implements ActionListener {

    Imagen imagenSelec;

    public OyenteRadioBotones(Imagen imagenSelec) {
      setImagenSelec(imagenSelec);
    }

    private Imagen getImagenSelec() {
      return imagenSelec;
    }

    private void setImagenSelec(Imagen imagenSelec) {
      this.imagenSelec = imagenSelec;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      setImagenSeleccionada(getImagenSelec());
    }

  }

  private Vector<JRadioButton> getVectorBotones() {
    return vectorBotones;
  }

  private void setVectorBotones(Vector<JRadioButton> vectorBotones) {
    this.vectorBotones = vectorBotones;
  }

  private ButtonGroup getSelectorImagenes() {
    return selectorImagenes;
  }

  private void setSelectorImagenes(ButtonGroup selectorImagenes) {
    this.selectorImagenes = selectorImagenes;
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

  private Imagen getImagenSeleccionada() {
    return imagenSeleccionada;
  }

  private void setImagenSeleccionada(Imagen imagenSeleccionada) {
    this.imagenSeleccionada = imagenSeleccionada;
  }

}
