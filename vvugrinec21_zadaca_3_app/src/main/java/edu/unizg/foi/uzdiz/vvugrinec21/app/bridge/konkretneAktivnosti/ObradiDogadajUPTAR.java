package edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaUPTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;


public class ObradiDogadajUPTAR extends Aktivnost {

    private final RezervacijskiSustav sustav;

    public ObradiDogadajUPTAR(RezervacijskiSustav sustav, KomandaUPTAR komanda) {
        super(komanda);
        this.sustav = sustav;
    }

    @Override
    public void izvrsiAktivnost() {

        KomandaUPTAR cmd = (KomandaUPTAR) komanda;

        if (!cmd.provjeriIspravnost()) {
            System.out.println("Neispravna UPTAR komanda.");
            return;
        }

        Aranzman a = sustav.dohvatiAranzman(cmd.getOznaka());
        if (a == null) {
            System.out.println("Ne postoji aranžman s oznakom " + cmd.getOznaka());
            return;
        }

        if (cmd.jeSamoOznaka()) {
            a.ukloniSvePromatrace();
            System.out.println("Ukinute sve pretplate za aranžman " + cmd.getOznaka());
        } else {
            a.ukloniPromatraca(cmd.getIme(), cmd.getPrezime());
            System.out.println(
                "Ukinuta pretplata za " +
                cmd.getIme() + " " +
                cmd.getPrezime() +
                " na aranžman " +
                cmd.getOznaka()
            );
        }
    }
}
