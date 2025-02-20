package com.example.recap11;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdaugaMedicament extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_medicament);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etDenumire = findViewById(R.id.etDenumire);
        EditText etData = findViewById(R.id.etData);
        Spinner spnSubstanta = findViewById(R.id.spnSubstanta);

        String[] substante = {"Paracetamol", "Ibuprofen"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, substante);
        spnSubstanta.setAdapter(spnAdapter);

        Button btnSalvare = findViewById(R.id.btnSalvare);
        btnSalvare.setOnClickListener(view -> {
            String denumire = etDenumire.getText().toString();
            Date data = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            try {
                data = sdf.parse(etData.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String substanta = spnSubstanta.getSelectedItem().toString();

            Medicament medicament = new Medicament(denumire,data,substanta);
            Intent intent = getIntent();
            intent.putExtra("medicamentFromIntent", medicament);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}