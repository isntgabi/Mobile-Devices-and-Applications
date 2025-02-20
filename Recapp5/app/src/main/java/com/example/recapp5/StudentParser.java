package com.example.recapp5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentParser {
    private static final String NUME = "nume";
    private static final String MEDIE = "medie";
    private static final String SPECIALIZARE = "specializare";

    public static List<Student> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareStudenti(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    private static List<Student> parsareStudenti(JSONArray jsonArray) throws JSONException {
        List<Student> studenti = new ArrayList<>();
        for(int i=0;i< jsonArray.length();i++){
            studenti.add(parsareStudent(jsonArray.getJSONObject(i)));
        }
        return studenti;
    }

    private static Student parsareStudent(JSONObject jsonObject) throws JSONException {
        String nume = jsonObject.getString(NUME);
        float medie = (float) jsonObject.getDouble(MEDIE);
        String specializare = jsonObject.getString(SPECIALIZARE);

        return new Student(nume,medie,specializare);
    }
}
