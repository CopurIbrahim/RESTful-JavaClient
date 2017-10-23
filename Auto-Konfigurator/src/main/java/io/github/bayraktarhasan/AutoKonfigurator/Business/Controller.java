package io.github.bayraktarhasan.AutoKonfigurator.Business;

import io.github.bayraktarhasan.AutoKonfigurator.Persistense.Generator;
import io.github.bayraktarhasan.AutoKonfigurator.Presentation.Bestellung;
import io.github.bayraktarhasan.AutoKonfigurator.Presentation.Menue;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Controller {

    Scanner scanInt;
    Scanner scanString;

    private Generator generator;
    private Autokonfigurator auto;
    private Bestellung bestellung;
    private Menue menue;

    private int wahl;
    private int dbWahl;

    public Controller() throws IOException, ParserConfigurationException {

        this.scanInt = new Scanner(System.in);
        this.scanString = new Scanner(System.in);

        this.menue = new Menue();

        this.generator = new Generator();
        this.dbWahl = 2;
        this.generator.setData(this.dbWahl);

        this.wahl = 0;

        this.bestellung = new Bestellung();
        this.auto = new Autokonfigurator(generator);
    }

    public void startProg() throws IOException, ParserConfigurationException {
        int s = 61;
        while (s != 0) {
            this.menue.menueAnzeigen();
            s= scanInt.nextInt();
            switch (s) {
                case 1: {
                    System.out.println(this.menue.headerPlattform());
                    String plattformen = this.auto.listPlattformen();

                    System.out.println(plattformen);
                    System.out.print(this.menue.wahlText());

                    this.wahl = scanInt.nextInt();
                    this.auto.setPlattform(wahl);
                    System.out.println(this.menue.leerZeile());
                    break;

                }

                case 2: {
                    System.out.println(this.menue.headerPaket());
                    this.auto.setPaketList();
                    boolean wahl = this.auto.listPakete();

                    if (wahl) {
                        System.out.print(this.menue.wahlText());
                        int wahlPaket = scanInt.nextInt();
                        this.auto.setAusstattungsPaket(wahlPaket);
                    }

                    System.out.println(this.menue.leerZeile());
                    break;

                }
                case 3: {

                    this.bestellung.bestellen(this.auto);
                    reset();



                    break;
                }

                case 4: {

                    System.exit(0);
                }
                default:
                    System.out.println(this.menue.fehler());
                    scanString.close();
                    break;
            }
        }

    }

    public void reset() throws IOException, ParserConfigurationException {
        this.generator = new Generator();
        this.generator.setData(this.dbWahl);
        this.bestellung = new Bestellung();
        this.auto = new Autokonfigurator(generator);
    }

}
