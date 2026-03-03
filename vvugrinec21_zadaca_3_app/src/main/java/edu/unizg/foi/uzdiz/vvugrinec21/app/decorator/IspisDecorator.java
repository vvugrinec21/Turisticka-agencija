package edu.unizg.foi.uzdiz.vvugrinec21.app.decorator;

import edu.unizg.foi.uzdiz.vvugrinec21.app.decorator.komponenta.Ispis;

public abstract class IspisDecorator implements Ispis {

    protected final Ispis wrappee;

    protected IspisDecorator(Ispis wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void ispisi() {
        wrappee.ispisi();
    }
}
