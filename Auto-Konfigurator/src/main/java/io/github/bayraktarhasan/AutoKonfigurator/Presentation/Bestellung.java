package io.github.bayraktarhasan.AutoKonfigurator.Presentation;

import io.github.bayraktarhasan.AutoKonfigurator.Business.Autokonfigurator;
import io.github.bayraktarhasan.AutoKonfigurator.Persistense.Output;

import java.util.Scanner;

public class Bestellung {
    Scanner scanString;

    private Menue menue;
    private Output output;

    public Bestellung() {
        this.scanString = new Scanner(System.in);
        this.output = new Output();
        this.menue = new Menue();
    }

    public void bestellen(Autokonfigurator auto) {
        System.out.println(this.menue.leerZeile());
        System.out.println(auto.toString());
        System.out.println(this.menue.leerZeile());
        System.out.print(this.menue.bestaetigen());
        String kaufen = scanString.nextLine();

        if (kaufen.equals("Ja") || kaufen.equals("ja")) {

            System.out.println(this.menue.infoKauf());
            System.out.println(this.menue.leerZeile());

            output.output(auto);

        } else {
            System.out.println(this.menue.leerZeile());

        }
    }
}
