package edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;

public class AktivnaRezervacija implements StatusRezervacije {

	@Override
	public void obradiDodavanje(Rezervacija r, RezervacijskiSustav sustav) {
		
		return;
	}

	@Override
	public void obradiOtkazivanje(Rezervacija r, RezervacijskiSustav sustav) {
	    r.setStatus(new OtkazanaRezervacija());

	    sustav.aktivirajNajranijuOdgodenuZaOsobu(
	        r.getIme(),
	        r.getPrezime()
	    );
	}
	


	@Override
	public String naziv() {
		return "AKTIVNA";
	}
}
