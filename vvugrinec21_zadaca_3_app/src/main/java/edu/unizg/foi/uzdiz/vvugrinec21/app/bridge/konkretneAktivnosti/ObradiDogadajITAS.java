package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import edu.unizg.foi.uzdiz.vvugrinec21.app.NacinIspisa;
import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaITAS;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.*;

public class ObradiDogadajITAS extends Aktivnost {

	private final RezervacijskiSustav sustav;

	public ObradiDogadajITAS(RezervacijskiSustav sustav, KomandaITAS komanda) {
		super(komanda);
		this.sustav = sustav;
	}

	private static final String HEADER_FORMAT =
		    "| %-5s | %-40s | %10s | %10s | %10s | %10s | %10s | %15s |";
	
	@Override
	public void izvrsiAktivnost() {

	    KomandaITAS cmd = (KomandaITAS) komanda;

	    if (!cmd.provjeriIspravnost()) {
	        ispisiGresku();
	        return;
	    }

	    System.out.println(cmd.getKorisnickiUnos());

	    List<Aranzman> aranzmani = filtrirajAranzmane(cmd);

	    if (aranzmani.isEmpty()) {
	        ispisiPraznuPoruku();
	        return;
	    }

	    sortirajAranzmane(aranzmani);

	    ispisiZaglavlje();

	    ispisiStatistiku(aranzmani);

	    ispisiLiniju();
	}
	
	
	private List<Aranzman> filtrirajAranzmane(KomandaITAS cmd) {

	    return sustav.sviAranzmani().stream()
	        .filter(a -> !cmd.imaRazdoblje() || uRazdoblju(a, cmd))
	        .collect(Collectors.toList());
	}

	private boolean uRazdoblju(Aranzman a, KomandaITAS cmd) {

	    LocalDate od = cmd.getOd();
	    LocalDate doo = cmd.getDo();

	    return !(a.getZavrsniDatum().isBefore(od)
	          || a.getPocetniDatum().isAfter(doo));
	}
	
	private void sortirajAranzmane(List<Aranzman> aranzmani) {

	    Comparator<Aranzman> cmp = Comparator.comparing(Aranzman::getOznaka);

	    if (sustav.getNacinIspisa() == NacinIspisa.S) {
	        cmp = cmp.reversed();
	    }

	    aranzmani.sort(cmp);
	}
	
	private void ispisiZaglavlje() {

	    System.out.println("Statistika turističkih aranžmana");

	    String header = String.format(
	        HEADER_FORMAT,
	        "Ozn.", "Naziv", "Ukupno", "Aktivne",
	        "Čekanje", "Odgođene", "Otkazane", "Prihod"
	    );

	    ispisiCrta(header.length());
	    System.out.println(header);
	    ispisiCrta(header.length());
	}
	
	private void ispisiStatistiku(List<Aranzman> aranzmani) {

	    DecimalFormat df = new DecimalFormat("#,##0.00");

	    for (Aranzman a : aranzmani) {

	        List<Rezervacija> rez = a.getRezervacije();

	        long ukupno = rez.size();
	        long aktivne = broj(rez, AktivnaRezervacija.class);
	        long cekanje = broj(rez, CekanjeRezervacija.class);
	        long odgodene = broj(rez, OdgodenaRezervacija.class);
	        long otkazane = broj(rez, OtkazanaRezervacija.class);

	        BigDecimal prihod =
	            a.getCijena().multiply(BigDecimal.valueOf(aktivne));

	        ispisiRed(a, ukupno, aktivne, cekanje, odgodene, otkazane, prihod, df);
	    }
	}
	
	private long broj(List<Rezervacija> rez, Class<?> status) {

	    return rez.stream()
	        .filter(r -> status.isInstance(r.getStatus()))
	        .count();
	}
	
	private void ispisiRed(
		    Aranzman a,
		    long ukupno,
		    long aktivne,
		    long cekanje,
		    long odgodene,
		    long otkazane,
		    BigDecimal prihod,
		    DecimalFormat df
		) {
		    System.out.printf(
		        "| %-5d | %-40s | %10d | %10d | %10d | %10d | %10d | %15s |%n",
		        a.getOznaka(),
		        skrati(a.getNaziv(), 40),
		        ukupno,
		        aktivne,
		        cekanje,
		        odgodene,
		        otkazane,
		        df.format(prihod)
		    );
		}
	
	private void ispisiGresku() {
	    System.out.println("Neispravna komanda ITAS.");
	}

	private void ispisiPraznuPoruku() {
	    System.out.println("Nema aranžmana za zadano razdoblje.");
	}

	private void ispisiLiniju() {

	    String dummyHeader = String.format(
	        HEADER_FORMAT,
	        "", "", "", "", "", "", "", ""
	    );

	    ispisiCrta(dummyHeader.length());
	}
	
	private void ispisiCrta(int duljina) {
	    System.out.println("-".repeat(duljina));
	}
	
	private String skrati(String s, int max) {
		if (s == null) return "-";
		if (s.length() <= max) return s;
		return s.substring(0, max - 3) + "...";
	}
}
