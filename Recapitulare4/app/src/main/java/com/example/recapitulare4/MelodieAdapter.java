package com.example.recapitulare4;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;

public class MelodieAdapter extends ArrayAdapter<Melodie> {
    private Context context;
    private int layoutId;
    private List<Melodie> melodii;
    private LayoutInflater layoutInflater;

    public MelodieAdapter(@NonNull Context context, int layoutId, List<Melodie> melodii, LayoutInflater layoutInflater) {
        super(context, layoutId, melodii);
        this.context = context;
        this.layoutId = layoutId;
        this.melodii = melodii;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(layoutId, parent, false);
        Melodie melodie = melodii.get(position);

        TextView tvNume = view.findViewById(R.id.tvNume);
        TextView tvDurata = view.findViewById(R.id.tvDurata);
        TextView tvData = view.findViewById(R.id.tvData);
        TextView tvGen = view.findViewById(R.id.tvGen);
        TextView tvFeedback = view.findViewById(R.id.tvFeedback);
        if(melodie.getFeedback().compareTo("Like") == 0) {
            tvFeedback.setTextColor(Color.GREEN);
        } else {
            tvFeedback.setTextColor(Color.RED);
        }
        tvNume.setText(melodie.getNume());
        tvDurata.setText(String.valueOf(melodie.getDurata()));
        tvData.setText(new SimpleDateFormat("dd.MM.yyyy").format(melodie.getPublicare()));
        tvGen.setText(melodie.getGen());
        tvFeedback.setText(melodie.getFeedback());

        return view;
    }
}
