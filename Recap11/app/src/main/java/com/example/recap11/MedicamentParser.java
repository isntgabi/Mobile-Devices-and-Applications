package com.example.recap11;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicamentParser {
    private static final String DENUMIRE = "denumire";
    private static final String DATA = "dataExpirare";
    private static final String SUBSTANTA = "substantaPrincipala";

    public static List<Medicament> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareMedicamente(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Medicament> parsareMedicamente(JSONArray jsonArray) throws JSONException {
        List<Medicament> medicamente = new ArrayList<>();
        for(int i=0;i< jsonArray.length();i++){
            medicamente.add(parsareMedicament(jsonArray.getJSONObject(i)));
        }

        return medicamente;
    }

    private static Medicament parsareMedicament(JSONObject jsonObject) throws JSONException {
        String denumire = jsonObject.getString(DENUMIRE);
        String data = jsonObject.getString(DATA);
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            date = sdf.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String substanta = jsonObject.getString(SUBSTANTA);

        return new Medicament(denumire,date,substanta);
    }
}
