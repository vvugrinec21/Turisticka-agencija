package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;

public class KomandaOTA implements Komanda {

	private final String korisnickiUnos;  

	private boolean ispravno;
	private int oznaka;

	public KomandaOTA(String unos) {

		this.korisnickiUnos = unos.trim(); 

		Matcher m = Pattern.compile("^OTA\\s+(\\d+)$").matcher(unos.trim());
		if (m.matches()) {
			oznaka = Integer.parseInt(m.group(1));
			ispravno = true;
		} else {
			ispravno = false;
		}
	}

	@Override
	public boolean provjeriIspravnost() {
		return ispravno;
	}

	public int getOznaka() {
		return oznaka;
	}

	
	public String getKorisnickiUnos() {
		return korisnickiUnos;
	}
}
