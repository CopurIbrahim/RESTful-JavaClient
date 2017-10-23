package io.github.bayraktarhasan.AutoKonfigurator.Persistense;

import io.github.bayraktarhasan.AutoKonfigurator.Business.Ausstattung;
import io.github.bayraktarhasan.AutoKonfigurator.Business.Modell;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

    public void setData(int wahl) throws IOException, ParserConfigurationException {
        switch (wahl){
            case 1: {
                parseCSV();
                break;
            }
            case 2: {
                parseJson();
                break;
            }
            case 3: {
                parseXML();
                break;
            }

        }
    }

    public void parseCSV(){
        loadModell();
        loadAusstattung();
    }

    public void loadModell() {
        String csvFilePlattform = "C:\\Users\\Haso\\Documents\\GitHub\\RESTful-JavaClient\\Auto-Konfigurator\\src\\main\\java\\io\\github\\bayraktarhasan\\AutoKonfigurator\\Persistense\\Modell.csv";
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
        String csvFileModell = "C:\\Users\\Haso\\Documents\\GitHub\\RESTful-JavaClient\\Auto-Konfigurator\\src\\main\\java\\io\\github\\bayraktarhasan\\AutoKonfigurator\\Persistense\\Ausstattung.csv";
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
        loadModellJson();
        loadPaketeJson();
    }

    public void loadModellJson(){
        try {
        String modellList = null;
        modellList = loadModellFromJson("http://localhost/server/restful.php?option=1");

        JSONObject modellObject = new JSONObject(modellList);
        JSONArray modellArr = modellObject.getJSONArray("modelle");

        for(int i=0; i<modellArr.length(); i++){
            Modell tmpModell = new Modell(
                    modellArr.getJSONObject(i).getDouble("preis"),
                    modellArr.getJSONObject(i).getString("name"));
            this.modell.modellHinzufuegen(tmpModell);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPaketeJson(){
        try {
            String paketList = loadModellFromJson("http://localhost/server/restful.php?option=2");
            JSONObject paketObject = new JSONObject(paketList);
            JSONArray paketArr = paketObject.getJSONArray("pakete");

            for (int i = 0; i < paketArr.length(); i++) {
                Ausstattung tmpPaket = new Ausstattung(paketArr.getJSONObject(i).getString("modell"),
                        paketArr.getJSONObject(i).getDouble("preis"),
                        paketArr.getJSONObject(i).getString("name"),
                        paketArr.getJSONObject(i).getString("bezeichnung")
                );
                this.ausstattung.paketHinzufuegen(tmpPaket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseXML(){
        try {
            loadModellXML();
            loadPaketeXML();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadModellXML(){
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new URL("http://localhost/server/restful.php?option=3").openStream());
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("modell");

            for(int i=0; i <nList.getLength(); i++){
                Node nNode = nList.item(i);

                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    Modell tmpModell = new Modell(
                            Double.parseDouble(eElement.getElementsByTagName("preis").item(0).getTextContent()),
                            eElement.getElementsByTagName("name").item(0).getTextContent()
                    );
                    this.modell.modellHinzufuegen(tmpModell);
                }
            }

            loadPaketeXML();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void loadPaketeXML() throws ParserConfigurationException, IOException {
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new URL("http://localhost/server/restful.php?option=4").openStream());
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("pakete");

            for(int i=0; i <nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Ausstattung tmpAusstattung = new Ausstattung(
                            eElement.getElementsByTagName("modell").item(0).getTextContent(),
                            Double.parseDouble(eElement.getElementsByTagName("preis").item(0).getTextContent()),
                            eElement.getElementsByTagName("name").item(0).getTextContent(),
                            eElement.getElementsByTagName("bezeichnung").item(0).getTextContent());

                    this.ausstattung.paketHinzufuegen(tmpAusstattung);
                }
            }
            } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


    }



}
