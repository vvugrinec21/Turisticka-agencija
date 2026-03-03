package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaOTA;

public class ObradiDogadajOTA extends Aktivnost {

	private final RezervacijskiSustav sustav;

	public ObradiDogadajOTA(RezervacijskiSustav sustav, KomandaOTA komanda) {
		super(komanda);
		this.sustav = sustav;
	}

	@Override
	public void izvrsiAktivnost() {

		KomandaOTA cmd = (KomandaOTA) komanda;

		
		System.out.println(cmd.getKorisnickiUnos());

		if (!cmd.provjeriIspravnost()) {
			System.out.println("Neispravna komanda OTA.");
			return;
		}

		boolean ok = sustav.otkaziAranzman(cmd.getOznaka());

		if (ok) {
			System.out.println(
				"Turistički aranžman s oznakom " +
				cmd.getOznaka() + " je uspješno otkazan."
			);
		} else {
			System.out.println(
				"Ne postoji turistički aranžman s oznakom " +
				cmd.getOznaka() + "."
			);
		}
	}

}
