package edu.unizg.foi.uzdiz.vvugrinec21.app.decorator.konkretnaKomponenta;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.decorator.komponenta.Ispis;

public class OsnovniIspis implements Ispis {

    private final Aktivnost aktivnost;

    public OsnovniIspis(Aktivnost aktivnost) {
        this.aktivnost = aktivnost;
    }

    @Override
    public void ispisi() {
        aktivnost.izvrsiAktivnost();
    }
}
