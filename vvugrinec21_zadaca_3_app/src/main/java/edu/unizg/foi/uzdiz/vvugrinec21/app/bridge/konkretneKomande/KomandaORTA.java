package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;

public class KomandaORTA implements Komanda {

	private boolean ispravno;
	private final String korisnickiUnos;

	private String ime;
	private String prezime;
	private int oznaka;

	public KomandaORTA(String unos) {
		this.korisnickiUnos = unos.trim();
		this.ispravno = parsiraj(this.korisnickiUnos);
	}

	private boolean parsiraj(String unos) {

		String regex =
			"^ORTA\\s+([A-Za-zČĆŽŠĐčćžšđ\\-]+)\\s+" +
			"([A-Za-zČĆŽŠĐčćžšđ\\-]+)\\s+(\\d+)$";

		Matcher m = Pattern.compile(regex).matcher(unos);

		if (!m.matches()) {
			return false;
		}

		ime = m.group(1);
		prezime = m.group(2);
		oznaka = Integer.parseInt(m.group(3));

		return true;
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

	public int getOznaka() {
		return oznaka;
	}

	public String getKorisnickiUnos() {
		return korisnickiUnos;
	}
}
