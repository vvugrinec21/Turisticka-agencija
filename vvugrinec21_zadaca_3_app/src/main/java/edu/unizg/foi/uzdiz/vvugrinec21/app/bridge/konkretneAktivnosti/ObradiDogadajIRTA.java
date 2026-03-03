package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import edu.unizg.foi.uzdiz.vvugrinec21.app.NacinIspisa;
import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaIRTA;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Format;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.*;

public class ObradiDogadajIRTA extends Aktivnost {

    private final RezervacijskiSustav sustav;

    public ObradiDogadajIRTA(RezervacijskiSustav sustav, KomandaIRTA komanda) {
        super(komanda);
        this.sustav = sustav;
    }

    @Override
    public void izvrsiAktivnost() {

        KomandaIRTA cmd = (KomandaIRTA) komanda;

        if (!cmd.provjeriIspravnost()) {
            System.out.println("Neispravna komanda IRTA.");
            return;
        }

      
        System.out.println(cmd.getKorisnickiUnos());

        int oznaka = cmd.getOznaka();
        String filter = cmd.getFilter(); 

        List<Rezervacija> sveRez = sustav.rezervacijeZaAranzman(oznaka);
        if (sveRez.isEmpty()) {
            System.out.println("Nema rezervacija za aranžman " + oznaka);
            return;
        }

       
        List<Rezervacija> filtrirane = sveRez.stream()
                .filter(r -> dozvoljenStatus(r, filter))
                .collect(Collectors.toList());

        if (filtrirane.isEmpty()) {
            System.out.println("Nema rezervacija koje odgovaraju filteru " + filter);
            return;
        }

        
        Comparator<Rezervacija> cmp =
                Comparator.comparing(Rezervacija::getZaprimljena);

        if (sustav.getNacinIspisa() == NacinIspisa.S) {
            cmp = cmp.reversed();
        }

        filtrirane = filtrirane.stream()
                .sorted(cmp)
                .collect(Collectors.toList());

        
        System.out.println("Pregled rezervacija za turistički aranžman "
                + oznaka + " (" + filter + ")");

        boolean ispisOtkaza = filter.contains("O");

        if (ispisOtkaza) {
            ispisiSTimenomOtkaza(filtrirane);
        } else {
            ispisiBezVremenaOtkaza(filtrirane);
        }
    }

  

    private boolean dozvoljenStatus(Rezervacija r, String filter) {
    	
    	if (filter == null || filter.isBlank() || filter.equals("PAČO")) {
            return true;
        }
    	
        
        if (filter.contains("PA")) {
            if (r.getStatus() instanceof PrimljenaRezervacija) return true;
            if (r.getStatus() instanceof AktivnaRezervacija) return true;
        }

        
        if (filter.contains("Č")) {
            if (r.getStatus() instanceof CekanjeRezervacija) return true;
        }

        
        if (filter.contains("OD")) {
            if (r.getStatus() instanceof OdgodenaRezervacija) return true;
        }

        
        if (filter.contains("O")) {
            if (r.getStatus() instanceof OtkazanaRezervacija) return true;
        }

        return false;
    }

    private void ispisiSTimenomOtkaza(List<Rezervacija> rezervacije) {

        String header = String.format(
                "| %-15s | %-15s | %-19s | %-10s | %-19s |",
                "Ime", "Prezime", "Datum i vrijeme", "Status", "Vrijeme otkaza"
        );
        String line = "-".repeat(header.length());

        System.out.println(line);
        System.out.println(header);
        System.out.println(line);

        for (Rezervacija r : rezervacije) {
            System.out.printf(
                    "| %-15s | %-15s | %-19s | %-10s | %-19s |%n",
                    r.getIme(),
                    r.getPrezime(),
                    r.getZaprimljena().format(Format.DATUM_VRIJEME),
                    r.getStatus().naziv(),
                    r.getVrijemeOtkaza() != null
                            ? r.getVrijemeOtkaza().format(Format.DATUM_VRIJEME)
                            : "-"
            );
        }

        System.out.println(line);
    }

    private void ispisiBezVremenaOtkaza(List<Rezervacija> rezervacije) {

        String header = String.format(
                "| %-15s | %-15s | %-19s | %-10s |",
                "Ime", "Prezime", "Datum i vrijeme", "Status"
        );
        String line = "-".repeat(header.length());

        System.out.println(line);
        System.out.println(header);
        System.out.println(line);

        for (Rezervacija r : rezervacije) {
            System.out.printf(
                    "| %-15s | %-15s | %-19s | %-10s |%n",
                    r.getIme(),
                    r.getPrezime(),
                    r.getZaprimljena().format(Format.DATUM_VRIJEME),
                    r.getStatus().naziv()
            );
        }

        System.out.println(line);
    }
}
