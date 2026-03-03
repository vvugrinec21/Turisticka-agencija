package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge;

public abstract class Aktivnost {
	protected Komanda komanda;

	public Aktivnost(Komanda komanda) {
		this.komanda = komanda;
	}

	public abstract void izvrsiAktivnost();
}