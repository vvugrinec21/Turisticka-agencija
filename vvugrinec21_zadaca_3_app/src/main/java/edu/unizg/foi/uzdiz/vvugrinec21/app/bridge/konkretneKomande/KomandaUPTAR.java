package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;

public class KomandaUPTAR implements Komanda {

    private final String unos;
    private String ime;
    private String prezime;
    private int oznaka;
    private boolean samoOznaka;

    public KomandaUPTAR(String unos) {
        this.unos = unos.trim();
    }

    @Override
    public boolean provjeriIspravnost() {

        String[] dijelovi = unos.split("\\s+");

        try {
            if (dijelovi.length == 2) {
                oznaka = Integer.parseInt(dijelovi[1]);
                samoOznaka = true;
                return true;
            }

            if (dijelovi.length == 4) {
                ime = dijelovi[1];
                prezime = dijelovi[2];
                oznaka = Integer.parseInt(dijelovi[3]);
                samoOznaka = false;
                return true;
            }

            return false;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean jeSamoOznaka() {
        return samoOznaka;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public int getOznaka() {
        return oznaka;
    }

    @Override
    public String getKorisnickiUnos() {
        return unos;
    }
}
