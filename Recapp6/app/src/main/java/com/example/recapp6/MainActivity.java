package com.example.recapp6;

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

    private ListView lvBonuri;
    private List<Bon> bonuri = new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher;
    private ArrayAdapter<Bon> adapter;
    private int selectedItem;

    private static final String url = "https://www.jsonkeeper.com/b/V48C";

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

        lvBonuri = findViewById(R.id.lvBonuri);

        adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,bonuri);
        lvBonuri.setAdapter(adapter);

        lvBonuri.setOnItemClickListener((adapterView,view,position,l) -> {
            selectedItem = position;
            Bon bon = bonuri.get(position);
            Intent intent = new Intent(getApplicationContext(), AdaugaBon.class);
            intent.putExtra("edit",bon);
            launcher.launch(intent);
        });

        BonDB instance = BonDB.getInstance(getApplicationContext());

        bonuri.clear();
        bonuri.addAll(instance.getBonDAO().getBonuri());
        adapter.notifyDataSetChanged();

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getData().hasExtra("add")) {
                Intent intent = result.getData();
                Bon bon = (Bon) intent.getSerializableExtra("add");
                if(bon!=null) {
                    //bonuri.add(bon);
                    instance.getBonDAO().insertBon(bon);
                    bonuri.clear();
                    bonuri.addAll(instance.getBonDAO().getBonuri());
                    adapter.notifyDataSetChanged();
                }
            } else if (result.getData().hasExtra("edit")) {
                Intent intent = result.getData();
                Bon bon = (Bon) intent.getSerializableExtra("edit");
                if(bon!=null) {
                    Bon bonAct = bonuri.get(selectedItem);
                    bonAct.setCod(bon.getCod());
                    bonAct.setPlata(bon.getPlata());
                    bonAct.setSuma(bon.getSuma());
                    instance.getBonDAO().updateBon(bonAct);
                    bonuri.clear();
                    bonuri.addAll(instance.getBonDAO().getBonuri());
                    adapter.notifyDataSetChanged();
                }
            } else if (result.getData().hasExtra("sters")) {
                bonuri.clear();
                bonuri.addAll(instance.getBonDAO().getBonuri());
                adapter.notifyDataSetChanged();
            }
        });

        Button btnCalculare = findViewById(R.id.btnCalculeaza);
        btnCalculare.setOnClickListener(view -> {
            List<Bon> bonuriOnline = new ArrayList<>();
            bonuriOnline.addAll(instance.getBonDAO().getBonuriOnline("Online"));
            float suma=0;
            for(int i=0;i< bonuriOnline.size();i++) {
                suma += bonuriOnline.get(i).getSuma();
            }
            Toast.makeText(this, "Ai platit " + suma + " lei pe cumparaturile ONLINE!", Toast.LENGTH_SHORT).show();
        });

        Button btnStergere = findViewById(R.id.btnSterge);
        btnStergere.setOnClickListener(view -> {
            for(int i=0;i<bonuri.size();i++) {
                if(bonuri.get(i).getPlata().equals("Cash")) {
                    instance.getBonDAO().deleteBon(bonuri.get(i));
                }
            }
            bonuri.clear();
            bonuri.addAll(instance.getBonDAO().getBonuri());
            adapter.notifyDataSetChanged();
        });

        Button btnIncarca = findViewById(R.id.btnIncarca);
        btnIncarca.setOnClickListener(view -> {
            Retea();
        });
    }

    private void Retea() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(url);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(() -> {
                   incarcareBonuri(json);
                });
            }
        };
        thread.start();
    }

    private void incarcareBonuri(String json) {
        List<Bon> bons = new ArrayList<>();
        bons.addAll(BonParser.parsareJson(json));
        for(int i=0;i<bons.size();i++) {
            BonDB.getInstance(getApplicationContext()).getBonDAO().insertBon(bons.get(i));
        }
        bonuri.clear();
        bonuri.addAll(BonDB.getInstance(getApplicationContext()).getBonDAO().getBonuri());
        ArrayAdapter<Bon> adapter1 = (ArrayAdapter<Bon>) lvBonuri.getAdapter();
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
            Intent intent = new Intent(getApplicationContext(),AdaugaBon.class);
            launcher.launch(intent);
            return true;
        }
        return false;
    }
}