package edu.unizg.foi.uzdiz.vvugrinec21.app.podaci;

import java.time.LocalDateTime;
import java.util.Objects;

import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;
import edu.unizg.foi.uzdiz.vvugrinec21.app.composite.component.StavkaAranzmana;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.NovaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.StatusRezervacije;
import edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.PretrazivacVisitor;
import edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.element.PretraziviElement;

public class Rezervacija extends StavkaAranzmana 
    implements PretraziviElement{

    private final String ime;
    private final String prezime;
    private final int oznakaAranzmana;
    private final LocalDateTime zaprimljena;

    private StatusRezervacije status;
    private LocalDateTime vrijemeOtkaza;

    public Rezervacija(
            String ime,
            String prezime,
            int oznakaAranzmana,
            LocalDateTime zaprimljena
    ) {
        this.ime = ime;
        this.prezime = prezime;
        this.oznakaAranzmana = oznakaAranzmana;
        this.zaprimljena = zaprimljena;
        this.status = new NovaRezervacija();
    }
    
    private boolean odgodenaStrategijom = false;

    public boolean isOdgodenaStrategijom() {
        return odgodenaStrategijom;
    }

    public void setOdgodenaStrategijom(boolean v) {
        this.odgodenaStrategijom = v;
    }
    
    @Override
    public void azurirajStatus() {
        
    }
    
    @Override
    public void dodaj(StavkaAranzmana s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void ukloni(StavkaAranzmana s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StavkaAranzmana vratiDijete(int i) {
        throw new UnsupportedOperationException();
    }
    

    public void obradiDodavanje(RezervacijskiSustav sustav) {
        status.obradiDodavanje(this, sustav);
    }

    public void obradiOtkazivanje(RezervacijskiSustav sustav, LocalDateTime vrijeme) {
        this.vrijemeOtkaza = vrijeme;
        status.obradiOtkazivanje(this, sustav);
    }

    public void setStatus(StatusRezervacije noviStatus) {
        this.status = noviStatus;
    }

    public StatusRezervacije getStatus() {
        return status;
    }

    public String getNazivStatusa() {
        return status.naziv();
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public int getOznakaAranzmana() {
        return oznakaAranzmana;
    }

    public LocalDateTime getZaprimljena() {
        return zaprimljena;
    }

    public LocalDateTime getVrijemeOtkaza() {
        return vrijemeOtkaza;
    }

    public String getOsobaKey() {
        return (ime + " " + prezime).trim().toLowerCase();
    }
    
    @Override
    public void accept(PretrazivacVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rezervacija)) return false;
        Rezervacija that = (Rezervacija) o;
        return oznakaAranzmana == that.oznakaAranzmana
                && Objects.equals(ime, that.ime)
                && Objects.equals(prezime, that.prezime)
                && Objects.equals(zaprimljena, that.zaprimljena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, prezime, oznakaAranzmana, zaprimljena);
    }
    
    public void postaviVrijemeOtkaza(LocalDateTime vrijeme) {
        this.vrijemeOtkaza = vrijeme;
    }
}
