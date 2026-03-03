package edu.unizg.foi.uzdiz.vvugrinec21.app.strategy;

import java.util.Comparator;
import java.util.List;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.AktivnaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.OdgodenaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.OtkazanaRezervacija;

public class VdrStrategijaRezervacija implements StrategijaRezervacija {


	@Override
	public void primijeni(Rezervacija nova, RezervacijskiSustav sustav) {

	    Aranzman a = sustav.dohvatiAranzman(nova.getOznakaAranzmana());
	    if (a == null) return;

	    int limit = Math.max(1, a.getMaxBrojPutnika() / 4);

	
	    List<Rezervacija> iste =
	        sustav.dohvatiRezervacijeOsobe(nova.getOsobaKey()).stream()
	            .filter(r -> r.getOznakaAranzmana() == nova.getOznakaAranzmana())
	            .filter(r -> !(r.getStatus() instanceof OtkazanaRezervacija))
	            .sorted(Comparator.comparing(Rezervacija::getZaprimljena))
	            .toList();

	
	    for (int i = 0; i < iste.size(); i++) {
	        iste.get(i).setOdgodenaStrategijom(i >= limit);
	    }
	}
	
}
