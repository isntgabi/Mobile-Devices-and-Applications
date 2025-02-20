package com.example.recap16;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrasParser {
    private static final String DENUMIRE = "denumire";
    private static final String POPULATIE = "populatie";

    public static List<Oras> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareOrase(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Oras> parsareOrase(JSONArray jsonArray) throws JSONException {
        List<Oras> orase = new ArrayList<>();
        for(int i=0;i< jsonArray.length();i++) {
            orase.add(parsareOras(jsonArray.getJSONObject(i)));
        }
        return orase;
    }

    private static Oras parsareOras(JSONObject jsonObject) throws JSONException {

        String denumire = jsonObject.getString(DENUMIRE);
        double populatie = jsonObject.getDouble(POPULATIE);

        return new Oras(denumire,populatie);

    }
}
