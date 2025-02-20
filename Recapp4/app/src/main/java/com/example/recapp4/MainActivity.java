package com.example.recapp4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

    private ListView lvBurse;
    private List<Bursa> burse = new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher;

    private ArrayAdapter<Bursa> adapter;
    private int selectedItem;

    private static final String url = "https://www.jsonkeeper.com/b/RQGG";

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

        lvBurse = findViewById(R.id.lvBurse);

        adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, burse);
        lvBurse.setAdapter(adapter);

        lvBurse.setOnItemClickListener((adapterView, view, position, l) -> {
            selectedItem = position;
            Bursa bursa = burse.get(position);
            Intent intent = new Intent(getApplicationContext(), AdaugaBursa.class);
            intent.putExtra("edit",bursa);
            launcher.launch(intent);
        });

        BurseDB instance = BurseDB.getInstance(getApplicationContext());

        burse.clear();
        burse.addAll(instance.getBursaDAO().getBurse());
        adapter.notifyDataSetChanged();

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           if(result.getData().hasExtra("add")) {
               Intent intent = result.getData();
               Bursa bursa = (Bursa) intent.getSerializableExtra("add");
               if(bursa!=null) {
                   //burse.add(bursa);
                   instance.getBursaDAO().insertBursa(bursa);
                   burse.clear();
                   burse.addAll(instance.getBursaDAO().getBurse());
                   adapter.notifyDataSetChanged();
               }
           } else if (result.getData().hasExtra("edit")) {
               Intent intent = result.getData();
               Bursa bursa = (Bursa) intent.getSerializableExtra("edit");
               if(bursa!=null) {
                   Bursa bursaAct = burse.get(selectedItem);
                   bursaAct.setDenumire(bursa.getDenumire());
                   bursaAct.setSuma(bursa.getSuma());
                   bursaAct.setTip(bursa.getTip());
                   instance.getBursaDAO().updateBursa(bursaAct);
                   burse.clear();
                   burse.addAll(instance.getBursaDAO().getBurse());
                   adapter.notifyDataSetChanged();
               }
           } else if (result.getData().hasExtra("delete")) {
               burse.clear();
               burse.addAll(instance.getBursaDAO().getBurse());
               adapter.notifyDataSetChanged();
           }
        });

        Button btnCalculare = findViewById(R.id.btnCalculare);

        btnCalculare.setOnClickListener(view -> {
            List<Bursa> lista = instance.getBursaDAO().getBurseDupaTip("Performanta");
            int suma=0;
            for(int i=0;i<lista.stream().count();i++) {
                suma += lista.get(i).getSuma();
            }
            Toast.makeText(this, "Bursele de performanta: " + String.valueOf(suma), Toast.LENGTH_SHORT).show();
        });

        Button btnSterg = findViewById(R.id.btnStergere);
        btnSterg.setOnClickListener(view -> {
            List<Bursa> lista = instance.getBursaDAO().getBurseDupaTip("Sociala");
            for(int i=0;i<lista.stream().count();i++) {
                instance.getBursaDAO().deleteBursa(lista.get(i));
            }
            burse.clear();
            burse.addAll(instance.getBursaDAO().getBurse());
            adapter.notifyDataSetChanged();
        });

        Button btnIncarca = findViewById(R.id.btnIncarcare);
        btnIncarca.setOnClickListener(view -> {
            Retea();
        });
    }

    public void Retea() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(url);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(() -> {
                   preluareBurse(json);
                });
            }
        };
        thread.start();
    }

    private void preluareBurse(String json) {
//        burse.addAll(BursaParser.parsareJson(json));
//        ArrayAdapter<Bursa> adapter1 = (ArrayAdapter<Bursa>) lvBurse.getAdapter();
//        adapter1.notifyDataSetChanged();
        List<Bursa> bursieri = new ArrayList<>();
        bursieri.addAll(BursaParser.parsareJson(json));
        for(int i=0;i< bursieri.size();i++) {
            BurseDB.getInstance(getApplicationContext()).getBursaDAO().insertBursa(bursieri.get(i));
        }
        burse.clear();
        burse.addAll(BurseDB.getInstance(getApplicationContext()).getBursaDAO().getBurse());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.Optiune) {
            Intent intent = new Intent(getApplicationContext(), AdaugaBursa.class);
            launcher.launch(intent);
            //startActivity(intent);
            return true;
        }
        return false;
    }
}