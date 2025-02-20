package com.example.recapitulare7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterFacultate extends ArrayAdapter<Facultate> {
    private Context context;
    private int layoutId;
    private List<Facultate> facultati;
    private LayoutInflater layoutInflater;

    public AdapterFacultate(@NonNull Context context, int layoutId, List<Facultate> facultati, LayoutInflater layoutInflater) {
        super(context, layoutId, facultati);
        this.context = context;
        this.layoutId = layoutId;
        this.facultati = facultati;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(layoutId, parent, false);
        Facultate facultate = facultati.get(position);

        TextView tvDenumire = view.findViewById(R.id.tvDenumire);
        TextView tvMedieIntrare = view.findViewById(R.id.tvMedieIntrare);
        TextView tvDataExaminarii = view.findViewById(R.id.tvDataExaminarii);
        TextView tvTip = view.findViewById(R.id.tvTip);
        TextView tvExamen = view.findViewById(R.id.tvExamen);

        tvDenumire.setText(facultate.getDenumire());
        tvMedieIntrare.setText(String.valueOf(facultate.getMedieIntrare()));
        tvDataExaminarii.setText(new SimpleDateFormat("dd.MM.yyyy").format(facultate.getDataExaminarii()).toString());
        tvTip.setText(facultate.getTip());
        tvExamen.setText(facultate.getExamen());

        return view;
    }
}
