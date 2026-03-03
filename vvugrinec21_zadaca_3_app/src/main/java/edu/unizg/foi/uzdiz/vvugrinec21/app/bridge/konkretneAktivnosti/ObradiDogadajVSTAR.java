package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaVSTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.command.invoker.VSTARInvoker;
import edu.unizg.foi.uzdiz.vvugrinec21.app.command.konkretneCommand.VSTARKonkretnaCommand;
import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.AranzmanMemento;
import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.caretaker.SpremisteMemento;
import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.originator.AranzmanOriginator;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;

public class ObradiDogadajVSTAR extends Aktivnost {

    private final RezervacijskiSustav sustav;
    private final SpremisteMemento spremiste;

    public ObradiDogadajVSTAR(
            RezervacijskiSustav sustav,
            KomandaVSTAR komanda,
            SpremisteMemento spremiste
    ) {
        super(komanda);
        this.sustav = sustav;
        this.spremiste = spremiste;
    }

    @Override
    public void izvrsiAktivnost() {

        KomandaVSTAR cmd = (KomandaVSTAR) komanda;

        if (!cmd.provjeriIspravnost()) {
            System.out.println("Neispravna VSTAR komanda.");
            return;
        }

        int oznaka = cmd.getOznaka();

        VSTARKonkretnaCommand command =
                new VSTARKonkretnaCommand(sustav, spremiste, oznaka);

        VSTARInvoker invoker = new VSTARInvoker();
        invoker.setCommand(command);

        boolean uspjeh = invoker.execute();

        if (uspjeh) {
            System.out.println("Vraćen aranžman " + oznaka + " i njegove rezervacije.");
        }
    }
}
