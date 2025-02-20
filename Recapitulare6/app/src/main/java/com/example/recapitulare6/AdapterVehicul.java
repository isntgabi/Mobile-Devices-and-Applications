package com.example.recapitulare6;

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

public class AdapterVehicul extends ArrayAdapter<Vehicul> {
    private Context context;
    private int layoutId;
    private List<Vehicul> vehicule;

    private LayoutInflater layoutInflater;

    public AdapterVehicul(@NonNull Context context, int layoutId, List<Vehicul> vehicule, LayoutInflater layoutInflater) {
        super(context, layoutId, vehicule);
        this.context = context;
        this.layoutId = layoutId;
        this.vehicule = vehicule;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(layoutId, parent, false);
        Vehicul vehicul = vehicule.get(position);

        TextView tvDenumire = view.findViewById(R.id.tvDenumire);
        TextView tvNrLocuri = view.findViewById(R.id.tvNrLocuri);
        TextView tvData = view.findViewById(R.id.tvData);
        TextView tvCuloare = view.findViewById(R.id.tvCuloare);
        TextView tvTip = view.findViewById(R.id.tvTip);

        tvDenumire.setText(vehicul.getDenumire());
        tvNrLocuri.setText(String.valueOf(vehicul.getNrLocuri()));
        tvData.setText(new SimpleDateFormat("dd.MM.yyyy").format(vehicul.getData()).toString());
        tvCuloare.setText(vehicul.getCuloare());
        tvTip.setText(vehicul.getTip());

        return view;
    }
}
