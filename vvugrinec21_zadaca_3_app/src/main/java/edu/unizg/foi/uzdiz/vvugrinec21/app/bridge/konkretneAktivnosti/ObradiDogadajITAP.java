package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaITAP;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Format;

public class ObradiDogadajITAP extends Aktivnost {

	private final RezervacijskiSustav sustav;

	public ObradiDogadajITAP(RezervacijskiSustav sustav, KomandaITAP komanda) {
		super(komanda);
		this.sustav = sustav;
	}

	@Override
	public void izvrsiAktivnost() {

		KomandaITAP cmd = (KomandaITAP) komanda;
		
		
		if (!cmd.provjeriIspravnost()) {
			System.out.println("Neispravna komanda ITAP.");
			return;
		}
		
		System.out.println(cmd.getKorisnickiUnos());

		int oznaka = cmd.getOznaka();
		Aranzman a = sustav.dohvatiAranzman(oznaka);

		if (a == null) {
			System.out.println("Ne postoji aranžman s oznakom " + oznaka);
			return;
		}

		System.out.println("Pregled turističkog aranžmana");
		System.out.println("------------------------------");

		System.out.printf("Oznaka: %d%n", a.getOznaka());
		System.out.printf("Naziv: %s%n", a.getNaziv());
		System.out.printf("Program: %s%n", a.getProgram());
		System.out.printf("Početni datum: %s%n", a.getPocetniDatum().format(Format.DATUM));
		System.out.printf("Završni datum: %s%n", a.getZavrsniDatum().format(Format.DATUM));

		System.out.printf("Vrijeme kretanja: %s%n",
				a.getVrijemeKretanja() != null
						? a.getVrijemeKretanja().format(Format.VRIJEME)
						: "-");

		System.out.printf("Vrijeme povratka: %s%n",
				a.getVrijemePovratka() != null
						? a.getVrijemePovratka().format(Format.VRIJEME)
						: "-");

		System.out.printf("Cijena: %s €%n", a.getCijena().toPlainString());
		System.out.printf("Min broj putnika: %d%n", a.getMinBrojPutnika());
		System.out.printf("Maks broj putnika: %d%n", a.getMaxBrojPutnika());

		System.out.printf("Broj noćenja: %s%n",
				a.getBrojNocenja() != null ? a.getBrojNocenja() : "-");

		System.out.printf("Doplata jednokrevetna: %s €%n",
				a.getDoplataJednokrevetna() != null
						? a.getDoplataJednokrevetna().toPlainString()
						: "-");

		System.out.printf("Prijevoz: %s%n",
				a.getPrijevoz() != null && !a.getPrijevoz().isBlank()
						? a.getPrijevoz()
						: "-");

		System.out.printf("Broj doručaka: %s%n",
				a.getBrojDorucaka() != null ? a.getBrojDorucaka() : "-");
		System.out.printf("Broj ručkova: %s%n",
				a.getBrojRuckova() != null ? a.getBrojRuckova() : "-");
		System.out.printf("Broj večera: %s%n",
				a.getBrojVecera() != null ? a.getBrojVecera() : "-");

		
		System.out.printf("Status aranžmana: %s%n", a.getNazivStatusa());

		System.out.println("------------------------------");
	}
}
