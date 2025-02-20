package com.example.recapitulare3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int layoutId;
    private List<Student> studenti;
    private LayoutInflater layoutInflater;

    public StudentAdapter(@NonNull Context context, int layoutId, List<Student> studenti, LayoutInflater layoutInflater) {
        super(context, layoutId, studenti);
        this.context = context;
        this.layoutId = layoutId;
        this.studenti = studenti;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(layoutId,parent,false);
        Student student = studenti.get(position);
        TextView tvNume = view.findViewById(R.id.tvNume);
        TextView tvMedie = view.findViewById(R.id.tvMedie);
        TextView tvSpecializare = view.findViewById(R.id.tvSpecializare);
        tvNume.setText(student.getNume());
        tvMedie.setText(String.valueOf(student.getMedie()));
        tvSpecializare.setText(student.getSpecializare());
        
        return view;
    }
}
