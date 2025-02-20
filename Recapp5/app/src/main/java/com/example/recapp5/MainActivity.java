package com.example.recapp5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvStudenti;
    private List<Student> studenti = new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher;
    private ArrayAdapter<Student> adapter;
    private int selectedItem;

    private static final String url = "https://www.jsonkeeper.com/b/BCXN";

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

        lvStudenti = findViewById(R.id.lvStudenti);
        adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, studenti);
        lvStudenti.setAdapter(adapter);

        lvStudenti.setOnItemClickListener((adapterView, view, position, l) -> {
            selectedItem = position;
            Student student = studenti.get(position);
            Intent intent = new Intent(getApplicationContext(), AdaugaStudent.class);
            intent.putExtra("edit",student);
            launcher.launch(intent);
        });

        StudentDB instance = StudentDB.getInstance(getApplicationContext());

        studenti.clear();
        studenti.addAll(instance.getStudentDAO().getStudenti());
        adapter.notifyDataSetChanged();

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           if(result.getData().hasExtra("add")) {
               Intent intent = result.getData();
               Student stud = (Student) intent.getSerializableExtra("add");
               if(stud!=null) {
                   //studenti.add(stud);
                   instance.getStudentDAO().insertStudent(stud);
                   studenti.clear();
                   studenti.addAll(instance.getStudentDAO().getStudenti());
                   adapter.notifyDataSetChanged();
               }
           } else if (result.getData().hasExtra("edit")) {
               Intent intent = result.getData();
               Student stud = (Student) intent.getSerializableExtra("edit");
               if(stud!=null) {
                   Student studAct = studenti.get(selectedItem);
                   studAct.setMedie(stud.getMedie());
                   studAct.setNume(stud.getNume());
                   studAct.setSpecializare(stud.getSpecializare());
                   instance.getStudentDAO().updateStudent(studAct);
                   studenti.clear();
                   studenti.addAll(instance.getStudentDAO().getStudenti());
                   adapter.notifyDataSetChanged();
               }
           } else if (result.getData().hasExtra("stergere")) {
               studenti.clear();
               studenti.addAll(instance.getStudentDAO().getStudenti());
               adapter.notifyDataSetChanged();
           }
        });

        Button btnMedie = findViewById(R.id.btnMedie);
        btnMedie.setOnClickListener(view -> {
            List<Student> studInfo = new ArrayList<>();
            studInfo.addAll(instance.getStudentDAO().getStudentiINFO("Informatica"));
            int count=0;
            float suma=0;
            for(int i=0;i<studInfo.size();i++) {
                suma+=studInfo.get(i).getMedie();
                count++;
            }
            float medie = suma/count;

            Toast.makeText(this, "Medie pe INFO: " + String.valueOf(medie), Toast.LENGTH_SHORT).show();
        });
        LinearLayout ln = findViewById(R.id.lnStudenti);
        Button btnStudenti = findViewById(R.id.btnStudenti);
        btnStudenti.setOnClickListener(view -> {
            List<Student> studStat = new ArrayList<>();
            studStat.addAll(instance.getStudentDAO().getStudentiINFO("Statistica"));
            for(int i=0;i< studStat.size();i++) {
                TextView tv = new TextView(this);
                tv.setText(studStat.get(i).getNume());
                ln.addView(tv);
            }
        });

        Retea();
    }

    private void Retea() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(url);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(() -> {
                   preluareStudenti(json);
                });
            }
        };
        thread.start();
    }

    private void preluareStudenti(String json) {
//        studenti.addAll(StudentParser.parsareJson(json));
        List<Student> students = new ArrayList<>();
        students.addAll(StudentParser.parsareJson(json));
        for(int i=0;i<students.size();i++) {
            StudentDB.getInstance(getApplicationContext()).getStudentDAO().insertStudent(students.get(i));
        }
        studenti.clear();
        studenti.addAll(StudentDB.getInstance(getApplicationContext()).getStudentDAO().getStudenti());
        ArrayAdapter<Student> adapter1 = (ArrayAdapter<Student>) lvStudenti.getAdapter();
        adapter1.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.Optiune) {
            Intent intent = new Intent(getApplicationContext(), AdaugaStudent.class);
            launcher.launch(intent);
            return true;
        }
        return false;
    }
}