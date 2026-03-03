package edu.unizg.foi.uzdiz.vvugrinec21.app.builder.KonkretniBuilder;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.vvugrinec21.app.builder.RezervacijaBuilder;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;

public class KonkretniRezervacijaBuilder implements RezervacijaBuilder {

	private String ime;
	private String prezime;
	private Integer oznakaAranzmana;
	private LocalDateTime vrijeme;

	@Override
	public void reset() {
		ime = null;
		prezime = null;
		oznakaAranzmana = null;
		vrijeme = null;
	}

	@Override
	public void ime(String ime) {
		this.ime = ime;
	}

	@Override
	public void prezime(String prezime) {
		this.prezime = prezime;
	}

	@Override
	public void oznakaAranzmana(int oznaka) {
		this.oznakaAranzmana = oznaka;
	}

	@Override
	public void vrijemeZaprimanja(LocalDateTime vrijeme) {
		this.vrijeme = vrijeme;
	}

	@Override
	public Rezervacija build() {
		return new Rezervacija(
			ime,
			prezime,
			oznakaAranzmana,
			vrijeme
		);
	}
}
