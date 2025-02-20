package com.example.recapp4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaBursa extends AppCompatActivity {

    private boolean isEditing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_bursa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etDenumire = findViewById(R.id.etDenumire);
        EditText etSuma = findViewById(R.id.etSuma);
        Spinner spnTip = findViewById(R.id.spnTip);

        String[] tipuri = {"Performanta","Studiu","Sociala"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tipuri);
        spnTip.setAdapter(spnAdapter);

        Button btnStergere = findViewById(R.id.btnDelete);

        Intent editIntent = getIntent();
        if(editIntent.hasExtra("edit")) {
            isEditing = true;

            btnStergere.setVisibility(View.VISIBLE);

            Bursa bursa = (Bursa) editIntent.getSerializableExtra("edit");

            etDenumire.setText(bursa.getDenumire().toString());
            etSuma.setText(String.valueOf(bursa.getSuma()));
            for(int i=0;i<spnAdapter.getCount();i++) {
                if(bursa.getTip().equals(spnAdapter.getItem(i))) {
                    spnTip.setSelection(i);
                }
            }

            btnStergere.setOnClickListener(view -> {
                BurseDB.getInstance(getApplicationContext()).getBursaDAO().deleteBursa(bursa);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("delete", bursa);
                setResult(RESULT_OK, intent);
                finish();
            });
        }

        Button btnSalvare = findViewById(R.id.btnSalvare);
        btnSalvare.setOnClickListener(view -> {
            String denumire = etDenumire.getText().toString();
            int suma = Integer.parseInt(etSuma.getText().toString());
            String tip = spnTip.getSelectedItem().toString();

            Bursa bursa = new Bursa(denumire,suma,tip);
            Intent intent = getIntent();
            if(isEditing) {
                intent.putExtra("edit", bursa);
                isEditing = false;
            } else {
                intent.putExtra("add", bursa);
            }
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}