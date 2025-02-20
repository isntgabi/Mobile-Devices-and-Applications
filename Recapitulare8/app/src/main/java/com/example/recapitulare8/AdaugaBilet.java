package com.example.recapitulare8;

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

public class AdaugaBilet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_bilet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etNume = findViewById(R.id.etNume);
        EditText etDestinatie = findViewById(R.id.etDestinatie);
        EditText etData = findViewById(R.id.etData);
        EditText etPret = findViewById(R.id.etPret);

        String[] metode = {"CASH", "CARD"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, metode);
        Spinner spnMetode = findViewById(R.id.spnMetodaPlana);
        spnMetode.setAdapter(spnAdapter);

        Button btnSalveaza = findViewById(R.id.btnSalvare);

        btnSalveaza.setOnClickListener(view -> {
            String nume = etNume.getText().toString();
            String destinatie = etDestinatie.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date data = null;
            try {
                data = sdf.parse(etData.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            double pret = Double.parseDouble(etPret.getText().toString());

            String metoda = spnMetode.getSelectedItem().toString();

            BiletDeTren bilet = new BiletDeTren(nume,destinatie,metoda,data,pret);

            Intent intent = getIntent();
            intent.putExtra("biletFromIntent", bilet);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}