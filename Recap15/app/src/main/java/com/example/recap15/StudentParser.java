package com.example.recap15;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentParser {
    private static final String NUME = "nume";
    private static final String FACULTATE = "facultate";
    private static final String MEDIE = "medie";


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
        for(int i=0;i< jsonArray.length();i++) {
            studenti.add(parsareStudent(jsonArray.getJSONObject(i)));
        }
        return studenti;
    }

    private static Student parsareStudent(JSONObject jsonObject) throws JSONException {
        String nume = jsonObject.getString(NUME);
        String facultate = jsonObject.getString(FACULTATE);
        double medie = jsonObject.getDouble(MEDIE);

        return new Student(nume, facultate, medie);
    }
}
