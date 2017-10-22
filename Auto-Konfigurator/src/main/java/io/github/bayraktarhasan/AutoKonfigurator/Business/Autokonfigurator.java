package io.github.bayraktarhasan.AutoKonfigurator.Business;

import io.github.bayraktarhasan.AutoKonfigurator.Persistense.Generator;
import io.github.bayraktarhasan.AutoKonfigurator.Presentation.Menue;

import java.util.ArrayList;

public class Autokonfigurator {

    private double preis;
    private Menue menue;
    private Modell modell;
    private Ausstattung ausstattung;
    private ArrayList<Ausstattung> ausstattungsList;


    public Autokonfigurator(Generator generator) {
        this.menue = new Menue();


        this.ausstattungsList = new ArrayList<Ausstattung>();
        this.modell = generator.getModell();
        this.ausstattung = generator.getAusstattung();
    }

    public String listPlattformen() {
        String temp = "";
        for (int i = 0; i < this.modell.getModellList().size(); i++) {
            temp += i + " " + this.modell.getModellList().get(i).toString() + "\n";
        }
        return temp;
    }

    public void setPlattform(int wahl) {
        this.ausstattungsList = new ArrayList<Ausstattung>();
        this.modell = this.modell.getModellList().get(wahl);
    }

    public void setPaketList() {

        this.ausstattungsList = new ArrayList<Ausstattung>();
        for (int i = 0; i < this.ausstattung.getAusstattungList().size(); i++) {
            if (this.modell.getName().equals(this.ausstattung.getAusstattungList().get(i).getModell())) {
                this.ausstattungsList.add(this.ausstattung.getAusstattungList().get(i));
            }
        }
    }

    public boolean listPakete() {

        if (this.ausstattungsList.size() > 0) {
            for (int i = 0; i < this.ausstattungsList.size(); i++) {
                System.out.println(i + " " + this.ausstattungsList.get(i).toString());
            }
        }
        else {
            System.out.println(this.menue.fehlerPaket());
            return false;
        }

        return true;

    }

    public void setAusstattungsPaket(int wahl) {
        this.ausstattung = this.ausstattungsList.get(wahl);
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public double gesamtBerechnen() {
        if(this.ausstattung != null && this.modell != null) {
            return this.preis = this.modell.getPreis() + this.ausstattung.getPreis();
        }
        else if (this.modell != null) {
            return this.preis = this.modell.getPreis();
        }
        else {
            return 0;
        }

    }

    public String toString() {
        if (this.modell != null && this.ausstattung != null) {
            return 	  "Auto: " + this.modell.getName() + " | "
                    + " Preis: " + this.modell.getPreis() + "€" + "\n"
                    + "Ausstattungspaket: " + this.ausstattung.getName() + " | " + " "
                    + "Preis: " + this.ausstattung.getPreis() + "€"
                    + "\nGesamt: " + gesamtBerechnen() + " €";
        }

        else if (this.modell != null && this.ausstattung == null) {
            return "Auto: " + this.modell.getName() + " | "
                    + " Preis :" + this.modell.getPreis() + "€" +
                    "\nGesamt: " + gesamtBerechnen() + " €";
        }
        else {
            return this.menue.fehlerAuto();
        }

    }

}
