package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;


public class KomandaPPTAR implements Komanda {

    private static final Pattern PATTERN =
            Pattern.compile("^PPTAR\\s+(A|R)\\s+(.+)$");

    private final String korisnickiUnos;
    private char tip;      
    private String rijec;

    public KomandaPPTAR(String korisnickiUnos) {
        this.korisnickiUnos = korisnickiUnos;
        parsiraj();
    }

    private void parsiraj() {
        Matcher m = PATTERN.matcher(korisnickiUnos);
        if (m.matches()) {
            tip = m.group(1).charAt(0);
            rijec = m.group(2);
        }
    }

    @Override
    public boolean provjeriIspravnost() {
        return (tip == 'A' || tip == 'R')
                && rijec != null
                && !rijec.isBlank();
    }

    @Override
    public String getKorisnickiUnos() {
        return korisnickiUnos;
    }

  

    public char getTip() {
        return tip;
    }

    public String getRijec() {
        return rijec;
    }
}
