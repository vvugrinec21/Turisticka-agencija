package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import java.time.LocalDate;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Format;

public class KomandaITAK implements Komanda {
	private boolean ispravno;
	private final String korisnickiUnos;
	private LocalDate datumOd;
	private LocalDate datumDo;
	private boolean imaDatume;

	public KomandaITAK(String korisnickiUnos) {
		this.korisnickiUnos = korisnickiUnos.trim();
		this.ispravno = provjeriIspravnost();
	}

	@Override
	public boolean provjeriIspravnost() {
		try {
			String[] dijelovi = korisnickiUnos.trim().split("\\s+");

			if (dijelovi.length == 1 && dijelovi[0].equalsIgnoreCase("ITAK")) {
				imaDatume = false;
				return true;
			}

			if (dijelovi.length == 3 && dijelovi[0].equalsIgnoreCase("ITAK")) {
				String odStr = normalizirajDatum(dijelovi[1]);
				String doStr = normalizirajDatum(dijelovi[2]);

				datumOd = Format.parseDatum(odStr);
				datumDo = Format.parseDatum(doStr);

				if (datumOd.isAfter(datumDo)) {
					return false;
				}

				imaDatume = true;
				return true;
			}

			return false;
		} catch (Exception e) {
			return false;
		}
	}

	private String normalizirajDatum(String unos) {
		String d = unos.trim().replaceAll("\\.\\.+", ".").replaceAll("[^0-9.]", "");
		if (!d.endsWith(".")) {
			d += ".";
		}
		return d;
	}

	public boolean isIspravno() {
		return ispravno;
	}

	public boolean imaDatume() {
		return imaDatume;
	}

	public LocalDate getDatumOd() {
		return datumOd;
	}

	public LocalDate getDatumDo() {
		return datumDo;
	}

	public String getKorisnickiUnos() {
		return korisnickiUnos;
	}
}
