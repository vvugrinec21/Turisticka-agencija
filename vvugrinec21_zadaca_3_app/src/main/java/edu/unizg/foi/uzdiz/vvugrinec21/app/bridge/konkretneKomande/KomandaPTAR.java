package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;

public class KomandaPTAR implements Komanda {

    private final String unos;
    private String ime;
    private String prezime;
    private Integer oznaka;

    public KomandaPTAR(String unos) {
        this.unos = unos;
        parsiraj();
    }

    private void parsiraj() {
        String[] dijelovi = unos.split("\\s+");
        if (dijelovi.length == 4) {
            ime = dijelovi[1];
            prezime = dijelovi[2];
            oznaka = Integer.parseInt(dijelovi[3]);
        }
    }

    @Override
    public boolean provjeriIspravnost() {
        return ime != null && prezime != null && oznaka != null;
    }

    @Override
    public String getKorisnickiUnos() {
        return unos;
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
}
