package com.example.recapp2;

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

    private ListView lvExamene;
    private List<Examen> examene = new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher;
    private ArrayAdapter<Examen> adapter;

    private int selectedItem;

    private static final String url = "https://www.jsonkeeper.com/b/TWQT";
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

        lvExamene = findViewById(R.id.lvExamene);

        adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,examene);
        lvExamene.setAdapter(adapter);



        lvExamene.setOnItemClickListener((adapterView, view, position, l) -> {
            selectedItem = position;
            Examen examen = examene.get(position);
            Intent intent = new Intent(getApplicationContext(), AdaugaExamen.class);
            intent.putExtra("edit", examen);
            launcher.launch(intent);
        });

        ExamenDB instance = ExamenDB.getInstance(getApplicationContext());

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getData().hasExtra("add")) {
                Intent intent = result.getData();
                Examen examen = (Examen) intent.getSerializableExtra("add");
                if (examen != null) {
                    instance.getExamenDAO().insertExamen(examen);
                    examene.clear();
                    examene.addAll(instance.getExamenDAO().getExamene());
                    adapter.notifyDataSetChanged();
                }
            } else if (result.getData().hasExtra("edit")) {
                Intent intent = result.getData();
                Examen examen = (Examen) intent.getSerializableExtra("edit");
                if (examen != null) {
                    Examen examenDeActualizat = examene.get(selectedItem);
                    examenDeActualizat.setDenumire(examen.getDenumire());
                    examenDeActualizat.setPunctaj(examen.getPunctaj());
                    examenDeActualizat.setTip(examen.getTip());
                    instance.getExamenDAO().updateExamen(examenDeActualizat);
                    examene.clear();
                    examene.addAll(instance.getExamenDAO().getExamene());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        Retea();
    }

    private void Retea() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(url);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(() -> {
                   preluareExamene(json);
                });
            }
        };
        thread.start();
    }

    private void preluareExamene(String json) {
        examene.addAll(ExamenParser.parsareJson(json));
        ArrayAdapter<Examen> adapter1 = (ArrayAdapter<Examen>) lvExamene.getAdapter();
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
            Intent intent = new Intent(getApplicationContext(), AdaugaExamen.class);
            launcher.launch(intent);
            //startActivity(intent);
            return true;
        }
        return false;
    }
}