package edu.unizg.foi.uzdiz.vvugrinec21.app.strategy;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;

public interface StrategijaRezervacija {
    void primijeni(Rezervacija nova, RezervacijskiSustav sustav);
}
