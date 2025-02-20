package com.example.recapitulare4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

    private ListView lvMelodii;
    private List<Melodie> melodii = new ArrayList<>();
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

        FloatingActionButton fabAdaugare = findViewById(R.id.fabAdaugare);

        fabAdaugare.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdaugareMelodie.class);
            launcher.launch(intent);
        });

        lvMelodii = findViewById(R.id.lvMelodii);

        lvMelodii.setOnItemClickListener((adapterView, view, position, l) -> {
            selectedPosition = position;
            Intent intent = new Intent(getApplicationContext(), AdaugareMelodie.class);
            intent.putExtra("edit", melodii.get(position));
            launcher.launch(intent);
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getData().hasExtra("adaugareMelodie")) {
                Intent intent = result.getData();
                Melodie melodie = (Melodie) intent.getSerializableExtra("adaugareMelodie");
                if (melodie != null) {
                    melodii.add(melodie);
                    //ArrayAdapter<Melodie> lvAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,melodii);
                    MelodieAdapter lvAdapter = new MelodieAdapter(getApplicationContext(), R.layout.view_melodii, melodii, getLayoutInflater());


                    lvMelodii.setAdapter(lvAdapter);
                }
            } else if(result.getData().hasExtra("edit")) {
                Intent intent = result.getData();
                Melodie melodie = (Melodie) intent.getSerializableExtra("edit");
                if(melodie!=null) {
                    Melodie melodieActualizata = melodii.get(selectedPosition);
                    melodieActualizata.setNume(melodie.getNume());
                    melodieActualizata.setDurata(melodie.getDurata());
                    melodieActualizata.setPublicare(melodie.getPublicare());
                    melodieActualizata.setGen(melodie.getGen());
                    melodieActualizata.setFeedback(melodie.getFeedback());
                    MelodieAdapter adapter = (MelodieAdapter) lvMelodii.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("local", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", "BLABLABLA");
        editor.apply();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.Optiune) {
            Intent intent = new Intent(getApplicationContext(), AdaugareMelodie.class);
            launcher.launch(intent);
            return true;
        }
        return false;
    }
}