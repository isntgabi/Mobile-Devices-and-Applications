package com.example.recapitulare9;

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

public class AdaugaCompanie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_companie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText etNume = findViewById(R.id.etNume);
        EditText etData = findViewById(R.id.etData);
        EditText etCifra = findViewById(R.id.etCifra);
        Spinner spnTip = findViewById(R.id.spnTip);

        String[] tipuri = {"S.R.L", "S.A.", "P.F.A."};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tipuri);
        spnTip.setAdapter(spnAdapter);

        Button btnSalveaza = findViewById(R.id.btnSalveaza);
        btnSalveaza.setOnClickListener(view -> {
            String nume = etNume.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date data = null;
            try {
                data = sdf.parse(etData.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            double cifra = Double.parseDouble(etCifra.getText().toString());
            String tip = spnTip.getSelectedItem().toString();

            Companie companie = new Companie(nume,tip,data,cifra);

            Intent intent = getIntent();
            intent.putExtra("companieFromIntent", companie);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}