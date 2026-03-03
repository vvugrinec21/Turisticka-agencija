package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaORTA;

public class ObradiDogadajORTA extends Aktivnost {

	private final RezervacijskiSustav sustav;

	public ObradiDogadajORTA(RezervacijskiSustav sustav, KomandaORTA komanda) {
		super(komanda);
		this.sustav = sustav;
	}

	@Override
	public void izvrsiAktivnost() {

		KomandaORTA cmd = (KomandaORTA) komanda;

		if (!cmd.provjeriIspravnost()) {
			System.out.println("Neispravna komanda ORTA.");
			return;
		}

		
		System.out.println(cmd.getKorisnickiUnos());

		
		boolean uspjeh = sustav.otkaziRezervaciju(
				cmd.getIme(),
				cmd.getPrezime(),
				cmd.getOznaka(),
				LocalDateTime.now()
		);

	
		if (uspjeh) {
			System.out.println(
				"Otkazana rezervacija osobe " +
				cmd.getIme() + " " + cmd.getPrezime() +
				" za turistički aranžman s oznakom " + cmd.getOznaka() + "."
			);
		} else {
			System.out.println(
				"Nije pronađena aktivna rezervacija osobe " +
				cmd.getIme() + " " + cmd.getPrezime() +
				" za turistički aranžman s oznakom " + cmd.getOznaka() + "."
			);
		}
	}
}
