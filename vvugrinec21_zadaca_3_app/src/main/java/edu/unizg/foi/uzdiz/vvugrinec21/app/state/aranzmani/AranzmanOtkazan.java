package edu.unizg.foi.uzdiz.vvugrinec21.app.state.aranzmani;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;

public class AranzmanOtkazan implements StatusAranzmana {

	@Override
	public void azurirajStatus(Aranzman a) {
		
	}

	@Override
	public String naziv() {
		return "OTKAZAN";
	}
}
