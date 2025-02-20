package com.example.recapp1;

import android.content.Intent;
import android.content.SharedPreferences;
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

    private ListView lvOchelari;
    private List<Ochelari> ochelarii = new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher;

    private ArrayAdapter<Ochelari> adapter;

    private int selectedItem;

    private static final String url = "https://www.jsonkeeper.com/b/0B42";

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

        lvOchelari = findViewById(R.id.lvOchelari);

        adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, ochelarii);
        lvOchelari.setAdapter(adapter);

        lvOchelari.setOnItemClickListener((adapterView,view,position, l) -> {
            selectedItem = position;
            Ochelari ochelari = ochelarii.get(position);
            Intent intent = new Intent(getApplicationContext(), AdaugaOchelari.class);
            intent.putExtra("edit", ochelari);
            launcher.launch(intent);
        });

        OchelariDB instance = OchelariDB.getInstance(getApplicationContext());

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           if (result.getData().hasExtra("ochelariFromIntent")) {
               Intent intent = result.getData();
               Ochelari ochelari = (Ochelari) intent.getSerializableExtra("ochelariFromIntent");
               if(ochelari!=null) {
                   //ochelarii.add(ochelari);
                   instance.getOchelariDAO().insertOchelari(ochelari);
                   ochelarii.clear();
                   ochelarii.addAll(instance.getOchelariDAO().getOchelari());
                   adapter.notifyDataSetChanged();
               }
           } else if (result.getData().hasExtra("edit")) {
                Intent intent = result.getData();
                Ochelari ochelari = (Ochelari) intent.getSerializableExtra("edit");
                if(ochelari!=null) {
                    Ochelari ochelariDeActualizat = ochelarii.get(selectedItem);
                    ochelariDeActualizat.setFirma(ochelari.getFirma());
                    ochelariDeActualizat.setDioptrie(ochelari.getDioptrie());
                    ochelariDeActualizat.setTip(ochelari.getTip());
                    instance.getOchelariDAO().updateOchelari(ochelariDeActualizat);
                    ochelarii.clear();
                    ochelarii.addAll(instance.getOchelariDAO().getOchelari());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        Retea();

        SharedPreferences shp = getSharedPreferences("local", MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putString("token", "hello");
        editor.apply();
    }

    private void Retea() {
        Thread thread = new Thread() {

            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(url);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(() -> {
                   preluareOchelari(json);
                });
            }
        };

        thread.start();
    }

    private void preluareOchelari(String json) {
        ochelarii.addAll(ParsareOchelari.parsareJson(json));
        ArrayAdapter<Ochelari> adapter1 = (ArrayAdapter<Ochelari>) lvOchelari.getAdapter();
        adapter1.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.Optiune) {
            Intent intent = new Intent(getApplicationContext(), AdaugaOchelari.class);
            launcher.launch(intent);
            return true;
        }
        return false;
    }
}