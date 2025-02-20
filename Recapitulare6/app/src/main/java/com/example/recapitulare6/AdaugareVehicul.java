package com.example.recapitulare6;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdaugareVehicul extends AppCompatActivity {

    private boolean isEditing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adaugare_vehicul);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etDenumire = findViewById(R.id.etDenumire);
        EditText etNrLocuri = findViewById(R.id.etNrLocuri);
        EditText etData = findViewById(R.id.etData);
        Spinner spnCuloare = findViewById(R.id.spnCuloare);
        RadioGroup rgTip = findViewById(R.id.rgTip);

        String[] culori = {"ALB","NEGRU","ROSU"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, culori);
        spnCuloare.setAdapter(spnAdapter);

        Button btnSalvare = findViewById(R.id.btnSalveaza);

        Intent editIntent = getIntent();
        if(editIntent.hasExtra("edit")) {
          isEditing = true;
          Vehicul vehiculLuat = (Vehicul) editIntent.getSerializableExtra("edit");
          etDenumire.setText(vehiculLuat.getDenumire());
          etNrLocuri.setText(String.valueOf(vehiculLuat.getNrLocuri()));
          etData.setText(new SimpleDateFormat("dd.MM.yyyy").format(vehiculLuat.getData()));
          for(int i=0;i<spnCuloare.getCount();i++) {
              if(vehiculLuat.getCuloare().equals(spnAdapter.getItem(i))) {
                  spnCuloare.setSelection(i);
              }
          }
          switch (vehiculLuat.getTip()) {
              case "Electrica" : {
                  rgTip.check(R.id.rbElectrica);
                  break;
              }
              case "Motor": {
                  rgTip.check(R.id.rbMotor);
                  break;
              }
          }

        }

        btnSalvare.setOnClickListener(view -> {

            String denumire = etDenumire.getText().toString();
            int nr = Integer.parseInt(String.valueOf(etNrLocuri.getText()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date data = null;
            try {
                data = sdf.parse(etData.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String culoare = spnCuloare.getSelectedItem().toString();
            RadioButton rbSelectat = findViewById(rgTip.getCheckedRadioButtonId());
            String tip = rbSelectat.getText().toString();

            Vehicul vehicul = new Vehicul(denumire,nr,data,tip,culoare);

            Intent intent = getIntent();
            if(isEditing) {
                intent.putExtra("edit", vehicul);
            } else {
                intent.putExtra("vehiculAdaugat", vehicul);
            }
            setResult(RESULT_OK, intent);
            finish();

        });
    }
}