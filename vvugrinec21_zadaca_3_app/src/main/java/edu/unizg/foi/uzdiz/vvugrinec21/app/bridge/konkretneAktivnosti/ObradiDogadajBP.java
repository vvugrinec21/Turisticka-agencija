package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaBP;

public class ObradiDogadajBP extends Aktivnost {

	private final RezervacijskiSustav sustav;

	public ObradiDogadajBP(RezervacijskiSustav sustav, KomandaBP komanda) {
		super(komanda);
		this.sustav = sustav;
	}

	@Override
	public void izvrsiAktivnost() {

		KomandaBP cmd = (KomandaBP) komanda;

		if (!cmd.provjeriIspravnost()) {
			System.out.println("Neispravna komanda BP.");
			return;
		}

		
		System.out.println(cmd.getKorisnickiUnos());

	
		if (cmd.getTip() == 'A') {
			sustav.obrisiSveAranzmane();
			System.out.println("Svi turistički aranžmani i njihove rezervacije su obrisani.");
		} else {
			sustav.obrisiSveRezervacije();
			System.out.println("Sve rezervacije su obrisane.");
		}
	}
}
