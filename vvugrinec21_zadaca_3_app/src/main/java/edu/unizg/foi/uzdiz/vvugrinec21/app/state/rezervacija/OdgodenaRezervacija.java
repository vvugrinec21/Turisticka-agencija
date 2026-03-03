package edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;

public class OdgodenaRezervacija implements StatusRezervacije {

	@Override
	public void obradiDodavanje(Rezervacija r, RezervacijskiSustav sustav) {
		
	}

	@Override
	public void obradiOtkazivanje(Rezervacija r, RezervacijskiSustav sustav) {
		r.setStatus(new OtkazanaRezervacija());
	}

	@Override
	public String naziv() {
		return "ODGOĐENA";
	}
}
