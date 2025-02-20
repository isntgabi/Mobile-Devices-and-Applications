package com.example.recapitulare3;

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

public class AdaugareStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adaugare_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etNume = findViewById(R.id.etNume);
        EditText etMedie = findViewById(R.id.etMedie);
        Spinner spnSpecializare = findViewById(R.id.spnSpecializare);
        String[] specializari = {"Statistica", "Informatica", "Cibernetica"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, specializari);
        spnSpecializare.setAdapter(spnAdapter);
        Button btnSalvare = findViewById(R.id.btnSalveaza);
        btnSalvare.setOnClickListener(view -> {
            String nume = etNume.getText().toString();
            float medie = Float.parseFloat(String.valueOf(etMedie.getText()));
            String specializare = spnSpecializare.getSelectedItem().toString();

            Student student = new Student(nume,medie,specializare);
            Intent intent = getIntent();
            intent.putExtra("studentFromIntent", student);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}