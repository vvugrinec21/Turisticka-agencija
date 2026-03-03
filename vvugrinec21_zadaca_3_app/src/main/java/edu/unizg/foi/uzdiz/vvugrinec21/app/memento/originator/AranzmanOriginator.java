package edu.unizg.foi.uzdiz.vvugrinec21.app.memento.originator;

import java.util.List;
import java.util.stream.Collectors;

import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.AranzmanMemento;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;

public class AranzmanOriginator {

    private Aranzman aranzman;

    public AranzmanOriginator(Aranzman aranzman) {
        this.aranzman = aranzman;
    }

   
    public AranzmanMemento createMemento() {

        Aranzman kopijaAranzmana = new Aranzman(
                aranzman.getOznaka(),
                aranzman.getNaziv(),
                aranzman.getProgram(),
                aranzman.getPocetniDatum(),
                aranzman.getZavrsniDatum(),
                aranzman.getVrijemeKretanja(),
                aranzman.getVrijemePovratka(),
                aranzman.getCijena(),
                aranzman.getMinBrojPutnika(),
                aranzman.getMaxBrojPutnika(),
                aranzman.getBrojNocenja(),
                aranzman.getDoplataJednokrevetna(),
                aranzman.getPrijevoz(),
                aranzman.getBrojDorucaka(),
                aranzman.getBrojRuckova(),
                aranzman.getBrojVecera()
        );

        List<Rezervacija> kopijeRezervacija =
                aranzman.getRezervacije().stream()
                        .map(this::kopirajRezervaciju)
                        .collect(Collectors.toList());

        for (Rezervacija r : kopijeRezervacija) {
            kopijaAranzmana.dodajRezervaciju(r);
        }

        kopijaAranzmana.azurirajStatus();

        return new AranzmanMemento(
                aranzman.getOznaka(),
                kopijaAranzmana,
                kopijeRezervacija,
                aranzman.kopijaPromatraca()
        );
    }

    
    public void restore(AranzmanMemento memento) {
        this.aranzman = memento.getAranzmanKopija();
    }

    public Aranzman getAranzman() {
        return aranzman;
    }


    private Rezervacija kopirajRezervaciju(Rezervacija r) {
        Rezervacija kopija = new Rezervacija(
                r.getIme(),
                r.getPrezime(),
                r.getOznakaAranzmana(),
                r.getZaprimljena()
        );
        kopija.setStatus(r.getStatus());
        if (r.getVrijemeOtkaza() != null) {
            kopija.postaviVrijemeOtkaza(r.getVrijemeOtkaza());
        }
        return kopija;
    }
}
