package edu.unizg.foi.uzdiz.vvugrinec21.app.decorator.konkretniDecorator;

import edu.unizg.foi.uzdiz.vvugrinec21.app.decorator.IspisDecorator;
import edu.unizg.foi.uzdiz.vvugrinec21.app.decorator.komponenta.Ispis;

public class IspisSStatistikom extends IspisDecorator {

    private final int brojZapisa;

    public IspisSStatistikom(Ispis wrappee, int brojZapisa) {
        super(wrappee);
        this.brojZapisa = brojZapisa;
    }

    @Override
    public void ispisi() {
        super.ispisi();
        System.out.println("Ukupan broj ispisanih zapisa: " + brojZapisa);
    }
}
