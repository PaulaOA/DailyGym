package com.example.dailygym;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AllRegistrosAdapter extends RecyclerView.Adapter<AllRegistrosAdapter.AllRegistrosViewHolder> {
    private final List<Registro> registroList;

    public AllRegistrosAdapter(List<Registro> registroList){
        this.registroList = registroList;
    }

    @NonNull
    @Override
    public AllRegistrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.registros_por_ejercicio, parent, false);
        return new AllRegistrosViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AllRegistrosViewHolder holder, int position) {
        Registro registro = registroList.get(position);
        holder.peso.setText("Peso: " + registro.getPeso() + " kg");
        holder.repeticiones.setText("Reps: " + registro.getRepeticiones());
        holder.series.setText("Series: " + registro.getSeries());
    }

    @Override
    public int getItemCount() {
        return registroList.size();
    }

    public static class AllRegistrosViewHolder extends RecyclerView.ViewHolder {
        TextView peso, repeticiones, series;

        public AllRegistrosViewHolder(@NonNull View itemView) {
            super(itemView);
            peso = itemView.findViewById(R.id.textViewPesoRegistro);
            repeticiones = itemView.findViewById(R.id.textViewRepeticionesRegistro);
            series = itemView.findViewById(R.id.textViewSeriesRegistro);
        }
    }
}