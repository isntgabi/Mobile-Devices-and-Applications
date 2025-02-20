package com.example.recap17;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvTari;
    private List<Tara> tari = new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher;

    private ArrayAdapter<Tara> lvAdapter;

    private static final String url = "https://www.jsonkeeper.com/b/KRE6";

    private int pozitieSalvata;

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

        lvTari = findViewById(R.id.lvTari);

        lvTari.setOnItemClickListener((adapterView, view, position, l) -> {
            pozitieSalvata = position;
            Intent intent = new Intent(getApplicationContext(), AdaugaTara.class);
            intent.putExtra("edit", tari.get(position));
            launcher.launch(intent);
        });

        TaraDB instance = TaraDB.getInstance(getApplicationContext());

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getData().hasExtra("taraFromIntent")) {
                Intent intent = result.getData();
                Tara tara = (Tara) intent.getSerializableExtra("taraFromIntent");
                if (tara != null) {
                    tari.add(tara);
                    //instance.getTaraDAO().insertTara(tara);
                    //tari.clear();
                    //tari.addAll(instance.getTaraDAO().getTari());
                    lvAdapter.notifyDataSetChanged();
                }
            } else if (result.getData().hasExtra("edit")) {
                Intent intent = result.getData();
                Tara tara = (Tara) intent.getSerializableExtra("edit");
                if (tara != null) {
                    Tara taraActualizata = tari.get(pozitieSalvata);
                    taraActualizata.setNume(tara.getNume());
                    taraActualizata.setPopulatie(tara.getPopulatie());
                    taraActualizata.setContinent(tara.getContinent());
                    lvAdapter.notifyDataSetChanged();
                }
            }
        });

        lvAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tari);
        lvTari.setAdapter(lvAdapter);

        //Retea();
    }


    private void Retea() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(url);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(() -> {
                   getTari(json);
                });
            }
        };
        thread.start();
    }

    private void getTari(String json) {
        tari.addAll(TaraParser.parsareJson(json));
        lvAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.Optiune) {
            Intent intent = new Intent(getApplicationContext(), AdaugaTara.class);
            launcher.launch(intent);
            return true;
        }

        return false;
    }
}