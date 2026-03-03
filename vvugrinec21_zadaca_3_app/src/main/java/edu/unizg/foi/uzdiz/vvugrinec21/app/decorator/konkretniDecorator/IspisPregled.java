package edu.unizg.foi.uzdiz.vvugrinec21.app.decorator.konkretniDecorator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.decorator.IspisDecorator;
import edu.unizg.foi.uzdiz.vvugrinec21.app.decorator.komponenta.Ispis;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.AktivnaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.CekanjeRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.NovaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.OdgodenaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.OtkazanaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.PrimljenaRezervacija;

public class IspisPregled extends IspisDecorator {

    private final RezervacijskiSustav sustav;

    private static final DateTimeFormatter DT_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");

    public IspisPregled(Ispis wrappee, RezervacijskiSustav sustav) {
        super(wrappee);
        this.sustav = sustav;
    }

    @Override
    public void ispisi() {

       
        System.out.println("=== Pregled ISPISA ===");
        System.out.println("Vrijeme ispisa: " +
                LocalDateTime.now().format(DT_FORMAT));
        System.out.println("====================");

        super.ispisi();

        
        System.out.println();
        System.out.println("PROŠIRENI PREGLED ARANŽMANA I REZERVACIJA");

        String header = String.format(
            "| %-4s | %-25s | %-10s | %4s | %4s | %6s | %4s | %4s | %4s | %4s | %4s | %4s |",
            "Ozn", "Naziv", "Status",
            "Min", "Max",
            "Uk", "Nova", "Prim", "Akt", "Ček", "Odg", "Otk"
        );

        String line = "-".repeat(header.length());

        System.out.println(line);
        System.out.println(header);
        System.out.println(line);

        for (Aranzman a : sustav.sviAranzmani()) {

            int ukupno = 0;
            int nova = 0, prim = 0, akt = 0, cek = 0, odg = 0, otk = 0;

            for (Rezervacija r : a.getRezervacije()) {
                ukupno++;

                if (r.getStatus() instanceof NovaRezervacija) nova++;
                else if (r.getStatus() instanceof PrimljenaRezervacija) prim++;
                else if (r.getStatus() instanceof AktivnaRezervacija) akt++;
                else if (r.getStatus() instanceof CekanjeRezervacija) cek++;
                else if (r.getStatus() instanceof OdgodenaRezervacija) odg++;
                else if (r.getStatus() instanceof OtkazanaRezervacija) otk++;
            }

            System.out.printf(
                "| %-4d | %-25s | %-10s | %4d | %4d | %6d | %4d | %4d | %4d | %4d | %4d | %4d |%n",
                a.getOznaka(),
                skrati(a.getNaziv(), 25),
                a.getNazivStatusa(),
                a.getMinBrojPutnika(),
                a.getMaxBrojPutnika(),
                ukupno, nova, prim, akt, cek, odg, otk
            );
        }

        System.out.println(line);
    }

    private String skrati(String s, int max) {
        if (s == null) return "-";
        return s.length() <= max ? s : s.substring(0, max - 3) + "...";
    }
}
 