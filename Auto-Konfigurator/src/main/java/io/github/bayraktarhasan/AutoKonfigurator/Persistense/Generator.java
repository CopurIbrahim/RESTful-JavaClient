package io.github.bayraktarhasan.AutoKonfigurator.Persistense;

import io.github.bayraktarhasan.AutoKonfigurator.Business.Ausstattung;
import io.github.bayraktarhasan.AutoKonfigurator.Business.Modell;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;



public class Generator {

    private Modell modell;
    private Ausstattung ausstattung;
    private String json;

    public Generator() {
        this.ausstattung = new Ausstattung("test", 0,"","");
        this.modell = new Modell(0,"");
        this.json = "";
    }

    public void setData() throws IOException {
        parseJson();
        //loadModell();
        //loadAusstattung();
    }

    public void loadModell() {
        String csvFilePlattform = "C:\\Users\\Haso\\Documents\\GitHub\\RESTfulJava-Client\\Auto-Konfigurator\\src\\main\\java\\io\\github\\bayraktarhasan\\AutoKonfigurator\\Persistense\\Modell.csv";
        BufferedReader br = null;
        FileReader fl = null;
        String line = "";
        String splitBy = ";";

        int first = 0;

        try {
            fl = new FileReader(csvFilePlattform);
            br = new BufferedReader(fl);

            while ((line = br.readLine()) != null) {
                String[] reader = line.split(splitBy);

                if (first != 0) {
                    double tmpPreis = Double.parseDouble(reader[0]);
                    String tmpName = reader[1];
                    Modell tmpModell = new Modell(tmpPreis, tmpName);
                    this.modell.modellHinzufuegen(tmpModell);

                } else {
                    first++;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAusstattung() {
        String csvFileModell = "C:\\Users\\Haso\\Documents\\GitHub\\RESTfulJava-Client\\Auto-Konfigurator\\src\\main\\java\\io\\github\\bayraktarhasan\\AutoKonfigurator\\Persistense\\Ausstattung.csv";
        BufferedReader br = null;
        FileReader fl = null;
        String line = "";
        String splitBy = ";";

        int first = 0;

        try {

            fl = new FileReader(csvFileModell);
            br = new BufferedReader(fl);
            while ((line = br.readLine()) != null) {
                String[] reader2 = line.split(splitBy);

                if (first != 0) {

                    String tmpModellName = reader2[0];
                    double tmpAusstattungsPreis = Double.parseDouble(reader2[1]);
                    String tmpPaketName = reader2[2];
                    String tmpBez = reader2[3];

                    Ausstattung tmpAusstattung = new Ausstattung(tmpModellName, tmpAusstattungsPreis, tmpPaketName, tmpBez);

                    this.ausstattung.paketHinzufuegen(tmpAusstattung);

                } else {
                    first++;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Modell getModell(){
        return this.modell;
    }

    public Ausstattung getAusstattung(){
        return this.ausstattung;
    }


    public String loadModellFromJson(String urlString) throws IOException {
        BufferedReader br = null;
        InputStreamReader fl = null;
        String result = "";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if(connection.getResponseCode() != 200){
                throw  new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            }

            fl = new InputStreamReader((connection.getInputStream()));
            br = new BufferedReader(fl);

            String output;

            while ((output = br.readLine()) != null) {
                result += output;

            }
            connection.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if(fl != null){
                fl.close();
            }
        }

        return result;
    }

    public void parseJson() throws IOException {
        String modellList = loadModellFromJson("http://localhost/server/restful.php?option=1");
        JSONObject modellObject = new JSONObject(modellList);
        JSONArray modellArr = modellObject.getJSONArray("modelle");

        for(int i=0; i<modellArr.length(); i++){
            Modell tmpModell = new Modell(modellArr.getJSONObject(i).getDouble("preis"),
                    modellArr.getJSONObject(i).getString("name"));
            this.modell.modellHinzufuegen(tmpModell);
        }


        String paketList = loadModellFromJson("http://localhost/server/restful.php?option=2");
        JSONObject paketObject = new JSONObject(paketList);
        JSONArray paketArr = paketObject.getJSONArray("pakete");

        for(int i=0; i<paketArr.length(); i++){
            Ausstattung tmpPaket = new Ausstattung(paketArr.getJSONObject(i).getString("modell"),
                    paketArr.getJSONObject(i).getDouble("preis"),
                    paketArr.getJSONObject(i).getString("name"),
                    paketArr.getJSONObject(i).getString("bezeichnung")
                    );
            this.ausstattung.paketHinzufuegen(tmpPaket);
        }
    }



}
