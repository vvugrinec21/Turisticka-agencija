package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import edu.unizg.foi.uzdiz.vvugrinec21.app.NacinIspisa;
import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaIP;

public class ObradiDogadajIP extends Aktivnost {

	private final RezervacijskiSustav sustav;

	public ObradiDogadajIP(RezervacijskiSustav sustav, KomandaIP komanda) {
		super(komanda);
		this.sustav = sustav;
	}

	@Override
	public void izvrsiAktivnost() {

		KomandaIP cmd = (KomandaIP) komanda;

		
		System.out.println(cmd.getKorisnickiUnos());

		if (!cmd.provjeriIspravnost()) {
			System.out.println("Neispravna komanda IP.");
			return;
		}

		if (cmd.getOznaka() == 'N') {
			sustav.postaviNacinIspisa(NacinIspisa.N);
			System.out.println("Postavljen kronološki način ispisa.");
		} else {
			sustav.postaviNacinIspisa(NacinIspisa.S);
			System.out.println("Postavljen obrnuti kronološki način ispisa.");
		}
	}
}
