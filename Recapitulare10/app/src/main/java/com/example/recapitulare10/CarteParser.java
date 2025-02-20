package com.example.recapitulare10;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarteParser {
    private static final String TITLU = "titlu";
    private static final String DATAPUBLICARE = "dataPublicare";
    private static final String PRET = "pret";
    private static final String TIP = "tip";

    public static List<Carte> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareCarti(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Carte> parsareCarti(JSONArray jsonArray) throws JSONException {
       List<Carte> carti = new ArrayList<>();
       for (int i=0;i< jsonArray.length();i++) {
           carti.add(parsareCarte(jsonArray.getJSONObject(i)));
       }
       return carti;
    }

    private static Carte parsareCarte(JSONObject jsonObject) throws JSONException {
        String titlu = jsonObject.getString(TITLU);
        String data = jsonObject.getString(DATAPUBLICARE);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date dataPublicare = null;
        try {
            dataPublicare = sdf.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        double pret = jsonObject.getDouble(PRET);
        String tip = jsonObject.getString(TIP);

        return new Carte(titlu,dataPublicare,pret,tip);
    }
}
