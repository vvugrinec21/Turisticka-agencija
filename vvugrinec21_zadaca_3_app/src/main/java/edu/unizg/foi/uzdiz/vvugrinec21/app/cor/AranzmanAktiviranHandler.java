package edu.unizg.foi.uzdiz.vvugrinec21.app.cor;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;

public class AranzmanAktiviranHandler extends ObavijestHandler {

    @Override
    protected boolean obradiInterno(Aranzman a) {
        if ("AKTIVAN".equalsIgnoreCase(a.getNazivStatusa())) {
            a.obavijestiPromatrace(
                "Aranžman '" + a.getNaziv() + "' je aktiviran."
            );
            return true;
        }
        return false;
    }
}
