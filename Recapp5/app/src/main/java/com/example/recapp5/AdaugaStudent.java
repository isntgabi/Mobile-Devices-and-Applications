package com.example.recapp5;

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

public class AdaugaStudent extends AppCompatActivity {

    private boolean isEditing=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etNume = findViewById(R.id.etNume);
        EditText etMedie = findViewById(R.id.etMedie);
        Spinner spnSpecializare = findViewById(R.id.spnSpecializare);

        String[] spec = {"Informatica","Statistica","Cibernetica"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spec);
        spnSpecializare.setAdapter(spnAdapter);

        Button btnStergere = findViewById(R.id.btnStergere);

        Intent editIntent = getIntent();
        if(editIntent.hasExtra("edit")) {
            isEditing=true;
            Student student = (Student) editIntent.getSerializableExtra("edit");

            etNume.setText(student.getNume().toString());
            etMedie.setText(String.valueOf(student.getMedie()));
            for(int i=0;i<spnAdapter.getCount();i++) {
                if(student.getSpecializare().equals(spnAdapter.getItem(i))) {
                    spnSpecializare.setSelection(i);
                }
            }

            btnStergere.setVisibility(View.VISIBLE);

            btnStergere.setOnClickListener(view -> {
                StudentDB.getInstance(getApplicationContext()).getStudentDAO().deleteStudent(student);
                Intent sters = new Intent(getApplicationContext(), MainActivity.class);
                sters.putExtra("stergere", student);
                setResult(RESULT_OK, sters);
                finish();
            });
        }

        Button btnSalvare = findViewById(R.id.btnSalvare);
        btnSalvare.setOnClickListener(view -> {
            String nume = etNume.getText().toString();
            float medie = Float.parseFloat(etMedie.getText().toString());
            String specializare = spnSpecializare.getSelectedItem().toString();

            Student student = new Student(nume,medie,specializare);

            Intent intent = getIntent();
            if(isEditing) {
                intent.putExtra("edit", student);
                isEditing=false;
            } else {
                intent.putExtra("add",student);
            }
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}