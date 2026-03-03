package edu.unizg.foi.uzdiz.vvugrinec21.lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CitacDatoteka {

	public static List<String> procitajZapise(String putanja) {
		List<String> zapisi = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(putanja))) {
			String line;
			boolean firstLine = true;

			while ((line = reader.readLine()) != null) {
				line = line.trim();

				if (firstLine) {
					firstLine = false;
					continue;
				}

				if (line.isEmpty())
					continue;

				if (line.startsWith("#"))
					continue;

				zapisi.add(line);
			}
		} catch (IOException e) {
			System.out.println("Greška pri čitanju datoteke: " + putanja);
		}

		return zapisi;
	}
}
