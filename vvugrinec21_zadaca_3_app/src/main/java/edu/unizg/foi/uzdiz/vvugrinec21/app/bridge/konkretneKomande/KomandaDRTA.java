package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;

public class KomandaDRTA implements Komanda {

	private final String korisnickiUnos;
	private boolean ispravno;

	private String ime;
	private String prezime;
	private int oznaka;
	private String datumVrijeme;

	public KomandaDRTA(String unos) {

		this.korisnickiUnos = unos.trim();
		unos = unos.trim();

		String regex =
			"^DRTA\\s+" +
			"([A-Za-zČĆŽŠĐčćžšđ\\-]+)\\s+" +        
			"([A-Za-zČĆŽŠĐčćžšđ\\-]+)\\s+" +       
			"(\\d+)\\s+" +                        
			"(\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\.?" +   
			"\\s+" +
			"\\d{1,2}:\\d{2}(:\\d{2})?)$";          

		Matcher m = Pattern.compile(regex).matcher(unos);

		if (!m.matches()) {
			ispravno = false;
			return;
		}

		ime = m.group(1);
		prezime = m.group(2);
		oznaka = Integer.parseInt(m.group(3));

		datumVrijeme = m.group(4)
			.trim()
			.replaceAll("\\s+", " ")
			.replaceAll("(\\d{4})\\.", "$1");

		ispravno = true;
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

	public String getDatumVrijeme() {
		return datumVrijeme;
	}

	public String getKorisnickiUnos() {
		return korisnickiUnos;
	}
}
