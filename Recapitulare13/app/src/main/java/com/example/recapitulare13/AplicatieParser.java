package com.example.recapitulare13;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AplicatieParser {
    private static final String DENUMIRE = "denumire";
    private static final String STOCARE = "stocare";
    private static final String AUTOR = "autor";

    public static List<Aplicatie> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareAplicatii(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Aplicatie> parsareAplicatii(JSONArray jsonArray) throws JSONException {
        List<Aplicatie> aplicatii = new ArrayList<>();
        for(int i=0;i< jsonArray.length();i++) {
            aplicatii.add(parsareAplicatie(jsonArray.getJSONObject(i)));
        }

        return aplicatii;
    }
    private static Aplicatie parsareAplicatie(JSONObject jsonObject) throws JSONException {
        String denumire = jsonObject.getString(DENUMIRE);
        String autor = jsonObject.getString(AUTOR);
        double stocare = jsonObject.getDouble(STOCARE);

        return new Aplicatie(denumire,stocare,autor);
    }
}
