package com.example.recap12;

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

    private ListView lvCamine;

    private List<Camin> camine = new ArrayList<>();

    private static final String url = "https://www.jsonkeeper.com/b/XTD7";
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

        lvCamine = findViewById(R.id.lvCamine);

        ArrayAdapter<Camin> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, camine);
        lvCamine.setAdapter(adapter);

        incarcareCamine();

    }

    private void incarcareCamine() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(url);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(() -> {
                   getCamine(json);
                });
            }
        };

        thread.start();
    }


    private void getCamine(String json) {
        camine.addAll(CaminParser.parsareJson(json));
        ArrayAdapter<Camin> adapter = (ArrayAdapter<Camin>) lvCamine.getAdapter();
        adapter.notifyDataSetChanged();
    }
}