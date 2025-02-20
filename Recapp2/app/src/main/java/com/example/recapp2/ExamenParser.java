package com.example.recapp2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExamenParser {
    private static final String DENUMIRE = "denumire";
    private static final String PUNCTAJ = "punctaj";
    private static final String TIP = "tip";

    public static List<Examen> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareExamene(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Examen> parsareExamene(JSONArray jsonArray) throws JSONException {
        List<Examen> examene = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++) {
            examene.add(parsareExamen(jsonArray.getJSONObject(i)));
        }
        return examene;
    }

    private static Examen parsareExamen(JSONObject jsonObject) throws JSONException {
        String denumire = jsonObject.getString(DENUMIRE);
        int punctaj = jsonObject.getInt(PUNCTAJ);
        String tip = jsonObject.getString(TIP);

        return new Examen(denumire,punctaj,tip);
    }
}
