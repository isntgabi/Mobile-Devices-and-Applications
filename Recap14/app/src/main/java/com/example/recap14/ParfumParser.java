package com.example.recap14;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParfumParser {
    private static final String DENUMIRE = "denumire";
    private static final String AROMA  = "aroma";
    private static final String ML  = "ml";


    public static List<Parfum> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareParfumuri(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Parfum> parsareParfumuri(JSONArray jsonArray) throws JSONException {
        List<Parfum> parfumuri = new ArrayList<>();
        for(int i=0;i< jsonArray.length();i++) {
            parfumuri.add(parsareParfum(jsonArray.getJSONObject(i)));
        }

        return parfumuri;
    }
    private static Parfum parsareParfum(JSONObject jsonObject) throws JSONException {
        String denumire = jsonObject.getString(DENUMIRE);
        String aroma = jsonObject.getString(AROMA);
        int ml = jsonObject.getInt(ML);

        return new Parfum(denumire,aroma,ml);
    }
}
