package edu.unizg.foi.uzdiz.vvugrinec21.app.composite.component;

public abstract class StavkaAranzmana {

    public void dodaj(StavkaAranzmana s) {
        throw new UnsupportedOperationException();
    }

    public void ukloni(StavkaAranzmana s) {
        throw new UnsupportedOperationException();
    }
    
    public StavkaAranzmana vratiDijete(int i) {
        throw new UnsupportedOperationException();
    }
    
    public abstract void azurirajStatus();
}
