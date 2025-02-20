package com.example.recap14;

import android.graphics.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String url = "https://www.jsonkeeper.com/b/4KN1";
    private ListView lvParfumuri;
    private List<Parfum> parfumuri = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvParfumuri = findViewById(R.id.lvParfumuri);

        ArrayAdapter<Parfum> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,parfumuri);
        lvParfumuri.setAdapter(adapter);

        Retea();
    }

    private void Retea() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(url);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(() -> {
                   getParfumuri(json);
                });
            }
        };

        thread.start();
    }

    private void getParfumuri(String json) {
        parfumuri.addAll(ParfumParser.parsareJson(json));
        ArrayAdapter<Parfum> adapter = (ArrayAdapter<Parfum>) lvParfumuri.getAdapter();
        adapter.notifyDataSetChanged();
    }
}