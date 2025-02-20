package com.example.recap16;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaOras extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_oras);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etDenumire = findViewById(R.id.etDenumire);
        EditText etPopulatie = findViewById(R.id.etPopulatie);

        Button btnSalvare = findViewById(R.id.btnSalvare);
        btnSalvare.setOnClickListener(view -> {
            String denumire = etDenumire.getText().toString();
            double populatie = Double.parseDouble(etPopulatie.getText().toString());

            Oras oras = new Oras(denumire, populatie);
            Intent intent = getIntent();
            intent.putExtra("orasFromIntent", oras);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}