package edu.unizg.foi.uzdiz.vvugrinec21.app.state.aranzmani;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.OdgodenaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.OtkazanaRezervacija;

public class AranzmanPopunjen implements StatusAranzmana {

	@Override
	public void azurirajStatus(Aranzman a) {

		long prijave = a.getRezervacije().stream()
			    .filter(r -> !(r.getStatus() instanceof OtkazanaRezervacija))
			    .count();

			if (prijave < a.getMaxBrojPutnika()) {
			    a.setStatus(new AranzmanAktivan());
			}
	}

	@Override
	public String naziv() {
		return "POPUNJEN";
	}
}
