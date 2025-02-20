package com.example.recapitulare9;

import android.content.Intent;
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

    private ListView lvCompanii;
    private List<Companie> companii = new ArrayList<>();
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

        lvCompanii = findViewById(R.id.lvCompanii);

        CompanieDB dbInstance = CompanieDB.getInstance(getApplicationContext());

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           Intent intent = result.getData();
           Companie companie = (Companie) intent.getSerializableExtra("companieFromIntent");
           if(companie!=null) {
               //companii.add(companie);
               dbInstance.getCompanieDAO().insertCompanie(companie);
               companii = dbInstance.getCompanieDAO().getCompanii();
               ArrayAdapter<Companie> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, companii);
               lvCompanii.setAdapter(adapter);
           }
        });

        FloatingActionButton fabAdauga = findViewById(R.id.fabAdauga);

        fabAdauga.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdaugaCompanie.class);
            launcher.launch(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.Optiune1) {
            Intent intent = new Intent(getApplicationContext(), AdaugaCompanie.class);
            launcher.launch(intent);
            return true;
        }

        return false;
    }
}