package com.example.recapitulare4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdaugareMelodie extends AppCompatActivity {

    private boolean isEditing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adaugare_melodie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etNume = findViewById(R.id.etNume);
        EditText etDurata = findViewById(R.id.etDurata);
        EditText etData = findViewById(R.id.etData);
        Spinner spnGen = findViewById(R.id.spnGen);
        RadioGroup rgFeedback = findViewById(R.id.rgFeedback);

        String[] genuri = {"Rock", "Pop", "Manele"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,genuri);
        spnGen.setAdapter(spnAdapter);

        Button btnSalvare = findViewById(R.id.btnSalvare);

        Intent editIntent = getIntent();
        if(editIntent.hasExtra("edit")) {
            isEditing = true;
            Melodie melodie = (Melodie) editIntent.getSerializableExtra("edit");

            etNume.setText(melodie.getNume().toString());
            etDurata.setText(String.valueOf(melodie.getDurata()));
            etData.setText(new SimpleDateFormat("dd.MM.yyyy").format(melodie.getPublicare()));
            for(int i=0;i<spnGen.getCount();i++) {
                if(melodie.getNume().equals(spnAdapter.getItem(i))) {
                    spnGen.setSelection(i);
                }
            }
            switch (melodie.getFeedback()) {
                case "Like": {
                    rgFeedback.check(R.id.rbLike);
                    break;
                }
                case "Dislike":
                {
                    rgFeedback.check(R.id.rbDislike);
                    break;
                }
            }

        }

        btnSalvare.setOnClickListener(view -> {
            String nume = etNume.getText().toString();
            float durata = Float.parseFloat(String.valueOf(etDurata.getText()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date data = null;
            try {
                data = sdf.parse(etData.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            RadioButton rbSelectat = findViewById(rgFeedback.getCheckedRadioButtonId());
            String feedback = rbSelectat.getText().toString();
            String gen = spnGen.getSelectedItem().toString();

            Melodie melodie = new Melodie(nume,durata,data,gen,feedback);
            Intent intent = getIntent();
            if(isEditing) {
                intent.putExtra("edit", melodie);
                isEditing = false;
            } else {
                intent.putExtra("adaugareMelodie", melodie);
            }
            setResult(RESULT_OK, intent);
            finish();
        });

        SharedPreferences sharedPreferences = getSharedPreferences("local", MODE_PRIVATE);
        String token = sharedPreferences.getString("token","default");
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
    }
}