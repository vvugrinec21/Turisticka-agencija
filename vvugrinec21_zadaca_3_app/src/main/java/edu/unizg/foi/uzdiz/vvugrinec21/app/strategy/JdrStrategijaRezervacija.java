package edu.unizg.foi.uzdiz.vvugrinec21.app.strategy;

import java.util.Comparator;
import java.util.List;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.AktivnaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.OdgodenaRezervacija;

public class JdrStrategijaRezervacija implements StrategijaRezervacija {

	@Override
	public void primijeni(Rezervacija nova, RezervacijskiSustav sustav) {

	    List<Rezervacija> presjek = sustav.dohvatiRezervacijeOsobe(nova.getOsobaKey())
	        .stream()
	        .filter(r -> sustav.preklapanje(r, nova))
	        .sorted(Comparator.comparing(Rezervacija::getZaprimljena))
	        .toList();

	    if (presjek.isEmpty()) return;

	    Rezervacija prva = presjek.get(0);
	    prva.setOdgodenaStrategijom(false);

	    presjek.stream()
	        .skip(1)
	        .forEach(r -> r.setOdgodenaStrategijom(true));
	}
}
