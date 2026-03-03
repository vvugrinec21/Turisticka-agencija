package edu.unizg.foi.uzdiz.vvugrinec21.app.obsrver.KonkretniObserver;

import edu.unizg.foi.uzdiz.vvugrinec21.app.observer.Promatrac;

public class Pretplatnik implements Promatrac {

    private final String ime;
    private final String prezime;
    private final int oznakaAranzmana;

    public Pretplatnik(String ime, String prezime, int oznakaAranzmana) {
        this.ime = ime;
        this.prezime = prezime;
        this.oznakaAranzmana = oznakaAranzmana;
    }

    @Override
    public void update(String poruka) {
        System.out.println(ime + " " + prezime);
        System.out.println("Aranžman: " + oznakaAranzmana);
        System.out.println(poruka);
    }

    public String getKey() {
        return (ime + " " + prezime).toLowerCase();
    }

    public int getOznakaAranzmana() {
        return oznakaAranzmana;
    }
}
