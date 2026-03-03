package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaDRTA;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Format;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;

public class ObradiDogadajDRTA extends Aktivnost {

	private final RezervacijskiSustav sustav;

	public ObradiDogadajDRTA(RezervacijskiSustav sustav, KomandaDRTA komanda) {
		super(komanda);
		this.sustav = sustav;
	}

	@Override
	public void izvrsiAktivnost() {

		KomandaDRTA cmd = (KomandaDRTA) komanda;

		if (!cmd.provjeriIspravnost()) {
			System.out.println("Neispravna komanda DRTA.");
			return;
		}

		
		LocalDateTime dt;
		try {
			dt = Format.parseDatumVrijemeFlex(cmd.getDatumVrijeme());
		} catch (Exception e) {
			System.out.println("Neispravan format datuma i vremena.");
			return;
		}

		
		Rezervacija r = new Rezervacija(
			cmd.getIme(),
			cmd.getPrezime(),
			cmd.getOznaka(),
			dt
		);

		boolean ok = sustav.dodajRezervaciju(r);

		if (!ok) {
			System.out.println("Dodavanje rezervacije nije uspjelo.");
			return;
		}

		
		if (!"ODGOĐENA".equals(r.getStatus().naziv())) {
			System.out.println(
				"Rezervacija uspješno dodana za " +
				cmd.getIme() + " " + cmd.getPrezime() + "."
			);
		}
	}
}
