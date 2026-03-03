package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;

public class KomandaUP implements Komanda {

	private boolean ispravno;
	private char tip;             
	private String nazivDatoteke;
	private final String korisnickiUnos;

	public KomandaUP(String unos) {
		this.korisnickiUnos = unos.trim();

		Matcher m = Pattern
			.compile("^UP\\s+([AR])\\s+(.+)$", Pattern.CASE_INSENSITIVE)
			.matcher(this.korisnickiUnos);

		if (m.matches()) {
			tip = Character.toUpperCase(m.group(1).charAt(0));
			nazivDatoteke = m.group(2).trim();
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

	public String getNazivDatoteke() {
		return nazivDatoteke;
	}

	public String getKorisnickiUnos() {
		return korisnickiUnos;
	}
}
