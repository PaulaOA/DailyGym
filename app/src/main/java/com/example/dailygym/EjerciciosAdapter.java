package com.example.dailygym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EjerciciosAdapter extends RecyclerView.Adapter<EjerciciosAdapter.EjercicioViewHolder> {
    private static List<Ejercicios> ejerciciosList;
    private final Context context;
    private final int idRutina;
    private final int idDiaEntreno;

    public EjerciciosAdapter(Context context, List<Ejercicios> ejerciciosList, int idRutina, int idDiaEntreno) {
        EjerciciosAdapter.ejerciciosList = ejerciciosList;
        this.context = context;
        this.idRutina = idRutina;
        this.idDiaEntreno = idDiaEntreno;
    }

    @NonNull
    @Override
    public EjercicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ejercicio, parent, false);
        return new EjercicioViewHolder(view, context, idRutina, idDiaEntreno);
    }

    @Override
    public void onBindViewHolder(@NonNull EjercicioViewHolder holder, int position) {
        Ejercicios ejercicio = ejerciciosList.get(position);
        holder.bind(ejercicio);
    }

    @Override
    public int getItemCount() {
        return ejerciciosList.size();
    }

    public static class EjercicioViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNombre;

        public EjercicioViewHolder(@NonNull View itemView, Context context, int idRutina, int idDiaEntreno) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombreEjercicio);

            itemView.setOnClickListener(v -> {
                int position = getAbsoluteAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Ejercicios ejercicio = ejerciciosList.get(position);

                    Fragment detallesEjercicioFragment = DetallesEjercicioFragment.newInstance(ejercicio, idRutina, idDiaEntreno);

                    ((MainActivity) context).replaceFragment(detallesEjercicioFragment, true);
                }
            });
        }

        public void bind(Ejercicios ejercicio) {
            String nombreConEspacios = "  " + ejercicio.getNombreEjercicio();
            textViewNombre.setText(nombreConEspacios);
        }
    }
    public void setEjerciciosList(List<Ejercicios> ejerciciosList) {
        EjerciciosAdapter.ejerciciosList = ejerciciosList;
    }
}