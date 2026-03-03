package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaITAK;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Format;
import edu.unizg.foi.uzdiz.vvugrinec21.app.pomocnici.FormatIspisa;
import edu.unizg.foi.uzdiz.vvugrinec21.app.pomocnici.IspisTablica;

public class ObradiDogadajITAK extends Aktivnost {

	private final RezervacijskiSustav sustav;

	public ObradiDogadajITAK(RezervacijskiSustav sustav, KomandaITAK komanda) {
		super(komanda);
		this.sustav = sustav;
	}

	/*
	 * @Override public void izvrsiAktivnost() {
	 * 
	 * KomandaITAK cmd = (KomandaITAK) komanda;
	 * 
	 * if (!cmd.isIspravno()) { System.out.println("Neispravna komanda ITAK.");
	 * return; }
	 * 
	 * 
	 * IspisTablica.ispisiKomandu(cmd.getKorisnickiUnos());
	 * 
	 * Collection<Aranzman> svi = sustav.sviAranzmani();
	 * 
	 * List<Aranzman> filtrirani = svi.stream() .filter(a -> { if (cmd.imaDatume())
	 * { LocalDate od = cmd.getDatumOd(); LocalDate doo = cmd.getDatumDo(); return
	 * !(a.getZavrsniDatum().isBefore(od) || a.getPocetniDatum().isAfter(doo)); }
	 * return true; }) .collect(Collectors.toList());
	 * 
	 * if (filtrirani.isEmpty()) {
	 * System.out.println("Nema aranžmana za zadano razdoblje."); return; }
	 * 
	 * 
	 * IspisTablica.ispisiNazivTablice("TABLICA: Popis turističkih aranžmana");
	 * 
	 * String header = String.format(
	 * "| %5s | %-60s | %-12s | %-12s | %-8s | %-8s | %12s | %6s | %6s | %-10s |",
	 * "Ozn.", "Naziv", "Početak", "Završetak", "Polazak", "Povratak", "Cijena",
	 * "Min", "Max", "Status" );
	 * 
	 * IspisTablica.crta(header.length()); System.out.println(header);
	 * IspisTablica.crta(header.length());
	 * 
	 * for (Aranzman a : filtrirani) {
	 * 
	 * String naziv = formatNaziv(a.getNaziv(), 60); String vk =
	 * a.getVrijemeKretanja() == null ? "-" :
	 * a.getVrijemeKretanja().format(Format.VRIJEME); String vp =
	 * a.getVrijemePovratka() == null ? "-" :
	 * a.getVrijemePovratka().format(Format.VRIJEME);
	 * 
	 * System.out.printf(
	 * "| %5s | %-60s | %-12s | %-12s | %-8s | %-8s | %12s | %6s | %6s | %-10s |%n",
	 * FormatIspisa.intFmt(a.getOznaka()), naziv,
	 * a.getPocetniDatum().format(Format.DATUM),
	 * a.getZavrsniDatum().format(Format.DATUM), vk, vp,
	 * FormatIspisa.dec2Fmt(a.getCijena()),
	 * FormatIspisa.intFmt(a.getMinBrojPutnika()),
	 * FormatIspisa.intFmt(a.getMaxBrojPutnika()), a.getNazivStatusa() ); }
	 * 
	 * IspisTablica.crta(header.length()); }
	 */
	
	
	private static final String HEADER_FORMAT =
		    "| %5s | %-60s | %-12s | %-12s | %-8s | %-8s | %12s | %6s | %6s | %-10s |";
	
	
	@Override
	public void izvrsiAktivnost() {

	    KomandaITAK cmd = (KomandaITAK) komanda;

	    if (!cmd.isIspravno()) {
	        ispisiGresku();
	        return;
	    }

	    IspisTablica.ispisiKomandu(cmd.getKorisnickiUnos());

	    List<Aranzman> aranzmani = filtrirajAranzmane(cmd);

	    if (aranzmani.isEmpty()) {
	        ispisiPraznuPoruku();
	        return;
	    }

	    ispisiZaglavlje();

	    ispisiAranzmane(aranzmani);

	    ispisiLiniju();
	}
	
	
	private List<Aranzman> filtrirajAranzmane(KomandaITAK cmd) {

	    return sustav.sviAranzmani().stream()
	        .filter(a -> !cmd.imaDatume() || uRazdoblju(a, cmd))
	        .collect(Collectors.toList());
	}

	private boolean uRazdoblju(Aranzman a, KomandaITAK cmd) {

	    LocalDate od = cmd.getDatumOd();
	    LocalDate doo = cmd.getDatumDo();

	    return !(a.getZavrsniDatum().isBefore(od)
	          || a.getPocetniDatum().isAfter(doo));
	}
	
	private void ispisiZaglavlje() {

	    IspisTablica.ispisiNazivTablice("TABLICA: Popis turističkih aranžmana");

	    String header = String.format(
	        HEADER_FORMAT,
	        "Ozn.", "Naziv", "Početak", "Završetak",
	        "Polazak", "Povratak", "Cijena", "Min", "Max", "Status"
	    );

	    IspisTablica.crta(header.length());
	    System.out.println(header);
	    IspisTablica.crta(header.length());
	}
	
	
	
	private void ispisiAranzmane(List<Aranzman> aranzmani) {

	    for (Aranzman a : aranzmani) {
	        ispisiRed(a);
	    }
	}
	
	private void ispisiRed(Aranzman a) {

	    String naziv = formatNaziv(a.getNaziv(), 60);

	    String vk = formatVrijeme(a.getVrijemeKretanja());
	    String vp = formatVrijeme(a.getVrijemePovratka());

	    System.out.printf(
	        "| %5s | %-60s | %-12s | %-12s | %-8s | %-8s | %12s | %6s | %6s | %-10s |%n",
	        FormatIspisa.intFmt(a.getOznaka()),
	        naziv,
	        a.getPocetniDatum().format(Format.DATUM),
	        a.getZavrsniDatum().format(Format.DATUM),
	        vk,
	        vp,
	        FormatIspisa.dec2Fmt(a.getCijena()),
	        FormatIspisa.intFmt(a.getMinBrojPutnika()),
	        FormatIspisa.intFmt(a.getMaxBrojPutnika()),
	        a.getNazivStatusa()
	    );
	}
	
	private String formatVrijeme(LocalTime vrijeme) {

	    return vrijeme == null
	        ? "-"
	        : vrijeme.format(Format.VRIJEME);
	}
	
	private void ispisiGresku() {
	    System.out.println("Neispravna komanda ITAK.");
	}

	private void ispisiPraznuPoruku() {
	    System.out.println("Nema aranžmana za zadano razdoblje.");
	}

	private void ispisiLiniju() {

	    String header = String.format(
	        HEADER_FORMAT,
	        "", "", "", "", "", "", "", "", "", ""
	    );

	    IspisTablica.crta(header.length());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String formatNaziv(String naziv, int width) {
		if (naziv == null) naziv = "-";
		naziv = naziv.trim();
		if (naziv.length() > width)
			return naziv.substring(0, width - 3) + "...";
		return String.format("%-" + width + "s", naziv);
	}
}
