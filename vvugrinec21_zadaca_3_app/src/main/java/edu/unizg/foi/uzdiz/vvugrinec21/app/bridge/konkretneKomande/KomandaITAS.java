package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande;

import java.time.LocalDate;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Komanda;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Format;

public class KomandaITAS implements Komanda {

	private boolean ispravno;
	private final String korisnickiUnos;
	private LocalDate od;
	private LocalDate doo;
	private boolean imaRazdoblje;

	public KomandaITAS(String unos) {
		this.korisnickiUnos = unos.trim();
		this.ispravno = provjeri();
	}

	private boolean provjeri() {
		try {
			String[] d = korisnickiUnos.split("\\s+");

			if (d.length == 1 && d[0].equalsIgnoreCase("ITAS")) {
				imaRazdoblje = false;
				return true;
			}

			if (d.length == 3 && d[0].equalsIgnoreCase("ITAS")) {
				od = Format.parseDatum(d[1]);
				doo = Format.parseDatum(d[2]);

				if (od.isAfter(doo)) return false;

				imaRazdoblje = true;
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean provjeriIspravnost() {
		return ispravno;
	}

	public boolean imaRazdoblje() {
		return imaRazdoblje;
	}

	public LocalDate getOd() {
		return od;
	}

	public LocalDate getDo() {
		return doo;
	}

	public String getKorisnickiUnos() {
		return korisnickiUnos;
	}
}
