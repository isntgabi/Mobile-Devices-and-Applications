package com.example.recapp1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaOchelari extends AppCompatActivity {

    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_ochelari);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etFirma = findViewById(R.id.etFirma);
        EditText etDioptrie = findViewById(R.id.etDioptrie);
        Spinner spnTip = findViewById(R.id.spnTip);

        String[] tipuri = {"Miopie", "Hipermetropie"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tipuri);
        spnTip.setAdapter(adapter);

        Intent intentEdit = getIntent();
        if(intentEdit.hasExtra("edit")) {
            isEditing=true;
            Ochelari ochelari = (Ochelari) intentEdit.getSerializableExtra("edit");
            etFirma.setText(ochelari.getFirma());
            etDioptrie.setText(String.valueOf(ochelari.getDioptrie()));
            for(int i=0;i<adapter.getCount();i++) {
                if(ochelari.getTip().equals(adapter.getItemId(i))) {
                    spnTip.setSelection(i);
                }
            }
        }

        Button btnSalvare = findViewById(R.id.btnSalvare);
        btnSalvare.setOnClickListener(view -> {
            String firma = etFirma.getText().toString();
            float dioptrie = Float.parseFloat(etDioptrie.getText().toString());
            String tip = spnTip.getSelectedItem().toString();

            Ochelari ochelari = new Ochelari(firma,dioptrie,tip);
            Intent intent = getIntent();
            if (isEditing) {
                intent.putExtra("edit", ochelari);
                isEditing = false;
            } else {
                intent.putExtra("ochelariFromIntent", ochelari);
            }
            setResult(RESULT_OK, intent);
            finish();
        });

        SharedPreferences shp = getSharedPreferences("local", MODE_PRIVATE);
        String mesaj = shp.getString("token", "default");
        Toast.makeText(this, mesaj, Toast.LENGTH_SHORT).show();
    }
}