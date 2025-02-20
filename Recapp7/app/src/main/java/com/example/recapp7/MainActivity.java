package com.example.recapp7;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    private ListView lvMasini;
    private List<Masina> masini = new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher;
    private ArrayAdapter<Masina> adapter;
    private int selectedItem;

    private static final String url = "https://www.jsonkeeper.com/b/GGQR";
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

        lvMasini = findViewById(R.id.lvMasini);

        lvMasini.setOnItemClickListener((adapterView, view, position, l) -> {
            selectedItem = position;
            Masina masina = masini.get(position);
            Intent intent = new Intent(getApplicationContext(), AdaugaMasina.class);
            intent.putExtra("edit", masina);
            launcher.launch(intent);
        });

        adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, masini);
        lvMasini.setAdapter(adapter);

        MasinaDB instance = MasinaDB.getInstance(getApplicationContext());

        masini.clear();
        masini.addAll(instance.getMasinaDAO().getMasini());
        adapter.notifyDataSetChanged();

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           if(result.getData().hasExtra("add")) {
               Intent intent = result.getData();
               Masina masina = (Masina) intent.getSerializableExtra("add");
               if(masina!=null) {
                   //masini.add(masina);
                   instance.getMasinaDAO().insertMasina(masina);
                   masini.clear();
                   masini.addAll(instance.getMasinaDAO().getMasini());
                   adapter.notifyDataSetChanged();
               }
           } else if (result.getData().hasExtra("edit")) {
               Intent intent = result.getData();
               Masina masina = (Masina) intent.getSerializableExtra("edit");
               if(masina!=null) {
                   Masina masinaAct = masini.get(selectedItem);
                   masinaAct.setMarca(masina.getMarca());
                   masinaAct.setPret(masina.getPret());
                   masinaAct.setTip(masina.getTip());
                   instance.getMasinaDAO().updateMasina(masinaAct);
                   masini.clear();
                   masini.addAll(instance.getMasinaDAO().getMasini());
                   adapter.notifyDataSetChanged();
               }
           } else if (result.getData().hasExtra("delete")) {
               masini.clear();
               masini.addAll(instance.getMasinaDAO().getMasini());
               adapter.notifyDataSetChanged();
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
                   incarcareMasini(json);
                });
            }
        };
        thread.start();
    }

    private void incarcareMasini(String json) {
        List<Masina> masiniIncarcate = new ArrayList<>();
        masiniIncarcate.addAll(ParserMasina.parsareJson(json));
        for(int i=0;i<masiniIncarcate.size();i++) {
            MasinaDB.getInstance(getApplicationContext()).getMasinaDAO().insertMasina(masiniIncarcate.get(i));
        }
        masini.clear();
        masini.addAll(MasinaDB.getInstance(getApplicationContext()).getMasinaDAO().getMasini());
        ArrayAdapter<Masina> adapter1 = (ArrayAdapter<Masina>) lvMasini.getAdapter();
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
            Intent intent = new Intent(getApplicationContext(), AdaugaMasina.class);
            launcher.launch(intent);
            return true;
        }
        return false;
    }
}