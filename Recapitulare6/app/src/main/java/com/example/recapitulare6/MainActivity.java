package com.example.recapitulare6;

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

    private ListView lvVehicule;

    private List<Vehicul> vehicule = new ArrayList<>();

    private ActivityResultLauncher<Intent> launcher;

    private int positionItem;

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

        FloatingActionButton fabAdaugare = findViewById(R.id.fabAdaugare);

        lvVehicule = findViewById(R.id.lvVehicule);

        lvVehicule.setOnItemClickListener((adapterView, view, position, l) -> {
            positionItem = position;
            Vehicul vehicul = vehicule.get(position);
            Intent intent = new Intent(getApplicationContext(), AdaugareVehicul.class);
            intent.putExtra("edit", vehicul);
            launcher.launch(intent);
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getData().hasExtra("vehiculAdaugat")) {
                Intent intent = result.getData();
                Vehicul vehicul = (Vehicul) intent.getSerializableExtra("vehiculAdaugat");
                if (vehicul != null) {
                    vehicule.add(vehicul);
                    AdapterVehicul adapter = new AdapterVehicul(getApplicationContext(), R.layout.view_vehicule, vehicule, getLayoutInflater());
                    lvVehicule.setAdapter(adapter);
                }
            } else if (result.getData().hasExtra("edit")) {
                Intent intent = result.getData();
                Vehicul vehicul = (Vehicul) intent.getSerializableExtra("edit");
                if(vehicul != null) {
                    Vehicul vehiculActualizat = vehicule.get(positionItem);
                    vehiculActualizat.setDenumire(vehicul.getDenumire());
                    vehiculActualizat.setNrLocuri(vehicul.getNrLocuri());
                    vehiculActualizat.setData(vehicul.getData());
                    vehiculActualizat.setCuloare(vehicul.getCuloare());
                    vehiculActualizat.setTip(vehicul.getTip());
                    AdapterVehicul adapter = (AdapterVehicul) lvVehicule.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        fabAdaugare.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdaugareVehicul.class);
            launcher.launch(intent);
        });


    }
}