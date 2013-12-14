package es.ull.etsii.visionPorComputador;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class VentanaRotacionLibreIndirecto extends JFrame {
  
  JTextField field;
  JButton botonOk;
  Interfaz interfazRef;
  Imagen imagen;
  
  public static final int ANCHO = 200;
  public static final int ALTO = 120;
  
  public VentanaRotacionLibreIndirecto(Imagen imagen, Interfaz interfazRef)  {
    setLayout(new FlowLayout());
    setBotonOk(new JButton("Aceptar"));
    setInterfazRef(interfazRef);
    setImagen(imagen);
    setField(new JTextField(4));
    add(getField());
    add(getBotonOk());
    
    getBotonOk().addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        String textoField = getField().getText();
        Imagen imagenGrados = new Imagen(getImagen().turn(
            Integer.parseInt(textoField)), getImagen().getNombre() + "_grados");
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
  
}
