package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaID;

public class ObradiDogadajID extends Aktivnost {

    private final RezervacijskiSustav sustav;

    public ObradiDogadajID(RezervacijskiSustav sustav, KomandaID komanda) {
        super(komanda);
        this.sustav = sustav;
    }

    @Override
    public void izvrsiAktivnost() {
        KomandaID cmd = (KomandaID) komanda;

        
        System.out.println(komanda.getKorisnickiUnos());
        
        if (!cmd.provjeriIspravnost()) {
            System.out.println("Neispravna komanda ID.");
            return;
        }

        if (cmd.ukljuci()) {
            sustav.ukljuciDecorator();
            System.out.println("Decorator ispisa uključen.");
        } else {
            sustav.iskljuciDecorator();
            System.out.println("Decorator ispisa isključen.");
        }
    }
}
