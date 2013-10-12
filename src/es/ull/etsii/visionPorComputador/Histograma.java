package es.ull.etsii.visionPorComputador;

import java.util.Vector;

public class Histograma {
  
  private Vector<Integer> histoGrama;
  
  public Histograma(Imagen imagen) {
    // TODO Construir histograma de imagen
    setHistoGrama(new Vector<Integer>());
  }

  private Vector<Integer> getHistoGrama() {
    return histoGrama;
  }

  private void setHistoGrama(Vector<Integer> histoGrama) {
    this.histoGrama = histoGrama;
  }
  
}
