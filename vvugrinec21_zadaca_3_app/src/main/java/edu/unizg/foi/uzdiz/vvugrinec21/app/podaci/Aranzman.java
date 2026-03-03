package edu.unizg.foi.uzdiz.vvugrinec21.app.podaci;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import edu.unizg.foi.uzdiz.vvugrinec21.app.composite.component.StavkaAranzmana;
import edu.unizg.foi.uzdiz.vvugrinec21.app.cor.AranzmanAktiviranHandler;
import edu.unizg.foi.uzdiz.vvugrinec21.app.cor.AranzmanOtkazanHandler;
import edu.unizg.foi.uzdiz.vvugrinec21.app.cor.AranzmanPopunjenHandler;
import edu.unizg.foi.uzdiz.vvugrinec21.app.cor.ObavijestHandler;
import edu.unizg.foi.uzdiz.vvugrinec21.app.observer.Promatrac;
import edu.unizg.foi.uzdiz.vvugrinec21.app.obsrver.KonkretniObserver.Pretplatnik;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.aranzmani.*;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.*;
import edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.PretrazivacVisitor;
import edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.element.PretraziviElement;
import edu.unizg.foi.uzdiz.vvugrinec21.app.RezervacijskiSustav;

public class Aranzman extends StavkaAranzmana
        implements PretraziviElement {

    private final int oznaka;
    private final String naziv;
    private final String program;
    private final LocalDate pocetniDatum;
    private final LocalDate zavrsniDatum;
    private final LocalTime vrijemeKretanja;
    private final LocalTime vrijemePovratka;
    private final BigDecimal cijena;
    private final int minBrojPutnika;
    private final int maxBrojPutnika;
    private final Integer brojNocenja;
    private final BigDecimal doplataJednokrevetna;
    private final String prijevoz;
    private final Integer brojDorucaka;
    private final Integer brojRuckova;
    private final Integer brojVecera;

    private final List<StavkaAranzmana> djeca = new ArrayList<>();
    private final List<Promatrac> promatraci = new ArrayList<>();

    private StatusAranzmana status = new AranzmanUPripremi();

    public Aranzman(
            int oznaka,
            String naziv,
            String program,
            LocalDate pocetniDatum,
            LocalDate zavrsniDatum,
            LocalTime vrijemeKretanja,
            LocalTime vrijemePovratka,
            BigDecimal cijena,
            int minBrojPutnika,
            int maxBrojPutnika,
            Integer brojNocenja,
            BigDecimal doplataJednokrevetna,
            String prijevoz,
            Integer brojDorucaka,
            Integer brojRuckova,
            Integer brojVecera
    ) {
        this.oznaka = oznaka;
        this.naziv = naziv;
        this.program = program;
        this.pocetniDatum = pocetniDatum;
        this.zavrsniDatum = zavrsniDatum;
        this.vrijemeKretanja = vrijemeKretanja;
        this.vrijemePovratka = vrijemePovratka;
        this.cijena = cijena;
        this.minBrojPutnika = minBrojPutnika;
        this.maxBrojPutnika = maxBrojPutnika;
        this.brojNocenja = brojNocenja;
        this.doplataJednokrevetna = doplataJednokrevetna;
        this.prijevoz = prijevoz;
        this.brojDorucaka = brojDorucaka;
        this.brojRuckova = brojRuckova;
        this.brojVecera = brojVecera;
    }

    @Override
    public void dodaj(StavkaAranzmana s) {
        djeca.add(s);
        
        obavijestiPromatrace("Dodana nova rezervacija");
    }

    @Override
    public void ukloni(StavkaAranzmana s) {
        djeca.remove(s);
        azurirajStatus();
        obavijestiPromatrace("Uklonjena rezervacija");
    }

    public void dodajRezervaciju(Rezervacija r) {
        dodaj(r);
    }

    public void obrisiSveRezervacije() {
        djeca.clear();
        azurirajStatus();
        obavijestiPromatrace("Obrisane sve rezervacije aranžmana");
    }

    public List<Rezervacija> getRezervacije() {
        return djeca.stream()
                .filter(s -> s instanceof Rezervacija)
                .map(s -> (Rezervacija) s)
                .toList();
    }


    
    @Override
    public void azurirajStatus() {

    	if (status instanceof AranzmanOtkazan) {
            return;
        }
    	
    	String stariStatus = status.naziv();

        List<Rezervacija> valjane = getRezervacije().stream()
                .filter(r -> !(r.getStatus() instanceof OtkazanaRezervacija))
                .sorted(Comparator.comparing(Rezervacija::getZaprimljena))
                .toList();
        
        List<Rezervacija> zaMin = valjane.stream()
        	    .filter(r ->
        	        !(r.getStatus() instanceof OdgodenaRezervacija) &&
        	        !(r.getStatus() instanceof CekanjeRezervacija)
        	    )
        	    .toList();
        
        
        if (zaMin.size() < minBrojPutnika) {

            for (Rezervacija r : valjane) {

                if (r.isOdgodenaStrategijom()) {
                    r.setStatus(new OdgodenaRezervacija());
                    continue;
                }

             
                r.setStatus(new PrimljenaRezervacija());
            }

            status.azurirajStatus(this);

            if (!stariStatus.equals(status.naziv())) {
                obavijestChain.obradi(this);
            }
            return;
        }
        
      
        int aktivne = 0;

        for (Rezervacija r : valjane) {
        	
        	
        	if (r.getStatus() instanceof OtkazanaRezervacija) {
                continue;
            }
        	
            if (r.isOdgodenaStrategijom()) {
                r.setStatus(new OdgodenaRezervacija());
                continue;
            }

            if (aktivne < maxBrojPutnika) {
                r.setStatus(new AktivnaRezervacija());
                aktivne++;
            } else {
                r.setStatus(new CekanjeRezervacija());
            }
        }

        status.azurirajStatus(this);
      

		if (!stariStatus.equals(status.naziv())) {
		    obavijestChain.obradi(this);
		}
    }
    
    
    private final ObavijestHandler obavijestChain = kreirajLanac();
    
    private ObavijestHandler kreirajLanac() {

        ObavijestHandler aktiviran = new AranzmanAktiviranHandler();
        ObavijestHandler popunjen = new AranzmanPopunjenHandler();
        ObavijestHandler otkazan = new AranzmanOtkazanHandler();

        aktiviran.setNext(popunjen).setNext(otkazan);

        return aktiviran;
    }
    
    public void dodajPromatraca(Promatrac p) {
        promatraci.add(p);
    }
    
    
    public void ukloniPromatraca(String ime, String prezime) {
        String key = (ime + " " + prezime).toLowerCase();
        promatraci.removeIf(p ->
                p instanceof Pretplatnik pr &&
                pr.getKey().equals(key) &&
                pr.getOznakaAranzmana() == oznaka
        );
    }

    public void ukloniSvePromatrace() {
        promatraci.clear();
    }

    public void obavijestiPromatrace(String poruka) {
        for (Promatrac p : promatraci) {
            p.update(poruka);
        }
    }

    public int getOznaka() { return oznaka; }
    public String getNaziv() { return naziv; }
    public String getProgram() { return program; }
    public LocalDate getPocetniDatum() { return pocetniDatum; }
    public LocalDate getZavrsniDatum() { return zavrsniDatum; }
    public LocalTime getVrijemeKretanja() { return vrijemeKretanja; }
    public LocalTime getVrijemePovratka() { return vrijemePovratka; }
    public BigDecimal getCijena() { return cijena; }
    public int getMinBrojPutnika() { return minBrojPutnika; }
    public int getMaxBrojPutnika() { return maxBrojPutnika; }
    public Integer getBrojNocenja() { return brojNocenja; }
    public BigDecimal getDoplataJednokrevetna() { return doplataJednokrevetna; }
    public String getPrijevoz() { return prijevoz; }
    public Integer getBrojDorucaka() { return brojDorucaka; }
    public Integer getBrojRuckova() { return brojRuckova; }
    public Integer getBrojVecera() { return brojVecera; }

    public void setStatus(StatusAranzmana status) {
        this.status = status;
    }
    
    public boolean jeOtkazan() {
        return status instanceof AranzmanOtkazan;
    }
    
    public void otkazi() {

        if (status instanceof AranzmanOtkazan) {
            return;
        }

        String stariStatus = status.naziv();

        setStatus(new AranzmanOtkazan());

        if (!stariStatus.equals(status.naziv())) {
            obavijestChain.obradi(this);
        }
    }
    
    public List<Promatrac> kopijaPromatraca() {
        return new ArrayList<>(promatraci);
    }

    public void postaviPromatrace(List<Promatrac> novi) {
        promatraci.clear();
        promatraci.addAll(novi);
    }
    
    public String getNazivStatusa() {
        return status.naziv();
    }

    @Override
    public void accept(PretrazivacVisitor visitor) {
        visitor.visit(this);
        for (Rezervacija r : getRezervacije()) {
            r.accept(visitor);
        }
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Aranzman a && oznaka == a.oznaka;
    }

    @Override
    public int hashCode() {
        return Objects.hash(oznaka);
    }
    
    @Override
    public StavkaAranzmana vratiDijete(int i) {
        return djeca.get(i);
    }
}
