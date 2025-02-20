package com.example.recapp6;

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

public class AdaugaBon extends AppCompatActivity {

    private boolean isEditing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_bon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etCod = findViewById(R.id.etCod);
        EditText etSuma = findViewById(R.id.etSuma);
        Spinner spnPlata = findViewById(R.id.spnPlata);

        String[] plati = {"Cash","Card","Online"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,plati);
        spnPlata.setAdapter(spnAdapter);

        Intent editIntent = getIntent();

        Button btnStergere = findViewById(R.id.btnStergere);
        if(editIntent.hasExtra("edit")) {
            isEditing=true;
            Bon bon = (Bon) editIntent.getSerializableExtra("edit");

            etCod.setText(bon.getCod().toString());
            etSuma.setText(String.valueOf(bon.getSuma()));
            for(int i=0;i< spnAdapter.getCount();i++) {
                if(bon.getPlata().equals(spnAdapter.getItem(i))) {
                    spnPlata.setSelection(i);
                }
            }

            btnStergere.setVisibility(View.VISIBLE);

            btnStergere.setOnClickListener(view -> {
                BonDB.getInstance(getApplicationContext()).getBonDAO().deleteBon(bon);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("sters", bon);
                setResult(RESULT_OK, intent);
                finish();
            });
        }

        Button btnSalvare = findViewById(R.id.btnSalvare);
        btnSalvare.setOnClickListener(view -> {
            String cod = etCod.getText().toString();
            float suma = Float.parseFloat(etSuma.getText().toString());
            String plata = spnPlata.getSelectedItem().toString();

            Bon bon = new Bon(cod,suma,plata);

            Intent intent = getIntent();
            if(isEditing) {
                intent.putExtra("edit", bon);
                isEditing=false;
            } else {
                intent.putExtra("add",bon);
            }
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}