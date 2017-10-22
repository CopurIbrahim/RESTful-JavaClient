package io.github.bayraktarhasan.AutoKonfigurator.Business;

import java.util.ArrayList;

public class Modell {
    private double preis;
    private String name;
    private ArrayList<Modell> modellList;

    public Modell(double preis, String name) {
        this.preis = preis;
        this.name = name;
        this.modellList = new ArrayList<Modell>();

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

    public void modellHinzufuegen(Modell modell) {
        this.modellList.add(modell);
    }

    public ArrayList<Modell> getModellList() {
        return this.modellList;
    }

    public String toString() {
        return "Name: " + this.name + " | " + "Preis: " + this.preis + " €";
    }
}
