package com.example.recapitulare2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvHuse;
    private List<HusaTelefon> huse = new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher;

    private int selected;

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

        lvHuse = findViewById(R.id.lvHuse);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           Intent intent = result.getData();
           HusaTelefon husa = (HusaTelefon) intent.getSerializableExtra("husaFromIntent");
           if(husa!=null) {
               huse.add(husa);
               //ArrayAdapter<HusaTelefon> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,huse);
               HusaAdapter adapter = new HusaAdapter(getApplicationContext(),R.layout.view_huse,huse,getLayoutInflater());
               lvHuse.setAdapter(adapter);
           }
        });

        lvHuse.setOnItemClickListener((adapterView, view, position, l) -> {
            selected = position;
            HusaTelefon husaTelefon = huse.get(selected);
            Toast.makeText(this, husaTelefon.toString(), Toast.LENGTH_SHORT).show();
        });

        Button btnAdauga = findViewById(R.id.btnAdaugare);
        btnAdauga.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdaugaHusa.class);
            launcher.launch(intent);
        });
    }
}