package es.ull.etsii.visionPorComputador;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaBrilloContraste extends JFrame {

  private static final long serialVersionUID = 6616563043634063243L;

  public static final int ANCHO = 400;
  public static final int ALTO = 120;
  public static final int NUM_COLS_FIELD = 5;

  public static final boolean RESIZABLE = true;

  Interfaz interfazRef;
  Imagen imagen;
  JTextField fieldContraste;
  JTextField fieldBrillo;

  public VentanaBrilloContraste(Imagen imagen, Interfaz interfazRef) {
    setLayout(new FlowLayout());
    JLabel labelContraste = new JLabel("Contraste: ");
    JLabel labelBrillo = new JLabel("Brillo: ");
    JButton botonOk = new JButton("Aceptar");
    setInterfazRef(interfazRef);
    setImagen(imagen);
    setFieldContraste(new JTextField(NUM_COLS_FIELD));
    setFieldBrillo(new JTextField(NUM_COLS_FIELD));
    add(labelContraste);
    add(getFieldContraste());
    add(labelBrillo);
    add(getFieldBrillo());
    add(botonOk);
    botonOk.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        int brillo = Integer.parseInt(getFieldBrillo().getText());
        int contraste = Integer.parseInt(getFieldContraste().getText());
        Imagen imagenNueva = new Imagen(getImagen().BrilloYContraste(brillo,
            contraste), getImagen().getNombre() + "brillo_cont");
        getInterfazRef()
            .crearNuevaVentana(imagenNueva, imagenNueva.getNombre());
        System.out.println(brillo + " " + contraste);
        dispose();
      }
    });
    setSize(ANCHO, ALTO);
    setResizable(RESIZABLE);
    setVisible(true);
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

  private JTextField getFieldContraste() {
    return fieldContraste;
  }

  private void setFieldContraste(JTextField fieldContraste) {
    this.fieldContraste = fieldContraste;
  }

  private JTextField getFieldBrillo() {
    return fieldBrillo;
  }

  private void setFieldBrillo(JTextField fieldBrillo) {
    this.fieldBrillo = fieldBrillo;
  }

}
