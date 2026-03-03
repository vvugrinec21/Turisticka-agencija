package edu.unizg.foi.uzdiz.vvugrinec21.app.command.konkretneCommand;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.command.PSTARCommand;
import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.AranzmanMemento;
import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.caretaker.SpremisteMemento;
import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.originator.AranzmanOriginator;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;

public class PSTARKonkretnaCommand implements PSTARCommand {

    private final RezervacijskiSustav sustav;
    private final SpremisteMemento spremiste;
    private final int oznaka;

    public PSTARKonkretnaCommand(
            RezervacijskiSustav sustav,
            SpremisteMemento spremiste,
            int oznaka
    ) {
        this.sustav = sustav;
        this.spremiste = spremiste;
        this.oznaka = oznaka;
    }

    @Override
    public void execute() {

        Aranzman a = sustav.dohvatiAranzman(oznaka);

        if (a == null) {
            System.out.println("Ne postoji aranžman s oznakom " + oznaka);
            return;
        }

        AranzmanOriginator originator = new AranzmanOriginator(a);
        AranzmanMemento memento = originator.createMemento();

        spremiste.spremi(memento);
    }
}
