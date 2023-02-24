/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadistica;

import Utiles.ClasesUtiles.Matematicas.ConjuntoResultadoEstadistico;
import Utiles.MetodosUtiles.MetodosUtiles;
import static Utiles.MetodosUtiles.MetodosUtiles.eliminarCerosDerecha;
import static Utiles.MetodosUtiles.MetodosUtiles.redondearA3String;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Rene
 */
public class ConjuntoResultadoExpandido extends ConjuntoResultadoEstadistico{
private double valorDeComparacion, proporcion, porcentage;
   private String promedioPasos[], varianzaPasos[], posicionMediaPasos[], varibilidadRelativaPasos[], proporcionPasos[], moda;
    private TipoDeComparacion tipoDeComparacion;

    public ConjuntoResultadoExpandido(double[] datos) {
        this(datos, Double.MAX_EXPONENT, null);
    }

   
    

    public ConjuntoResultadoExpandido(double[] datos, double valorDeComparacion, TipoDeComparacion tipoDeComparacion) {
        super(datos);
        this.tipoDeComparacion = tipoDeComparacion;
        this.valorDeComparacion=valorDeComparacion;
        promedioPasos = iniPromedioPasos();
        varianzaPasos = iniVarianzaPasos();
        posicionMediaPasos = iniPosicionMediaPasos();
        moda = iniModa();
        varibilidadRelativaPasos = iniVaribilidadRelativaPasos();
        proporcionPasos=iniProporcionPasos();
    }

    public String[] iniPromedioPasos() {
        LinkedList<String> l = new LinkedList<>();
        String linea = "";
        double suma = 0;
        for (int i = 0; i < datos.length; i++) {
            linea += (i != 0 ? " + " : "") + redondearA3String(datos[i]);
            suma += datos[i];
        }
        linea += " / " + datos.length;
        l.add(linea);
        linea = eliminarCerosDerecha(suma) + " / " + datos.length;
        l.add(linea);
      //  promedio = suma / datos.length;
        l.add(eliminarCerosDerecha(promedio) + "");

        return l.toArray(new String[0]);
    }

    public String[] iniVarianzaPasos() {
        LinkedList<String> l = new LinkedList<>();
        double diferencias[] = new double[datos.length], elevaciones[] = new double[datos.length], suma = 0;

        String linea = "";
        for (int i = 0; i < datos.length; i++) {
            linea += (i != 0 ? " + " : "") + " (" + eliminarCerosDerecha(datos[i]) + "-" + redondearA3String(promedio) + ")^2 ";
            diferencias[i] = datos[i] - promedio;
            elevaciones[i] = Math.pow(diferencias[i], 2);
            suma += elevaciones[i];
        }
        linea += " / (" + datos.length + " - 1)";
        l.add(linea);
        linea = "";
        for (int i = 0; i < datos.length; i++) {
            linea += (i != 0 ? " + " : "") + " (" + redondearA3String(diferencias[i]) + ")^2 ";
        }
        linea += " / (" + datos.length + " - 1)";
        l.add(linea);
        linea = "";
        for (int i = 0; i < datos.length; i++) {
            linea += (i != 0 ? " + " : "") + redondearA3String(elevaciones[i]);
        }
        linea += " / (" + datos.length + " - 1)";
        l.add(linea);

        linea = eliminarCerosDerecha(suma) + " / " + (datos.length - 1);
        l.add(linea);

       // varianza = suma / (datos.length - 1);
        l.add(eliminarCerosDerecha(varianza) + "");
       // desviacionEstandar = Math.sqrt(varianza);
        return l.toArray(new String[0]);
    }

////    private double calcularPosicionMedia() {
////        datosOrdenados = new double[datos.length];
////        System.arraycopy(datos, 0, datosOrdenados, 0, datos.length);
////        Arrays.sort(datosOrdenados);
////        return datos.length % 2 == 0 ? (datosOrdenados[datos.length / 2] + datosOrdenados[(datos.length / 2) + 1]) / 2 : datosOrdenados[(datos.length / 2) + 1];
////    }
    private String[] iniPosicionMediaPasos() {
//        datosOrdenados = new double[datos.length];
//        System.arraycopy(datos, 0, datosOrdenados, 0, datos.length);
        LinkedList<String> l = new LinkedList<>();
//        Arrays.sort(datosOrdenados);
        l.add(MetodosUtiles.unirString(v -> MetodosUtiles.eliminarCerosDerecha(v) + " ", datosOrdenados));
        if (datos.length % 2 == 0) {
            l.add(eliminarCerosDerecha(datosOrdenados[(datos.length / 2) - 1]) + " + " + eliminarCerosDerecha(datosOrdenados[(datos.length / 2)]) + "/2");
        }

        //posicionMedia = datos.length % 2 == 0 ? (datosOrdenados[(datos.length / 2) - 1] + datosOrdenados[(datos.length / 2)]) / 2 : datosOrdenados[(datos.length / 2) + 1];
        l.add(eliminarCerosDerecha(posicionMedia) + "");
        return l.toArray(new String[0]);
   }

    private String iniModa() {
        HashMap<Double, Integer> set = new HashMap<Double, Integer>();
        List l = Arrays.asList(datosOrdenados);
        for (int i = 0; i < datos.length; i++) {
            if (!set.containsKey(datosOrdenados[i])) {
                set.put(datosOrdenados[i], 0);
            } else {
                set.put(datosOrdenados[i], set.get(datosOrdenados[i]) + 1);
            }
        }
        LinkedList<Double> moda = new LinkedList<>();
        set.forEach((valor, frecuencia) -> {
//            System.out.println("valor=" + valor + " frecuencia=" + frecuencia);
            if (moda.isEmpty()) {
                moda.add(valor);
                return;
            }
            int frecuenciaAnterior = set.get(moda.getFirst());
            if (frecuencia == frecuenciaAnterior) {
                moda.add(valor);
//                System.out.println("add");
                return;
            }
            if (frecuencia > frecuenciaAnterior) {
                moda.clear();
//                System.out.println("clear");
                moda.add(valor);
            }
        });
        if (moda.size() == set.size()) {
            return "no hay moda";
        }
        String respuesta = "";
        for (int i = 0; i < moda.size(); i++) {
            respuesta += eliminarCerosDerecha(moda.get(i)) + " ";
        }
        return respuesta;
    }

    private String[] iniVaribilidadRelativaPasos() {
//        varibilidadRelativa = (desviacionEstandar / promedio) * 100;
        LinkedList<String> l = new LinkedList<>();
        l.add(eliminarCerosDerecha(desviacionEstandar) + "/" + eliminarCerosDerecha(promedio) + " * 100");
        l.add(eliminarCerosDerecha(varibilidadRelativa) + "%");
        return l.toArray(new String[0]);
    }

    private String[] iniProporcionPasos() {
        LinkedList<String> l = new LinkedList<>();
        if (tipoDeComparacion != null) {
            int cantidad = 0;
//            System.out.println("tipoDeComparacion="+tipoDeComparacion);
            For1:
            for (int i = 0; i < datosOrdenados.length; i++) {
                switch (tipoDeComparacion) {
                    case IGUAL_QUE:
                        if (datosOrdenados[i] == valorDeComparacion) {
                            cantidad++;
                            break;
                        }
                        if (datosOrdenados[i] > valorDeComparacion) {
                            break For1;
                        }
                        break;
                    case MAYOR_QUE:
                        if (datosOrdenados[i] > valorDeComparacion) {
                            cantidad += datosOrdenados.length - i;
                            break For1;
                        }
                        break;
                    case MENOR_QUE:
                        if (datosOrdenados[i] < valorDeComparacion) {
                            cantidad++;
                            break;
                        }
                        break For1;

                    case MENOR_IGUAL_QUE:
                        if (datosOrdenados[i] <= valorDeComparacion) {
                            cantidad++;
                            break;
                        }
                        break For1;

                    case MAYOR_IGUAL_QUE:
                        if (datosOrdenados[i] >= valorDeComparacion) {
                            cantidad += datosOrdenados.length - i;
                            break For1;
                        }
                        break;

                }
            }
            proporcion = (double)cantidad / (double)datos.length;
            porcentage = proporcion * 100;
            l.add(cantidad + "/" + datos.length );
            l.add(eliminarCerosDerecha(proporcion) + "");
            l.add(eliminarCerosDerecha(proporcion) + " * 100");
            l.add(eliminarCerosDerecha(porcentage) + "%");

        }
        return l.toArray(new String[0]);
    }

    public double getValorDeComparacion() {
        return valorDeComparacion;
    }

    public double getProporcion() {
        return proporcion;
    }

    public double getPorcentage() {
        return porcentage;
    }

    public String[] getPromedioPasos() {
        return promedioPasos;
    }

    public String[] getVarianzaPasos() {
        return varianzaPasos;
    }

    public String[] getPosicionMediaPasos() {
        return posicionMediaPasos;
    }

    public String[] getVaribilidadRelativaPasos() {
        return varibilidadRelativaPasos;
    }

    public String[] getProporcionPasos() {
        return proporcionPasos;
    }

    public String getModa() {
        return moda;
    }

    public TipoDeComparacion getTipoDeComparacion() {
        return tipoDeComparacion;
    }

}
