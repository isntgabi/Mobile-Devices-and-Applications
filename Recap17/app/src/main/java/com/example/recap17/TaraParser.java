package com.example.recap17;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TaraParser {
    private static final String NUME = "nume";
    private static final String POPULATIE = "populatie";
    private static final String CONTINENT = "continent";

    public static List<Tara> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareTari(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Tara> parsareTari(JSONArray jsonArray) throws JSONException {
        List<Tara> tari = new ArrayList<>();
        for(int i=0;i< jsonArray.length();i++) {
            tari.add(parsareTara(jsonArray.getJSONObject(i)));
        }
        return tari;
    }

    private static Tara parsareTara(JSONObject jsonObject) throws JSONException {
        String nume = jsonObject.getString(NUME);
        double populatie = jsonObject.getDouble(POPULATIE);
        String continent = jsonObject.getString(CONTINENT);

        return new Tara(nume, populatie, continent);
    }
}
