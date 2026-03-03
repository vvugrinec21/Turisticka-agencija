package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;

public class KomandaQ implements Komanda {
	
	private final String korisnickiUnos;
	private boolean ispravno;

	public KomandaQ(String unos) {
		this.korisnickiUnos = unos.trim();
		ispravno = korisnickiUnos.equalsIgnoreCase("Q");
	}

	@Override
	public boolean provjeriIspravnost() {
		return ispravno;
	}

	
	public String getKorisnickiUnos() {
		return korisnickiUnos;
	}
}
