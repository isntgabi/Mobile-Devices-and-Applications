package com.example.recapitulare5;

import android.content.Intent;
import android.os.Bundle;
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

    private List<Energizant> energizante = new ArrayList<>();

    private ListView lvEnergizante;

    private ActivityResultLauncher<Intent> launcher;

    private int selectedPosition;

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

        FloatingActionButton fabAdauga = findViewById(R.id.fabAdauga);
        fabAdauga.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdaugaEnergizant.class);
            launcher.launch(intent);
        });

        lvEnergizante = findViewById(R.id.lvEnergizante);

        lvEnergizante.setOnItemClickListener((adapterView, view, position, l) -> {
            selectedPosition = position;
            Intent intent = new Intent(getApplicationContext(), AdaugaEnergizant.class);
            intent.putExtra("edit", energizante.get(position));
            launcher.launch(intent);
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getData().hasExtra("adaugareEnergizant")) {
                Intent intent = result.getData();
                Energizant energizant = (Energizant) intent.getSerializableExtra("adaugareEnergizant");
                if (energizant != null) {
                    energizante.add(energizant);
                    AdapterEnergizant adapter = new AdapterEnergizant(getApplicationContext(), R.layout.view_energizante, energizante, getLayoutInflater());
                    lvEnergizante.setAdapter(adapter);
                }
            } else if(result.getData().hasExtra("edit")) {
                Intent intent = result.getData();
                Energizant energizant = (Energizant) intent.getSerializableExtra("edit");
                if(energizant!=null) {
                    Energizant energizantActualizat = energizante.get(selectedPosition);
                    energizantActualizat.setDenumire(energizant.getDenumire());
                    energizantActualizat.setCantitate(energizant.getCantitate());
                    energizantActualizat.setAroma(energizant.getAroma());
                    energizantActualizat.setIndulcit(energizant.getIndulcit());
                    AdapterEnergizant adapter = (AdapterEnergizant) lvEnergizante.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}