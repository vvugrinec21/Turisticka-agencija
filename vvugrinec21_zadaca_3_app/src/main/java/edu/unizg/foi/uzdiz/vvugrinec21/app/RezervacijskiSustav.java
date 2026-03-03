package edu.unizg.foi.uzdiz.vvugrinec21.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Aranzman;
import edu.unizg.foi.uzdiz.vvugrinec21.app.podaci.Rezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.pomocnici.NacinIspisaDecoratora;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.aranzmani.AranzmanOtkazan;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.AktivnaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.OdgodenaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.OtkazanaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.state.rezervacija.PrimljenaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.strategy.JdrStrategijaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.strategy.NullStrategijaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.strategy.StrategijaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.KonkretniVisitor.PretragaAranzmanaVisitor;
import edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.KonkretniVisitor.PretragaRezervacijaVisitor;
import edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.PretrazivacVisitor;

public class RezervacijskiSustav {

    private final Map<Integer, Aranzman> aranzmani = new HashMap<>();

    private NacinIspisa nacinIspisa = NacinIspisa.N;

    public void postaviNacinIspisa(NacinIspisa nacin) {
        this.nacinIspisa = nacin;
    }

    public NacinIspisa getNacinIspisa() {
        return nacinIspisa;
    }

    public void dodajAranzman(Aranzman a) {
        aranzmani.put(a.getOznaka(), a);
    }

    public Aranzman dohvatiAranzman(int oznaka) {
        return aranzmani.get(oznaka);
    }

    public Collection<Aranzman> sviAranzmani() {
        return aranzmani.values().stream()
                .sorted(Comparator.comparing(Aranzman::getOznaka))
                .collect(Collectors.toList());
    }

    private StrategijaRezervacija strategija = new NullStrategijaRezervacija();

    public void postaviStrategiju(StrategijaRezervacija strategija) {
        this.strategija = strategija;
    }

    public void primijeniStrategiju(Rezervacija r) {
        strategija.primijeni(r, this);
    }

    public boolean dodajRezervaciju(Rezervacija r) {

        Aranzman a = aranzmani.get(r.getOznakaAranzmana());
        if (a == null) {
            return false;
        }
        
        if (a.jeOtkazan()) {
            System.out.println("Nije moguće dodati rezervaciju u otkazan aranžman.");
            return false;
        }

        a.dodajRezervaciju(r);

        
        r.obradiDodavanje(this);
        
        a.azurirajStatus(); 
        primijeniStrategiju(r);
        
        obradiSveRezervacije();
        
        return true;
    }

    public boolean otkaziRezervaciju(
            String ime,
            String prezime,
            int oznakaAranzmana,
            LocalDateTime vrijeme
    ) {
        Aranzman a = aranzmani.get(oznakaAranzmana);
        if (a == null) {
            return false;
        }

        Optional<Rezervacija> target = a.getRezervacije().stream()
                .filter(r -> r.getOsobaKey().equals((ime + " " + prezime).toLowerCase()))
                .filter(r -> !(r.getStatus() instanceof OtkazanaRezervacija))
                .findFirst();

        if (target.isEmpty()) {
            return false;
        }

        target.get().obradiOtkazivanje(this, vrijeme);
        a.azurirajStatus();

        return true;
    }

    public List<Rezervacija> rezervacijeZaAranzman(int oznaka) {
        Aranzman a = aranzmani.get(oznaka);
        if (a == null) return List.of();

        return a.getRezervacije().stream()
                .sorted(Comparator.comparing(Rezervacija::getZaprimljena))
                .collect(Collectors.toList());
    }

    public List<Rezervacija> rezervacijeZaOsobu(String ime, String prezime) {
        String key = (ime + " " + prezime).trim().toLowerCase();

        return aranzmani.values().stream()
                .flatMap(a -> a.getRezervacije().stream())
                .filter(r -> r.getOsobaKey().equals(key))
                .sorted(Comparator.comparing(Rezervacija::getZaprimljena))
                .collect(Collectors.toList());
    }

    public List<Rezervacija> dohvatiRezervacijeOsobe(String osobaKey) {
        return aranzmani.values().stream()
                .flatMap(a -> a.getRezervacije().stream())
                .filter(r -> r.getOsobaKey().equals(osobaKey))
                .sorted(Comparator.comparing(Rezervacija::getZaprimljena))
                .toList();
    }

    public List<Rezervacija> dohvatiAktivneRezervacijeOsobe(String osobaKey) {
        return dohvatiRezervacijeOsobe(osobaKey).stream()
                .filter(r -> r.getStatus() instanceof AktivnaRezervacija)
                .toList();
    }

    public boolean preklapanje(Rezervacija r1, Rezervacija r2) {

        Aranzman a1 = dohvatiAranzman(r1.getOznakaAranzmana());
        Aranzman a2 = dohvatiAranzman(r2.getOznakaAranzmana());

        if (a1 == null || a2 == null) return false;

        return !a1.getPocetniDatum().isAfter(a2.getZavrsniDatum())
                && !a2.getPocetniDatum().isAfter(a1.getZavrsniDatum());
    }

    public boolean imaAktivniPreklapajuci(
            String ime,
            String prezime,
            Aranzman kandidat
    ) {
        String key = (ime + " " + prezime).trim().toLowerCase();

        for (Aranzman a : aranzmani.values()) {
            for (Rezervacija r : a.getRezervacije()) {

                if (!r.getOsobaKey().equals(key)) continue;
                if (!(r.getStatus() instanceof AktivnaRezervacija)) continue;

                if (a.getOznaka() == kandidat.getOznaka()) {
                    return true;
                }

                if (preklapa(
                        kandidat.getPocetniDatum(),
                        kandidat.getZavrsniDatum(),
                        a.getPocetniDatum(),
                        a.getZavrsniDatum())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean imaAktivnuRezervacijuUPreklapanju(
            String ime,
            String prezime,
            Aranzman kandidat
    ) {
        return imaAktivniPreklapajuci(ime, prezime, kandidat);
    }

    public int brojAktivnihZaAranzman(int oznaka) {
        Aranzman a = aranzmani.get(oznaka);
        if (a == null) return 0;

        return (int) a.getRezervacije().stream()
                .filter(r -> r.getStatus() instanceof AktivnaRezervacija)
                .count();
    }

	
    
    public boolean otkaziAranzman(int oznaka) {

        Aranzman a = aranzmani.get(oznaka);
        if (a == null) return false;

        for (Rezervacija r : a.getRezervacije()) {
            if (!(r.getStatus() instanceof OtkazanaRezervacija)) {
                r.obradiOtkazivanje(this, null);
            }
        }

        a.otkazi();
        return true;
    }

    public void aktivirajNajranijuOdgodenuZaOsobu(String ime, String prezime) {

        String key = (ime + " " + prezime).trim().toLowerCase();
        boolean jdr = strategija instanceof JdrStrategijaRezervacija;

        Optional<Rezervacija> kandidat =
                aranzmani.values().stream()
                        .flatMap(a -> a.getRezervacije().stream())
                        .filter(r -> r.getOsobaKey().equals(key))
                        .filter(r -> r.getStatus() instanceof OdgodenaRezervacija)
                        .sorted(Comparator.comparing(Rezervacija::getZaprimljena))
                        .filter(r -> !jdr || !imaAktivniPreklapajuci(
                                r.getIme(),
                                r.getPrezime(),
                                dohvatiAranzman(r.getOznakaAranzmana())))
                        .findFirst();

        kandidat.ifPresent(r -> {
            r.setStatus(new AktivnaRezervacija());
            dohvatiAranzman(r.getOznakaAranzmana()).azurirajStatus();
        });
    }

    public void aktivirajPrvuCekajucu(int oznaka) {

        Aranzman a = aranzmani.get(oznaka);
        if (a == null) return;

        boolean jdr = strategija instanceof JdrStrategijaRezervacija;

        Optional<Rezervacija> prva = a.getRezervacije().stream()
                .filter(r -> r.getStatus() instanceof OdgodenaRezervacija)
                .sorted(Comparator.comparing(Rezervacija::getZaprimljena))
                .filter(r -> !jdr || !imaAktivniPreklapajuci(
                        r.getIme(),
                        r.getPrezime(),
                        a))
                .findFirst();

        prva.ifPresent(r -> {
            r.setStatus(new AktivnaRezervacija());
            a.azurirajStatus();
        });
    }

    public void obrisiSveRezervacije() {
        for (Aranzman a : aranzmani.values()) {
            a.obrisiSveRezervacije();
        }
    }

    public void obrisiSveAranzmane() {
        aranzmani.clear();
    }

    public boolean postojiAranzman(int oznaka) {
        return aranzmani.containsKey(oznaka);
    }
    
	
    
    
    public void obradiSveRezervacije() {

        for (Aranzman a : aranzmani.values()) {
            for (Rezervacija r : a.getRezervacije()) {
                if (!(r.getStatus() instanceof OtkazanaRezervacija)) {
                    r.setOdgodenaStrategijom(false);
                    r.setStatus(new PrimljenaRezervacija());
                }
            }
        }

        
        for (Aranzman a : aranzmani.values()) {
            a.azurirajStatus();
        }

      
        for (Aranzman a : aranzmani.values()) {
            for (Rezervacija r : a.getRezervacije()) {
                strategija.primijeni(r, this);
            }
        }

        
        for (Aranzman a : aranzmani.values()) {
            a.azurirajStatus();
        }
    }
    

    
    
    
    private NacinIspisaDecoratora decoratorMode = NacinIspisaDecoratora.ISKLJUCEN;

    public void ukljuciDecorator() {
        decoratorMode = NacinIspisaDecoratora.UKLJUCEN;
    }

    public void iskljuciDecorator() {
        decoratorMode = NacinIspisaDecoratora.ISKLJUCEN;
    }

    public boolean jeDecoratorUkljucen() {
        return decoratorMode == NacinIspisaDecoratora.UKLJUCEN;
    }

    public void pretrazi(char tip, String rijec) {

        PretrazivacVisitor visitor =
                (tip == 'A')
                        ? new PretragaAranzmanaVisitor(rijec)
                        : new PretragaRezervacijaVisitor(rijec);

        for (Aranzman a : aranzmani.values()) {
            a.accept(visitor);
        }
    }

    public void prihvatiVisitor(PretrazivacVisitor visitor) {
        for (Aranzman a : aranzmani.values()) {
            a.accept(visitor);
        }
    }

    public void zamijeniAranzman(int oznaka, Aranzman novi) {
        aranzmani.put(oznaka, novi);
    }

    private static boolean preklapa(
            LocalDate s1,
            LocalDate e1,
            LocalDate s2,
            LocalDate e2
    ) {
        return !s1.isAfter(e2) && !s2.isAfter(e1);
    }
}
