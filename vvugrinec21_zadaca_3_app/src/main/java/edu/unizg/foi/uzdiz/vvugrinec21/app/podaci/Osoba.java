package edu.unizg.foi.uzdiz.vvugrinec21.app.podaci;

import java.util.Objects;

public class Osoba {
	private String ime;
	private String prezime;

	public Osoba(String ime, String prezime) {
		this.ime = ime;
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Osoba))
			return false;
		Osoba osoba = (Osoba) o;
		return ime.equals(osoba.ime) && prezime.equals(osoba.prezime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ime, prezime);
	}

	@Override
	public String toString() {
		return ime + " " + prezime;
	}
}
