package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaPPTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.PretrazivacVisitor;
import edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.KonkretniVisitor.PretragaAranzmanaVisitor;
import edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.KonkretniVisitor.PretragaRezervacijaVisitor;


public class ObradiDogadajPPTAR extends Aktivnost {

    private final RezervacijskiSustav sustav;
    private final KomandaPPTAR komanda;

    public ObradiDogadajPPTAR(RezervacijskiSustav sustav, KomandaPPTAR komanda) {
        super(komanda);
        this.sustav = sustav;
        this.komanda = komanda;
    }

    @Override
    public void izvrsiAktivnost() {

        if (!komanda.provjeriIspravnost()) {
            System.out.println(
                "Neispravna PPTAR komanda: " + komanda.getKorisnickiUnos()
            );
            return;
        }

        PretrazivacVisitor visitor =
                (komanda.getTip() == 'A')
                        ? new PretragaAranzmanaVisitor(komanda.getRijec())
                        : new PretragaRezervacijaVisitor(komanda.getRijec());

        sustav.prihvatiVisitor(visitor);
    }
}
