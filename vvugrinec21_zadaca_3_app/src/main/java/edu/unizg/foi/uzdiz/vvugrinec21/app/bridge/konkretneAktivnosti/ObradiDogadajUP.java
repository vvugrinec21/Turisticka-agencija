package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import java.time.LocalDateTime;
import java.util.List;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaUP;
import edu.unizg.foi.uzdiz.vvugrinec21.lib.UcitavanjePodatakaFacade;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Format;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.builder.*;
import edu.unizg.foi.uzdiz.vvugrinec21.app.builder.KonkretniBuilder.KonkretniAranzmanBuilder;
import edu.unizg.foi.uzdiz.vvugrinec21.app.builder.KonkretniBuilder.KonkretniRezervacijaBuilder;
import edu.unizg.foi.uzdiz.vvugrinec21.app.builder.direktori.*;

public class ObradiDogadajUP extends Aktivnost {

	private final RezervacijskiSustav sustav;
	private final UcitavanjePodatakaFacade facade = new UcitavanjePodatakaFacade();

	public ObradiDogadajUP(RezervacijskiSustav sustav, KomandaUP komanda) {
		super(komanda);
		this.sustav = sustav;
	}

	@Override
	public void izvrsiAktivnost() {

		KomandaUP cmd = (KomandaUP) komanda;

		if (!cmd.provjeriIspravnost()) {
			System.out.println("Neispravna komanda UP.");
			return;
		}

		
		System.out.println(cmd.getKorisnickiUnos());

		if (cmd.getTip() == 'A') {
			ucitajAranzmane(cmd.getNazivDatoteke());
		} else {
			ucitajRezervacije(cmd.getNazivDatoteke());
		}
	}

	

	private void ucitajAranzmane(String datoteka) {

		List<String[]> zapisi = facade.ucitajAranzmane(datoteka);

		AranzmanBuilder ab = new KonkretniAranzmanBuilder();
		AranzmanDirector ad = new AranzmanDirector();

		for (String[] r : zapisi) {
			int oznaka = Integer.parseInt(r[0]);

			if (sustav.postojiAranzman(oznaka)) {
				System.out.println(
					"Pogreška: aranžman s oznakom " + oznaka + " već postoji."
				);
				continue;
			}

			ad.izgradi(r, ab);
			Aranzman a = ab.build();
			sustav.dodajAranzman(a);
		}

		System.out.println("Učitavanje aranžmana završeno.");
	}

	

	private void ucitajRezervacije(String datoteka) {

		List<String[]> zapisi = facade.ucitajRezervacije(datoteka);

		RezervacijaBuilder rb = new KonkretniRezervacijaBuilder();
		RezervacijaDirector rd = new RezervacijaDirector();

		for (String[] r : zapisi) {

			int oznakaAranzmana = Integer.parseInt(r[2]);

			if (!sustav.postojiAranzman(oznakaAranzmana)) {
				System.out.println(
					"Pogreška: ne postoji aranžman s oznakom " +
					oznakaAranzmana + " za rezervaciju."
				);
				continue;
			}

			LocalDateTime dt =
				Format.parseDatumVrijemeFlex(r[3]);

			rd.izgradi(r[0], r[1], oznakaAranzmana, dt, rb);
			Rezervacija rez = rb.build();
			sustav.dodajRezervaciju(rez);
		}

		System.out.println("Učitavanje rezervacija završeno.");
	}
}
