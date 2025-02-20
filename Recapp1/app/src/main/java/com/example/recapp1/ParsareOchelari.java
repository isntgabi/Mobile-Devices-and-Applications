package com.example.recapp1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParsareOchelari {
    private static final String FIRMA = "firma";
    private static final String DIOPTRIE = "dioptrie";
    private static final String TIP = "tip";

    public static List<Ochelari> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareLista(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Ochelari> parsareLista(JSONArray jsonArray) throws JSONException {
        List<Ochelari> lista = new ArrayList<>();
        for(int i=0;i< jsonArray.length();i++) {
            lista.add(parsareOchelari(jsonArray.getJSONObject(i)));
        }
        return lista;
    }


    private static Ochelari parsareOchelari(JSONObject jsonObject) throws JSONException {
        String firma = jsonObject.getString(FIRMA);
        float dioptrie = (float) jsonObject.getDouble(DIOPTRIE);
        String tip = jsonObject.getString(TIP);

        return new Ochelari(firma,dioptrie,tip);
    }
}
