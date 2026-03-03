package edu.unizg.foi.uzdiz.vvugrinec21.app.memento;

import java.util.List;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.observer.Promatrac;

public class AranzmanMemento {

    private final int oznaka;
    private final Aranzman aranzmanKopija;
    private final List<Rezervacija> rezervacijeKopija;
    private final List<Promatrac> promatraciKopija;

    public AranzmanMemento(
            int oznaka,
            Aranzman aranzmanKopija,
            List<Rezervacija> rezervacijeKopija,
            List<Promatrac> promatraciKopija
    ) {
        this.oznaka = oznaka;
        this.aranzmanKopija = aranzmanKopija;
        this.rezervacijeKopija = rezervacijeKopija;
        this.promatraciKopija = promatraciKopija;
    }

    public int getOznaka() {
        return oznaka;
    }
    
    public Aranzman getAranzmanKopija() {
        return aranzmanKopija;
    }

    public List<Rezervacija> getRezervacijeKopija() {
        return rezervacijeKopija;
    }
    
    public List<Promatrac> getPromatraciKopija() {
        return promatraciKopija;
    }
}

