package edu.unizg.foi.uzdiz.vvugrinec21.lib;

import java.util.ArrayList;
import java.util.List;

public class UcitavanjePodatakaFacade {

	private final ProvjeraInt provjeraInt = new ProvjeraInt();
	private final ProvjeraDatum provjeraDatum = new ProvjeraDatum();
	private final ProvjeraDecimal provjeraDecimal = new ProvjeraDecimal();
	private final ProvjeraVrijeme provjeraVrijeme = new ProvjeraVrijeme();
	private final IspisGresaka ispisGresaka = new IspisGresaka();

	

	public List<String[]> ucitajAranzmane(String datoteka) {
		return ucitaj(datoteka, 16, TipZapisa.ARANZMAN);
	}

	public List<String[]> ucitajRezervacije(String datoteka) {
		return ucitaj(datoteka, 4, TipZapisa.REZERVACIJA);
	}

	

	private List<String[]> ucitaj(String datoteka, int ocekivaniStupci, TipZapisa tip) {

		List<String[]> ispravni = new ArrayList<>();
		List<String> linije = CitacDatoteka.procitajZapise(datoteka);

		int redak = 1;

		for (String linija : linije) {

			List<String> greske = new ArrayList<>();

			
			String[] stupci =
				linija.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

			for (int i = 0; i < stupci.length; i++) {
				stupci[i] = stupci[i].replace("\"", "").trim();
			}

			
			if (stupci.length != ocekivaniStupci) {
				greske.add(
					"Neispravan broj stupaca (očekivano "
					+ ocekivaniStupci + ", dobiveno " + stupci.length + ")"
				);
			}

			

			if (tip == TipZapisa.ARANZMAN && stupci.length == 16) {

				
				try {
					provjeraInt.provjeri(stupci[0]);
				} catch (Exception e) {
					greske.add("Oznaka nije cijeli broj");
				}

				if (stupci[1].isBlank()) {
					greske.add("Naziv ne smije biti prazan");
				}

				try {
					provjeraDatum.provjeri(stupci[3]);
				} catch (Exception e) {
					greske.add("Neispravan početni datum");
				}

				try {
					provjeraDatum.provjeri(stupci[4]);
				} catch (Exception e) {
					greske.add("Neispravan završni datum");
				}

				try {
					provjeraDecimal.provjeri(stupci[7]);
				} catch (Exception e) {
					greske.add("Cijena nije decimalan broj");
				}

				try {
					provjeraInt.provjeri(stupci[8]);
				} catch (Exception e) {
					greske.add("Min broj putnika nije cijeli broj");
				}

				try {
					provjeraInt.provjeri(stupci[9]);
				} catch (Exception e) {
					greske.add("Max broj putnika nije cijeli broj");
				}

				
				try {
					provjeraVrijeme.provjeri(stupci[5]);
				} catch (Exception e) {
					greske.add("Neispravno vrijeme polaska");
				}

				try {
					provjeraVrijeme.provjeri(stupci[6]);
				} catch (Exception e) {
					greske.add("Neispravno vrijeme povratka");
				}

				try {
					if (!stupci[10].isBlank())
						provjeraInt.provjeri(stupci[10]);
				} catch (Exception e) {
					greske.add("Broj noćenja nije cijeli broj");
				}

				try {
					if (!stupci[11].isBlank())
						provjeraDecimal.provjeri(stupci[11]);
				} catch (Exception e) {
					greske.add("Doplata jednokrevetna nije decimalan broj");
				}

				try {
					if (!stupci[13].isBlank())
						provjeraInt.provjeri(stupci[13]);
				} catch (Exception e) {
					greske.add("Broj doručaka nije cijeli broj");
				}

				try {
					if (!stupci[14].isBlank())
						provjeraInt.provjeri(stupci[14]);
				} catch (Exception e) {
					greske.add("Broj ručkova nije cijeli broj");
				}

				try {
					if (!stupci[15].isBlank())
						provjeraInt.provjeri(stupci[15]);
				} catch (Exception e) {
					greske.add("Broj večera nije cijeli broj");
				}
			}

			if (tip == TipZapisa.REZERVACIJA && stupci.length == 4) {

				try {
					provjeraInt.provjeri(stupci[2]);
				} catch (Exception e) {
					greske.add("Oznaka aranžmana nije cijeli broj");
				}

				try {
					ProvjeraDatumVrijeme.provjeri(stupci[3]);
				} catch (Exception e) {
					greske.add("Neispravan datum i vrijeme");
				}
			}

			
			if (greske.isEmpty()) {
				ispravni.add(stupci);
			} else {
				ispisGresaka.ispisi(redak, linija, greske);
			}

			redak++;
		}

		return ispravni;
	}
}
