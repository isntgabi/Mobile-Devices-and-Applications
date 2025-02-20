package com.example.recapitulare5;

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

public class AdaugaEnergizant extends AppCompatActivity {

    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_energizant);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etDenumire = findViewById(R.id.etDenumire);
        EditText etCantitate = findViewById(R.id.etCantitate);
        Spinner spnAroma = findViewById(R.id.spnAroma);
        RadioGroup rgIndulcit = findViewById(R.id.rgIndulcit);

        String[] arome = {"WATERMELON", "APPLE", "NORMAL"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arome);
        spnAroma.setAdapter(spnAdapter);

        Intent editIntent = getIntent();

        if(editIntent.hasExtra("edit")) {
            isEditing = true;
            Energizant energizantLuat = (Energizant) editIntent.getSerializableExtra("edit");
            etDenumire.setText(energizantLuat.getDenumire());
            etCantitate.setText(String.valueOf(energizantLuat.getCantitate()));
            for(int i=0;i<spnAroma.getCount();i++){
                if(energizantLuat.getAroma().equals(spnAdapter.getItem(i))) {
                    spnAroma.setSelection(i);
                }
            }
            switch (energizantLuat.getIndulcit()) {
                case "Cu zahar": {
                    rgIndulcit.check(R.id.rbZahar);
                    break;
                }
                case "Fara zahar": {
                    rgIndulcit.check(R.id.rbFaraZahar);
                    break;
                }
            }
        }

        Button btnSalvare = findViewById(R.id.btnSalvare);
        btnSalvare.setOnClickListener(view -> {
            String denumire = etDenumire.getText().toString();
            int cantitate = Integer.parseInt(String.valueOf(etCantitate.getText()));
            String aroma = spnAroma.getSelectedItem().toString();
            RadioButton rbSelectat = findViewById(rgIndulcit.getCheckedRadioButtonId());
            String indulcit = rbSelectat.getText().toString();
            Energizant energizant = new Energizant(denumire,cantitate,aroma,indulcit);

            Intent intent = getIntent();
            if(isEditing) {
                intent.putExtra("edit", energizant);
                isEditing=false;
            } else {
                intent.putExtra("adaugareEnergizant", energizant);
            }
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}