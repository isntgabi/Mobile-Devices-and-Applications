package com.example.recapp6;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BonParser {
    private static final String COD = "cod";
    private static final String SUMA = "suma";
    private static final String PLATA = "plata";

    public static List<Bon> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareBonuri(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Bon> parsareBonuri(JSONArray jsonArray) throws JSONException {
        List<Bon> bonuri = new ArrayList<>();
        for(int i=0;i< jsonArray.length();i++) {
            bonuri.add(parsareBon(jsonArray.getJSONObject(i)));
        }
        return bonuri;
    }

    private static Bon parsareBon(JSONObject jsonObject) throws JSONException {
        String cod = jsonObject.getString(COD);
        float suma = (float) jsonObject.getDouble(SUMA);
        String plata = jsonObject.getString(PLATA);

        return new Bon(cod,suma,plata);
    }
}
