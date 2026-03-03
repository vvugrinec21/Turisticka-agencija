package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;

public class KomandaIP implements Komanda {

	private final String korisnickiUnos;   

	private boolean ispravno;
	private char oznaka;

	public KomandaIP(String unos) {

		this.korisnickiUnos = unos.trim();   

		Matcher m = Pattern.compile("^IP\\s+([NS])$").matcher(unos.trim());
		if (m.matches()) {
			oznaka = m.group(1).charAt(0);
			ispravno = true;
		} else {
			ispravno = false;
		}
	}

	@Override
	public boolean provjeriIspravnost() {
		return ispravno;
	}

	public char getOznaka() {
		return oznaka;
	}

	
	public String getKorisnickiUnos() {
		return korisnickiUnos;
	}
}
