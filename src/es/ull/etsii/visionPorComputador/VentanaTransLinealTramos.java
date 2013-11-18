package es.ull.etsii.visionPorComputador;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VentanaTransLinealTramos extends JFrame {

  private static final long serialVersionUID = 2882987894820705108L;

  public static final int ANCHO = 500;
  public static final int ALTO = 200;
  public static final boolean RESIZABLE = true;

  public static final String REGEXP_SEPARAR_PUNTOS = "\\s*,?\\s+";

  public static final int PUNTOS_DEFECTO = 2;
  public static final int NUM_COLS_FIELD_ELEGIR_NUM_PUNTOS = 4;
  public static final int NUM_COLS_FIELD_PUNTOS = 5;
  public static final int NUM_COLS_PANEL_ESCRIBIR_PUNTOS = 1;

  public static final int MAX_PUNTOS = 8;

  private Imagen imagen;
  private Interfaz interfazRef;
  private Vector<JTextField> fieldPuntos;
  private int numPuntos;
  JSpinner spinnerElegPuntos;
  private JButton botonOk;
  private JPanel panelEscribirPuntos;

  public VentanaTransLinealTramos(Imagen imagen, Interfaz interfazRef) {
    JLabel labelElegirPuntos = new JLabel("Elegir número de puntos: ");
    setImagen(imagen);
    setInterfazRef(interfazRef);
    setFieldPuntos(new Vector<JTextField>());
    setNumPuntos(PUNTOS_DEFECTO);
    setBotonOk(new JButton("Aceptar"));
    setLayout(new BorderLayout());
    setSize(ANCHO, ALTO);
    
    // Lista donde se elige el número de puntos
    SpinnerNumberModel modeloNumero = new SpinnerNumberModel(PUNTOS_DEFECTO,
        PUNTOS_DEFECTO, MAX_PUNTOS, 1);
    setSpinnerElegPuntos(new JSpinner(modeloNumero));
    getSpinnerElegPuntos().addChangeListener(new ChangeListener() {
      
      @Override
      public void stateChanged(ChangeEvent arg0) {
        setNumPuntos((int)getSpinnerElegPuntos().getValue());
        actualizarIntefaz();
      }
    });

    // Panel superior donde se controla el número de puntos
    JPanel panelControl = new JPanel();
    panelControl.setLayout(new FlowLayout());
    panelControl.add(labelElegirPuntos);
    // panelControl.add(getFieldElegirNumPuntos());
    panelControl.add(getSpinnerElegPuntos());
    panelControl.add(getBotonOk());
    add(panelControl, BorderLayout.NORTH);
    getBotonOk().addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        try {
          String nombre = getImagen().getNombre();
          String[] arrayPuntos;
          int numPuntos = getNumPuntos();
          ArrayList<Coordenadas> points = new ArrayList<Coordenadas>();
          for (int i = 0; i < numPuntos; i++) {
            arrayPuntos = getField(i).getText().split(REGEXP_SEPARAR_PUNTOS);
            
            System.out.print("Array puntos = ");
            for(int j = 0; j < arrayPuntos.length; j++)
              System.out.print(arrayPuntos[j] + " ");
            
            points.add(new Coordenadas(Integer.parseInt(arrayPuntos[0]),
                Integer.parseInt(arrayPuntos[1])));
          }
          getInterfazRef().crearNuevaVentana(
              new Imagen(getInterfazRef().getImagenActiva()
                  .Linear_trans(points), nombre + "Linear_trans"),
              nombre + "Linear_trans");
          dispose();
        } catch (NumberFormatException noNumero) {
          System.err.println("Error en puntos");
        }
      }
    });
    
    // Panel donde se escriben los JTextField para indicar los puntos
    setPanelEscribirPuntos(new JPanel());
    add(getPanelEscribirPuntos(), BorderLayout.CENTER);
    actualizarIntefaz();
    setResizable(RESIZABLE);
    setVisible(true);
  }

  public void actualizarIntefaz() {
    int numPuntos = getNumPuntos(); // Número de puntos que se quiere poner
    int numFieldPuntosActual = getFieldPuntos().size();
    // TODO No eliminar todo antes de repintar el JPanel (se necesita ir elimi-
    // nando también los JLabel (ponerlas accesibles desde aquí))
    getPanelEscribirPuntos().removeAll();
    System.out.println("act intefaz " + getNumPuntos());
    getPanelEscribirPuntos().setLayout(new FlowLayout());
    //for(int i = 0; i < numPuntos; i++)
      //getFieldPuntos().add(new JTextField(NUM_COLS_FIELD_PUNTOS));
    if (numPuntos < numFieldPuntosActual) {
      for (int i = numFieldPuntosActual; i == numPuntos; i--)
        eliminarUltimoField();
    } else {
      for (int i = numFieldPuntosActual; i < numPuntos; i++)
        getFieldPuntos().add(new JTextField(NUM_COLS_FIELD_PUNTOS));
    }
    for (int i = 0; i < numPuntos; i++) {
      getPanelEscribirPuntos().add(new JLabel("Punto " + (i + 1)));
      getPanelEscribirPuntos().add(getField(i));
    }
    getPanelEscribirPuntos().revalidate();
    getPanelEscribirPuntos().repaint();
  }

  private Imagen getImagen() {
    return imagen;
  }

  private void setImagen(Imagen imagen) {
    this.imagen = imagen;
  }

  private Interfaz getInterfazRef() {
    return interfazRef;
  }

  private void setInterfazRef(Interfaz interfazRef) {
    this.interfazRef = interfazRef;
  }

  private void eliminarUltimoField() {
    getFieldPuntos().remove(getFieldPuntos().size() - 1);
  }

  private JTextField getField(int i) {
    return getFieldPuntos().get(i);
  }

  private Vector<JTextField> getFieldPuntos() {
    return fieldPuntos;
  }

  private void setFieldPuntos(Vector<JTextField> fieldPuntos) {
    this.fieldPuntos = fieldPuntos;
  }

  private int getNumPuntos() {
    return numPuntos;
  }

  private void setNumPuntos(int numPuntos) {
    this.numPuntos = numPuntos;
  }

  private JButton getBotonOk() {
    return botonOk;
  }

  private void setBotonOk(JButton botonOk) {
    this.botonOk = botonOk;
  }

  private JPanel getPanelEscribirPuntos() {
    return panelEscribirPuntos;
  }

  private void setPanelEscribirPuntos(JPanel panelEscribirPuntos) {
    this.panelEscribirPuntos = panelEscribirPuntos;
  }

  private JSpinner getSpinnerElegPuntos() {
    return spinnerElegPuntos;
  }

  private void setSpinnerElegPuntos(JSpinner spinnerElegPuntos) {
    this.spinnerElegPuntos = spinnerElegPuntos;
  }

}
