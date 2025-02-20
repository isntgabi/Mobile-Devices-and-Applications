package com.example.recapp3;

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
import androidx.room.util.DBUtil;

public class AdaugaTraseu extends AppCompatActivity {


    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_traseu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etPornire = findViewById(R.id.etPornire);
        EditText etDestinatie = findViewById(R.id.etDestinatie);
        Spinner spnVehicul = findViewById(R.id.spnVehicul);

        String[] vehicule = {"Tren","Microbuz","Autobuz"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, vehicule);
        spnVehicul.setAdapter(spnAdapter);

        Intent editIntent = getIntent();
        if(editIntent.hasExtra("edit")) {
            isEditing=true;
            Traseu traseu = (Traseu) editIntent.getSerializableExtra("edit");
            etPornire.setText(traseu.getPornire());
            etDestinatie.setText(traseu.getDestinatie());
            for(int i=0;i< spnAdapter.getCount();i++) {
                if(traseu.getVehicul().equals(spnAdapter.getItem(i))) {
                    spnVehicul.setSelection(i);
                }
            }
        }

        Button btnSalvare = findViewById(R.id.btnSalvare);
        btnSalvare.setOnClickListener(view -> {
            String pornire = etPornire.getText().toString();
            String destinatie = etDestinatie.getText().toString();
            String vehicul = spnVehicul.getSelectedItem().toString();

            Traseu traseu = new Traseu(pornire,destinatie,vehicul);
            Intent intent = getIntent();
            if(isEditing) {
                intent.putExtra("edit", traseu);
                isEditing=false;
            } else {
                intent.putExtra("add", traseu);
            }
            setResult(RESULT_OK, intent);
            finish();
        });

        Button btnStergere = findViewById(R.id.btnStergere);
        btnStergere.setOnClickListener(view -> {
            Intent intent = getIntent();
            if (isEditing) {
                Traseu traseu = (Traseu) intent.getSerializableExtra("edit");
                if (traseu != null) {
                    intent.putExtra("delete", traseu); // Adaugă traseul pentru ștergere
                    setResult(RESULT_OK, intent); // Setează rezultatul ca OK
                    finish(); // Închide activitatea
                }
            }
        });
    }
}