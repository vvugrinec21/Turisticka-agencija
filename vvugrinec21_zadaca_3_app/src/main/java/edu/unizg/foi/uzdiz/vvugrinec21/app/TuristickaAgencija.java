package edu.unizg.foi.uzdiz.vvugrinec21.app;

import edu.unizg.foi.uzdiz.vvugrinec21.app.builder.AranzmanBuilder;
import edu.unizg.foi.uzdiz.vvugrinec21.app.builder.RezervacijaBuilder;
import edu.unizg.foi.uzdiz.vvugrinec21.app.builder.KonkretniBuilder.KonkretniAranzmanBuilder;
import edu.unizg.foi.uzdiz.vvugrinec21.app.builder.KonkretniBuilder.KonkretniRezervacijaBuilder;
import edu.unizg.foi.uzdiz.vvugrinec21.app.builder.direktori.AranzmanDirector;
import edu.unizg.foi.uzdiz.vvugrinec21.app.builder.direktori.RezervacijaDirector;
import edu.unizg.foi.uzdiz.vvugrinec21.lib.UcitavanjePodatakaFacade;

public final class TuristickaAgencija {

	private static volatile TuristickaAgencija INSTANCE = new TuristickaAgencija();

	public RezervacijskiSustav sustav;
	public boolean running = true;

	private TuristickaAgencija() {}

	public static TuristickaAgencija getInstance() {
		if (INSTANCE == null) {
			synchronized (TuristickaAgencija.class) {
				if (INSTANCE == null)
					INSTANCE = new TuristickaAgencija();
			}
		}
		return INSTANCE;
	}

	public void inicijalizirajSustav(
			String datotekaAranzmani,
			String datotekaRezervacije
	) {
		System.out.println("Inicijalizacija turističke agencije...");

		sustav = new RezervacijskiSustav();

		try {
		
			UcitavanjePodatakaFacade facade = new UcitavanjePodatakaFacade();

			
			AranzmanBuilder ab = new KonkretniAranzmanBuilder();
			AranzmanDirector ad = new AranzmanDirector();

			RezervacijaBuilder rb = new KonkretniRezervacijaBuilder();
			RezervacijaDirector rd = new RezervacijaDirector();

		
			for (String[] r : facade.ucitajAranzmane(datotekaAranzmani)) {
				ad.izgradi(r, ab);
				sustav.dodajAranzman(ab.build());
			}

			
			int rbBrojac = 1;
			for (String[] r : facade.ucitajRezervacije(datotekaRezervacije)) {

				boolean ok = rd.izgradi(r, rb, rbBrojac);

				if (ok) {
					sustav.dodajRezervaciju(rb.build());
				}

				rbBrojac++;
			}

			System.out.println("Sustav uspješno inicijaliziran!");

		} catch (Exception e) {
			System.out.println("Greška pri inicijalizaciji: " + e.getMessage());
			running = false;
		}
	}
}
