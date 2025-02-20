package com.example.recap17;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaTara extends AppCompatActivity {

    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_tara);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etNume = findViewById(R.id.etNume);
        EditText etPopulatie = findViewById(R.id.etPopulatie);
        Spinner spnContinent = findViewById(R.id.spnContinent);

        String[] continente = {"Europa", "America", "Asia"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, continente);
        spnContinent.setAdapter(spnAdapter);

        Intent edit = getIntent();
        if(edit.hasExtra("edit")) {
            isEditing = true;
            Tara tara = (Tara) edit.getSerializableExtra("edit");
            etNume.setText(tara.getNume());
            etPopulatie.setText(String.valueOf(tara.getPopulatie()));
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) spnContinent.getAdapter();
            for(int i=0;i< adapter.getCount();i++) {
                if (tara.getContinent().toString().equals(spnAdapter.getItem(i))) {
                    spnContinent.setSelection(i);
                }
            }
        }

        Button btnSalvare = findViewById(R.id.btnSalvare);
        btnSalvare.setOnClickListener(view -> {
            String nume = etNume.getText().toString();
            double populatie = Double.parseDouble(etPopulatie.getText().toString());
            String continent = spnContinent.getSelectedItem().toString();

            Tara tara = new Tara(nume,populatie,continent);
            Intent intent = getIntent();
            if(isEditing) {
                intent.putExtra("edit", tara);
                isEditing=false;
            } else {
                intent.putExtra("taraFromIntent", tara);
            }
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}