package com.example.recapp4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BursaParser {
    private static final String DENUMIRE = "denumire";
    private static final String SUMA = "suma";
    private static final String TIP = "tip";

    public static List<Bursa> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareBurse(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Bursa> parsareBurse(JSONArray jsonArray) throws JSONException {
        List<Bursa> burse = new ArrayList<>();
        for(int i=0;i< jsonArray.length();i++) {
            burse.add(parsareBursa(jsonArray.getJSONObject(i)));
        }
        return burse;
    }

    private static Bursa parsareBursa(JSONObject jsonObject) throws JSONException {
        String denumire = jsonObject.getString(DENUMIRE);
        int suma = jsonObject.getInt(SUMA);
        String tip = jsonObject.getString(TIP);

        return new Bursa(denumire,suma,tip);
    }
}
