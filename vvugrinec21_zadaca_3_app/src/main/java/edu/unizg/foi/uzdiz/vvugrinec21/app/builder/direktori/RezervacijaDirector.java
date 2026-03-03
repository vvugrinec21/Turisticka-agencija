package edu.unizg.foi.uzdiz.vvugrinec21.app.builder.direktori;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import edu.unizg.foi.uzdiz.vvugrinec21.app.builder.RezervacijaBuilder;

public class RezervacijaDirector {

	
	private static final DateTimeFormatter FORMAT =
		DateTimeFormatter.ofPattern("d.M.yyyy H:mm[:ss]");

	
	public boolean izgradi(String[] d, RezervacijaBuilder b, int rb) {

		try {
			b.reset();
			b.ime(d[0].trim());
			b.prezime(d[1].trim());
			b.oznakaAranzmana(Integer.parseInt(d[2].trim()));

			String normalizirano = normalizirajDatumVrijeme(d[3]);
			LocalDateTime dt = LocalDateTime.parse(normalizirano, FORMAT);

			b.vrijemeZaprimanja(dt);
			return true;

		} catch (NumberFormatException | DateTimeParseException | ArrayIndexOutOfBoundsException e) {

			System.out.println(
				"Greška u retku " + rb + ": " + String.join(", ", d)
			);
			System.out.println("  → Neispravan datum i vrijeme");
			return false;
		}
	}

	

	public void izgradi(
		String ime,
		String prezime,
		int oznaka,
		LocalDateTime datumVrijeme,
		RezervacijaBuilder b
	) {
		b.reset();
		b.ime(ime);
		b.prezime(prezime);
		b.oznakaAranzmana(oznaka);
		b.vrijemeZaprimanja(datumVrijeme);
	}

	

	private String normalizirajDatumVrijeme(String s) {
	    return s.trim()
	        
	        .replaceAll("\\b(\\d)\\.", "0$1.")
	        .replaceAll("\\.(\\d)\\.", ".0$1.")
	        
	        .replaceAll("(\\d{4})\\.", "$1")
	        
	        .replaceAll("\\s+", " ");
	}


}
