package edu.unizg.foi.uzdiz.vvugrinec21.lib;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


class ProvjeraVrijeme {

	private static final DateTimeFormatter FORMAT =
		    DateTimeFormatter.ofPattern("H:mm[:ss]");;

	void provjeri(String vrijednost) {
		if (vrijednost == null || vrijednost.isBlank())
			return; 

		LocalTime.parse(vrijednost.trim(), FORMAT);
	}
}
