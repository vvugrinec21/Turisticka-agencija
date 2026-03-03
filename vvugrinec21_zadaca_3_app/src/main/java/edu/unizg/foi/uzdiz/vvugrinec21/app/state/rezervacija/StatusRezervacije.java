package edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;

public interface StatusRezervacije {

	void obradiDodavanje(Rezervacija rezervacija, RezervacijskiSustav sustav);

	void obradiOtkazivanje(Rezervacija rezervacija, RezervacijskiSustav sustav);

	String naziv();
}
