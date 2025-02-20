package com.example.recapitulare2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HusaAdapter extends ArrayAdapter<HusaTelefon> {
    private Context context;
    private int layoutId;

    private List<HusaTelefon> huse;

    private LayoutInflater layoutInflater;

    public HusaAdapter(@NonNull Context context, int layoutId, List<HusaTelefon> huse, LayoutInflater layoutInflater) {
        super(context, layoutId, huse);
        this.context = context;
        this.layoutId = layoutId;
        this.huse = huse;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(layoutId,parent,false);
        HusaTelefon husaTelefon = huse.get(position);
        TextView tvMaterial = view.findViewById(R.id.tvMaterial);
        TextView tvLungime = view.findViewById(R.id.tvLungime);
        TextView tvCuloare = view.findViewById(R.id.tvCuloare);
        tvMaterial.setText(husaTelefon.getMaterial());
        tvLungime.setText(String.valueOf(husaTelefon.getLungime()));
        tvCuloare.setText(husaTelefon.getCuloare());
        return view;
    }
}
