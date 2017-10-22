package io.github.bayraktarhasan.AutoKonfigurator.Persistense;

import io.github.bayraktarhasan.AutoKonfigurator.Business.Autokonfigurator;

import java.io.*;

public class Output {
    private Writer writer;

    public Output() {
        this.writer = null;
    }

    public void output(Autokonfigurator auto) {

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("C:\\Users\\Haso\\Desktop\\Bestellung.txt"), "utf-8"));

            try {
                writer.write(auto.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.exit(0);
    }
}
