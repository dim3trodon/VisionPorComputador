/**
 * @author Daniel Afonso González
 * @author Rodrigo Valladares Santana
 * 
 * @version 1.0, 11/11/13
 *
 * Proyecto de Visión Por Computador 2013/14
 * 
 * Ventana que muestra el histograma de una imagen. Necesita las librerías de 
 * JFreeChart.
 * 
 */
package es.ull.etsii.visionPorComputador;

import java.awt.Font;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

public class VentanaHistograma extends JFrame {

  private static final long serialVersionUID = -2502439510518041846L;
  
  public static final int ANCHO = 200;
  public static final int ALTO = 120;
  
  public static final int ANCHO_HISTOGRAMA = ANCHO;
  public static final int ALTO_HISTOGRAMA = ALTO;
  
  public static final int ESPACIO_CATEGORIA = 10;
  
  public static final String TITULO = "Histograma";
  
  private Histograma histograma;
  private String nombreImagen;
  
  public VentanaHistograma(Histograma histograma, String nombreImagen) {
    
    System.out.println("histograma");
    
    setHistograma(histograma);
    setNombreImagen(nombreImagen);
    JFreeChart chart = crearChart(crearDataset());
    add(new ChartPanel(chart));
    pack();
    RefineryUtilities.centerFrameOnScreen(this);
    setVisible(true);
    
    System.out.println("Fin_histograma");
    
  }
  
  private CategoryDataset crearDataset() {
    // TODO volverlo a hacer con histograma
    /*CategoryDataset histoDataSet = new CategoryDataSet();
    HistogramDataset histoDataSet = new HistogramDataset();
    double[] vectorDatos = new double[Histograma.NUMERO_PIXELES];
    for(int i = 0; i < Histograma.NUMERO_PIXELES; i++)
      vectorDatos[i] = (double)getHistograma().getValor(i);
    histoDataSet.addSeries("", vectorDatos, 
        Histograma.NUMERO_PIXELES);
    return (CategoryDataset) histoDataSet;*/
    String categoria;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for(int i = 0; i < Histograma.NUMERO_PIXELES; i++) {
        categoria = String.valueOf(i);
      dataset.addValue(getHistograma().getValor(i), "Valor", categoria);
    }
    return dataset;
  }
  
  private JFreeChart crearChart(CategoryDataset histoDataSet) {
    JFreeChart chart = ChartFactory.createBarChart(
        TITULO + " de " + getNombreImagen(), "", "Valor",
        histoDataSet, PlotOrientation.VERTICAL, true, true, false);
    /*JFreeChart chart = ChartFactory.createHistogram(
        TITULO + " de " + getNombreImagen(), null, null, histoDataSet,
        PlotOrientation.VERTICAL, true, true, false);*/
    /*XYPlot plot = (XYPlot) chart.getPlot();
    XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
    renderer.setDrawBarOutline(false);
    try {
      ChartUtilities.saveChartAsJPEG(new File(nombreImagen + "histo"), chart, 
          Histograma.NUMERO_PIXELES, Histograma.NUMERO_PIXELES);
    }
    catch(IOException e) {
      System.err.println("Error al guardar " + nombreImagen + "histo");
    }*/
    CategoryPlot plot = chart.getCategoryPlot();
    
    //final NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
    
    
    
    final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    //rangeAxis.resizeRange(arg0, arg1);
    rangeAxis.setAutoRange(true);
    Font fuente = new Font("Symbols", Font.PLAIN, 6);
    //rangeAxis.setLabelFont(fuente);
    
    plot.getDomainAxis().setTickLabelFont(fuente);
    
    final BarRenderer renderer = (BarRenderer) plot.getRenderer();
    renderer.setDrawBarOutline(false);
    
    final CategoryAxis domainAxis = plot.getDomainAxis();
    domainAxis.setCategoryLabelPositions(
        CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 2));
    return chart;
  }

  private Histograma getHistograma() {
    return histograma;
  }

  private void setHistograma(Histograma histograma) {
    this.histograma = histograma;
  }

  private String getNombreImagen() {
    return nombreImagen;
  }

  private void setNombreImagen(String nombreImagen) {
    this.nombreImagen = nombreImagen;
  }

}
