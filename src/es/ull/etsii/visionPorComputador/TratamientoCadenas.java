package es.ull.etsii.visionPorComputador;

public class TratamientoCadenas {
  
  public static int NUM_CARACTERES_ABREVIAR = 30;
  
  private TratamientoCadenas() {}
  
  public static String getNombreImagen(String path) {
    String pathCopia = path.replace('\\', '/');
    String[] partesPath = pathCopia.split("/");
    return partesPath[partesPath.length - 1];
  }

  public static String abreviarNombre(String nombre) {
    if ((nombre.length() > 0) && (nombre.length() > NUM_CARACTERES_ABREVIAR)) {
      String nombreAbreviado = "";
      String segundaParteAbrev = "";
      for(int i = 0; i < NUM_CARACTERES_ABREVIAR / 2; i++) 
        nombreAbreviado += nombre.charAt(i);
      nombreAbreviado += "...";
      //if (nombre.length() > 2 * NUM_CARACTERES_ABREVIAR) {
        /*
       * for (int i = nombre.length() - NUM_CARACTERES_ABREVIAR / 2; i < nombre
       * .length(); i++) nombreAbreviado += nombre.charAt(i);
       */
      for (int i = nombre.length() - 1; (i > nombre.length()
          - NUM_CARACTERES_ABREVIAR / 2)
          && (i > NUM_CARACTERES_ABREVIAR / 2); i--)
        segundaParteAbrev += nombre.charAt(i);
      System.out.println(nombreAbreviado.length() + " == "
          + segundaParteAbrev.length());
      StringBuilder builder = new StringBuilder(segundaParteAbrev);
      nombreAbreviado += builder.reverse().toString();
      // }
      return nombreAbreviado;
    } else
      return nombre;
  }
  
}
