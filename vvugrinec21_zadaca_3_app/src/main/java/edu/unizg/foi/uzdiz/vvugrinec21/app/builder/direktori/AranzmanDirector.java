package edu.unizg.foi.uzdiz.vvugrinec21.app.builder.direktori;

import edu.unizg.foi.uzdiz.vvugrinec21.app.builder.AranzmanBuilder;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Format;

public class AranzmanDirector {

	public void izgradi(String[] d, AranzmanBuilder b) {
		b.reset();
		b.oznaka(Integer.parseInt(d[0]));
		b.naziv(d[1]);
		b.program(d[2]);
		b.pocetak(Format.parseDatum(d[3]));
		b.zavrsetak(Format.parseDatum(d[4]));
		b.vrijemePolaska(Format.parseVrijeme(d[5]));
		b.vrijemePovratka(Format.parseVrijeme(d[6]));
		b.cijena(new java.math.BigDecimal(d[7]));
		b.minPutnika(Integer.parseInt(d[8]));
		b.maxPutnika(Integer.parseInt(d[9]));
	}
	
}
