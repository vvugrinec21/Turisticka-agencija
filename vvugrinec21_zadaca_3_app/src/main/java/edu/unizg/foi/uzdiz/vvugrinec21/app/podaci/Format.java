package edu.unizg.foi.uzdiz.vvugrinec21.app.podaci;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class Format {
	private Format() {
	}

	public static final DateTimeFormatter DATUM = DateTimeFormatter.ofPattern("d.M.yyyy.");

	public static final DateTimeFormatter VRIJEME = DateTimeFormatter.ofPattern("H:mm[:ss]");

	public static final DateTimeFormatter DATUM_VRIJEME =
		    DateTimeFormatter.ofPattern("d.M.yyyy H:mm[:ss]");

	public static String poravnajDesno(String tekst, int duljina) {
		if (tekst == null)
			tekst = "";
		int stvarnaDuljina = tekst.length();
		if (stvarnaDuljina >= duljina)
			return tekst.substring(0, duljina);
		StringBuilder sb = new StringBuilder(duljina);
		sb.append(tekst);
		for (int i = stvarnaDuljina; i < duljina; i++)
			sb.append(' ');
		return sb.toString();
	}

	public static LocalDateTime parseDatumVrijemeFlex(String s) {
		if (s == null || s.isBlank())
			return null;

		String normalized = s.trim().replaceAll("\\b(\\d)\\.", "0$1.").replaceAll("\\.(\\d)\\.", ".0$1.")
				.replaceFirst("(\\d{4})\\.", "$1");

		try {
			return LocalDateTime.parse(normalized, DATUM_VRIJEME);
		} catch (Exception e) {
			throw new IllegalArgumentException("Neispravan format datuma/vremena: " + s, e);
		}
	}

	public static LocalDate parseDatum(String s) {
		return LocalDate.parse(s.trim(), DATUM);
	}

	public static LocalTime parseVrijeme(String s) {
		return s == null || s.isBlank() ? null : LocalTime.parse(s.trim(), VRIJEME);
	}

	public static LocalDateTime parseDatumVrijeme(String s) {
		return LocalDateTime.parse(s.trim(), DATUM_VRIJEME);
	}
}
