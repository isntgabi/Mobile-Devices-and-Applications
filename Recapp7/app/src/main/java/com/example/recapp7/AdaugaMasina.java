package com.example.recapp7;

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

public class AdaugaMasina extends AppCompatActivity {

    boolean isEditing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_masina);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText etMarca = findViewById(R.id.etMarca);
        EditText etPret = findViewById(R.id.etPret);
        Spinner spnTip = findViewById(R.id.spnTip);

        String[] tipuri = {"Mare","Mica","Medie"};

        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,tipuri);
        spnTip.setAdapter(spnAdapter);

        Button btnSterge = findViewById(R.id.btnSterge);
        Intent editIntent = getIntent();
        if(editIntent.hasExtra("edit")) {
            isEditing = true;
            Masina masina = (Masina) editIntent.getSerializableExtra("edit");

            etMarca.setText(masina.getMarca().toString());
            etPret.setText(String.valueOf(masina.getPret()));
            for(int i=0;i< spnAdapter.getCount();i++) {
                if(masina.getTip().equals(spnAdapter.getItem(i))) {
                    spnTip.setSelection(i);
                }
            }

            btnSterge.setVisibility(View.VISIBLE);
            btnSterge.setOnClickListener(view -> {
                MasinaDB.getInstance(getApplicationContext()).getMasinaDAO().deleteMasina(masina);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("delete", masina);
                setResult(RESULT_OK, intent);
                finish();
            });
        }

        Button btnSalvare = findViewById(R.id.btnSalvare);
        btnSalvare.setOnClickListener(view -> {
            String marca = etMarca.getText().toString();
            float pret = Float.parseFloat(String.valueOf(etPret.getText()));
            String tip = spnTip.getSelectedItem().toString();

            Masina masina = new Masina(marca,pret,tip);
            Intent intent = getIntent();
            if(isEditing) {
                intent.putExtra("edit", masina);
                isEditing=false;
            } else {
                intent.putExtra("add", masina);
            }
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}