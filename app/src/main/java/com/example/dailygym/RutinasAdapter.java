package com.example.dailygym;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RutinasAdapter extends RecyclerView.Adapter<RutinasAdapter.RutinasViewHolder> {

    private List<Rutinas> rutinasList;
    private Context context;

    public RutinasAdapter(List<Rutinas> rutinasList, Context context) {
        this.rutinasList = rutinasList;
        this.context = context;
    }

    @NonNull
    @Override
    public RutinasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tus_rutinas, parent, false);
        return new RutinasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RutinasViewHolder holder, int position) {
        Rutinas rutina = rutinasList.get(position);
        holder.textViewNombreRutina.setText(rutina.getNombreRutina());
        holder.textViewDescripcionRutina.setText(rutina.getDescripcionRutina());
    }

    @Override
    public int getItemCount() {
        return rutinasList.size();
    }

    public class RutinasViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNombreRutina;
        TextView textViewDescripcionRutina;

        public RutinasViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombreRutina = itemView.findViewById(R.id.textViewNombreRutinaUsuario);
            textViewDescripcionRutina = itemView.findViewById(R.id.textViewDescripcionRutinaUsuario);
        }
    }
}
