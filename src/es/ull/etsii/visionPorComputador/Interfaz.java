package es.ull.etsii.visionPorComputador;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Interfaz extends JFrame {
  
  private static final long serialVersionUID = -5863315165069961036L;
  public static String TITULO_VENTANA = "Interfaz";
  public static int ANCHO_VENTANA = 400;
  public static int ALTO_VENTANA = 400;
  
  public Interfaz() {
	Imagen imagen = new Imagen("/home/dani/Imágenes/index.jpeg");
    
    PanelImagen panelImagen = new PanelImagen(imagen);
    
    add(panelImagen);
    imagen.setImagen(imagen.set_gris(imagen.getImagen()));
    
    
  }
  
  public class PanelImagen extends JPanel {
    Imagen imagen;

    private Imagen getImagen() {
      return imagen;
    }

    private void setImagen(Imagen imagen) {
      this.imagen = imagen;
    }
    
    public PanelImagen(Imagen imagen) {
      setImagen(imagen);
    }
    
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(getImagen().getImagen(), 0, 0, this);
    }
    
  }

  public static void main(String [] args) {
    JFrame frame = new Interfaz();
    frame.setTitle(TITULO_VENTANA);
    frame.setSize(ANCHO_VENTANA, ALTO_VENTANA);
    frame.setVisible(true);
    
  }

}
