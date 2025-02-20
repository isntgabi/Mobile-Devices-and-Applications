package com.example.recapitulare10;

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

public class AdaugaCarte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_carte);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etTitlu = findViewById(R.id.etTitlu);
        EditText etData = findViewById(R.id.etDataPublicare);
        EditText etPret = findViewById(R.id.etPret);
        Spinner spnTip = findViewById(R.id.spnTip);

        String[] tipuri = {"Drama", "Fictiune", "Actiune"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tipuri);
        spnTip.setAdapter(spnAdapter);

        Button btnSalvare = findViewById(R.id.btnSalvare);

        btnSalvare.setOnClickListener(view -> {
            String titlu = etTitlu.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date data = null;
            try {
                data = sdf.parse(etData.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            double pret = Double.parseDouble(etPret.getText().toString());
            String tip = spnTip.getSelectedItem().toString();

            Carte carte = new Carte(titlu,data,pret,tip);
            Intent intent = getIntent();
            intent.putExtra("carteFromIntent", carte);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}