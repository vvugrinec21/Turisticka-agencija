package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;

public class KomandaVSTAR implements Komanda {

    private static final Pattern P = Pattern.compile("^VSTAR\\s+(\\d+)$");

    private final String korisnickiUnos;
    private Integer oznaka;

    public KomandaVSTAR(String korisnickiUnos) {
        this.korisnickiUnos = korisnickiUnos;
        parsiraj();
    }

    private void parsiraj() {
        Matcher m = P.matcher(korisnickiUnos.trim());
        if (m.matches()) {
            oznaka = Integer.parseInt(m.group(1));
        }
    }

    @Override
    public boolean provjeriIspravnost() {
        return oznaka != null && oznaka > 0;
    }

    @Override
    public String getKorisnickiUnos() {
        return korisnickiUnos;
    }

    public int getOznaka() {
        return oznaka;
    }
}
