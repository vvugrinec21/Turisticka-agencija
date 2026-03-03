package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;

public class KomandaIRO implements Komanda {

	private boolean ispravno;
	private String ime;
	private String prezime;
	private final String korisnickiUnos;

	public KomandaIRO(String unos) {
		this.korisnickiUnos = unos.trim();

		Matcher m = Pattern.compile(
			"^IRO\\s+([A-Za-zČĆŽŠĐčćžšđ\\-]+)\\s+([A-Za-zČĆŽŠĐčćžšđ\\-]+)$"
		).matcher(this.korisnickiUnos);

		if (m.matches()) {
			ime = m.group(1);
			prezime = m.group(2);
			ispravno = true;
		} else {
			ispravno = false;
		}
	}

	@Override
	public boolean provjeriIspravnost() {
		return ispravno;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	
	public String getKorisnickiUnos() {
		return korisnickiUnos;
	}
}
