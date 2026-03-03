package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;

public class KomandaITAP implements Komanda {

	private final String korisnickiUnos;   
	private boolean ispravno;
	private int oznaka;

	public KomandaITAP(String unos) {
		this.korisnickiUnos = unos.trim();  
		this.ispravno = provjeri(this.korisnickiUnos);
	}

	private boolean provjeri(String unos) {
		Matcher m = Pattern.compile("^ITAP\\s+(\\d+)$").matcher(unos);
		if (m.matches()) {
			oznaka = Integer.parseInt(m.group(1));
			return true;
		}
		return false;
	}

	@Override
	public boolean provjeriIspravnost() {
		return ispravno;
	}

	@Override
	public String getKorisnickiUnos() {    
		return korisnickiUnos;
	}

	public int getOznaka() {
		return oznaka;
	}
}
