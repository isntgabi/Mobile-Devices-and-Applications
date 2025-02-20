package com.example.recapitulare5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterEnergizant extends ArrayAdapter<Energizant> {
    private Context context;

    private int layoutId;

    private List<Energizant> energizante;

    private LayoutInflater layoutInflater;

    public AdapterEnergizant(@NonNull Context context, int layoutId, List<Energizant> energizante, LayoutInflater layoutInflater) {
        super(context, layoutId, energizante);
        this.context = context;
        this.layoutId = layoutId;
        this.energizante = energizante;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(layoutId, parent, false);
        Energizant energizant = energizante.get(position);

        TextView tvDenumire = view.findViewById(R.id.tvDenumire);
        TextView tvCantitate = view.findViewById(R.id.tvCantitate);
        TextView tvAroma = view.findViewById(R.id.tvAroma);
        TextView tvIndulcit = view.findViewById(R.id.tvIndulcit);

        tvDenumire.setText(energizant.getDenumire());
        tvCantitate.setText(String.valueOf(energizant.getCantitate()));
        tvAroma.setText(energizant.getAroma());
        tvIndulcit.setText(energizant.getIndulcit());

        return view;
    }
}
