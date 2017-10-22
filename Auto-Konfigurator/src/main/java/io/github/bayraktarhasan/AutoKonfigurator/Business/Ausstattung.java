package io.github.bayraktarhasan.AutoKonfigurator.Business;

import java.util.ArrayList;

public class Ausstattung {

    private double preis;
    private String name;
    private String bezeichnung;
    private String modell;
    private ArrayList<Ausstattung> ausstattungList;

    public Ausstattung(String modell, double preis, String name, String bezeichnung) {
        this.modell = modell;
        this.preis = preis;
        this.name = name;
        this.bezeichnung = bezeichnung;
        this.ausstattungList = new ArrayList<Ausstattung>();

    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void paketHinzufuegen(Ausstattung ausstattung) {
        this.ausstattungList.add(ausstattung);
    }

    public ArrayList<Ausstattung> getAusstattungList() {
        return ausstattungList;
    }

    public String toString() {
        return "Ausstattungspaket: " + this.name + " | " + "Preis: " + this.preis + " | " + "Info:  "
                + this.bezeichnung;
    }


}
