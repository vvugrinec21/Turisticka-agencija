package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;

public class KomandaID implements Komanda {

    private boolean ispravno;
    private boolean ukljuci;
    private final String korisnickiUnos;

    public KomandaID(String unos) {
    	
    	this.korisnickiUnos = unos.trim();
        unos = unos.trim().toUpperCase();
        if (unos.equals("ID ON")) {
            ukljuci = true;
            ispravno = true;
        } else if (unos.equals("ID OFF")) {
            ukljuci = false;
            ispravno = true;
        }
    }

    @Override
    public boolean provjeriIspravnost() {
        return ispravno;
    }

    public boolean ukljuci() {
        return ukljuci;
    }
    
    public String getKorisnickiUnos() {
        return korisnickiUnos;
    }
}
