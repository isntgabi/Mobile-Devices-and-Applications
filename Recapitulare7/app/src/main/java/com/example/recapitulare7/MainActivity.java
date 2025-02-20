package com.example.recapitulare7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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

    private int selectedPosition;
    private ListView lvFacultati;
    private List<Facultate> facultati = new ArrayList<>();

    private ActivityResultLauncher<Intent> launcher;
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

        lvFacultati = findViewById(R.id.lvFacultati);

        lvFacultati.setOnItemClickListener((adapterView, view, position, l) -> {
            selectedPosition = position;
            Facultate facultate = facultati.get(position);
            Intent intent = new Intent(getApplicationContext(), AdaugaFacultate.class);
            intent.putExtra("edit", facultate);
            launcher.launch(intent);
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getData().hasExtra("adaugaFacultate")) {
                Intent intent = result.getData();
                Facultate facultate = (Facultate) intent.getSerializableExtra("adaugaFacultate");
                if (facultate != null) {
                    facultati.add(facultate);
                    //ArrayAdapter<Facultate> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, facultati);
                    AdapterFacultate adapter = new AdapterFacultate(getApplicationContext(), R.layout.view_facultati, facultati, getLayoutInflater());
                    lvFacultati.setAdapter(adapter);
                }
            } else if (result.getData().hasExtra("edit")) {
                Intent intent = result.getData();
                Facultate facultate = (Facultate) intent.getSerializableExtra("edit");
                if (facultate != null) {
                    Facultate facultateActualizata = facultati.get(selectedPosition);

                    facultateActualizata.setDenumire(facultate.getDenumire());
                    facultateActualizata.setMedieIntrare(facultate.getMedieIntrare());
                    facultateActualizata.setDataExaminarii(facultate.getDataExaminarii());
                    facultateActualizata.setTip(facultate.getTip());
                    facultateActualizata.setExamen(facultate.getExamen());

                    AdapterFacultate adapter = (AdapterFacultate) lvFacultati.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        FloatingActionButton fabAdauga = findViewById(R.id.fabAdauga);
        fabAdauga.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdaugaFacultate.class);
            launcher.launch(intent);
        });
    }
}