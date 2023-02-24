/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadistica;

import javafx.scene.control.SplitMenuButton;

/**
 *
 * @author Rene
 */
public enum TipoDeComparacion {
    MENOR_IGUAL_QUE("<=", "menor o igual que"),MAYOR_QUE(">", "mayor que"), MENOR_QUE("<", "menor que"), IGUAL_QUE("=", "igual que"), MAYOR_IGUAL_QUE(">=", "mayor o igual que");
    private final String valorString, frase;

    private TipoDeComparacion(String valorString, String frase) {
        this.valorString = valorString;
        this.frase = frase;
    }

    public String getFrase() {
        return frase;
    }

    public String getValorString() {
        return valorString;
    }

    public static String[] getComparadoresString() {
        String valores[] = new String[TipoDeComparacion.values().length];
        for (int i = 0; i < TipoDeComparacion.values().length; i++) {
            valores[i] = TipoDeComparacion.values()[i].getValorString();
        }
        return valores;
    }

    public static TipoDeComparacion getTipoDeComparacion(SplitMenuButton b) {
//        System.out.println("b.getText()="+b.getText());
        return getTipoDeComparacion(b.getText());
    }

    public static TipoDeComparacion getTipoDeComparacion(String valorString) {
        for (int i = 0; i < TipoDeComparacion.values().length; i++) {
            if (valorString.equals(TipoDeComparacion.values()[i].getValorString())) {
                 return TipoDeComparacion.values()[i];
            }
           
        }
        return null;
    }
//    @Override
//    public String toString() {
//        return valorString; //To change body of generated methods, choose Tools | Templates.
//    }

}
