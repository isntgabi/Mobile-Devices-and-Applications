package com.example.recapp3;

import android.content.Intent;
import android.os.Bundle;
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

    private ListView lvTrasee;
    private List<Traseu> trasee = new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher;
    private ArrayAdapter<Traseu> adapter;
    private int selectedItem;

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

        lvTrasee = findViewById(R.id.lvTrasee);

        adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,trasee);
        lvTrasee.setAdapter(adapter);

        lvTrasee.setOnItemClickListener((adapterView,view,position,l) -> {
            selectedItem = position;
            Traseu traseu = trasee.get(position);
            Intent intent = new Intent(getApplicationContext(), AdaugaTraseu.class);
            intent.putExtra("edit", traseu);
            launcher.launch(intent);
        });

        TraseuDB instance = TraseuDB.getInstance(getApplicationContext());

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           if(result.getData().hasExtra("add")) {
               Intent intent = result.getData();
               Traseu traseu = (Traseu) intent.getSerializableExtra("add");
               if(traseu!=null) {
                   //trasee.add(traseu);
                   instance.getTraseuDAO().insertTraseu(traseu);
                   trasee.clear();
                   trasee.addAll(instance.getTraseuDAO().getTrasee());
                   adapter.notifyDataSetChanged();
               }
           } else if (result.getData().hasExtra("edit")) {
               Intent intent = result.getData();
               Traseu traseu = (Traseu) intent.getSerializableExtra("edit");
               if(traseu!=null) {
                   Traseu traseuActualizat = trasee.get(selectedItem);
                   traseuActualizat.setDestinatie(traseu.getDestinatie());
                   traseuActualizat.setPornire(traseu.getPornire());
                   traseuActualizat.setVehicul(traseu.getVehicul());
                   instance.getTraseuDAO().updateTraseu(traseuActualizat);
                   trasee.clear();
                   trasee.addAll(instance.getTraseuDAO().getTrasee());
                   adapter.notifyDataSetChanged();
               }
           } else if (result.getData().hasExtra("delete")) {
               Intent intent = result.getData();
               Traseu traseu = (Traseu) intent.getSerializableExtra("delete");
               if (traseu != null) {
                   instance.getTraseuDAO().deleteTraseu(traseu); // Șterge traseul din baza de date
                   trasee.clear();
                   trasee.addAll(instance.getTraseuDAO().getTrasee()); // Reîncarcă lista
                   adapter.notifyDataSetChanged(); // Notifică lista de modificări
               }
           }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.Optiune) {
            Intent intent = new Intent(getApplicationContext(), AdaugaTraseu.class);
            launcher.launch(intent);
            return true;
        }
        return false;
    }
}