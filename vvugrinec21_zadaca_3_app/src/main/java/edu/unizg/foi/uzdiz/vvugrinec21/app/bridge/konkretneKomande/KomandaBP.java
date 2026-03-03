package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;

public class KomandaBP implements Komanda {

	private boolean ispravno;
	private char tip; 
	private final String korisnickiUnos;

	public KomandaBP(String unos) {
		this.korisnickiUnos = unos.trim();

		Matcher m = Pattern
			.compile("^BP\\s+([AR])$", Pattern.CASE_INSENSITIVE)
			.matcher(this.korisnickiUnos);

		if (m.matches()) {
			tip = Character.toUpperCase(m.group(1).charAt(0));
			ispravno = true;
		} else {
			ispravno = false;
		}
	}

	@Override
	public boolean provjeriIspravnost() {
		return ispravno;
	}

	public char getTip() {
		return tip;
	}

	public String getKorisnickiUnos() {
		return korisnickiUnos;
	}
}
