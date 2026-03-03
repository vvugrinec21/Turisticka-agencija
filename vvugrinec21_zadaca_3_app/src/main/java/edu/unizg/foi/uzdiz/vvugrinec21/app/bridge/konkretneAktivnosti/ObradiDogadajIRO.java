package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import edu.unizg.foi.uzdiz.vvugrinec21.app.NacinIspisa;
import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaIRO;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Format;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;

public class ObradiDogadajIRO extends Aktivnost {

	private final RezervacijskiSustav sustav;

	public ObradiDogadajIRO(RezervacijskiSustav sustav, KomandaIRO komanda) {
		super(komanda);
		this.sustav = sustav;
	}

	@Override
	public void izvrsiAktivnost() {

		KomandaIRO cmd = (KomandaIRO) komanda;

		if (!cmd.provjeriIspravnost()) {
			System.out.println("Neispravna komanda IRO.");
			return;
		}

	
		System.out.println(cmd.getKorisnickiUnos());

		List<Rezervacija> lista =
				sustav.rezervacijeZaOsobu(cmd.getIme(), cmd.getPrezime());

		if (lista.isEmpty()) {
			System.out.println(
				"Nema rezervacija za osobu " +
				cmd.getIme() + " " + cmd.getPrezime() + "."
			);
			return;
		}

	
		Comparator<Rezervacija> cmp =
				Comparator.comparing(Rezervacija::getZaprimljena);

		if (sustav.getNacinIspisa() == NacinIspisa.S) {
			cmp = cmp.reversed();
		}

		lista = lista.stream()
				.sorted(cmp)
				.collect(Collectors.toList());

	
		System.out.println(
			"Pregled rezervacija za osobu " +
			cmd.getIme() + " " + cmd.getPrezime()
		);

		String header = String.format(
			"| %-19s | %6s | %-30s | %-10s |",
			"Datum i vrijeme", "Ozn.", "Naziv aranžmana", "Status"
		);

		String line = "-".repeat(header.length());

		
		System.out.println(line);
		System.out.println(header);
		System.out.println(line);

		
		for (Rezervacija r : lista) {

			Aranzman a = sustav.dohvatiAranzman(r.getOznakaAranzmana());
			String nazivAranzmana = (a != null) ? a.getNaziv() : "-";

			System.out.printf(
				"| %-19s | %6d | %-30s | %-10s |%n",
				r.getZaprimljena().format(Format.DATUM_VRIJEME),
				r.getOznakaAranzmana(),
				nazivAranzmana,
				r.getStatus().naziv()  
			);
		}

		System.out.println(line);
	}
}
