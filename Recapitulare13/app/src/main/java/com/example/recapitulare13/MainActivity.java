package com.example.recapitulare13;

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

    private ListView lvAplicatii;
    private List<Aplicatie> aplicatii = new ArrayList<>();
    private static final String url = "https://www.jsonkeeper.com/b/53A8";

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

        lvAplicatii = findViewById(R.id.lvAplicatii);

        ArrayAdapter<Aplicatie> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, aplicatii);
        lvAplicatii.setAdapter(adapter);

        Retea();
    }

    private void Retea() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(url);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(() -> {
                    getAplicatiiJson(json);
                });
            }
        };

        thread.start();
    }
    private void getAplicatiiJson(String json) {
        aplicatii.addAll(AplicatieParser.parsareJson(json));
        ArrayAdapter<Aplicatie> adapter = (ArrayAdapter<Aplicatie>) lvAplicatii.getAdapter();
        adapter.notifyDataSetChanged();
    }
}