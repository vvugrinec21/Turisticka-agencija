package edu.unizg.foi.uzdiz.vvugrinec21.app.memento.caretaker;

import java.util.HashMap;
import java.util.Map;

import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.AranzmanMemento;


public class SpremisteMemento {

    private final Map<Integer, AranzmanMemento> spremiste = new HashMap<>();

    public boolean spremi(AranzmanMemento memento) {
        if (spremiste.containsKey(memento.getOznaka())) {
            return false;
        }
        spremiste.put(memento.getOznaka(), memento);
        return true;
    }

    public AranzmanMemento dohvati(int oznaka) {
        return spremiste.get(oznaka);
    }

    public boolean postoji(int oznaka) {
        return spremiste.containsKey(oznaka);
    }
}

