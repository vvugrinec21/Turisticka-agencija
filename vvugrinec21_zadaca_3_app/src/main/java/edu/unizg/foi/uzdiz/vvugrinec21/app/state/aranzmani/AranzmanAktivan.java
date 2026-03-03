package edu.unizg.foi.uzdiz.vvugrinec21.app.state.aranzmani;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.AktivnaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.OdgodenaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.OtkazanaRezervacija;

public class AranzmanAktivan implements StatusAranzmana {

	
	@Override
	public void azurirajStatus(Aranzman a) {

	    long aktivne = a.getRezervacije().stream()
	        .filter(r -> r.getStatus() instanceof AktivnaRezervacija)
	        .count();

	    if (aktivne < a.getMinBrojPutnika()) {
	        a.setStatus(new AranzmanUPripremi());
	    } else if (aktivne >= a.getMaxBrojPutnika()) {
	        a.setStatus(new AranzmanPopunjen());
	    }
	}

	@Override
	public String naziv() {
		return "AKTIVAN";
	}
}
