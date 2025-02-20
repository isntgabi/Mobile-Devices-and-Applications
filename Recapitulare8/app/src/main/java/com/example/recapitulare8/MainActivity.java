package com.example.recapitulare8;

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

    private ListView lvBilete;
    private List<BiletDeTren> bilete = new ArrayList<>();
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

        lvBilete = findViewById(R.id.lvBilete);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           Intent intent = result.getData();
           BiletDeTren bilet = (BiletDeTren) intent.getSerializableExtra("biletFromIntent");
           if (bilet != null) {
               bilete.add(bilet);
               ArrayAdapter<BiletDeTren> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, bilete);
               lvBilete.setAdapter(adapter);
           }
        });

        FloatingActionButton fabDeschide = findViewById(R.id.fabDeschide);
        fabDeschide.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdaugaBilet.class);
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
            Intent intent = new Intent(getApplicationContext(), AdaugaBilet.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}