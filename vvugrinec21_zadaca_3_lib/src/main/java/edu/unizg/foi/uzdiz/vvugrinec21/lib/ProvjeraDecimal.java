package edu.unizg.foi.uzdiz.vvugrinec21.lib;

import java.math.BigDecimal;

class ProvjeraDecimal {

	void provjeri(String vrijednost) {
		if (vrijednost == null || vrijednost.isBlank())
			return; 

		new BigDecimal(vrijednost.trim());
	}
}

