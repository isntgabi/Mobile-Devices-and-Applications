package ro.ase.grupa1095;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdaugaBiletConcertActivity extends AppCompatActivity {
    private Spinner spnCategoriiBilete;
    private Button btnSalvare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_bilet_concert);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spnCategoriiBilete = findViewById(R.id.spnCategorie);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoriiBilete, android.R.layout.simple_spinner_dropdown_item);
        spnCategoriiBilete.setAdapter(adapter);

        btnSalvare = findViewById(R.id.btnSalveaza);

        ArrayList<Bilet> bilete = new ArrayList<>();
        EditText etNume = findViewById(R.id.etNume);
        EditText etNumarBilete = findViewById(R.id.etNrBilete);
        EditText etLocatie = findViewById(R.id.etLocatie);
        EditText etData = findViewById(R.id.etDataConcert);
        RadioGroup rgMetodaPlata = findViewById(R.id.rgMetodaPlata);
        btnSalvare.setOnClickListener(view -> {
            String nume = etNume.getText().toString();
            String locatie = etLocatie.getText().toString();
            int nrBilete = Integer.parseInt(etNumarBilete.getText().toString());
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date dataConcert = null;
            try {
                dataConcert = sdf.parse(etData.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
;            Categorie categorie = Categorie.valueOf(spnCategoriiBilete.getSelectedItem().toString());
            RadioButton metodaPlataSelectata = findViewById(rgMetodaPlata.getCheckedRadioButtonId());
            MetodaPlata metodaPlata = MetodaPlata.valueOf(metodaPlataSelectata.getText().toString());
            Bilet bilet = new Bilet(nume,locatie,nrBilete,dataConcert,metodaPlata,categorie);
            //Toast.makeText(this,bilet.toString(),Toast.LENGTH_SHORT).show();

            Intent intent = getIntent();
            intent.putExtra("biletFromIntent",bilet);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}