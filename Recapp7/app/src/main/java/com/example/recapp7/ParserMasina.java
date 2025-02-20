package com.example.recapp7;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParserMasina {
    private static final String MARCA = "marca";
    private static final String PRET = "pret";
    private static final String TIP = "tip";

    public static List<Masina> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareMasini(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Masina> parsareMasini(JSONArray jsonArray) throws JSONException {
        List<Masina> masini = new ArrayList<>();
        for(int i=0;i< jsonArray.length();i++) {
            masini.add(parsareMasina(jsonArray.getJSONObject(i)));
        }
        return masini;
    }

    private static Masina parsareMasina(JSONObject jsonObject) throws JSONException {
        String marca = jsonObject.getString(MARCA);
        float pret = (float) jsonObject.getDouble(PRET);
        String tip = jsonObject.getString(TIP);

        return new Masina(marca,pret,tip);
    }
}
