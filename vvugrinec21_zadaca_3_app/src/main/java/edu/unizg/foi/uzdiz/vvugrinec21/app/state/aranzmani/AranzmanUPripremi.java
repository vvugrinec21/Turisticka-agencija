package edu.unizg.foi.uzdiz.vvugrinec21.app.state.aranzmani;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.OdgodenaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.OtkazanaRezervacija;

public class AranzmanUPripremi implements StatusAranzmana {

	@Override
	public void azurirajStatus(Aranzman a) {

		long prijave = a.getRezervacije().stream()
			.filter(r ->
				!(r.getStatus() instanceof OtkazanaRezervacija) &&
				!(r.getStatus() instanceof OdgodenaRezervacija)
			)
			.count();

		if (prijave >= a.getMinBrojPutnika()) {
			a.setStatus(new AranzmanAktivan());
		}
	}

	@Override
	public String naziv() {
		return "U_PRIPREMI";
	}
}
