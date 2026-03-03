package edu.unizg.foi.uzdiz.vvugrinec21.app.builder;


import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;

public interface RezervacijaBuilder {

	void reset();

	void ime(String ime);
	void prezime(String prezime);
	void oznakaAranzmana(int oznaka);
	void vrijemeZaprimanja(LocalDateTime vrijeme);

	Rezervacija build();
}

