package edu.unizg.foi.uzdiz.vvugrinec21.app.cor;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;

public class AranzmanPopunjenHandler extends ObavijestHandler {

    @Override
    protected boolean obradiInterno(Aranzman a) {
        if ("POPUNJEN".equalsIgnoreCase(a.getNazivStatusa())) {
            a.obavijestiPromatrace(
                "Aranžman '" + a.getNaziv() + "' je popunjen."
            );
            return true;
        }
        return false;
    }
}
