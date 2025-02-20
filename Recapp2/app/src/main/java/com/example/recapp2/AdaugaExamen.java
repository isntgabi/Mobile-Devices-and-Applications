package com.example.recapp2;

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

public class AdaugaExamen extends AppCompatActivity {

    private boolean isEditing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_examen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etDenumire = findViewById(R.id.etDenumire);
        EditText etPunctaj = findViewById(R.id.etPunctaj);
        Spinner spnTip = findViewById(R.id.spnTip);

        String[] tipuri = {"Sesiune", "Restanta"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tipuri);
        spnTip.setAdapter(spnAdapter);


        Intent editIntent = getIntent();
        if(editIntent.hasExtra("edit")) {
            isEditing=true;
            Examen examen = (Examen) editIntent.getSerializableExtra("edit");
            etDenumire.setText(examen.getDenumire());
            etPunctaj.setText(String.valueOf(examen.getPunctaj()));
            for(int i=0;i< spnAdapter.getCount();i++) {
                if (examen.getTip().equals(spnAdapter.getItemId(i))) {
                    spnTip.setSelection(i);
                }
            }
        }

        Button btnSalvare = findViewById(R.id.btnSalvare);
        btnSalvare.setOnClickListener(view -> {
            String denumire = etDenumire.getText().toString();
            int punctaj = Integer.parseInt(etPunctaj.getText().toString());
            String tip = spnTip.getSelectedItem().toString();

            Examen examen = new Examen(denumire,punctaj,tip);
            Intent intent = getIntent();
            if(isEditing) {
                intent.putExtra("edit",examen);
                isEditing=false;
            } else {
                intent.putExtra("add", examen);
            }
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}