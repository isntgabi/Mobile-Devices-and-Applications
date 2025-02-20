package com.example.recapitulare7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdaugaFacultate extends AppCompatActivity {

    private boolean isEditing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_facultate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etDenumire = findViewById(R.id.etDenumire);
        EditText etMedieIntrare = findViewById(R.id.etMedieIntrare);
        EditText etDataExaminarii = findViewById(R.id.etDataExaminarii);
        Spinner spnTip = findViewById(R.id.spnTip);
        RadioGroup rgExamen = findViewById(R.id.rgExamen);

        String[] tipuri = { "Economica" , "Stiintifica", "Umana", "Reala"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tipuri);
        spnTip.setAdapter(spnAdapter);

        Intent editIntent = getIntent();
        if(editIntent.hasExtra("edit")) {
            isEditing = true;
            Facultate facultate = (Facultate) editIntent.getSerializableExtra("edit");

            etDenumire.setText(facultate.getDenumire().toString());
            etMedieIntrare.setText(String.valueOf(facultate.getMedieIntrare()));
            etDataExaminarii.setText(new SimpleDateFormat("dd.MM.yyyy").format(facultate.getDataExaminarii()));
            for(int i=0;i<spnTip.getCount();i++) {
                if(facultate.getTip().equals(spnAdapter.getItem(i))) {
                    spnTip.setSelection(i);
                }
            }

            switch (facultate.getExamen()) {
                case "Online" : {
                    rgExamen.check(R.id.rbOnline);
                    break;
                }
                case "Fizic" : {
                    rgExamen.check(R.id.rbFizic);
                    break;
                }
            }

        }

        Button btnSalveaza = findViewById(R.id.btnSalveaza);
        btnSalveaza.setOnClickListener(view -> {
            String denumire = etDenumire.getText().toString();
            float medie = Float.parseFloat(String.valueOf(etMedieIntrare.getText()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date data = null;
            try {
                data = sdf.parse(etDataExaminarii.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String tip = spnTip.getSelectedItem().toString();
            RadioButton rbSelectat = findViewById(rgExamen.getCheckedRadioButtonId());
            String examen = rbSelectat.getText().toString();

            Facultate facultate = new Facultate(denumire, medie, data, tip, examen);

            Intent intent = getIntent();
            if(isEditing) {
                intent.putExtra("edit", facultate);
                isEditing = false;
            } else {
                intent.putExtra("adaugaFacultate", facultate);
            }
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}