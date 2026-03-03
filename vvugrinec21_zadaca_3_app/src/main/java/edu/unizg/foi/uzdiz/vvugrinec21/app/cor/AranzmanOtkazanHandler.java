package edu.unizg.foi.uzdiz.vvugrinec21.app.cor;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;

public class AranzmanOtkazanHandler extends ObavijestHandler {

    @Override
    protected boolean obradiInterno(Aranzman a) {
        if ("OTKAZAN".equalsIgnoreCase(a.getNazivStatusa())) {
            a.obavijestiPromatrace(
                "Aranžman '" + a.getNaziv() + "' je otkazan."
            );
            return true;
        }
        return false;
    }
}
