package com.example.recap11;

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

    private ListView lvMedicamente;
    private List<Medicament> medicamente = new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher;

    private ArrayAdapter<Medicament> lvAdapter;

    private static final String url = "https://www.jsonkeeper.com/b/X87B";


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

        lvMedicamente = findViewById(R.id.lvMedicamente);

        MedicamentDB instance = MedicamentDB.getInstance(getApplicationContext());

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           Intent intent = result.getData();
           Medicament medicament = (Medicament) intent.getSerializableExtra("medicamentFromIntent");
           if(medicament != null) {
               instance.getMedicamentDAO().insertMedicament(medicament);
               medicamente.clear();
               medicamente.addAll(instance.getMedicamentDAO().getMedicamente());
               lvAdapter.notifyDataSetChanged();
           }
        });

        lvAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, medicamente);
        lvMedicamente.setAdapter(lvAdapter);
        incarcareDinHttps();
    }

    public void incarcareDinHttps() {
        Thread thread = new Thread() {

            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(url);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(() -> {
                    getMedicamenteJson(json);
                });
            }
        };

        thread.start();
    }

    private void getMedicamenteJson(String json) {
        medicamente.addAll(MedicamentParser.parsareJson(json));
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
            Intent intent = new Intent(getApplicationContext(), AdaugaMedicament.class);
            launcher.launch(intent);
            return true;
        }
        return false;
    }
}