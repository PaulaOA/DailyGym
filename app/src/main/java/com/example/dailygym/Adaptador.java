package com.example.dailygym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private final List<Rutinas> datosRutina;
    private final Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreRutina;
        public TextView descripcionRutina;
        public ImageView imagenRutina;

        public ViewHolder(View itemView) {
            super(itemView);
            nombreRutina = itemView.findViewById(R.id.nombreRutina);
            descripcionRutina = itemView.findViewById(R.id.descripcionRutina);
            imagenRutina = itemView.findViewById(R.id.imagenRutina);
        }
    }

    public Adaptador(Context context, List<Rutinas> datosRutina) {
        this.context = context;
        this.datosRutina = datosRutina;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.vista_rutina, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rutinas rutina = datosRutina.get(position);

        holder.nombreRutina.setText(rutina.getNombreRutina());
        holder.descripcionRutina.setText(rutina.getDescripcionRutina());

        switch (rutina.getAutorRutina()) {
            case "Mujer":
                holder.imagenRutina.setImageResource(R.drawable.gym_mujer);
                break;
            case "Hombre":
                holder.imagenRutina.setImageResource(R.drawable.gym_hombre);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return datosRutina.size();
    }
}
