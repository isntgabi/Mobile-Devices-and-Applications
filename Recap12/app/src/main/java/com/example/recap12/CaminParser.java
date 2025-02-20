package com.example.recap12;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaminParser {

    private static final String NUME = "nume";
    private static final String NRPERSOANE = "nrPersoane";
    private static final String DATA = "dataInf";


    public static List<Camin> parsareJson(String json) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);
            return parsareCamine(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Camin> parsareCamine(JSONArray jsonArray) throws JSONException {
        List<Camin> camine = new ArrayList<>();
        for(int i=0;i< jsonArray.length();i++) {
            camine.add(parsareCamin(jsonArray.getJSONObject(i)));
        }

        return camine;
    }
    private static Camin parsareCamin(JSONObject jsonObject) throws JSONException {
        String nume = jsonObject.getString(NUME);
        int nrPersoane = jsonObject.getInt(NRPERSOANE);
        String data = jsonObject.getString(DATA);
        Date dataInf = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            dataInf = sdf.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new Camin(nume,nrPersoane,dataInf);
    }
}
