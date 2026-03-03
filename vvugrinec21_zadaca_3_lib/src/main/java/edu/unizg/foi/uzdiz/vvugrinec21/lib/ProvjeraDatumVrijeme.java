package edu.unizg.foi.uzdiz.vvugrinec21.lib;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

class ProvjeraDatumVrijeme {

    private static final List<DateTimeFormatter> FORMATI = List.of(
            DateTimeFormatter.ofPattern("d.M.yyyy H:mm:ss"),
            DateTimeFormatter.ofPattern("d.M.yyyy H:mm"),
            DateTimeFormatter.ofPattern("d.M.yyyy. H:mm:ss"),
            DateTimeFormatter.ofPattern("d.M.yyyy. H:mm")
    );

    static void provjeri(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalArgumentException("Datum i vrijeme su prazni");
        }

        String vrijednost = s.trim();

        for (DateTimeFormatter f : FORMATI) {
            try {
                LocalDateTime.parse(vrijednost, f);
                return; 
            } catch (DateTimeParseException ignored) {
            }
        }

        throw new IllegalArgumentException("Neispravan datum i vrijeme");
    }
}
