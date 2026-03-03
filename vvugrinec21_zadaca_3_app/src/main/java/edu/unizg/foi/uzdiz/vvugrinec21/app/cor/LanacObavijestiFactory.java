package edu.unizg.foi.uzdiz.vvugrinec21.app.cor;

public class LanacObavijestiFactory {

    public static ObavijestHandler kreirajLanac() {

        ObavijestHandler h1 = new AranzmanOtkazanHandler();
        ObavijestHandler h2 = new AranzmanAktiviranHandler();
        ObavijestHandler h3 = new AranzmanPopunjenHandler();

        h1.setNext(h2).setNext(h3);

        return h1;
    }
}
