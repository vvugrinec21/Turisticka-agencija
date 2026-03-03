package edu.unizg.foi.uzdiz.vvugrinec21.app.builder.KonkretniBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import edu.unizg.foi.uzdiz.vvugrinec21.app.builder.AranzmanBuilder;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;

public class KonkretniAranzmanBuilder implements AranzmanBuilder {

	private Integer oznaka;
	private String naziv;
	private String program;
	private LocalDate pocetak;
	private LocalDate zavrsetak;
	private LocalTime polazak;
	private LocalTime povratak;
	private BigDecimal cijena;
	private Integer min;
	private Integer max;
	private Integer brojNocenja;
	private BigDecimal doplataJednokrevetna;
	private String prijevoz;
	private Integer brojDorucaka;
	private Integer brojRuckova;
	private Integer brojVecera;

	@Override
	public void reset() {
		oznaka = null;
		naziv = null;
		program = null;
		pocetak = null;
		zavrsetak = null;
		polazak = null;
		povratak = null;
		cijena = null;
		min = null;
		max = null;
		brojNocenja = null;
		doplataJednokrevetna = null;
		prijevoz = null;
		brojDorucaka = null;
		brojRuckova = null;
		brojVecera = null;
	}

	

	@Override public void oznaka(int v) { oznaka = v; }
	@Override public void naziv(String v) { naziv = v; }
	@Override public void program(String v) { program = v; }
	@Override public void pocetak(LocalDate v) { pocetak = v; }
	@Override public void zavrsetak(LocalDate v) { zavrsetak = v; }
	@Override public void vrijemePolaska(LocalTime v) { polazak = v; }
	@Override public void vrijemePovratka(LocalTime v) { povratak = v; }
	@Override public void cijena(BigDecimal v) { cijena = v; }
	@Override public void minPutnika(int v) { min = v; }
	@Override public void maxPutnika(int v) { max = v; }

	@Override public void brojNocenja(Integer v) { brojNocenja = v; }
	@Override public void doplataJednokrevetna(BigDecimal v) { doplataJednokrevetna = v; }
	@Override public void prijevoz(String v) { prijevoz = v; }
	@Override public void brojDorucaka(Integer v) { brojDorucaka = v; }
	@Override public void brojRuckova(Integer v) { brojRuckova = v; }
	@Override public void brojVecera(Integer v) { brojVecera = v; }
	
	
	@Override
	public Aranzman build() {
	    return new Aranzman(
	        oznaka,
	        naziv,
	        program,
	        pocetak,
	        zavrsetak,
	        polazak,
	        povratak,
	        cijena,
	        min,
	        max,
	        brojNocenja,
	        doplataJednokrevetna,
	        prijevoz,
	        brojDorucaka,
	        brojRuckova,
	        brojVecera
	    );
	}
}