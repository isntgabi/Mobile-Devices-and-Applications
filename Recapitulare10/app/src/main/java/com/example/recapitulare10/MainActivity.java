package com.example.recapitulare10;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvCarti;

    private List<Carte> carti = new ArrayList<>();

    private ActivityResultLauncher<Intent> launcher;

    private ArrayAdapter<Carte> lvAdapter;

    private static final String URL = "https://www.jsonkeeper.com/b/Z4FX";

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

        lvCarti = findViewById(R.id.lvCarti);

        CarteDB instance = CarteDB.getInstance(getApplicationContext());


        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           Intent intent = result.getData();
           Carte carte = (Carte) intent.getSerializableExtra("carteFromIntent");
           if (carte != null) {
               instance.getCarteDAO().insert(carte);
               carti.clear();
               carti.addAll(instance.getCarteDAO().getCarti());
               lvAdapter.notifyDataSetChanged();
           }
        });

        lvAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, carti);
        lvCarti.setAdapter(lvAdapter);

        FloatingActionButton fabAdauga = findViewById(R.id.fabAdauga);
        fabAdauga.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdaugaCarte.class);
            launcher.launch(intent);
        });

        incarcareCartiDinRetea();
    }




    private void incarcareCartiDinRetea() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(URL);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(() -> {
                    getCartiJson(json);
                });
            }
        };

        thread.start();
    }

    private void getCartiJson(String json) {
        carti.addAll(CarteParser.parsareJson(json));
        ArrayAdapter<Carte> adapter = (ArrayAdapter<Carte>) lvCarti.getAdapter();
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.Optiune) {
            Intent intent = new Intent(getApplicationContext(), AdaugaCarte.class);
            launcher.launch(intent);
            return true;
        }

        return false;
    }


}