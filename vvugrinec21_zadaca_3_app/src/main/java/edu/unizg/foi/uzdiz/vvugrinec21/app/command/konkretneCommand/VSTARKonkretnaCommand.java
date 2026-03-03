package edu.unizg.foi.uzdiz.vvugrinec21.app.command.konkretneCommand;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.command.VSTARCommand;
import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.AranzmanMemento;
import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.caretaker.SpremisteMemento;
import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.originator.AranzmanOriginator;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;


public class VSTARKonkretnaCommand implements VSTARCommand {

    private final RezervacijskiSustav sustav;
    private final SpremisteMemento spremiste;
    private final int oznaka;

    public VSTARKonkretnaCommand(
            RezervacijskiSustav sustav,
            SpremisteMemento spremiste,
            int oznaka
    ) {
        this.sustav = sustav;
        this.spremiste = spremiste;
        this.oznaka = oznaka;
    }

    @Override
    public boolean execute() {

        if (!spremiste.postoji(oznaka)) {
            System.out.println("Ne postoji spremljeno stanje za aranžman " + oznaka);
            return false;
        }

        AranzmanMemento memento = spremiste.dohvati(oznaka);
        Aranzman vraceni = memento.getAranzmanKopija();

        sustav.zamijeniAranzman(oznaka, vraceni);
        
        vraceni.ukloniSvePromatrace();
        for (var p : memento.getPromatraciKopija()) {
            vraceni.dodajPromatraca(p);
        }
        
        return true;
    }
}
