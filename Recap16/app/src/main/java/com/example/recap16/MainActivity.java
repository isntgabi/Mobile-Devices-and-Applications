package com.example.recap16;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvOrase;

    private List<Oras> orase = new ArrayList<>();

    private ActivityResultLauncher<Intent> launcher;

    private ArrayAdapter<Oras> lvAdapter;

    private static final String url = "https://www.jsonkeeper.com/b/1Z5R";

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

        lvOrase = findViewById(R.id.lvOrase);

        OrasDB instance = OrasDB.getInstance(getApplicationContext());

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           Intent intent = result.getData();
           Oras oras = (Oras) intent.getSerializableExtra("orasFromIntent");
           if(oras!=null){
               //orase.add(oras);
               instance.getOrasDAO().insertOras(oras);
               orase.clear();
               orase.addAll(instance.getOrasDAO().getOrase());
               lvAdapter.notifyDataSetChanged();
           }
        });

        lvAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, orase);
        lvOrase.setAdapter(lvAdapter);

        FloatingActionButton fabAdauga = findViewById(R.id.fabAdauga);

        fabAdauga.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdaugaOras.class);
            launcher.launch(intent);
        });

        Retea();
    }

    private void Retea() {
        Thread thread = new Thread(){

            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(url);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(() -> {
                   getOrase(json);
                });
            }
        };

        thread.start();
    }

    private void getOrase(String json) {
        orase.addAll(OrasParser.parsareJson(json));
        lvAdapter.notifyDataSetChanged();
    }
}