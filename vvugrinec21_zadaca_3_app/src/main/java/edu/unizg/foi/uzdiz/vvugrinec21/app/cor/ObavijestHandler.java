package edu.unizg.foi.uzdiz.vvugrinec21.app.cor;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;

public abstract class ObavijestHandler {

    protected ObavijestHandler next;

    public ObavijestHandler setNext(ObavijestHandler next) {
        this.next = next;
        return next;
    }

    public void obradi(Aranzman a) {
        if (!obradiInterno(a) && next != null) {
            next.obradi(a);
        }
    }

    protected abstract boolean obradiInterno(Aranzman a);
}
