package com.example.recapitulare3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvStudenti;
    private List<Student> studenti = new ArrayList<>();

    private ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton fabAdauga = findViewById(R.id.fabAdauga);
        fabAdauga.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdaugareStudent.class);
            launcher.launch(intent);
        });

        lvStudenti = findViewById(R.id.lvStudenti);
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           Intent intent = result.getData();
           Student student = (Student) intent.getSerializableExtra("studentFromIntent");
           if(student != null) {
               studenti.add(student);
               //ArrayAdapter<Student> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, studenti);
               StudentAdapter adapter = new StudentAdapter(getApplicationContext(), R.layout.view_studenti, studenti, getLayoutInflater());
               lvStudenti.setAdapter(adapter);
           }
        });
    }
}