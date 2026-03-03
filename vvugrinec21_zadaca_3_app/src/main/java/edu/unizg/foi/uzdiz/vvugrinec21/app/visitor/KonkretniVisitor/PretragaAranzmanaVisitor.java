package edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.KonkretniVisitor;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.PretrazivacVisitor;

public class PretragaAranzmanaVisitor implements PretrazivacVisitor {

    private final String trazenaRijec;

    public PretragaAranzmanaVisitor(String trazenaRijec) {
        this.trazenaRijec = trazenaRijec;
    }

    @Override
    public void visit(Aranzman a) {
        if (a.getNaziv().contains(trazenaRijec)
                || a.getProgram().contains(trazenaRijec)) {

            System.out.println(
                a.getOznaka() + " | " +
                a.getNaziv() + " | " +
                a.getProgram()
            );
        }
    }

    @Override
    public void visit(Rezervacija r) {
        
    }
}

