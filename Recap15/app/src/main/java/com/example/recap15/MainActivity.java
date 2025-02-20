package com.example.recap15;

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

    private ListView lvStudenti;
    private List<Student> studenti = new ArrayList<>();
    private static final String url = "https://www.jsonkeeper.com/b/C3Q2";
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

        lvStudenti = findViewById(R.id.lvStudenti);

        ArrayAdapter<Student> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, studenti);
        lvStudenti.setAdapter(adapter);

        Retea();
    }

    private void Retea() {
        Thread thread = new Thread() {

            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(url);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(() -> {
                   getStudenti(json);
                });
            }
        };

        thread.start();
    }
    private void getStudenti(String json) {
        studenti.addAll(StudentParser.parsareJson(json));
        ArrayAdapter<Student> adapter = (ArrayAdapter<Student>) lvStudenti.getAdapter();
        adapter.notifyDataSetChanged();
    }
}