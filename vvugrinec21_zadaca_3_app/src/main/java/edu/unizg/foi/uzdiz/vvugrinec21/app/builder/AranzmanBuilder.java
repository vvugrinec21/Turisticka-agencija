package edu.unizg.foi.uzdiz.vvugrinec21.app.builder;

import java.math.BigDecimal;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;

public interface AranzmanBuilder {

	void reset();

	void oznaka(int oznaka);
	void naziv(String naziv);
	void program(String program);
	void pocetak(java.time.LocalDate datum);
	void zavrsetak(java.time.LocalDate datum);
	void vrijemePolaska(java.time.LocalTime vrijeme);
	void vrijemePovratka(java.time.LocalTime vrijeme);
	void cijena(java.math.BigDecimal cijena);
	void minPutnika(int min);
	void maxPutnika(int max);
	void brojNocenja(Integer v);
	void doplataJednokrevetna(BigDecimal v);
	void prijevoz(String v);
	void brojDorucaka(Integer v);
	void brojRuckova(Integer v);
	void brojVecera(Integer v);
	
	
	Aranzman build();
	
}
