package edu.unizg.foi.uzdiz.vvugrinec21.lib;


class ProvjeraInt {

	void provjeri(String vrijednost) {
		if (vrijednost == null || vrijednost.isBlank())
			throw new IllegalArgumentException("Cijeli broj je prazan");

		Integer.parseInt(vrijednost.trim());
	}
}
