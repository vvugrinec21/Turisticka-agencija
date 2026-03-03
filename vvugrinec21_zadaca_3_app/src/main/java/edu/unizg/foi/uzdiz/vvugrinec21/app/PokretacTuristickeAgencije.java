package edu.unizg.foi.uzdiz.vvugrinec21.app;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.Aktivnost;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajBP;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajDRTA;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajID;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajIP;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajIRO;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajIRTA;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajITAK;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajITAP;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajITAS;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajORTA;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajOTA;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajPPTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajPSTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajPTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajQ;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajUP;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajUPTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneAktivnosti.ObradiDogadajVSTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaBP;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaDRTA;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaID;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaIP;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaIRO;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaIRTA;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaITAK;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaITAP;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaITAS;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaORTA;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaOTA;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaPPTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaPSTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaPTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaQ;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaUP;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaUPTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.bridge.konkretneKomande.KomandaVSTAR;
import edu.unizg.foi.uzdiz.vvugrinec21.app.decorator.komponenta.*;
import edu.unizg.foi.uzdiz.vvugrinec21.app.decorator.konkretnaKomponenta.*;
import edu.unizg.foi.uzdiz.vvugrinec21.app.decorator.konkretniDecorator.*;
import edu.unizg.foi.uzdiz.vvugrinec21.app.memento.caretaker.SpremisteMemento;
import edu.unizg.foi.uzdiz.vvugrinec21.app.strategy.JdrStrategijaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.strategy.NullStrategijaRezervacija;
import edu.unizg.foi.uzdiz.vvugrinec21.app.strategy.VdrStrategijaRezervacija;

public class PokretacTuristickeAgencije {

	public static void main(String[] args) {

		TuristickaAgencija agencija = TuristickaAgencija.getInstance();

		String regex = "(?=.*--ta\\s+(?<ta>[A-Za-z0-9_./-]+))(?=.*--rta\\s+(?<rta>[A-Za-z0-9_./-]+)).*";
		String commandLine = String.join(" ", args);
		Matcher matcher = Pattern.compile(regex).matcher(commandLine);

		if (matcher.matches()) {
			agencija.inicijalizirajSustav(
					matcher.group("ta"),
					matcher.group("rta"));
		} else {
			System.out.println("Neispravan unos!");
			System.out.println("Uporaba: --ta <aranzmani.csv> --rta <rezervacije.csv>");
			return;
		}

		RezervacijskiSustav sustav = agencija.sustav;
	
		if (sustav == null) {
			System.out.println("Sustav nije učitan!");
			return;
		}
		
		boolean jdr = commandLine.contains("--jdr");
		boolean vdr = commandLine.contains("--vdr");

		if (jdr && vdr) {
		    System.out.println("Ne mogu se istovremeno koristiti --jdr i --vdr.");
		    return;
		}

		if (jdr) {
		    sustav.postaviStrategiju(new JdrStrategijaRezervacija());
		    System.out.println("Sustav inicijaliziran s opcijom --jdr");
		} else if (vdr) {
		    sustav.postaviStrategiju(new VdrStrategijaRezervacija());
		    System.out.println("Sustav inicijaliziran s opcijom --vdr");
		} else {
		    sustav.postaviStrategiju(new NullStrategijaRezervacija());
		    System.out.println("Sustav inicijaliziran bez strategije rezervacija");
		}
		
		sustav.obradiSveRezervacije();
		
		
		SpremisteMemento spremisteMemento = new SpremisteMemento();

		try (Scanner scanner = new Scanner(System.in)) {

			while (agencija.running) {

				System.out.print(">> ");
				String line = scanner.nextLine().trim();
				if (line.isEmpty()) continue;

				String cmd = line.split("\\s+")[0].toUpperCase();
				Aktivnost aktivnost = null;

				switch (cmd) {

					case "ITAK":
						aktivnost = new ObradiDogadajITAK(sustav, new KomandaITAK(line));
						break;

					case "ITAP":
						aktivnost = new ObradiDogadajITAP(sustav, new KomandaITAP(line));
						break;

					case "IRTA":
						aktivnost = new ObradiDogadajIRTA(sustav, new KomandaIRTA(line));
						break;

					case "IRO":
						aktivnost = new ObradiDogadajIRO(sustav, new KomandaIRO(line));
						break;

					case "DRTA":
						aktivnost = new ObradiDogadajDRTA(sustav, new KomandaDRTA(line));
						break;

					case "ORTA":
						aktivnost = new ObradiDogadajORTA(sustav, new KomandaORTA(line));
						break;

					case "OTA":
						aktivnost = new ObradiDogadajOTA(sustav, new KomandaOTA(line));
						break;

					case "IP":
						aktivnost = new ObradiDogadajIP(sustav, new KomandaIP(line));
						break;

					case "UP":
						aktivnost = new ObradiDogadajUP(sustav, new KomandaUP(line));
						break;

					case "BP":
						aktivnost = new ObradiDogadajBP(sustav, new KomandaBP(line));
						break;

					case "ITAS":
						aktivnost = new ObradiDogadajITAS(sustav, new KomandaITAS(line));
						break;
						
					case "ID":
						aktivnost = new ObradiDogadajID(sustav, new KomandaID(line));
						break;	
						
					case "PPTAR":
						aktivnost = new ObradiDogadajPPTAR(sustav, new KomandaPPTAR(line));
						break;	
					
					case "PSTAR":
					    aktivnost = new ObradiDogadajPSTAR(sustav, new KomandaPSTAR(line),spremisteMemento);
					    break;
					case "VSTAR":
					    aktivnost = new ObradiDogadajVSTAR(sustav,new KomandaVSTAR(line),spremisteMemento);
					    break;
					case "PTAR":
					    aktivnost = new ObradiDogadajPTAR(sustav, new KomandaPTAR(line));
					    break;	
						
					case "UPTAR":
					    aktivnost = new ObradiDogadajUPTAR(sustav, new KomandaUPTAR(line));
					    break;	
						
						
					case "Q":
						aktivnost = new ObradiDogadajQ(new KomandaQ(line));
						agencija.running = false;
						break;
					
					
					default:
						System.out.println("Nepoznata komanda: " + cmd);
						continue;
				}


				Ispis ispis = new OsnovniIspis(aktivnost);

	
				if (sustav.jeDecoratorUkljucen()) {
					ispis = new IspisPregled(ispis, sustav);
				}

				ispis.ispisi();
			}
		}
	}
}
