package edu.unizg.foi.uzdiz.vvugrinec21.app.pomocnici;

public final class IspisTablica {
    private IspisTablica() {}

    public static void ispisiKomandu(String raw) {
        System.out.println(raw);
    }

    public static void ispisiNazivTablice(String naziv) {
        System.out.println(naziv);
    }

    public static void crta(int sirina) {
        System.out.println("-".repeat(sirina));
    }
}