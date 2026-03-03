package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;

public class KomandaIRTA implements Komanda {

	private final String korisnickiUnos;
	private boolean ispravno;
	private int oznaka;
	private String filter;

	public KomandaIRTA(String unos) {
		this.korisnickiUnos = unos.trim();

		Matcher m = Pattern.compile(
				"^IRTA\\s+(\\d+)(\\s+([PAČOD]+))?$",
				Pattern.CASE_INSENSITIVE
		).matcher(this.korisnickiUnos);

		if (m.matches()) {
			this.oznaka = Integer.parseInt(m.group(1));
			this.filter = (m.group(3) != null)
					? m.group(3).toUpperCase()
					: "PAČO";   
			this.ispravno = true;
		} else {
			this.ispravno = false;
		}
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

	public String getFilter() {
		return filter;
	}
}
