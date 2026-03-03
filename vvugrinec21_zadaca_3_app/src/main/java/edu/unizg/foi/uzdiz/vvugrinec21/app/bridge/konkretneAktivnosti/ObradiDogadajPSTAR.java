package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaPSTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.command.invoker.PSTARInvoker;
import edu.unizg.foi.uzdiz.vvugrinec21.app.command.konkretneCommand.PSTARKonkretnaCommand;
import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.AranzmanMemento;
import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.caretaker.SpremisteMemento;
import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.originator.AranzmanOriginator;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;


public class ObradiDogadajPSTAR extends Aktivnost {

    private final RezervacijskiSustav sustav;
    private final SpremisteMemento spremiste;

    public ObradiDogadajPSTAR(
            RezervacijskiSustav sustav,
            KomandaPSTAR komanda,
            SpremisteMemento spremiste
    ) {
        super(komanda);
        this.sustav = sustav;
        this.spremiste = spremiste;
    }

    @Override
    public void izvrsiAktivnost() {

        KomandaPSTAR cmd = (KomandaPSTAR) komanda;

        if (!cmd.provjeriIspravnost()) {
            System.out.println("Neispravna PSTAR komanda.");
            return;
        }

        int oznaka = cmd.getOznaka();

        PSTARKonkretnaCommand command =
                new PSTARKonkretnaCommand(sustav, spremiste, oznaka);

        PSTARInvoker invoker = new PSTARInvoker();
        invoker.setCommand(command);
        invoker.execute();

        System.out.println("Spremljen aranžman " + oznaka + " i njegove rezervacije.");
    }
}
