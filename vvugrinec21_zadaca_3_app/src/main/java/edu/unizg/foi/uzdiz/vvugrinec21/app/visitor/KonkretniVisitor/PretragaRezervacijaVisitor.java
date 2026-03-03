package edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.KonkretniVisitor;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.PretrazivacVisitor;

public class PretragaRezervacijaVisitor implements PretrazivacVisitor {

    private final String trazenaRijec;

    public PretragaRezervacijaVisitor(String trazenaRijec) {
        this.trazenaRijec = trazenaRijec;
    }

    @Override
    public void visit(Rezervacija r) {
        if (r.getIme().contains(trazenaRijec)
                || r.getPrezime().contains(trazenaRijec)) {

            System.out.println(
                r.getIme() + " " +
                r.getPrezime() +
                " | aranžman: " +
                r.getOznakaAranzmana() +
                " | status: " +
                r.getNazivStatusa()
            );
        }
    }

    @Override
    public void visit(Aranzman a) {
       
    }
}