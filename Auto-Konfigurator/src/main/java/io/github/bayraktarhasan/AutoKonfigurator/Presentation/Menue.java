package io.github.bayraktarhasan.AutoKonfigurator.Presentation;

public class Menue {

    public Menue() {
    }

    public void menueAnzeigen() {
        System.out.println("-----------------------------------------Auto-Konfigurator-------------------------------------------------");
        System.out.println("MENUE");
        System.out.println("1) Modell auswählen");
        System.out.println("2) Ausstattungspakete");
        System.out.println("3) Bestellen");
        System.out.println("4) Beenden");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.print(wahlText());

    }

    public String headerPlattform() {
        return "-----------------------------------------Plattformen-----------------------------------------------------";
    }

    public String headerPaket() {
        return "-----------------------------------------Ausstattungspakete------------------------------------------------";
    }

    public String leerZeile() {
        return "----------------------------------------------------------------------------------------------------------";
    }

    public String wahlText() {
        return "Ihre Wahl: ";
    }

    public String bestaetigen() {
        return "Bestätigen Sie bitte Ihre Bestellung (Ja/Nein): ";
    }

    public String infoKauf() {
        return "Das Auto wird vorbereitet, Sie werden von uns eine E-Mail bekommen wenn diese bereit zur Abnahme ist.";
    }

    public String fehlerPaket() {
        return "Es sind keine Pakete vorhanden";
    }

    public String fehlerAuto() {
        return "Es wurde KEIN Auto ausgewaehlt!";
    }

    public String fehler() {
        return "Sie koennen nur 1,2 oder 3 eingeben!";
    }

}
