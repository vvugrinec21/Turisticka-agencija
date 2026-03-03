package edu.unizg.foi.uzdiz.vvugrinec21.lib;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class ProvjeraDatum {

	private static final DateTimeFormatter FORMAT =
		DateTimeFormatter.ofPattern("d.M.yyyy.");

	void provjeri(String vrijednost) {
		if (vrijednost == null || vrijednost.isBlank())
			throw new IllegalArgumentException("Datum je prazan");

		LocalDate.parse(vrijednost.trim(), FORMAT);
	}
}
