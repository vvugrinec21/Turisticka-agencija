package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaQ;

public class ObradiDogadajQ extends Aktivnost {

	public ObradiDogadajQ(KomandaQ komanda) {
		super(komanda);
	}

	@Override
	public void izvrsiAktivnost() {

		KomandaQ cmd = (KomandaQ) komanda;

		
		System.out.println(cmd.getKorisnickiUnos());

		System.out.println("Zatvaranje turističke agencije...");
	}
}
