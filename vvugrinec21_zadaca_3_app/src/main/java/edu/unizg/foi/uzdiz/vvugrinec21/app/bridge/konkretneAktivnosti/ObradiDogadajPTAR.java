package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaPTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.obsrver.KonkretniObserver.Pretplatnik;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;

public class ObradiDogadajPTAR extends Aktivnost {

    private final RezervacijskiSustav sustav;
    private final KomandaPTAR komanda;

    public ObradiDogadajPTAR(RezervacijskiSustav sustav, KomandaPTAR komanda) {
        super(komanda);
        this.sustav = sustav;
        this.komanda = komanda;
    }

    @Override
    public void izvrsiAktivnost() {

        if (!komanda.provjeriIspravnost()) {
            System.out.println("Neispravna PTAR komanda");
            return;
        }

        Aranzman a = sustav.dohvatiAranzman(komanda.getOznaka());
        if (a == null) {
            System.out.println("Ne postoji aranžman s oznakom " + komanda.getOznaka());
            return;
        }

        Pretplatnik p = new Pretplatnik(
                komanda.getIme(),
                komanda.getPrezime(),
                komanda.getOznaka()
        );

        a.dodajPromatraca(p);

        System.out.println(
                komanda.getIme() + " " + komanda.getPrezime()
                        + " se pretplatila na aranžman "
                        + komanda.getOznaka()
        );
    }
}
