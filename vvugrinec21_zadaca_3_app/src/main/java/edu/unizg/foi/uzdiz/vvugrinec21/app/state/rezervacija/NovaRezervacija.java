package edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;

public class NovaRezervacija implements StatusRezervacije {

	@Override
	public void obradiDodavanje(Rezervacija r, RezervacijskiSustav sustav) {
		Aranzman a = sustav.dohvatiAranzman(r.getOznakaAranzmana());

		if (a == null) {
			r.setStatus(new OtkazanaRezervacija());
			return;
		}

		
		r.setStatus(new PrimljenaRezervacija());
		r.obradiDodavanje(sustav);
	}

	@Override
	public void obradiOtkazivanje(Rezervacija r, RezervacijskiSustav sustav) {
		r.setStatus(new OtkazanaRezervacija());
	}

	@Override
	public String naziv() {
		return "NOVA";
	}
}
