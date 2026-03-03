package edu.unizg.foi.uzdiz.vvugrinec21.app.visitor;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;

public interface PretrazivacVisitor {
    void visit(Aranzman aranzman);
    void visit(Rezervacija rezervacija);
}
